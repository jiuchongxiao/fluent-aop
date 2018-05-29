/*  
 * project tool-common
 * fileName DateUtils.java 
 * package com.taiyue.tool.common
 * description 日期操作类文件
 * copyright © 2017 www.99114.com Inc. All rights reserved.	
 */
package com.taiyue.tool.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * description: 日期操作类 <br>
 * date: 2017年1月20日 下午2:09:27 <br>
 * @author: ljb
 * @since: v1.0.0
 * @version v1.0.0 
 */
public class DateUtils {
	/**
	 * 国际标准
	 * 一周是从 周日开始计算
	 */
	public static final int WEEK_OF_ISO = 0;
	/**
	 * 中国标准
	 * 一周是重周一开始计算
	 */
	public static final int WEEK_OF_GBK = 1;

	private static int localGMP = DateUtils.WEEK_OF_GBK;

	/**
	 * Description:获得当前时间YYYYMMDDHHmmss的格式字符串
	 * @return String 当前时间 格式为YYYYMMDDHHmmss的字符串
	 * @since v1.0.0
	 */
	public static String getNowDateYYYYMMDDHHmmss() {
		return getNowDate("yyyyMMddHHmmss");
	}

	/**
	 * Description: 获得当前时间。时间格式根据format进行格式化
	 * @param format 转换格式
	 * @return  String 格式化时间
	 * @since v1.0.0
	 */
	public static String getNowDate(String format) {
		Date now = new Date(System.currentTimeMillis());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(now).toString();
	}
	
	public static Date getNowDate()
	{
		return new Date();
	}

