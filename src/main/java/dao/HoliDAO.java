package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import check.InputCheck;
import check.TypeChange;

import model.*;

public class HoliDAO {
	String sql = "select * from HOLI ";
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 建立專案用來接受sql資料的表格
	Holi holi = null;// 先建立表格裡面每一個element，用之前建立的JavaBean來建立
	List<Holi> holis = new ArrayList<Holi>();// 再建立一個陣列，之後要把所有element裝進表格

	public List<Holi> findAllHoli() throws Exception {
		conn = ConnectionHelper.getConnection();
		stmt = conn.createStatement();
		sql = sql + " order by HOLIDAY desc";
		rs = stmt.executeQuery(sql);

		while (rs.next()) {
			holi = new Holi();
			holi.setHoliday(rs.getDate("HOLIDAY"));
			holi.setHoliReason(rs.getString("HOLI_REASON"));
			holi.setHrs(rs.getInt("HRS"));
			holis.add(holi);// 最後再把各個欄位填進大表格emps中
		}

		conn.close();
		return holis;// 回傳結果表格給findAllDepartment()方法
	}

	// 單筆
	public Holi findHoliByDate(java.sql.Date holiday) throws Exception {

		conn = ConnectionHelper.getConnection();// 連線sql
		sql = sql + " where HOLIDAY = ? ";// 把最前面設定的sql再加查詢條件where

		pstmt = conn.prepareStatement(sql);// 叫sql建立一個單筆查詢的表
		pstmt.setDate(1, holiday);// 把變數組合起來送進pstm因為sql是從1開始所以注意要前面填1，1指?，有幾個?就有多少數字
		rs = pstmt.executeQuery();// executeQuery()代表sql執行指令

		// sql有查詢結果，才執行
		if (rs.next()) {
			holi = new Holi();
			holi.setHoliday(rs.getDate("HOLIDAY"));
			holi.setHoliReason(rs.getString("HOLI_REASON"));
			holi.setHrs(rs.getInt("HRS"));
		}

		conn.close();
		return holi;
	}

	// 修改例假日
	public void updateHolidayByDate(Holi holi) throws Exception {
		conn = ConnectionHelper.getConnection();
		sql = "UPDATE Holi set HOLI_REASON=?, HRS=? where HOLIDAY=? ";
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, holi.getHoliReason());
		pstmt.setInt(2, holi.getHrs());
		pstmt.setDate(3, holi.getHoliday());
		pstmt.executeUpdate();
		conn.commit();
		pstmt.close();
		conn.close();
	}

	// 新增例假日
	public void insertHoliday(Holi holi) throws Exception {
		conn = ConnectionHelper.getConnection();
		sql = "INSERT INTO Holi(HOLIDAY,HOLI_REASON,HRS) "
				+ "VALUES(? ,? ,? ) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setDate(1, holi.getHoliday());
		pstmt.setString(2, holi.getHoliReason());
		pstmt.setInt(3, holi.getHrs());
		pstmt.executeUpdate();
		conn.commit();
		rs.close();
		pstmt.close();
		conn.close();
	}

	// 刪除例假日
	public void deleteHoliByDate(Holi holi) throws Exception {
		conn = ConnectionHelper.getConnection();
		sql = "DELETE FROM HOLI WHERE trunc(HOLIDAY)= trunc(?)";
		pstmt = conn.prepareStatement(sql);
		// System.out.println("holi.getHoliday()="+holi.getHoliday());
		pstmt.setDate(1, holi.getHoliday());
		pstmt.executeUpdate();
		conn.commit();
		rs.close();
		stmt.close();
		conn.close();
	}

	// 新增年度例假日
	public void addYearHoliday(String addYear) throws Exception {
		Connection conn = null;
		conn = ConnectionHelper.getConnection();

		java.sql.Date start = TypeChange.stringToSqlDate(addYear + "-01-01");// 開始時間
		java.sql.Date end = TypeChange.stringToSqlDate(addYear + "-12-31");// 結束時間
		List<java.sql.Date> lists = dateSplit(start, end);

		// -------------------插入周末時間---------------
		if (!lists.isEmpty()) {
			for (java.sql.Date date : lists) {
				Calendar cal = Calendar.getInstance();// 使用預設時區和語言環境獲得一個日曆
				cal.setTime(date);
				if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
						|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					String Sql = "INSERT INTO HOLI (HOLIDAY,HOLI_REASON,HRS) VALUES(?, '周末',8)";
					pstmt = conn.prepareStatement(Sql);
					pstmt.setDate(1, date);
					pstmt.executeUpdate();
					conn.commit();
//					pstmt.close();
//					conn.close();
				}
			}
		}

	}

	public List<java.sql.Date> dateSplit(java.sql.Date start, java.sql.Date end) throws Exception {
		Long spi = end.getTime() - start.getTime();
		Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数		
		List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
		dateList.add(end);
		for (int i = 1; i <= step; i++) {
			java.sql.Date addDate = new java.sql.Date(dateList.get(i - 1).getTime() - (24 * 60 * 60 * 1000));
				dateList.add(addDate);// 比上一天减一
			}

//		conn.close();
		return dateList;
	}

	// 測試
	public static void main(String arg[]) {
		// Holi h = new Holi();
		// h.setHoliday(TypeChange.stringToSqlDate("2017-01-05"));
		// deleteHoliByDate(h);
	}
	
	//確認年度例假日是否已產生
	public String findHoliByYear(String yearInput) throws Exception {
		String wrongReturn = null; 
		conn = ConnectionHelper.getConnection();
		sql =sql + " where HOLIDAY = to_date(?, 'yyyy') ";
		pstmt = conn.prepareStatement(sql);// 叫sql建立一個單筆查詢的表
		pstmt.setString(1, yearInput);// 把變數組合起來送進pstm因為sql是從1開始所以注意要前面填1，1指?，有幾個?就有多少數字
		rs = pstmt.executeQuery();// executeQuery()代表sql執行指令

		// sql有查詢結果，才執行
		if (rs.next()) {
			holi = new Holi();
			holi.setHoliday(rs.getDate("HOLIDAY"));
			holi.setHoliReason(rs.getString("HOLI_REASON"));
			holi.setHrs(rs.getInt("HRS"));
		}
		if(InputCheck.isNull(holi)==false){
			wrongReturn = "年度假日已經新增";
		}
		conn.close();
		return wrongReturn;
	}
	
	// 把變數還回去
	public void close() throws Exception {
		rs.close();
		stmt.close();
		conn.close();
		rs = null;
		stmt = null;
		conn = null;
	}
}
