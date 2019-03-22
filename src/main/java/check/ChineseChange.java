package check;

import java.io.UnsupportedEncodingException;

public class ChineseChange {
	
	// 解決submit中文亂碼
	public static String u2i(String str) {
		String tmp = null;
		try {
			tmp = new String(str.getBytes("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return tmp;
	}

	// 解決input text中文亂碼
	public static String u2i2(String str) {
		String tmp = null;
		try {
			tmp = new String(str.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return tmp;
	}
}
