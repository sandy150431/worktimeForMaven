package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRO database table.
 * 
 */
@Entity
@Table(name="Pro")
public class Pro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PRO_CODE")
	private String proCode;

	@Column(name="PRO_NAME")
	private String proName;

	public Pro() {
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

}