package dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import model.*;

public class WorkhoursDAO {
	String sql = "select * from Workhours ";
	Connection conn = null;
	Statement stat = null;
	PreparedStatement pstat = null;
	ResultSet rs = null;
	Workhours whr = null;
	List<Workhours> whrsinfo = new ArrayList<Workhours>();

	public List<Workhours> findAllWorkhour(String empNo) throws Exception {

		conn = ConnectionHelper.getConnection();
		sql = sql + " where emp_no=? order by dd DESC ";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, empNo);
		rs = pstat.executeQuery();

		while (rs.next()) {
			whr = new Workhours();
			whr.setEmpNo(rs.getString("Emp_No"));
			whr.setDd(rs.getDate("DD"));
			whr.setWe(rs.getString("WE"));
			whr.setProCode(rs.getString("PRO_CODE"));
			whr.setProName(rs.getString("PRO_NAME"));
			whr.setWhr(rs.getInt("WHR"));
			whr.setCont(rs.getString("CONT"));
			whr.setOt(rs.getInt("OT"));
			whr.setOtCont(rs.getString("OTCONT"));
			whr.setStat(rs.getString("STAT"));
			whrsinfo.add(whr);
		}
//		rs.close();
//		pstat.close();
		conn.close();
		return whrsinfo;
	}

	public Workhours findWorkhourById(String empNo, String proCode, java.sql.Date dd)
			throws Exception {

		conn = ConnectionHelper.getConnection();
		sql = sql + " where emp_no=? and pro_code=? and dd=? ";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, empNo);
		pstat.setString(2, proCode);
		pstat.setDate(3, dd);
		rs = pstat.executeQuery(sql);
		while (rs.next()) {
			whr = new Workhours();
			whr.setDd(rs.getDate("DD"));
			whr.setWe(rs.getString("WE"));
			whr.setProCode(rs.getString("PRO_CODE"));
			whr.setWhr(rs.getInt("WHR"));
			whr.setCont(rs.getString("CONT"));
			whr.setOt(rs.getInt("OT"));
			whr.setOtCont(rs.getString("OTCONT"));
			whr.setStat(rs.getString("STAT"));
		}
//		rs.close();
//		pstat.close();
		conn.close();
		return whr;

	}
	
	//修改工時：儲存
	public void updWorkhourById1(Workhours whr) throws Exception {
		conn = ConnectionHelper.getConnection();
		String sql = " update workhours set pro_code=?, " +
				"pro_name=(select pro_name from pro where pro_code=?), " +
				"whr=?, cont=?, ot=?, otcont=?, stat='1' where emp_no=? and dd=? ";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, whr.getProCode());
		pstat.setString(2, whr.getProCode());
		pstat.setInt(3, whr.getWhr());
		pstat.setString(4, whr.getCont());
		pstat.setInt(5, whr.getOt());
		pstat.setString(6, whr.getOtCont());
		pstat.setString(7, whr.getEmpNo());
		pstat.setDate(8, whr.getDd());
		pstat.executeUpdate();
		conn.commit();		
		pstat.close();
		conn.close();
	}


	//修改工時：儲存
	public void updWorkhourById2(Workhours whr) throws Exception {
		conn = ConnectionHelper.getConnection();
		String sql = " update workhours set pro_code=?, " +
				"pro_name=(select pro_name from pro where pro_code=?), " +
				"whr=?, cont=?, ot=?, otcont=?, stat='2' where emp_no=? and dd=? ";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, whr.getProCode());
		pstat.setString(2, whr.getProCode());
		pstat.setInt(3, whr.getWhr());
		pstat.setString(4, whr.getCont());
		pstat.setInt(5, whr.getOt());
		pstat.setString(6, whr.getOtCont());
		pstat.setString(7, whr.getEmpNo());
		pstat.setDate(8, whr.getDd());
		pstat.executeUpdate();
		conn.commit();		
		
		pstat.close();
		conn.close();
	}
	
	public java.sql.Date findNewDateByNo(String empNo)
			throws Exception {
		conn = ConnectionHelper.getConnection();
		sql = " select dd from workhours where emp_no=? and rownum=1 order by dd desc ";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, empNo);
		rs = pstat.executeQuery();

		if (rs.next()) {
			whr = new Workhours();// 用emp產生每一列的資料
			whr.setDd(rs.getDate("DD"));
		}
//		rs.close();
//		pstat.close();
		conn.close();
		return whr.getDd();
	}
	
	public Workhours findWorkhoursByNo(String empNo, java.sql.Date dd, int rowNumber)
			throws Exception {
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
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, empNo);
		pstat.setDate(2, dd);
		pstat.setInt(3, rowNumber);
		pstat.setInt(4, rowNumber);
		rs = pstat.executeQuery();

		if (rs.next()) {
			whr = new Workhours();// 用emp產生每一列的資料

			whr.setEmpNo(rs.getString("EMP_NO"));
			whr.setEmpName(rs.getString("EMP_NAME"));
			whr.setWe(rs.getString("WE"));
			whr.setDd(rs.getDate("DD"));
			whr.setProCode(rs.getString("PRO_CODE"));
			whr.setProName(rs.getString("PRO_NAME"));
			whr.setWhr(rs.getInt("WHR"));
			whr.setCont(rs.getString("CONT"));
			whr.setOt(rs.getInt("OT"));
			whr.setOtCont(rs.getString("OTCONT"));
			whr.setRejectReason(rs.getString("REJECT_REASON"));
			whr.setStat(rs.getString("STAT"));
		}
//		rs.close();
//		pstat.close();
		conn.close();
		return whr;
	}
	
	
	
	public void insertNW(Workhours workhour1) throws Exception {
		// TODO Auto-generated method stub
			   conn =ConnectionHelper.getConnection();
			   sql="insert into workhours (emp_no , we, dd, pro_code, whr, cont, ot , otcont , stat)" +"values (?, ? ,?, ?, ? , ? , ? ,?,?)";
			   pstat = conn.prepareStatement(sql);
			   pstat.setString(1, workhour1.getEmpNo());
			   pstat.setString(2, workhour1.getWe());
			   pstat.setDate(3,(Date) workhour1.getDd());
			   pstat.setString(4, workhour1.getProCode());
			   pstat.setInt(5, workhour1.getWhr());
			   pstat.setString(6, workhour1.getCont());
			   pstat.setInt(7, workhour1.getOt());
			   pstat.setString(8, workhour1.getOtCont());
			   pstat.setString(9, workhour1.getStat());
			   pstat.executeUpdate();
			   conn.commit();
			   rs.close();
				pstat.close();
				conn.close();
			  }

	public void close() throws Exception {
		rs.close();
		pstat.close();
		conn.close();
		rs = null;
		pstat = null;
		conn = null;
	}
}

	