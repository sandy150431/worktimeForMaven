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
	// VWorkhourA vWorkhourA = null;
	// List<VWorkhourA> vWorkhourAs = new ArrayList<VWorkhourA>();
	// List<VWorkhourA> vWorkhourAe = new ArrayList<VWorkhourA>();
	//
	// public List<VWorkhourA> findAllWorkhoure(String proCode) throws Exception {
	// conn = ConnectionHelper.getConnection();
	// // stmt = conn.createStatement();
	// sql = " SELECT PRO_CODE, PRO_NAME, YYYYMM, CCC from V_WORKHOUR_A where
	// PRO_CODE=? ";
	// pstmt = conn.prepareStatement(sql);
	// pstmt.setString(1, proCode);
	// rs = pstmt.executeQuery();
	// while (rs.next()) {
	// vWorkhourA = new VWorkhourA();
	// vWorkhourA.setProCode(rs.getString("PRO_CODE"));
	// vWorkhourA.setProName(rs.getString("PRO_NAME"));
	// vWorkhourA.setYyyymm(rs.getString("YYYYMM"));
	// vWorkhourA.setCcc(rs.getInt("CCC"));
	// vWorkhourAe.add(vWorkhourA);
	// // System.out.println("size=" + vWorkhourAe.size());
	// }
	//
	// conn.close();
	// return vWorkhourAe;
	// }

	public List<Workhours> findAllWorkhour() {

		List<Workhours> list = new ArrayList<Workhours>();
		try {
			conn = ConnectionHelper.getConnection();
			stmt = conn.createStatement();
			sql = " select pro_code, pro_name,dd, sum(ot+whr) sumWhr from workhours group by pro_code , pro_name , dd ";
			// System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Workhours workhours = new Workhours();
				workhours.setProCode(rs.getString("PRO_CODE"));
				workhours.setProName(rs.getString("PRO_NAME"));
				workhours.setDd(rs.getDate("DD"));
				workhours.setWhr(rs.getInt("sumWhr"));
				list.add(workhours);
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public void close() throws Exception {
		rs.close();
		stmt.close();
		conn.close();
		rs = null;
		stmt = null;
		conn = null;
	}

}