	private static Calendar getCalendar(String date, String format) {
		Calendar calendar = null;

		try {
			if ((date != null) && (format != null)) {
				SimpleDateFormat nowDate = new SimpleDateFormat(format);
				Date d = nowDate.parse(date, new ParsePosition(0));
				if (d != null) {
					calendar = Calendar.getInstance();
					calendar.clear();
					calendar.setTime(d);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("获取calendar错误", e);
		}

		return calendar;
	}

	/**
	 * Description: 计算当前日期是本年的第几周 
	 * @param strdate 计算日期
	 * @param fmt 日期格式
	 * @return int  strdate说在年份中有几周，如果strdate的格式为yyyy则默认为当年的1月1日
	 * @since v1.0.0
	 */
	public static int getWeekOfYear(String strdate, String format) {
		int weekNumber = -1;

		try {
			if ((strdate != null) && (format != null)) {
				Calendar calendar = getCalendar(strdate, format);
				if (calendar != null) {
					weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
					if (localGMP == DateUtils.WEEK_OF_GBK && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
						weekNumber -= 1;
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("计算错误", e);
		}

		return weekNumber;
	}

	/**
	 * Description: 计算给定日期所在周的全部日期
	 * @param strdate 指定日期
	 * @param oldfmt  自定日期格式
	 * @param newfmt  输出格式
	 * @return String[] 结果数组形式输出所在周的日期。指定日期的格式为yyyy或yyyyMM将计算出当年第一周或当月第一周的日期
	 * @since v1.0.0
	 */
	public String[] getWeekDay(String strdate, String oldfmt, String newfmt) {
		String[] weekday = new String[7];

		try {
			if ((strdate != null) && (oldfmt != null) && (newfmt != null)) {
				Calendar calendar = getCalendar(strdate, oldfmt);
				if (calendar != null) {
					int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
					dayOfWeek = -dayOfWeek + 1 + localGMP;
					if (dayOfWeek > 0) {
						dayOfWeek = -6;
					}
					calendar.add(Calendar.DATE, dayOfWeek);
					SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
					weekday[0] = sdf.format(calendar.getTime());
					for (int i = 1; i < 7; i++) {
						calendar.add(5, 1);
						weekday[i] = sdf.format(calendar.getTime());
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException("计算错误", e);
		}

		return weekday;
	}

	/**
	 * Description: 计算给定周内的全部日期
	 * @param year  年
	 * @param week 给定第几周
	 * @param newfmt 返回值格式
	 * @return String[]  结果数组形式输出所在周的日期。
	 * @since v1.0.0
	 */
	public String[] getWeekDate(String year, int week, String newfmt) {
		String[] jweekday = new String[7];
		try {
			if ((year != null) && (year.length() == 4) && (week > 0) && (newfmt != null)) {
				Calendar calendar = getCalendar(year + "0101", "yyyyMMdd");
				if (calendar != null) {
					week--;
					calendar.add(5, week * 7 - calendar.get(7) + 2);
					SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
					jweekday[0] = sdf.format(calendar.getTime());
					for (int i = 1; i < 7; i++) {
						calendar.add(5, 1);
						jweekday[i] = sdf.format(calendar.getTime());
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException("计算错误", e);
		}
		return jweekday;
	}

	/**
	 * Description: 计算指定日期是星期几
	 * @param strdate  指定日期
	 * @param oldfmt  指定日期格式
	 * @param fmt  输出格式
	 * @return String 返回结果为fmt+结果。
	 * @since v1.0.0
	 */
	public String getDayOfWeek(String strdate, String oldfmt, String fmt) {
		String sWeek = null;
		try {
			if ((strdate != null) && (oldfmt != null) && (fmt != null)) {
				Calendar cal = getCalendar(strdate, oldfmt);
				if (cal != null) {
					int iWeek = cal.get(7);
					sWeek = fmt + (iWeek - 1 == 0 ? 7 : iWeek - 1);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("计算错误", e);
		}
		return sWeek;
	}

	/**
	 * Description: 计算指定年共有多少周
	 * @param year 指定年 格式yyyy
	 * @return int 周数
	 * @since v1.0.0
	 */
	public int getWeekNum(String year) {
		int weeknum = -1;
		try {
			if (year != null) {
				Calendar cal = getCalendar(year + "1231", "yyyyMMdd");
				if (cal != null) {
					if (cal.get(3) == 1)
						cal.add(5, -7);
					weeknum = cal.get(3);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("计算错误", e);
		}
		return weeknum;
	}

	/**
	 * Description: 计算两个给定的时间之差
	 * @param startdate 开始日期
	 * @param enddate 结束日期
	 * @param fmt  日期格式
	 * @param refmt   返回值格式 ms毫秒 s秒 m分 h小时 d天
	 * @return String 返回值
	 * @since v1.0.0
	 */
	public String cntTimeDifference(String startdate, String enddate, String fmt, String refmt) {
		String ret = null;
		try {
			if ((startdate != null) && (enddate != null) && (fmt != null) && (refmt != null)) {
				Date scal = getCalendar(startdate, fmt).getTime();
				Date ecal = getCalendar(enddate, fmt).getTime();
				if ((scal == null) || (ecal == null)) {
					return null;
				} else {
					long diffMillis = ecal.getTime() - scal.getTime();
					long diffSecs = diffMillis / 1000L;
					long diffMins = diffMillis / 60000L;
					long diffHours = diffMillis / 3600000L;
					long diffDays = diffMillis / 86400000L;

					if (refmt.equals("ms")) {
						ret = Long.toString(diffMillis);
					} else if (refmt.equals("s")) {
						ret = Long.toString(diffSecs);
					} else if (refmt.equals("m")) {
						ret = Long.toString(diffMins);
					} else if (refmt.equals("h")) {
						ret = Long.toString(diffHours);
					} else if (refmt.equals("d")) {
						ret = Long.toString(diffDays);
					} else {
						ret = Long.toString(diffHours);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("计算错误", e);
		}
		return ret;
	}

	/**
	 * Description: 计算指定日期经过多少分钟后的日期
	 * @param deftime  自定日期
	 * @param oldfmt  日期格式
	 * @param timediff  分钟为单位
	 * @param newfmt 返回值的格式
	 * @return String timediff >0向前计算，timediff<0向后计算
	 * @since v1.0.0
	 */
	public String getBeforeTime(String deftime, String oldfmt, int timediff, String newfmt) {
		String rq = null;
		try {
			if ((deftime != null) && (!deftime.equals(""))) {
				Calendar cal = getCalendar(deftime, oldfmt);
				if (cal != null) {
					cal.add(Calendar.MINUTE, -timediff);
					SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
					rq = sdf.format(cal.getTime());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("计算错误", e);
		}
		return rq;
	}

	/**
	 * 计算指定日期经过多少小时后的日期
	 *
	 * @param deftime
	 *            自定日期
	 * @param oldfmt
	 *            日期格式
	 * @param timediff
	 *            小时为单位
	 * @param newfmt
	 *            返回值的格式
	 * @return 返回日期，timediff >0向前计算，timediff<0向后计算
	 */
	public String getBeforeTimeByH(String deftime, String oldfmt, int timediff, String newfmt) {
		String rq = null;
		try {
			if ((deftime != null) && (!deftime.equals(""))) {
				Calendar cal = getCalendar(deftime, oldfmt);
				if (cal != null) {
					cal.add(12, -timediff * 60);
					SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
					rq = sdf.format(cal.getTime());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("计算错误", e);
		}
		return rq;
	}

	/**
	 * 计算指定日期经过多少月后的日期
	 *
	 * @param deftime
	 *            自定日期
	 * @param oldfmt
	 *            日期格式
	 * @param timediff
	 *            月为单位
	 * @param newfmt
	 *            返回值的格式
	 * @return 返回日期，timediff >0向前计算，timediff<0向后计算
	 */
	public String getBeforeTimeByM(String deftime, String oldfmt, int timediff, String newfmt) {
		String rq = null;
		try {
			if ((deftime != null) && (!deftime.equals(""))) {
				Calendar cal = getCalendar(deftime, oldfmt);
				if (cal != null) {
					cal.add(2, -timediff);
					SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
					rq = sdf.format(cal.getTime());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rq;

	}

	/**
	 * 日期格式化
	 *
	 * @param mydate
	 *            日期
	 * @param oldfmt
	 *            旧格式
	 * @param newfmt
	 *            新格式
	 * @return 返回值的格式根据newfmt根式返回
	 */
	public String fmtDate(String mydate, String oldfmt, String newfmt) {
		String restr = null;
		try {
			if ((mydate != null) && (oldfmt != null) && (newfmt != null)) {
				SimpleDateFormat newDate = new SimpleDateFormat(newfmt);
				Calendar cal = getCalendar(mydate, oldfmt);
				if (cal != null) {
					restr = newDate.format(cal.getTime());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return restr;
	}

	/**
	 * 将文字行的date格式成Date类型
	 * @param mydate
	 * @param fmt
	 * @return
	 */
	public Date fmtDate(String myDate, String fmt) {
		Date newDate = null;
		if ((myDate != null) && (fmt != null)) {
			Calendar cal = getCalendar(myDate, fmt);
			newDate = cal.getTime();
		}

		return newDate;
	}

	public String fmtDate(Date myDate, String fmt) {
		String newDate = "";
		if (myDate != null) {
			SimpleDateFormat sDateformat = new SimpleDateFormat(fmt);
			newDate = sDateformat.format(myDate).toString();
		}
		return newDate;
	}

	public String fmtDate(Long myDate, String fmt) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(myDate);
		DateFormat formatter = new SimpleDateFormat(fmt);
		return formatter.format(calendar.getTime());
	}

	/**
	 * 计算某年某月有多少天 
	 * @param year
	 * @return
	 */
	public int getMonthDays(int year, int month) {
		if (month > 12 || month < 1) {
			throw new RuntimeException("month is error " + month);
		}
		String bigmonth = "1,3,5,7,8,10,12";
		String smallmonth = "4,6,9,11";
		if (bigmonth.indexOf(month + "") > -1) {
			return 31;
		}
		if (smallmonth.indexOf(month + "") > -1) {
			return 30;
		}
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			return 29;
		} else {
			return 28;
		}
	}

	/**  
	 * 计算两个日期之间相差的天数  
	 * @param smdate 较小的时间 
	 * @param bdate  较大的时间 
	 * @return 相差天数 
	 * @throws ParseException  
	 */
	public int daysBetween(Date smdate, Date bdate) {
		long between_days = 0l;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
		} catch (ParseException e) {
			throw new RuntimeException("日期计算异常", e);
		}

		return Integer.parseInt(String.valueOf(between_days));
	}

	/** 
	*字符串的日期格式的计算 
	 * @throws ParseException 
	*/
	public int daysBetween(String smdate, String bdate) {
		long between_days = 0l;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 得到某年某月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 得到某年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public String getLastDayOfMonth(int year, int month) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 计算指定日期经过多少天后的日期
	 *
	 * @param deftime
	 *            自定日期，当前传入时间
	 * @param oldfmt
	 *            日期格式，当前时间格式
	 * @param timediff
	 *            天为单位
	 * @param newfmt
	 *            返回值的格式
	 * @return 返回日期，timediff >0向前计算，timediff<0向后计算
	 */
	public String getBeforeTimeByD(String deftime, String oldfmt, int timediff, String newfmt) {
		String rq = null;
		try {
			if ((deftime != null) && (!deftime.equals(""))) {
				Calendar cal = getCalendar(deftime, oldfmt);
				if (cal != null) {
					cal.add(Calendar.DATE, -timediff);
					SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
					rq = sdf.format(cal.getTime());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rq;
	}

	/***
	 * *
	 * 功能描述：        计算月份相差几月 
	 *                                                       
	 * @return                                                                                                 
	 * @author chenQiX [chenqixiang@99114.com]
	 * @since 2016年12月22日
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 *
	 */
	public int getDiffMonth(String date1, String date2, String fmt) {
		int months = 0;// 相差月份
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);

			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();

			c1.setTime(sdf.parse(date1));
			c2.setTime(sdf.parse(date2));

			int y1 = c1.get(Calendar.YEAR);
			int y2 = c2.get(Calendar.YEAR);
			months = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH) + (y2 - y1) * 12;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return months;
	}

	/**
	 * *
	 * 功能描述：  获取周的开始时间       
	 *                                                       
	 * @param datetime
	 * @param fmt
	 * @param newfmt
	 * @return                                                                                                 
	 * @author chenQiX [chenqixiang@99114.com]
	 * @since 2017年1月4日
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 *
	 */
	public String getWeekStartTime(String datetime, String fmt, String newfmt) {
		int d = 0;
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			SimpleDateFormat newsdf = new SimpleDateFormat(newfmt);
			cal.setTime(sdf.parse(datetime));
			if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
				d = -6;
			} else {
				d = 2 - cal.get(Calendar.DAY_OF_WEEK);
			}
			cal.add(Calendar.DAY_OF_WEEK, d);
			return newsdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/***
	 * *
	 * 功能描述：  获取周的结束时间       
	 *                                                       
	 * @param datetime
	 * @param fmt
	 * @param newfmt
	 * @return                                                                                                 
	 * @author chenQiX [chenqixiang@99114.com]
	 * @since 2017年1月4日
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 *
	 */
	public String getWeekEndTime(String datetime, String fmt, String newfmt) {
		int d = 0;
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			SimpleDateFormat newsdf = new SimpleDateFormat(newfmt);
			cal.setTime(sdf.parse(datetime));
			if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
				d = -6;
			} else {
				d = 2 - cal.get(Calendar.DAY_OF_WEEK);
			}
			cal.add(Calendar.DAY_OF_WEEK, d);
			cal.add(Calendar.DAY_OF_WEEK, 6);
			return newsdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/***
	 * *
	 * 功能描述：   相差几周    
	 *                                                       
	 * @param d1
	 * @param d2
	 * @param fmt
	 * @return                                                                                                 
	 * @author chenQiX [chenqixiang@99114.com]
	 * @since 2017年1月4日
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 *
	 */
	public int getDiffWeekNum(String d1, String d2, String fmt) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(fmt);
			Date date1 = df.parse(d1);
			Date date2 = df.parse(d2);
			Long weeks = (date2.getTime() - date1.getTime()) / (7 * 24 * 60 * 60 * 1000);
			return weeks.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
