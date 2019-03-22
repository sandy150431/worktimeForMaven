package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the EMP database table.
 * 
 */
@Entity
@Table(name = "Emp")
public class Emp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMP_NO")
	private String empNo;
	@Column(name = "EMP_NAME")
	private String empName;
	@Column(name = "PW")
	private String pw;
	@Column(name = "TWID")
	private String twid;
	@Column(name = "SEX")
	private Integer sex; // 這邊有改
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "FIRST_DATE")
	private java.sql.Date firstDate;
	@Column(name = "AUTHORITY")
	private String authority;
	@Column(name = "RESIGN")
	private Integer resign; // 這邊有改
	@Column(name = "RESIGN_DATE")
	private java.sql.Date resignDate;

	public Emp() {
	}

	public String getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPw() {
		return this.pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getTwid() {
		return this.twid;
	}

	public void setTwid(String twid) {
		this.twid = twid;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.sql.Date getFirstDate() {
		return this.firstDate;
	}

	public void setFirstDate(java.sql.Date firstDate) {
		this.firstDate = firstDate;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Integer getResign() {
		return this.resign;
	}

	public void setResign(Integer resign) {
		this.resign = resign;
	}

	public java.sql.Date getResignDate() {
		return this.resignDate;
	}

	public void setResignDate(java.sql.Date resignDate) {
		this.resignDate = resignDate;
	}


//	public String getAuthorityChinese() {
//		if (this.authority == "1" || this.authority.equals("1")) {
//			return "Admin";
//		} else if (this.authority == "2" || this.authority.equals("2")) {
//			return "Manager";
//		} else {
//			return "Employee";
//		}
//	}
}
