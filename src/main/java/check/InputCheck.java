package check;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.io.IOException;

public class InputCheck {

	// 判斷字串是不是整數
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	// 判斷字串有沒有在範圍
	public static boolean isInRange(int num, int lower, int upper) {
		boolean inRange = false;
		if ((num >= lower) & (num <= upper)) {
			inRange = true;
		}
		return inRange;
	}

	// 判斷是不是空值
	public static boolean isNull(Object inputName) {
		boolean isNull = false;
		if (inputName == null || inputName.equals("")) {
			isNull = true;
		}
		return isNull;
	}

	// 判断字串是不是合法的日期格式
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
	
	// 判斷email格式參考資料：https://www.javaworld.com.tw/jute/post/view?bid=35&id=168222&sty=3
	public static final Pattern EMAIL_PATTERN = Pattern
			.compile("^\\w+\\.*\\w+@(\\w+\\.){1,5}[a-zA-Z]{2,3}$");

	public static boolean isValidEmail(String email) {
		boolean result = false;
		if (EMAIL_PATTERN.matcher(email).matches()) {
			result = true;
		}
		return result;
	}

	// 判斷身分證格式參考資料：https://www.javaworld.com.tw/jute/post/view?bid=35&id=168222&sty=3
//	public static boolean isValidIDorRCNumber(String str) {
//		if (str == null || "".equals(str)) {
//			return false;
//		}
//		final char[] pidCharArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
//				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
//				'U', 'V', 'W', 'X', 'Y', 'Z' };
//		// 原身分證英文字應轉換為10~33，這裡直接作個位數*9+10
//		final int[] pidIDInt = { 1, 10, 19, 28, 37, 46, 55, 64, 39, 73, 82, 2,
//				11, 20, 48, 29, 38, 47, 56, 65, 74, 83, 21, 3, 12, 30 };
//		// 原居留證第一碼英文字應轉換為10~33，十位數*1，個位數*9，這裡直接作[(十位數*1) mod 10] + [(個位數*9) mod
//		// 10]
//		final int[] pidResidentFirstInt = { 1, 10, 9, 8, 7, 6, 5, 4, 9, 3, 2,
//				2, 11, 10, 8, 9, 8, 7, 6, 5, 4, 3, 11, 3, 12, 10 };
//		// 原居留證第二碼英文字應轉換為10~33，並僅取個位數*6，這裡直接取[(個位數*6) mod 10]
//		final int[] pidResidentSecondInt = { 0, 8, 6, 4, 2, 0, 8, 6, 2, 4, 2,
//				0, 8, 6, 0, 4, 2, 0, 8, 6, 4, 2, 6, 0, 8, 4 };
//		str = str.toUpperCase();// 轉換大寫
//		final char[] strArr = str.toCharArray();// 字串轉成char陣列
//		int verifyNum = 0;
//		/* 檢查身分證字號 */
//		if (str.matches("[A-Z]{1}[1-2]{1}[0-9]{8}")) {
//			// 第一碼
//			verifyNum = verifyNum
//					+ pidIDInt[Arrays.binarySearch(pidCharArray, strArr[0])];
//			// 第二~九碼
//			for (int i = 1, j = 8; i < 9; i++, j--) {
//				verifyNum += Character.digit(strArr[i], 10) * j;
//			}
//			// 檢查碼
//			verifyNum = (10 - (verifyNum % 10)) % 10;
//
//			return verifyNum == Character.digit(strArr[9], 10);
//		}
//		/* 檢查統一證(居留證)編號 */
//		verifyNum = 0;
//		if (str.matches("[A-Z]{1}[A-D]{1}[0-9]{8}")) {
//			// 第一碼
//			verifyNum += pidResidentFirstInt[Arrays.binarySearch(pidCharArray,
//					strArr[0])];
//			// 第二碼
//			verifyNum += pidResidentSecondInt[Arrays.binarySearch(pidCharArray,
//					strArr[1])];
//			// 第三~八碼
//			for (int i = 2, j = 7; i < 9; i++, j--) {
//				verifyNum += Character.digit(strArr[i], 10) * j;
//			}
//			// 檢查碼
//			verifyNum = (10 - (verifyNum % 10)) % 10;
//			return verifyNum == Character.digit(strArr[9], 10);
//		}
//		return false;
//	}

	// 判斷新增員工資料輸入對不對
	public static boolean insEmpCheck(String empName, String pw, String email, String firstDate) {
		boolean isRight = false;

		// 判斷有沒有輸入東西
		boolean empNameNotInput = InputCheck.isNull(empName);
		boolean pwNotInput = InputCheck.isNull(pw);
//		boolean twidNotInput = InputCheck.isNull(twid);
		boolean emailNotInput = InputCheck.isNull(email);
		boolean firstDateNotInput = InputCheck.isNull(firstDate);
		// 判斷輸入的格式對不對：email、twid、firstDate
		boolean emailIsRight = InputCheck.isValidEmail(email);
		//boolean twidIsRight = InputCheck.isValidIDorRCNumber(twid);
		boolean firstDateIsValidDate = InputCheck.isValidDate(firstDate);
		
		if (empNameNotInput == false && pwNotInput== false && 
//				twidNotInput == false &&
				emailNotInput == false && firstDateNotInput == false
			&& emailIsRight 
			//&& twidIsRight 
			&& firstDateIsValidDate) {
			isRight = true;
		}
		return isRight;
	}
	
	// 判斷修改員工資料輸入對不對
	public static boolean updateEmpCheck(String empName, String email,
			String twid, String firstDate) {
		boolean isRight = false;

		// 判斷有沒有輸入東西
		boolean empNameNotInput = InputCheck.isNull(empName);
		boolean emailNotInput = InputCheck.isNull(email);
		boolean twidNotInput = InputCheck.isNull(twid);
		boolean firstDateNotInput = InputCheck.isNull(firstDate);
		// 判斷輸入的格式對不對：email、twid、firstDate
		boolean emailIsRight = InputCheck.isValidEmail(email);
		//boolean twidIsRight = InputCheck.isValidIDorRCNumber(twid);
		boolean firstDateIsValidDate = InputCheck.isValidDate(firstDate);
		
		if (empNameNotInput == false && emailNotInput == false
				&& twidNotInput == false && firstDateNotInput == false
				&& emailIsRight 
				//&& twidIsRight 
				&& firstDateIsValidDate) {
			isRight = true;
		}
		return isRight;
	}
	
	// 判斷新增例假日Holiday輸入對不對
	public static boolean insertHoliCheck(String holiday, String holiReason,
			String hrsString) {
		boolean isRight = false;

		// 判斷有沒有輸入東西
		boolean holidayNotInput = InputCheck.isNull(holiday);
		boolean holiReasonNotInput = InputCheck.isNull(holiReason);
		boolean hrsIsNotput = InputCheck.isNull(hrsString);
		// 判斷輸入的格式對不對：hrs是整數、holiday日期格式是對的
		boolean hrsIsInteger = InputCheck.isInteger(hrsString);
		boolean holidayisValidDate = InputCheck.isValidDate(holiday);
		if (holidayNotInput == false && holiReasonNotInput == false
				&& hrsIsNotput == false && hrsIsInteger && holidayisValidDate) {
			// 時間在區間內
			int hrs = Integer.valueOf(hrsString);
			boolean hrsIsInRange = InputCheck.isInRange(hrs, 1, 8);
			if (hrsIsInRange) {
				isRight = true;
			}
		}
		return isRight;
	}

}
