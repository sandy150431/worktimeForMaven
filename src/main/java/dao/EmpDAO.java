package dao;

import java.sql.*;
import java.util.*;

import model.*;

public class EmpDAO {
	// 把常用的變數放在方法外面設null，就不用在一直重寫

	// 先建立sql相關連線
	String sql = "select * from EMP ";// 給sql的指令
	Connection conn = null;// 連線sql
	Statement stmt = null;// 下指令給sql說我要一個表
	PreparedStatement pstmt = null;// 下指令給sql說我要一個可以單筆查詢(有變數)的表
	ResultSet rs = null;// sql回傳的資料表

	// 建立專案用來接受sql資料的表格
	Emp emp = null;// 先建立表格裡面每一個element，用之前建立的JavaBean來建立
	List<Emp> emps = new ArrayList<Emp>();// 再建立一個陣列，之後要把所有element裝進表格

	// 建立一個List，用來裝Emp資料表
	// 這個方法是找所有員工(方法命名首字母小寫，之後單字首字母大寫)
	public List<Emp> findAllEmp() throws Exception {

		// 把剛剛設定的變數填進東西
		conn = ConnectionHelper.getConnection();// 連線sql
		stmt = conn.createStatement();// 連線後，叫sql建立一個表
		rs = stmt.executeQuery(sql);// executeQuery()代表sql執行指令
									// 所以executeQuery(sql)就是指叫sql執行"sql"
									// 指前面設定的變數sql = "select * from Emp "
									// 所以整句話是stmt建立表後，執行"select * from Emp "，
									// 再把結果裝進rs，所以rs是一個表

		// 要把rs中的資料一筆一筆取出來，用while迴圈
		// 直到rs沒有下一個(就是都找到了)，迴圈才會停
		while (rs.next()) {
			emp = new Emp();// 用emp產生每一列的資料

			// 以下表示把剛剛從rs取到的表，填進對應的表格
			// 舉例emp.setEmpNo(rs.getString("empNo"));
			// rs.getString("empNo")表示從rs抓一個欄位叫做"empNo"的String
			// emp.setEmpNo()表示把從rs抓到的"empNo"填進emp中
			// 注意：因為型態都不一，所以get...都不一樣；set後面都是欄位名稱但首字母大寫
			emp.setEmpNo(rs.getString("EMP_NO"));
			emp.setEmpName(rs.getString("EMP_NAME"));
			emp.setPw(rs.getString("PW"));
			emp.setTwid(rs.getString("TWID"));
			emp.setSex(rs.getInt("SEX"));
			emp.setEmail(rs.getString("EMAIL"));
			emp.setFirstDate(rs.getDate("FIRST_DATE"));
			emp.setAuthority(rs.getString("AUTHORITY"));
			emp.setResign(rs.getInt("RESIGN"));
			emp.setResignDate(rs.getDate("RESIGN_DATE"));

			// 最後再把各個欄位填進大表格emps中
			emps.add(emp);
		}
//		rs.close();
//		stmt.close();
		conn.close();
		return emps;// 回傳結果表格給findAllDepartment()方法
	}

	// 單筆：根據輸入的員工編號找結果。因為員編不會重覆，所以只會有一列表格內容
	// 回傳的不再是List陣列，所以最前面要改成Emp
	public Emp findEmpByNo(String empNo) throws Exception {
		// 把變數填進東西
		conn = ConnectionHelper.getConnection();// 連線sql
		sql = sql + " where emp_no = ? ";// 把最前面設定的sql再加查詢條件where

		pstmt = conn.prepareStatement(sql);// 叫sql建立一個單筆查詢的表
		pstmt.setString(1, empNo);// 把變數組合起來送進pstm
									// 因為sql是從1開始所以注意要前面填1
									// 1指?，有幾個?就有多少數字
		rs = pstmt.executeQuery();// executeQuery()代表sql執行指令

		// sql有查詢結果，才執行
		if (rs.next()) {
			emp = new Emp(); // 用emp產生每一列的資料

			emp.setEmpNo(rs.getString("EMP_NO"));
			emp.setEmpName(rs.getString("EMP_NAME"));
			emp.setPw(rs.getString("PW"));
			emp.setTwid(rs.getString("TWID"));
			emp.setSex(rs.getInt("SEX"));
			emp.setEmail(rs.getString("EMAIL"));
			emp.setFirstDate(rs.getDate("FIRST_DATE"));
			emp.setAuthority(rs.getString("AUTHORITY"));
			emp.setResign(rs.getInt("RESIGN"));
			emp.setResignDate(rs.getDate("RESIGN_DATE"));
		}

		conn.close();
		return emp;// 回傳結果單筆表格給findEmpByNo()方法
	}

