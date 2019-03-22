package dao;

import java.sql.*;
import java.util.*;
import model.*;

public class ManagerReviewDAO {
	String sql = "select * from Workhours ";
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Workhours whr = null;
	List<Workhours> whrs = new ArrayList<Workhours>();

	// 搜尋全部未審核工時，並計算總工時、加班工時
	public List<Workhours> findAllNotReviewWorkhours() throws Exception {
		conn = ConnectionHelper.getConnection();
		sql =  " select emp_no, emp_name, we, stat, sum(whr) as whrs, sum(ot) as ots"
			 + " from workhours "
			 + " where stat='2' "
			 + " group by emp_no, emp_name, we, stat "
			 + " order by emp_no ";
		stmt = conn.createStatement();
		pstmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery(sql);

		while (rs.next()) {
			whr = new Workhours();// 用emp產生每一列的資料
			whr.setEmpNo(rs.getString("emp_No"));
			whr.setEmpName(rs.getString("emp_Name"));
			whr.setWe(rs.getString("we"));
			whr.setStat(rs.getString("stat"));
			whr.setWhr(rs.getInt("whrs"));
			whr.setOt(rs.getInt("ots"));
			whrs.add(whr);
		}
		conn.close();
		return whrs;
	}
	
	public void updateStat(String stat, String rr, String empNo, String we) throws Exception {
		conn = ConnectionHelper.getConnection();
		sql = " update workhours set stat=?, REJECT_REASON=?  " +
				" where emp_no=? and we=? " ;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, stat);
		pstmt.setString(2, rr);
		pstmt.setString(3, empNo);
		pstmt.setString(4, we);
		pstmt.executeUpdate();
		conn.commit();
		pstmt.close();
		conn.close();
	}
	
	// 把變數還回去
	public void close() throws Exception {
		rs.close();
		pstmt.close();
		conn.close();
		rs = null;
		pstmt = null;
		conn = null;
	}
}
