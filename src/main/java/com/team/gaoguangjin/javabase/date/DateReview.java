package com.team.gaoguangjin.javabase.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateReview {
    public static void main(String[] args) throws ParseException {
        //addTime();
        getBeginDay();
    }
    //日期相加几分钟或者几小时
    private static void addTime() throws ParseException {
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=sf.parse("2017-09-18 14:00:00");
        //相加1小时
        Long addTime=date.getTime()+60*60*1000;
        //相加50分钟
        Long addTime2=date.getTime()+50*60*1000;
        System.out.println(sf.format(new Date(addTime)));
        System.out.println(sf.format(new Date(addTime2)));

        Date date2 = sf.parse("2017-09-20 19:00:00");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date2);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        System.out.println(sf.format(calendar.getTime()));


    }

    private static String getWeekStr(Date date, SimpleDateFormat sdf) {
        Date nowDate = new Date();
        if (sdf.format(date).equals(sdf.format(nowDate))) {
            return "今天";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        switch (week) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
            default:
                return "";
        }
    }


    private static long getBeginDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTime());
        return cal.getTimeInMillis();
    }
}
