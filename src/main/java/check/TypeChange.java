package check;

import java.text.*;

public class TypeChange {
	//型態轉換
	public static java.sql.Date stringToSqlDate(String stringDate) {
		// 日期轉換：從網頁抓到的"holiday"，用字串holiString接
		// 字串stringDate轉utilDate
		// utilDate轉sqlDate
		java.util.Date utilDate = null;
		java.sql.Date sqlDate = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			utilDate = format.parse(stringDate);
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return sqlDate;
	}
	

	
	
}
