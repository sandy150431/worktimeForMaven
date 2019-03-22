package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the WORKHOURS database table.
 * 
 */
@Entity
@Table(name="WORKHOURS")
public class Workhours implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkhourPK id;

	@Column(name="EMP_NO", insertable=true, updatable=true)
	private String empNo;
	@Column(name = "EMP_NAME")
	private String empName;
	@Column(name = "WE")
	private String we;
	//@Temporal(TemporalType.DATE)
	@Column(name = "DD")
	private java.sql.Date dd;
	@Column(name = "PRO_CODE")
	private String proCode;	
	@Column(name="PRO_NAME")
	private String proName;
	@Column(name = "WHR")  //�D�n�u��
	private int whr;
	@Column(name = "CONT") //�D�n�u�ɤ��e
	private String cont;
	@Column(name = "OT")   //�[�Z
	private int ot;        
	@Column(name = "CONT") //�[�Z���e
	private String otCont;
	@Column(name="REJECT_REASON")
	private String rejectReason;
	@Column(name = "STAT")
	private String stat;
	@Column(name = "HURRY")
	private int hurry;

	public Workhours() {
	}
	
	public WorkhourPK getId() {
		return this.id;
	}
	public void setId(WorkhourPK id) {
		this.id = id;
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
	public String getWe() {
		return this.we;
	}
	public void setWe(String we) {
		this.we = we;
	}

	public java.sql.Date getDd() {
		return this.dd;
	}
	public void setDd(java.sql.Date dd) {
		this.dd = dd;
	}

	public String getProCode() {
		return this.proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return this.proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getWhr() {
		return this.whr;
	}
	public void setWhr(int whr) {
		this.whr = whr;
	}
	
	public String getCont() {
		return this.cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}

	public int getOt() {
		return this.ot;
	}
	public void setOt(int ot) {
		this.ot = ot;
	}

	public String getOtCont() {
		return this.otCont;
	}
	public void setOtCont(String otCont) {
		this.otCont = otCont;
	}

	public String getRejectReason() {
		return this.rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public String getStat() {
		return this.stat;
	}
	public void setStat(String i) {
		this.stat = i;
	}
	
	public int getHurry() {
		return this.hurry;
	}

	public void setHurry(int hurry) {
		this.hurry = hurry;
	}


}