package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the V_WORKHOUR_A database table.
 * 
 */
@Entity
@Table(name="V_WORKHOUR_A")
public class VWorkhourA implements Serializable {
	private static final long serialVersionUID = 1L;

	private int ccc;
    @Id
	@Column(name="PRO_CODE")
	private String proCode;

	@Column(name="PRO_NAME")
	private String proName;

	private String yyyymm;

	public VWorkhourA() {
	}

	public int getCcc() {
		return this.ccc;
	}

	public void setCcc(int ccc) {
		this.ccc = ccc;
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

	public String getYyyymm() {
		return this.yyyymm;
	}

	public void setYyyymm(String yyyymm) {
		this.yyyymm = yyyymm;
	}

}