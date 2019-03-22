package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Pro;

public class ProDAO {
	String sql = "select * from PRO ";
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Pro pro = null;
	List<Pro> pros = new ArrayList<Pro>();

	public String insProById(Pro pro) throws Exception{
		conn = ConnectionHelper.getConnection();
		sql = "INSERT INTO PRO(PRO_CODE,PRO_NAME) " +
				"VALUES('ATX' || LPAD(PRO_SEQ.NEXTVAL, 3, '0'),? ) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Pro.getProName());
		int cnt = pstmt.executeUpdate();
		conn.commit();
		pstmt.clearParameters();
		sql = "SELECT PRO_SEQ.CURRVAL proCode FROM Pro";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);
		rs.next();
		conn.close();
		return rs.getString("proCode");
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
