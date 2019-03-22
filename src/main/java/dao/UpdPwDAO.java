package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Emp;

public class UpdPwDAO {

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

	// 確認密碼
	public Emp checkPw(String empNo) throws Exception {
		conn = ConnectionHelper.getConnection();
		String sql = " select pw from emp where emp_no=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, empNo);
		pstmt.executeUpdate();
		rs = pstmt.executeQuery(sql);
		if (rs.next()) {
			emp = new Emp(); // 用emp產生每一列的資料
			emp.setPw(rs.getString("PW"));
		}
		conn.close();
		return emp;
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
