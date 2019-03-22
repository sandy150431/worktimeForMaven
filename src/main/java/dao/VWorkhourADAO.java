package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class VWorkhourADAO {
	String sql = " SELECT PRO_CODE,PRO_NAME,YYYYMM,CCC from V_WORKHOUR_A ";
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	VWorkhourA vWorkhourA = null;
	List<VWorkhourA> vWorkhourAs = new ArrayList<VWorkhourA>();
	List<VWorkhourA> vWorkhourAe = new ArrayList<VWorkhourA>();

	public List<VWorkhourA> findAllWorkhoure(String proCode) throws Exception {
		conn = ConnectionHelper.getConnection();
		//stmt = conn.createStatement();
		sql = " SELECT PRO_CODE, PRO_NAME, YYYYMM, CCC from V_WORKHOUR_A where PRO_CODE=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, proCode);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			vWorkhourA = new VWorkhourA();
			vWorkhourA.setProCode(rs.getString("PRO_CODE"));
			vWorkhourA.setProName(rs.getString("PRO_NAME"));
			vWorkhourA.setYyyymm(rs.getString("YYYYMM"));
			vWorkhourA.setCcc(rs.getInt("CCC"));
			vWorkhourAe.add(vWorkhourA);
			//System.out.println("size=" + vWorkhourAe.size());
		}

		conn.close();
		return vWorkhourAe;
	}
	
	public List<VWorkhourA> findAllWorkhour() throws Exception {
		conn = ConnectionHelper.getConnection();
		stmt = conn.createStatement();
		sql = " SELECT PRO_CODE,PRO_NAME,YYYYMM,CCC from V_WORKHOUR_A ";
		//System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			vWorkhourA = new VWorkhourA();
			vWorkhourA.setProCode(rs.getString("PRO_CODE"));
			vWorkhourA.setProName(rs.getString("PRO_NAME"));
			vWorkhourA.setYyyymm(rs.getString("YYYYMM"));
			vWorkhourA.setCcc(rs.getInt("CCC"));
			vWorkhourAs.add(vWorkhourA);
			//System.out.println("size=" + vWorkhourAs.size());
		}
		conn.close();
		return vWorkhourAs;
	}
//	public VWorkhourA findWorkhourByProCode(String proCode) throws Exception {
//		// 把變數填進東西
//		conn = ConnectionHelper.getConnection();
//		sql = " SELECT PRO_CODE,PRO_NAME,YYYYMM,CCC from V_WORKHOUR_A where PRO_CODE = ? ";
//		pstmt = conn.prepareStatement(sql);// 叫sql建立一個單筆查詢的表
//		pstmt.setString(1, proCode);
//		rs = pstmt.executeQuery();
//
//		// sql有查詢結果，才執行
//		if (rs.next()) {
//			vWorkhourA = new VWorkhourA(); 
//			vWorkhourA.setProCode(rs.getString("PRO_CODE"));
//			vWorkhourA.setProName(rs.getString("PRO_NAME"));
//			vWorkhourA.setYyyymm(rs.getString("YYYYMM"));
//			vWorkhourA.setCcc(rs.getInt("CCC"));
//		}
//		return vWorkhourA;// 回傳結果單筆表格給findEmpByNo()方法
//	}
	
	public void close() throws Exception {
		rs.close();
		stmt.close();
		conn.close();
		rs = null;
		stmt = null;
		conn = null;
	}
	
}
