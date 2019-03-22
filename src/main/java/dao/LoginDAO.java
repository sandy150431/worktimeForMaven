package dao;

import java.sql.*;
import model.Emp;
import dao.ConnectionHelper;

public class LoginDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// String driver = "oracle.jdbc.driver.OracleDriver";
	// String url = "jdbc:oracle:thin:@localhost:1521:xe";
	// String user = "abc";
	// String pwd = "abc";

	private static final String LOGIN = "SELECT EMP_NO,PW,AUTHORITY FROM EMP WHERE EMP_NO =? AND PW =?";

	public String authenticateUser(Emp emp) throws Exception {
		String authorityDB = null;
		conn = ConnectionHelper.getConnection();
		pstmt = conn.prepareStatement(LOGIN);
		pstmt.setString(1, emp.getEmpNo());
		pstmt.setString(2, emp.getPw());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			authorityDB = rs.getString("authority").trim();
			if (authorityDB.equals("Admin")) {
				return "Admin";
			} else if (authorityDB.equals("Manager")) {
				return "Manager";
			} else if (authorityDB.equals("Employee")) {
				return "Employee";
			}
		}
		rs.close();
		pstmt.close();
		conn.close();
		return "Invalid user";
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
