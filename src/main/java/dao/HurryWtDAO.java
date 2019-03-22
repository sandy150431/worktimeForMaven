package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Workhours;
import dao.ConnectionHelper;

public class HurryWtDAO {

	private static final String HURRYWT = " SELECT DISTINCT EMP_NO ,EMP_NAME, WE, HURRY  FROM WORKHOURS WHERE STAT='1' ";
	private static final String HURRYST = " update workhours set hurry=hurry+1 where stat='1' ";
	List<Workhours> workhours = new ArrayList<Workhours>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Workhours workhour;

	public List<Workhours> Find() throws Exception {
		conn = ConnectionHelper.getConnection();
		pstmt = conn.prepareStatement(HURRYWT);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			workhour = new Workhours();
			workhour.setEmpNo(rs.getString("Emp_no"));
			workhour.setEmpName(rs.getString("Emp_name"));
			workhour.setWe(rs.getString("We"));
			workhour.setHurry(rs.getInt("HURRY"));
			workhours.add(workhour);
		}
		conn.close();
		return workhours;
	}

	public void updateHurry(Workhours workhour) throws Exception {
		conn = ConnectionHelper.getConnection();
		pstmt = conn.prepareStatement(HURRYST);
		pstmt.executeUpdate();
		conn.commit();
		pstmt.close();
		conn.close();
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
