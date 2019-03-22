package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Emp;

public class ForgotDAO {

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Emp emp = null;

	// 修改密碼
	public void updatePw(Emp emp) throws Exception {
		conn = ConnectionHelper.getConnection();
		String sql = " UPDATE EMP set PW=? where EMP_NO=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, emp.getPw());
		pstmt.setString(2, emp.getEmpNo());
		pstmt.executeUpdate();
		conn.commit();
		pstmt.close();
		conn.close();
	}

	// 確認身分
	public String checkId(Emp emp) throws Exception {
		String ti = null;
		conn = ConnectionHelper.getConnection();
		String sql = " select twid from emp where emp_no=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, emp.getEmpNo());
		pstmt.executeUpdate();
		rs = pstmt.executeQuery(sql);
		if (rs.next()) {
			ti = rs.getString("TWID");
			return ti;
		}
		conn.close();
		return null;
		
	}
	public String checkMail(Emp emp) throws Exception {
		String ema = null;
		conn = ConnectionHelper.getConnection();
		String sql = " select email from emp where emp_no=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, emp.getEmpNo());
		pstmt.executeUpdate();
		rs = pstmt.executeQuery(sql);
		if (rs.next()) {
			ema = rs.getString("email");
			return ema;
		}
		conn.close();
		return null;
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
