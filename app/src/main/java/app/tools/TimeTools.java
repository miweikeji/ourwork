package app.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2015/11/1.
 */
public class TimeTools {

    public static String longToDate(String time){
        DateFormat formatter = new SimpleDateFormat("MM月dd日");
//        long n = new Long("time");
        long n  =Long.valueOf(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(n);
        return formatter.format(calendar.getTime());
    }
}
