package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Pro;

public class ProDAO {
	String sql = "select * from PRO ";
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void insertPro(Pro pro) throws Exception {
		conn = ConnectionHelper.getConnection();
		sql = "INSERT INTO PRO(PRO_CODE, PRO_NAME ) VALUES( ?,? ) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pro.getProCode());
		pstmt.setString(2, pro.getProName());
		pstmt.executeUpdate();
		pstmt.clearParameters();

		sql = "SELECT pro_code FROM Pro";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);
		rs.next();
		conn.close();
	}

	// 單筆
	public Pro findProByCode(String proCode) throws Exception {

		Pro pro = new Pro();

		conn = ConnectionHelper.getConnection();// 連線sql
		sql = sql + " where PRO_CODE = ? ";// 把最前面設定的sql再加查詢條件where

		pstmt = conn.prepareStatement(sql);// 叫sql建立一個單筆查詢的表
		pstmt.setString(1, proCode);// 把變數組合起來送進pstm因為sql是從1開始所以注意要前面填1，1指?，有幾個?就有多少數字
		rs = pstmt.executeQuery();// executeQuery()代表sql執行指令

		// sql有查詢結果，才執行
		if (rs.next()) {
			pro.setProCode(rs.getString("PRO_CODE"));
			pro.setProName(rs.getString("PRO_NAME"));
		}

		conn.close();
		return pro;
	}

	public ResultSet getMapOfPro() throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select pro_code ,pro_name from pro order by pro_code";
		conn = ConnectionHelper.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
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
