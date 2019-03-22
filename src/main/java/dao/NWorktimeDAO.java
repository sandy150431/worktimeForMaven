package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Emp;
import model.Workhours;

public class NWorktimeDAO {

	private static String INSERTNW ="insert into workhours (emp_no ,emp_name, we, dd, pro_code,pro_name, whr, cont, ot , otcont ,stat,hurry)" +
"values (?, ?,? ,?, ?, ? , ? , ? , ? , ? ,?,'0')";
	private static String HURRYME = "select ot from workhours where emp_no = ? and hurry = '1' ";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Workhours workhour = new Workhours();
	
	public void insertNW(Workhours workhour) throws Exception{
	
			conn =ConnectionHelper.getConnection();
			pstmt = conn.prepareStatement(INSERTNW);
			pstmt.setString(1, workhour.getEmpNo());
			pstmt.setString(2, workhour.getEmpName());
			pstmt.setString(3, workhour.getWe());
			pstmt.setDate(4,(Date) workhour.getDd());
			pstmt.setString(5, workhour.getProCode());
			pstmt.setString(6, workhour.getProName());
			pstmt.setInt(7, workhour.getWhr());
			pstmt.setString(8, workhour.getCont());
			pstmt.setInt(9, workhour.getOt());
			pstmt.setString(10, workhour.getOtCont());
			pstmt.setString(11, workhour.getStat());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			conn.close();
		}
	
		public void findproname(Workhours workhour)throws Exception{
			String pname = null;
			conn =ConnectionHelper.getConnection();
			pstmt = conn.prepareStatement("select pro_name from pro where pro_code = ?");
			pstmt.setString(1, workhour.getProCode());
			rs = pstmt.executeQuery();
			if(rs.next()){
				pname  = rs.getString("pro_name");
				workhour.setProName(pname);
				}
			rs.close();
			pstmt.close();
			conn.close();
			}
		
		public void findname(Workhours workhour)throws Exception{
			String name = null;
			conn =ConnectionHelper.getConnection();
			pstmt = conn.prepareStatement("select emp_name from emp where emp_no = ?");
			pstmt.setString(1, workhour.getEmpNo());
			rs = pstmt.executeQuery();
			if(rs.next()){
				name  = rs.getString("emp_name");
				workhour.setEmpName(name);
				}
			rs.close();
			pstmt.close();
			conn.close();
			}

		public String findme(Workhours workhour) throws Exception{
			
			String yn = null;
			conn = ConnectionHelper.getConnection();
			pstmt =conn.prepareStatement(HURRYME);
			pstmt.setString(1, workhour.getEmpNo());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				yn = rs.getString("ot").trim();
				if(yn != null){
					return " oops";
				}

				conn.close();
			}return "pass";
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
