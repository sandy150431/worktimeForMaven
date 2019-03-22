package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the WORKHOURS database table.
 * 
 */
@Embeddable
public class WorkhourPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="EMP_NO", insertable=true, updatable=true)
	private String empNo;

	//@Temporal(TemporalType.DATE)
	private java.sql.Date dd;

	public WorkhourPK() {
	}
	public String getEmpNo() {
		return this.empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public java.sql.Date getDd() {
		return this.dd;
	}
	public void setDd(java.sql.Date dd) {
		this.dd = dd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WorkhourPK)) {
			return false;
		}
		WorkhourPK castOther = (WorkhourPK)other;
		return 
			this.empNo.equals(castOther.empNo)
			&& this.dd.equals(castOther.dd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.empNo.hashCode();
		hash = hash * prime + this.dd.hashCode();
		
		return hash;
	}
}