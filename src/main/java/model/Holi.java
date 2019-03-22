package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HOLI database table.
 * 
 */
@Entity
public class Holi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="HOLIDAY")
	private java.sql.Date holiday;
	@Column(name="HOLI_REASON")
	private String holiReason;
	@Column(name="HRS")
	private int hrs;

	public Holi() {
	}

	public java.sql.Date getHoliday() {
		return this.holiday;
	}
	public void setHoliday(java.sql.Date holiday) {
		this.holiday = holiday;
	}

	public String getHoliReason() {
		return this.holiReason;
	}
	public void setHoliReason(String holiReason) {
		this.holiReason = holiReason;
	}

	public int getHrs() {
		return this.hrs;
	}
	public void setHrs(int hrs) {
		this.hrs = hrs;
	}

}