package app.tools;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2015/11/1.
 */
public class TimeTools {
    public static final String TIME_FORMAT  = "yyyy年MM月dd日";
    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException

    {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }
    public static String longToDateStr(Double timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        String date = sdf.format(new Date((long) (timestamp * 1000L)));
        return date;
    }
    public static String longToDateStrs(Double timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = sdf.format(new Date((long) (timestamp * 1000L)));
        return date;
    }

    public static Long strToDateLong(String timestamp) {
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = simpleDateFormat .parse(timestamp);
            Long timeStemp = date.getTime();
            return timeStemp;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static String getDayTime(Date today,int day) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
        today=sdf.parse(sdf.format(today));
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        long time1 = cal.getTimeInMillis();

        long item_day =(day*3600*24*(long)1000)+time1;

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        String date = sdf1.format(new Date((long) ((double)item_day)));
        return date;
    }



    /**
     * String转换为时间
     *
     * @param str
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static Date parseDate(String str) {
        return parseDate(str, TIME_FORMAT);
    }

    public static Date parseDate(String str, String format) {
        Date addTime = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            addTime = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return addTime;
    }

    public static String dateToLong(String time){
        Date datetime = parseDate(time);
        long datetimeTime = datetime.getTime();
        return String.valueOf(datetimeTime);
    }

}