	// 修改員工資料
	public void updateEmp(Emp emp) throws Exception {
		conn = ConnectionHelper.getConnection();
		sql = " UPDATE EMP set EMP_NAME=?, TWID=?, SEX=?,  EMAIL=?, FIRST_DATE=?, AUTHORITY=? where EMP_NO=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, emp.getEmpName());
		pstmt.setString(2, emp.getTwid());
		pstmt.setInt(3, emp.getSex());
		pstmt.setString(4, emp.getEmail());
		pstmt.setDate(5, emp.getFirstDate());
		pstmt.setString(6, emp.getAuthority());
		pstmt.setString(7, emp.getEmpNo());
		pstmt.executeUpdate();
		conn.commit();
		pstmt.close();
		conn.close();
	}

	// 查詢離職註記
	public Emp findEmpResignByNo(String empNo) throws Exception {
		conn = ConnectionHelper.getConnection();
		sql = sql + " where emp_no = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, empNo);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			emp = new Emp();
			emp.setEmpNo(rs.getString("EMP_NO"));
			emp.setEmpName(rs.getString("EMP_NAME"));
			emp.setResign(rs.getInt("RESIGN"));
			emp.setResignDate(rs.getDate("RESIGN_DATE"));
		}

		conn.close();
		return emp;
	}

	// 修改離職註記
	public void updateEmpResign(Emp emp) throws Exception {
		conn = ConnectionHelper.getConnection();
		sql = "update Emp set resign=?, resign_date=? where emp_no=? ";
		/* 建立SQL update statement */
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, emp.getResign());
		pstmt.setDate(2, emp.getResignDate());
		pstmt.setString(3, emp.getEmpNo());
		pstmt.executeUpdate();

		conn.commit();
		pstmt.close();
		conn.close();
	}

	//新增員工資料
	public String insEmp(Emp emp) throws Exception{
		  conn = ConnectionHelper.getConnection();
		  sql = "INSERT INTO EMP(EMP_NO,EMP_NAME,PW,TWID,SEX,EMAIL,FIRST_DATE,AUTHORITY,RESIGN) " +
				  "VALUES('a' || LPAD(EMP_SEQ.NEXTVAL, 6, '0'), ?, ?, ?, ?, ?, ?, ?,0) ";
		    pstmt = conn.prepareStatement(sql);
		  pstmt.setString(1, emp.getEmpName());
		  pstmt.setString(2, emp.getPw());
		  pstmt.setString(3, emp.getTwid());
		  pstmt.setInt(4, emp.getSex());
		  pstmt.setString(5, emp.getEmail());
		  pstmt.setDate(6, emp.getFirstDate());
		  pstmt.setString(7, emp.getAuthority());
		  pstmt.executeUpdate();
		  conn.commit();
		  pstmt.clearParameters();
		  sql = "SELECT EMP_SEQ.CURRVAL EMP_NO FROM DUAL ";
		  pstmt = conn.prepareStatement(sql);
		  rs = pstmt.executeQuery(sql);
		  rs.next();

		 conn.close();
		  return rs.getString("emp_no");
		 }
	public int delEmpById(Emp emp)throws Exception {
		// TODO Auto-generated method stub
		conn = ConnectionHelper.getConnection();
		sql = "DELETE FROM  EMP WHERE EMP_NO = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, emp.getEmpNo());
		int cnt = pstmt.executeUpdate();
		conn.commit();
		conn.close();
		return cnt;
	}
	public int delResignById(Emp emp)throws Exception {
		// TODO Auto-generated method stub
		conn = ConnectionHelper.getConnection();
		sql = "update emp set resign = 0,resign_date = null   where emp_no = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, emp.getEmpNo());
		int cnt = pstmt.executeUpdate();
		conn.commit();
		conn.close();
		return cnt;
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
