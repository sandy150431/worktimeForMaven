package check;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class IdTest {
	public static final Pattern TWPID_PATTERN = Pattern
		      .compile("[ABCDEFGHJKLMNPQRSTUVXYWZIO][12]\\d{8}");
	public static final Pattern EMAIL_PATTERN = Pattern
			.compile("^\\w+\\.*\\w+@(\\w+\\.){1,5}[a-zA-Z]{2,3}$");
	
	 public static boolean isValidTWPID(String twid) {
		    boolean result = false;
		    String pattern = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
		    if (TWPID_PATTERN.matcher(twid.toUpperCase()).matches()) {
		      int code = pattern.indexOf(twid.toUpperCase().charAt(0)) + 10;
		      int sum = 0;
		      sum = (int) (code / 10) + 9 * (code % 10) + 8 * (twid.charAt(1) - '0')
		          + 7 * (twid.charAt(2) - '0') + 6 * (twid.charAt(3) - '0')
		          + 5 * (twid.charAt(4) - '0') + 4 * (twid.charAt(5) - '0')
		          + 3 * (twid.charAt(6) - '0') + 2 * (twid.charAt(7) - '0')
		          + 1 * (twid.charAt(8) - '0') + (twid.charAt(9) - '0');
		      if ( (sum % 10) == 0) {
		        result = true;
		      }
		    }
		    return result;
		  }
	 public static boolean isValidDate(String str) {
			boolean convertSuccess = true;
			// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				// 设置lenient为false.
				// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				format.setLenient(false);
				format.parse(str);
			} catch (ParseException e) {
				// e.printStackTrace();
				// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
				convertSuccess = false;
			}
			return convertSuccess;
		}
	 public static boolean isValidEmail(String email) {
			boolean result = false;
			if (EMAIL_PATTERN.matcher(email).matches()) {
				result = true;
			}
			return result;
		}
}
