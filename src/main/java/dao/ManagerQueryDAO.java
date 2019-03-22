package dao;

import java.sql.*;
import java.util.*;
import model.*;

public class ManagerQueryDAO {
	String sql = "select * from Workhours ";
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Workhours whr = null;
	List<Workhours> whrs = new ArrayList<Workhours>();

	//搜尋全部，但不包含員工未送出的工時
	public List<Workhours> findAllWorkhours() throws Exception {
		conn = ConnectionHelper.getConnection();
		String sqls = sql + "where stat='2' or stat='3' order by stat asc , dd desc ";
		stmt = conn.createStatement();
		pstmt = conn.prepareStatement(sqls);
		rs = stmt.executeQuery(sqls);

		while (rs.next()) {
			whr = new Workhours();// 用emp產生每一列的資料
			whr.setEmpNo(rs.getString("emp_No"));
			whr.setEmpName(rs.getString("emp_Name"));
			whr.setProName(rs.getString("pro_Name"));
			whr.setWe(rs.getString("we"));
			whr.setDd(rs.getDate("dd"));
			whr.setWhr(rs.getInt("whr"));
			whr.setCont(rs.getString("cont"));
			whr.setOt(rs.getInt("ot"));
			whr.setOtCont(rs.getString("otCont"));
			whr.setRejectReason(rs.getString("reject_Reason"));
			whr.setStat(rs.getString("stat"));

			whrs.add(whr);
		}
		conn.close();
		return whrs;
	}
	
	//根據輸入週別找日期(第一筆)
	public Workhours findDateByWe(String we) throws Exception {
		conn = ConnectionHelper.getConnection();
		sql = " select dd  from Workhours where we = ? and rownum=1 ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, we);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			whr = new Workhours();// 用emp產生每一列的資料
			whr.setDd(rs.getDate("dd"));
		}
		conn.close();
		return whr;
	}
	
	// 單筆：根據輸入的員工編號、日期找那一週的工時
	public Workhours findWorkhoursByNo(String empNo, java.sql.Date dd, int rowNumber) throws Exception {
		conn = ConnectionHelper.getConnection();
		sql = "select * "
			+ " from ( select a.*, rownum rnum "
			         + " from ( select  * "
			         	     + " from Workhours "
			         		 + " where emp_no=? and "
			         	     + " we=(select DISTINCT we from Workhours "
			         	     + " where dd = ?) and "
			         	     + " (stat='2' or stat='3') "
			         	     + " order by dd) a "
			         + " where rownum <= ? ) "
			+ " where rnum >= ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, empNo);
		pstmt.setDate(2, dd);
		pstmt.setInt(3, rowNumber);
		pstmt.setInt(4, rowNumber);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			whr = new Workhours();// 用emp產生每一列的資料

			whr.setEmpNo(rs.getString("emp_No"));
			whr.setEmpName(rs.getString("emp_Name"));
			whr.setProName(rs.getString("PRO_Name"));
			whr.setWe(rs.getString("we"));
			whr.setDd(rs.getDate("dd"));
			whr.setWhr(rs.getInt("whr"));
			whr.setCont(rs.getString("cont"));
			whr.setOt(rs.getInt("ot"));
			whr.setOtCont(rs.getString("otCont"));
			whr.setRejectReason(rs.getString("reject_Reason"));
			whr.setStat(rs.getString("stat"));
		}
		conn.close();
		return whr;
	}

	// 多筆：根據輸入的員工編號、日期找那一周的工時
	public List<Workhours> findAllWeekWorkhoursByNo(String empNo, java.sql.Date dd) throws Exception {
		conn = ConnectionHelper.getConnection();
		sql = " select  * from Workhours "
              + " where emp_no=? and "
              + " we=(select DISTINCT we from Workhours "
              + " where dd = ? ) and  "
               + " (stat='2' or stat='3') "
               + " order by dd ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, empNo);
		pstmt.setDate(2, dd);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			whr = new Workhours();// 用emp產生每一列的資料
			whr.setEmpNo(rs.getString("emp_No"));
			whr.setEmpName(rs.getString("emp_Name"));
			whr.setWe(rs.getString("we"));
			whr.setDd(rs.getDate("dd"));
			whr.setWhr(rs.getInt("whr"));
			whr.setCont(rs.getString("cont"));
			whr.setOt(rs.getInt("ot"));
			whr.setOtCont(rs.getString("otCont"));
			whr.setRejectReason(rs.getString("reject_Reason"));
			whr.setStat(rs.getString("stat"));

			whrs.add(whr);
		}
		conn.close();
		return whrs;
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
