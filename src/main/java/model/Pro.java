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
	private static String proCode;

	@Column(name="PRO_NAME")
	private static String proName;

	public Pro() {
	}

	public static String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		Pro.proCode = proCode;
	}

	public static String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		Pro.proName = proName;
	}

}