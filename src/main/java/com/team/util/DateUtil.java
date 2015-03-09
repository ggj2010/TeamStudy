package com.team.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {
	
	public static Date getDate() {
		return new Date();
	}
	
	/**
	 * 获取指定日期加上day天后的日期
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getFormatDate(String date, int day) {
		String d = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			calendar.setTime(sdf.parse(date));
			calendar.add(Calendar.DATE, day);
			d = sdf.format(calendar.getTime());
		} catch (ParseException e) {
			log.error("ParseException:" + e);
		}
		return d;
	}
	
	/**
	 * 获取任意输入的八位有效日期的当前月份的第一天
	 * @param date
	 * @return
	 */
	public static String getActualMinimum(String date) {
		String currentYear = date.substring(0, 4); // 获取输入日期的年份
		String currentMonth = date.substring(4, 6); // 获取输入日期的月份
		String maxDate = currentYear + currentMonth + "01";
		return maxDate;
	}
	
	/**
	 * 获取任意输入的八位有效日期的当前月份的最后一天
	 * @param date
	 * @return
	 */
	public static String getActualMaximum(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Date formatDate = null;
		try {
			formatDate = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			log.error("ParseException：" + e);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(formatDate);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return simpleDateFormat.format(cal.getTime());
	}
	
	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 取两个日期之间的日期
	 * @param dateFrom 开始日期
	 * @param dateEnd 结束日期
	 * @return 之间的日期
	 * @throws ParseException
	 */
	public static List<String> eachDates(String dateFrom, String dateEnd) {
		List<String> dateList = new ArrayList<String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(dateFrom));
			while (calendar.getTime().before(sdf.parse(dateEnd))) {
				calendar.add(Calendar.DATE, 1);
				if (!sdf.format(calendar.getTime()).equals(dateEnd)) {
					dateList.add(sdf.format(calendar.getTime()));
				}
			}
		} catch (ParseException e) {
			return dateList;
		}
		return dateList;
	}
	
	/**
	 * 获取距当前日期day天前后的日期
	 * @param day
	 * @return
	 */
	public static String getPrevDate(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String prevDate = format.format(cal.getTime());
		return prevDate;
	}
	
	public static String getFmtDate(String fmt) {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		String date = sdf.format(new Date());
		return date;
	}
	
	public static String getFmtDate(String date, String oldFmt, String fmt) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(oldFmt);
		Date fmtDate = sdf.parse(date);
		sdf.applyPattern(fmt);
		return sdf.format(fmtDate);
	}
	
	/**
	 * 根据输入的格式化字符串,返回当前日期时间
	 * @param formatStringType 输入指定格式化日期的字符串(如：输入yyyy-MM-dd HH:mm:ss,返回当前系统时间的改格式字符串,如：2012-12-12 12:12:12)
	 * @return String 系统当前时间字符串
	 */
	public static String getDateTime(String formatStringType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStringType);
		String dateTime = sdf.format(new Date());
		return dateTime;
	}
	
	public static String getFmtBeorAfter(String date, String befor, String after, int day) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(befor);
		sdf.parse(date);
		Calendar calendar = sdf.getCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, day);
		sdf.applyPattern(after);
		return sdf.format(calendar.getTime());
	}
	
	public static List<String> eachMonthList(String start, String end) throws ParseException {
		List<String> monthList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date startDate = sdf.parse(start);
		Date endDate = sdf.parse(end);
		if (startDate.getTime() == endDate.getTime()) {
			monthList.add(new SimpleDateFormat("yyyyMM").format(startDate));
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			while (calendar.getTime().before(endDate)) {
				monthList.add(new SimpleDateFormat("yyyyMM").format(calendar.getTime()));
				calendar.add(Calendar.MONTH, 1);
			}
		}
		return monthList;
	}
	
	public static String getPrevMonth(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, day);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		String prevDate = format.format(cal.getTime());
		return prevDate;
	}
	
	/**
	 * 取两个日期之间的日期
	 * 
	 * @param dateFrom 开始日期
	 * @param dateEnd 结束日期
	 * @return 之间的日期
	 * @throws ParseException
	 */
	public static List<String> eachDates2(String dateFrom, String dateEnd) {
		List<String> dateList = new ArrayList<String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(dateFrom));
			while (calendar.getTime().before(sdf.parse(dateEnd))) {
				
				if (!sdf.format(calendar.getTime()).equals(dateEnd)) {
					dateList.add(sdf.format(calendar.getTime()));
				}
				calendar.add(Calendar.DATE, 1);
			}
			dateList.add(dateEnd);
			
		} catch (ParseException e) {
			return dateList;
		}
		return dateList;
	}
	
	public static List<String> getAllMonths(String start, String end) {
		List<String> list = new ArrayList<String>();
		String splitSign = "-";
		String regex = "\\d{4}" + splitSign + "(([0][1-9])|([1][012]))"; // 判断YYYY-MM时间格式的正则表达式
		if (!start.matches(regex) || !end.matches(regex)) {
			
		} else {
			if (start.equals(end)) {
				list.add(end);
			} else {
				
				if (start.compareTo(end) > 0) {
					// start大于end日期时，互换
					String temp = start;
					start = end;
					end = temp;
				}
				
				String temp = start; // 从最小月份开始
				while (temp.compareTo(start) >= 0 && temp.compareTo(end) < 0) {
					list.add(temp); // 首先加上最小月份,接着计算下一个月份
					String[] arr = temp.split(splitSign);
					int year = Integer.valueOf(arr[0]);
					int month = Integer.valueOf(arr[1]) + 1;
					if (month > 12) {
						month = 1;
						year++;
					}
					if (month < 10) {// 补0操作
						temp = year + splitSign + "0" + month;
					} else {
						temp = year + splitSign + month;
					}
				}
				list.add(end);
				
			}
			
		}
		
		return list;
	}
}
