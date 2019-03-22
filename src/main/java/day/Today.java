package day;

import java.util.Calendar;
import java.util.Date;

public class Today {
	static Date today = new Date();
	public static Date getMonDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(today);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()); 
        return calendar.getTime();
    }
	public static Date getTuesDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY );
        calendar.setTime(today);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()+1); 
        return calendar.getTime();
    }
	public static Date getWednesDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(today);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()+ 2); 
        return calendar.getTime();
    }
	public static Date getThursDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(today);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()+ 3); 
        return calendar.getTime();
    }
	public static Date getFriDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(today);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek() + 4); 
        return calendar.getTime();
    }
	public static Date getSaturDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(today);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek() + 5); 
        return calendar.getTime();
    }
	public static Date getSunDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(today);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek() +6); 
        return calendar.getTime();
    }
	public static int getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        int week =calendar.get(Calendar.WEEK_OF_YEAR);
        return week;
    }
}
