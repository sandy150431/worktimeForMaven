package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Workhours;
		
	
public class SWTDAO {
	private static final String CHECKDD = "select emp_no , dd,ot from workhours where emp_no = ? and dd = ?";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String ot = "" ;
	Workhours workhour = new Workhours();
	public String find(Workhours workhour) throws Exception{
		
		conn = ConnectionHelper.getConnection();
		pstmt = conn.prepareStatement(CHECKDD);
		pstmt.setString(1, workhour.getEmpNo());
		pstmt.setDate(2, workhour.getDd());
		rs =pstmt.executeQuery();
		while(rs.next()){
			ot = rs.getString("ot").trim();
			if (ot != null){
				return "done";
			}
		}
		rs.close();
		pstmt.close();
		conn.close();
		
	return "OK";
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

