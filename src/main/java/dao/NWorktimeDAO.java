package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Workhours;

public class NWorktimeDAO {

	private static String INSERTNW = "insert into workhours (emp_no , we, dd, pro_code, whr, cont, ot , otcont ,stat,hurry)"
			+ "values (?, ? , ? , ? , ? , ? , ? , ? ,?,'0')";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Workhours workhour = new Workhours();

	public void insertNW(Workhours workhour) throws Exception {

		conn = ConnectionHelper.getConnection();
		pstmt = conn.prepareStatement(INSERTNW);
		pstmt.setString(1, workhour.getEmpNo());
		pstmt.setString(2, workhour.getWe());
		pstmt.setDate(3, (Date) workhour.getDd());
		pstmt.setString(4, workhour.getProCode());
		pstmt.setInt(5, workhour.getWhr());
		pstmt.setString(6, workhour.getCont());
		pstmt.setInt(7, workhour.getOt());
		pstmt.setString(8, workhour.getOtCont());
		pstmt.setString(9, workhour.getStat());
		pstmt.executeUpdate();
		conn.commit();
		pstmt.close();
		conn.close();
	}

	public void findproname(Workhours workhour) throws Exception {
		String pname = null;
		conn = ConnectionHelper.getConnection();
		pstmt = conn.prepareStatement("select pro_name from pro where pro_code = ?");
		pstmt.setString(1, workhour.getProCode());
		rs = pstmt.executeQuery();
		if (rs.next()) {
			pname = rs.getString("pro_name");
			workhour.setProName(pname);
		}
		rs.close();
		pstmt.close();
		conn.close();
	}

	public void findname(Workhours workhour) throws Exception {
		String name = null;
		conn = ConnectionHelper.getConnection();
		pstmt = conn.prepareStatement("select emp_name from emp where emp_no = ?");
		pstmt.setString(1, workhour.getEmpNo());
		rs = pstmt.executeQuery();
		if (rs.next()) {
			name = rs.getString("emp_name");
			workhour.setEmpName(name);
		}
		rs.close();
		pstmt.close();
		conn.close();
	}

	public String findme(Workhours workhour) throws Exception {

		String yn = null;
		conn = ConnectionHelper.getConnection();
		pstmt = conn.prepareStatement("select ot from workhours where emp_no = ? and hurry = '1' ");
		pstmt.setString(1, workhour.getEmpNo());
		rs = pstmt.executeQuery();

		//TODO待確認邏輯
		while (rs.next()) {
			yn = rs.getString("ot").trim();
			if (yn != null) {
				return " oops";
			}
			conn.close();
		}
		return "pass";
	}

	public void close() throws Exception {
		rs.close();
		pstmt.close();
		conn.close();
		rs = null;
		pstmt = null;
		conn = null;
	}

}
