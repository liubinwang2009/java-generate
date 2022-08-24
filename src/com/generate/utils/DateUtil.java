package com.generate.utils;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
    static SimpleDateFormat sdfShort = new SimpleDateFormat("yyyyMMdd");
    static SimpleDateFormat sdfLong = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat sdfLongCn = new SimpleDateFormat("yyyy年MM月dd日");
    static SimpleDateFormat sdfShortU = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
    static SimpleDateFormat sdfLongU = new SimpleDateFormat("MMM dd,yyyy", Locale.ENGLISH);
    static SimpleDateFormat sdfLongTime = new SimpleDateFormat("yyyyMMddHHmmss");
    static SimpleDateFormat sdfLongTimePlus = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat sdfShortLongTimePlusCn = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    static SimpleDateFormat sdfLongTimePlusMill = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
    static SimpleDateFormat sdfMd = new SimpleDateFormat("MM月dd日");
    private static long DAY_IN_MILLISECOND = 86400000L;

    private static DateUtil instance = null;

    private static final Locale local = Locale.ENGLISH;
    public static final long millisInDay = 86400000L;
    private static SimpleDateFormat[] mDateFormats = loadDateFormats();

    private static final SimpleDateFormat mFormat8chars = new SimpleDateFormat("yyyyMMdd");

    private static final SimpleDateFormat mFormatIso8601Day = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat mFormatIso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    private static final SimpleDateFormat mFormatRfc822 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");

    private static final SimpleDateFormat mFormatTradeEasy = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    private static final SimpleDateFormat mFormatTradeEasyMMddyyyy = new SimpleDateFormat("MM/dd/yyyy");

    private static final SimpleDateFormat mFormatTradeEasyProduct = new SimpleDateFormat("dd/MM/yyyy");

    private static final SimpleDateFormat mFormatExpire = new SimpleDateFormat("MMMM dd, yyyy", local);

    public static String getDateLong(java.util.Date date) {
        try {
            if (date != null)
            return sdfLong.format(date);
        } catch (Exception e) {
            System.out.println("Error at getDate:" + e.getMessage());
        }
        return "";
    }

    public static String getDateLongCn(java.util.Date date) {
        
        try {
            if (date != null)
            return sdfLongCn.format(date);
        } catch (Exception e) {
            System.out.println("Error at getDate:" + e.getMessage());
        }
        return "";
    }

    public static String getDateMD(java.util.Date date) {
        
        try {
            if (date != null)
            return sdfMd.format(date);
        } catch (Exception e) {
            System.out.println("Error at getDate:" + e.getMessage());
        }
        return "";
    }

    public static String getDateShortLongTimeCn(java.util.Date date) {
        
        try {
            if (date != null)
            return sdfShortLongTimePlusCn.format(date);
        } catch (Exception e) {
            System.out.println("Error at getDate:" + e.getMessage());
        }
        return "";
    }

    public static String getDateUS(java.util.Date date) {
        
        try {
            if (date != null)
            return sdfLongU.format(date);
        } catch (Exception e) {
            System.out.println("Error at getDate:" + e.getMessage());
        }
        return "";
    }

    public static String getDateUSShort(java.util.Date date) {
        
        try {
            if (date != null)
            return sdfShortU.format(date);
        } catch (Exception e) {
            System.out.println("Error at getDate:" + e.getMessage());
        }
        return "";
    }

    public static String getFomartDate(java.util.Date date, String format) {
        try {
            return new SimpleDateFormat(format, Locale.UK).format(date);
        } catch (Exception e) {
            e.printStackTrace();
            if (date == null)
                return new java.util.Date().toString();
        }
        return date.toString();
    }

    public static String getNowLongTime() throws Exception {
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(new java.util.Date().getTime());
            return sdfLongTime.format(date);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getNowShortDate() throws Exception {
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(new java.util.Date().getTime());
            return sdfShort.format(date);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getNowFormateDate() throws Exception {
        
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(new java.util.Date().getTime());
            return sdfLong.format(date);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getNowPlusTime() throws Exception {
        
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(new java.util.Date().getTime());
            return sdfLongTimePlus.format(date);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getPlusTime(java.util.Date date) throws Exception {
        if (date == null)
            return null;
        try {
            return sdfLongTimePlus.format(date);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getPlusTime2(java.util.Date date) {
        if (date == null)
            return null;
        try {
            return sdfLongTimePlus.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getNowPlusTimeMill() throws Exception {
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(new java.util.Date().getTime());
            return sdfLongTimePlusMill.format(date);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getNowYear() throws Exception {
        try {
            String strTemp = getNowLongTime();
            return strTemp.substring(0, 4);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getNowMonth() throws Exception {
        try {
            String strTemp = getNowLongTime();
            return strTemp.substring(4, 6);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getNowDay() throws Exception {
        try {
            String strTemp = getNowLongTime();
            return strTemp.substring(6, 8);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getNowHour() throws Exception {
        try {
            String strTemp = getNowPlusTimeMill();
            return strTemp.substring(8, 10);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getTimeBySecond(String _second) throws Exception {
        long longHour = 0L;
        long longMinu = 0L;
        long longSec = 0L;
        try {
            longSec = Long.parseLong(_second);
            if (longSec == 0L) {
                return "0时0分0秒";
            }

            longHour = longSec / 3600L;
            longSec %= 3600L;
            longMinu = longSec / 60L;
            longSec %= 60L;
            return longHour + "时" + longMinu + "分" + longSec + "秒";
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getTimeBySecond(long ms_second) throws Exception {
        long longHour = 0L;
        long longMinu = 0L;
        long longSec = 0L;
        long longMs = ms_second;
        try {
            if (longMs == 0L) {
                return "0时0分0秒0毫秒";
            }

            longHour = longMs / 3600000L;
            longMs %= 3600000L;
            longMinu = longMs / 60000L;
            longMs %= 60000L;
            longSec = longMs / 1000L;
            longMs %= 1000L;
            return longHour + "时" + longMinu + "分" + longSec + "秒" + longMs + "毫秒";
        } catch (Exception e) {
            throw e;
        }
    }

    public static int convertDateToYear(java.util.Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy", new DateFormatSymbols());
        return Integer.parseInt(df.format(date));
    }

    public static String convertDateToYearMonth(java.util.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM", new DateFormatSymbols());
        return df.format(d);
    }

    public static String convertDateToYearMonthDay(java.util.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", new DateFormatSymbols());
        return df.format(d);
    }

    public static String convertDateToMonth(java.util.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("MM", new DateFormatSymbols());
        return df.format(d);
    }

    public static String convertDateToDay(java.util.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd", new DateFormatSymbols());
        return df.format(d);
    }

    public static String convertDateToHour(java.util.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("HH", new DateFormatSymbols());
        return df.format(d);
    }

    public static String convertDateToMinute(java.util.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("mm", new DateFormatSymbols());
        return df.format(d);
    }

    public static java.util.Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();

        java.util.Date d = cal.getTime();

        return d;
    }

    public static String getCurrentYearMonth() {
        Calendar cal = Calendar.getInstance();
        String currentYear = new Integer(cal.get(1)).toString();
        String currentMonth = null;
        if (cal.get(2) < 9)
            currentMonth = "0" + new Integer(cal.get(2) + 1).toString();
        else
            currentMonth = new Integer(cal.get(2) + 1).toString();
        return currentYear + currentMonth;
    }

    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(1);
        return currentYear;
    }

    public static String formatDateTime(String _dateTime, String _format) throws Exception {
        String returnValue = "";
        String formatString = _format.toUpperCase();
        String strYear = "";
        String strMonth = "";
        String strDay = "";
        String strHour = "";
        String strMinu = "";
        String strSec = "";
        int hourType = 12;
        int yearType = 1;
        try {
            if (formatString.indexOf("YYYY") >= 0) {
                int tempBeginPlace = formatString.indexOf("YYYY");
                int temEndPlace = tempBeginPlace + 4;
                strYear = _dateTime.substring(tempBeginPlace, temEndPlace);
            }
            if (formatString.indexOf("MM") >= 0) {
                int tempBeginPlace = formatString.indexOf("MM");
                int temEndPlace = tempBeginPlace + 2;
                strMonth = _dateTime.substring(tempBeginPlace, temEndPlace);
            }
            if (formatString.indexOf("DD") >= 0) {
                int tempBeginPlace = formatString.indexOf("DD");
                int temEndPlace = tempBeginPlace + 2;
                strDay = _dateTime.substring(tempBeginPlace, temEndPlace);
            }
            if (formatString.indexOf("HH24") >= 0) {
                int tempBeginPlace = formatString.indexOf("HH24");
                int temEndPlace = tempBeginPlace + 2;
                strHour = _dateTime.substring(tempBeginPlace, temEndPlace);
                formatString = formatString.replaceAll("24", "");

                hourType = 24;
            } else if (formatString.indexOf("HH12") >= 0) {
                int tempBeginPlace = formatString.indexOf("HH12");
                int temEndPlace = tempBeginPlace + 2;
                strHour = _dateTime.substring(tempBeginPlace, temEndPlace);
                formatString = formatString.replaceAll("12", "");

                hourType = 12;
            } else if (formatString.indexOf("HH") >= 0) {
                int tempBeginPlace = formatString.indexOf("HH");
                int temEndPlace = tempBeginPlace + 2;
                strHour = _dateTime.substring(tempBeginPlace, temEndPlace);
                hourType = 12;
            }
            if (formatString.indexOf("MI") >= 0) {
                int tempBeginPlace = formatString.indexOf("MI");
                int temEndPlace = tempBeginPlace + 2;
                strMinu = _dateTime.substring(tempBeginPlace, temEndPlace);
            }
            if (formatString.indexOf("SS") >= 0) {
                int tempBeginPlace = formatString.indexOf("SS");
                int temEndPlace = tempBeginPlace + 2;
                strSec = _dateTime.substring(tempBeginPlace, temEndPlace);
            }

            if (!strYear.equals("")) {
                int intYear = Integer.parseInt(strYear);

                if (intYear % 4 == 0) {
                    if (intYear % 100 != 0) {
                        yearType = 2;
                    }
                }
                if (intYear % 4 == 0) {
                    if (intYear % 400 == 0) {
                        yearType = 2;
                    }
                }
            }

            if (!strMonth.equals("")) {
                int intMonth = Integer.parseInt(strMonth);
                if (intMonth == 0) {
                    strMonth = "01";
                    intMonth = 1;
                }
                if (intMonth > 12) {
                    strMonth = "12";
                    intMonth = 12;
                }

            }

            if (!strDay.equals("")) {
                int intDay = Integer.parseInt(strDay);
                if (intDay == 0) {
                    strDay = "01";
                    intDay = 1;
                }
                if (intDay > 31) {
                    strDay = "31";
                    intDay = 31;
                }
                if ((strMonth.equals("01")) || (strMonth.equals("03")) || (strMonth.equals("05"))
                        || (strMonth.equals("07")) || (strMonth.equals("08")) || (strMonth.equals("10"))
                        || (strMonth.equals("12"))) {
                    if (intDay > 31) {
                        strDay = "31";
                        intDay = 31;
                    }
                }
                if ((strMonth.equals("02")) || (strMonth.equals("04")) || (strMonth.equals("06"))
                        || (strMonth.equals("09")) || (strMonth.equals("11"))) {
                    if (intDay > 30) {
                        strDay = "30";
                        intDay = 30;
                    }
                    if (strMonth.equals("02")) {
                        if (yearType == 2) {
                            if (intDay > 29) {
                                strDay = "29";
                                intDay = 29;
                            }

                        } else if (intDay > 28) {
                            strDay = "28";
                            intDay = 28;
                        }

                    }

                }

                if (!strHour.equals("")) {
                    int intHour = Integer.parseInt(strHour);
                    if (intHour > 24) {
                        strHour = "24";
                        intHour = 24;
                    }
                    if (hourType == 12) {
                        if (intHour == 0) {
                            intHour = 1;
                            strHour = "01";
                        }
                        if (intHour > 12) {
                            intHour -= 12;
                            strHour = "0" + intHour;
                        }

                    } else if (intHour > 23) {
                        intHour = 23;
                        strHour = "23";
                    }

                }

                if (!strMinu.equals("")) {
                    int intMinu = Integer.parseInt(strMinu);
                    if (intMinu > 59) {
                        strMinu = "59";
                        intMinu = 59;
                    }
                }

                if (!strSec.equals("")) {
                    int intSec = Integer.parseInt(strSec);
                    if (intSec > 59) {
                        strSec = "59";
                        intSec = 59;
                    }
                }
            }
            return strYear + strMonth + strDay + strHour + strMinu + strSec;
        } catch (Exception e) {
            throw e;
        }
    }

    public static java.util.Date stringToDate(String strDate, String oracleFormat) {
        if (strDate == null)
            return null;
        Hashtable h = new Hashtable();
        String javaFormat = new String();
        String s = oracleFormat.toLowerCase();
        if (s.indexOf("yyyy") != -1)
            h.put(new Integer(s.indexOf("yyyy")), "yyyy");
        else if (s.indexOf("yy") != -1)
            h.put(new Integer(s.indexOf("yy")), "yy");
        if (s.indexOf("mm") != -1) {
            h.put(new Integer(s.indexOf("mm")), "MM");
        }
        if (s.indexOf("dd") != -1)
            h.put(new Integer(s.indexOf("dd")), "dd");
        if (s.indexOf("hh24") != -1)
            h.put(new Integer(s.indexOf("hh24")), "HH");
        if (s.indexOf("mi") != -1)
            h.put(new Integer(s.indexOf("mi")), "mm");
        if (s.indexOf("ss") != -1) {
            h.put(new Integer(s.indexOf("ss")), "ss");
        }
        int intStart = 0;
        while (s.indexOf("-", intStart) != -1) {
            intStart = s.indexOf("-", intStart);
            h.put(new Integer(intStart), "-");
            intStart++;
        }

        intStart = 0;
        while (s.indexOf("/", intStart) != -1) {
            intStart = s.indexOf("/", intStart);
            h.put(new Integer(intStart), "/");
            intStart++;
        }

        intStart = 0;
        while (s.indexOf(" ", intStart) != -1) {
            intStart = s.indexOf(" ", intStart);
            h.put(new Integer(intStart), " ");
            intStart++;
        }

        intStart = 0;
        while (s.indexOf(":", intStart) != -1) {
            intStart = s.indexOf(":", intStart);
            h.put(new Integer(intStart), ":");
            intStart++;
        }

        if (s.indexOf("年") != -1)
            h.put(new Integer(s.indexOf("年")), "年");
        if (s.indexOf("月") != -1)
            h.put(new Integer(s.indexOf("月")), "月");
        if (s.indexOf("日") != -1)
            h.put(new Integer(s.indexOf("日")), "日");
        if (s.indexOf("时") != -1)
            h.put(new Integer(s.indexOf("时")), "时");
        if (s.indexOf("分") != -1)
            h.put(new Integer(s.indexOf("分")), "分");
        if (s.indexOf("秒") != -1) {
            h.put(new Integer(s.indexOf("秒")), "秒");
        }
        int i = 0;
        while (h.size() != 0) {
            Enumeration e = h.keys();
            int n = 0;
            while (e.hasMoreElements()) {
                i = ((Integer) e.nextElement()).intValue();
                if (i >= n)
                    n = i;
            }
            String temp = (String) h.get(new Integer(n));
            h.remove(new Integer(n));

            javaFormat = temp + javaFormat;
        }
        SimpleDateFormat df = new SimpleDateFormat(javaFormat);

        java.util.Date myDate = new java.util.Date();
        try {
            myDate = df.parse(strDate);
        } catch (Exception localException) {
            return null;
        }

        return myDate;
    }

    public static String dateToString(java.util.Date d, String format) {
        if (d == null)
            return "";
        Hashtable h = new Hashtable();
        String javaFormat = new String();
        String s = format.toLowerCase();
        if (s.indexOf("yyyy") != -1)
            h.put(new Integer(s.indexOf("yyyy")), "yyyy");
        else if (s.indexOf("yy") != -1)
            h.put(new Integer(s.indexOf("yy")), "yy");
        if (s.indexOf("mm") != -1) {
            h.put(new Integer(s.indexOf("mm")), "MM");
        }
        if (s.indexOf("dd") != -1)
            h.put(new Integer(s.indexOf("dd")), "dd");
        if (s.indexOf("hh24") != -1)
            h.put(new Integer(s.indexOf("hh24")), "HH");
        if (s.indexOf("mi") != -1)
            h.put(new Integer(s.indexOf("mi")), "mm");
        if (s.indexOf("ss") != -1) {
            h.put(new Integer(s.indexOf("ss")), "ss");
        }
        int intStart = 0;
        while (s.indexOf("-", intStart) != -1) {
            intStart = s.indexOf("-", intStart);
            h.put(new Integer(intStart), "-");
            intStart++;
        }

        intStart = 0;
        while (s.indexOf("/", intStart) != -1) {
            intStart = s.indexOf("/", intStart);
            h.put(new Integer(intStart), "/");
            intStart++;
        }

        intStart = 0;
        while (s.indexOf(" ", intStart) != -1) {
            intStart = s.indexOf(" ", intStart);
            h.put(new Integer(intStart), " ");
            intStart++;
        }

        intStart = 0;
        while (s.indexOf(":", intStart) != -1) {
            intStart = s.indexOf(":", intStart);
            h.put(new Integer(intStart), ":");
            intStart++;
        }

        if (s.indexOf("年") != -1)
            h.put(new Integer(s.indexOf("年")), "年");
        if (s.indexOf("月") != -1)
            h.put(new Integer(s.indexOf("月")), "月");
        if (s.indexOf("日") != -1)
            h.put(new Integer(s.indexOf("日")), "日");
        if (s.indexOf("时") != -1)
            h.put(new Integer(s.indexOf("时")), "时");
        if (s.indexOf("分") != -1)
            h.put(new Integer(s.indexOf("分")), "分");
        if (s.indexOf("秒") != -1) {
            h.put(new Integer(s.indexOf("秒")), "秒");
        }
        int i = 0;
        while (h.size() != 0) {
            Enumeration e = h.keys();
            int n = 0;
            while (e.hasMoreElements()) {
                i = ((Integer) e.nextElement()).intValue();
                if (i >= n)
                    n = i;
            }
            String temp = (String) h.get(new Integer(n));
            h.remove(new Integer(n));

            javaFormat = temp + javaFormat;
        }
        SimpleDateFormat df = new SimpleDateFormat(javaFormat, new DateFormatSymbols());

        return df.format(d);
    }

    public static String getDate(java.util.Date d, String format) {
        if (d == null)
            return "";
        Hashtable h = new Hashtable();
        String javaFormat = new String();
        String s = format.toLowerCase();
        if (s.indexOf("yyyy") != -1)
            h.put(new Integer(s.indexOf("yyyy")), "yyyy");
        else if (s.indexOf("yy") != -1)
            h.put(new Integer(s.indexOf("yy")), "yy");
        if (s.indexOf("mm") != -1) {
            h.put(new Integer(s.indexOf("mm")), "MM");
        }
        if (s.indexOf("dd") != -1)
            h.put(new Integer(s.indexOf("dd")), "dd");
        if (s.indexOf("hh24") != -1)
            h.put(new Integer(s.indexOf("hh24")), "HH");
        if (s.indexOf("mi") != -1)
            h.put(new Integer(s.indexOf("mi")), "mm");
        if (s.indexOf("ss") != -1) {
            h.put(new Integer(s.indexOf("ss")), "ss");
        }
        int intStart = 0;
        while (s.indexOf("-", intStart) != -1) {
            intStart = s.indexOf("-", intStart);
            h.put(new Integer(intStart), "-");
            intStart++;
        }

        intStart = 0;
        while (s.indexOf("/", intStart) != -1) {
            intStart = s.indexOf("/", intStart);
            h.put(new Integer(intStart), "/");
            intStart++;
        }

        intStart = 0;
        while (s.indexOf(" ", intStart) != -1) {
            intStart = s.indexOf(" ", intStart);
            h.put(new Integer(intStart), " ");
            intStart++;
        }

        intStart = 0;
        while (s.indexOf(":", intStart) != -1) {
            intStart = s.indexOf(":", intStart);
            h.put(new Integer(intStart), ":");
            intStart++;
        }

        if (s.indexOf("年") != -1)
            h.put(new Integer(s.indexOf("年")), "年");
        if (s.indexOf("月") != -1)
            h.put(new Integer(s.indexOf("月")), "月");
        if (s.indexOf("日") != -1)
            h.put(new Integer(s.indexOf("日")), "日");
        if (s.indexOf("时") != -1)
            h.put(new Integer(s.indexOf("时")), "时");
        if (s.indexOf("分") != -1)
            h.put(new Integer(s.indexOf("分")), "分");
        if (s.indexOf("秒") != -1) {
            h.put(new Integer(s.indexOf("秒")), "秒");
        }
        int i = 0;
        while (h.size() != 0) {
            Enumeration e = h.keys();
            int n = 0;
            while (e.hasMoreElements()) {
                i = ((Integer) e.nextElement()).intValue();
                if (i >= n)
                    n = i;
            }
            String temp = (String) h.get(new Integer(n));
            h.remove(new Integer(n));

            javaFormat = temp + javaFormat;
        }
        SimpleDateFormat df = new SimpleDateFormat(javaFormat, new DateFormatSymbols());

        return df.format(d);
    }

    public static int getAge(String id) throws Exception {
        int age = -1;
        int length = id.length();
        String birthday = "";
        if (length == 15) {
            birthday = id.substring(6, 8);
            birthday = "19" + birthday;
        } else if (length == 18) {
            birthday = id.substring(6, 10);
        } else {
            throw new Exception("错误的身份证号");
        }
        int currentYear = Calendar.getInstance().get(1);
        age = currentYear - new Integer(birthday).intValue();
        return age;
    }

    public static java.sql.Date getDateByAge(int age) {
        Calendar calendar = Calendar.getInstance(Locale.CHINESE);
        calendar.getTimeInMillis();
        calendar.set(calendar.get(1) - age, calendar.get(2), calendar.get(5));
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    public static int calBetweenTwoMonth(String dealMonth, String alterMonth) {
        int length = 0;
        if ((dealMonth.length() != 6) || (alterMonth.length() != 6)) {
            length = -1;
        } else {
            int dealInt = Integer.parseInt(dealMonth);
            int alterInt = Integer.parseInt(alterMonth);
            if (dealInt < alterInt) {
                length = -2;
            } else {
                int dealYearInt = Integer.parseInt(dealMonth.substring(0, 4));
                int dealMonthInt = Integer.parseInt(dealMonth.substring(4, 6));
                int alterYearInt = Integer.parseInt(alterMonth.substring(0, 4));
                int alterMonthInt = Integer.parseInt(alterMonth.substring(4, 6));
                length = (dealYearInt - alterYearInt) * 12 + (dealMonthInt - alterMonthInt);
            }
        }

        return length;
    }

    public static int daysBetweenDates(java.util.Date newDate, java.util.Date oldDate) {
        int days = 0;
        Calendar calo = Calendar.getInstance();
        Calendar caln = Calendar.getInstance();
        calo.setTime(oldDate);
        caln.setTime(newDate);
        int oday = calo.get(6);
        int nyear = caln.get(1);
        int oyear = calo.get(1);
        while (nyear > oyear) {
            calo.set(2, 11);
            calo.set(5, 31);
            days += calo.get(6);
            oyear++;
            calo.set(1, oyear);
        }
        int nday = caln.get(6);
        days = days + nday - oday;

        return days;
    }

    public static java.util.Date getDateBetween(java.util.Date date, int intBetween) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(date);
        calo.add(5, intBetween);
        return calo.getTime();
    }

    public static String getDateBetween_String(java.util.Date date, int intBetween, String strFromat) {
        java.util.Date dateOld = getDateBetween(date, intBetween);
        return getDate(dateOld, strFromat);
    }

    public static String increaseYearMonth(String yearMonth) {
        int year = new Integer(yearMonth.substring(0, 4)).intValue();
        int month = new Integer(yearMonth.substring(4, 6)).intValue();
        month++;
        if ((month <= 12) && (month >= 10))
            return yearMonth.substring(0, 4) + new Integer(month).toString();
        if (month < 10) {
            return yearMonth.substring(0, 4) + "0" + new Integer(month).toString();
        }

        return new Integer(year + 1).toString() + "0" + new Integer(month - 12).toString();
    }

    public static String increaseYearMonth(String yearMonth, int addMonth) {
        int year = new Integer(yearMonth.substring(0, 4)).intValue();
        int month = new Integer(yearMonth.substring(4, 6)).intValue();
        month += addMonth;
        year += month / 12;
        month %= 12;
        if ((month <= 12) && (month >= 10)) {
            return year + new Integer(month).toString();
        }
        return year + "0" + new Integer(month).toString();
    }

    public static String descreaseYearMonth(String yearMonth) {
        int year = new Integer(yearMonth.substring(0, 4)).intValue();
        int month = new Integer(yearMonth.substring(4, 6)).intValue();
        month--;
        if (month >= 10)
            return yearMonth.substring(0, 4) + new Integer(month).toString();
        if ((month > 0) && (month < 10)) {
            return yearMonth.substring(0, 4) + "0" + new Integer(month).toString();
        }

        return new Integer(year - 1).toString() + new Integer(month + 12).toString();
    }

    public static boolean yearMonthGreatEqual(String s1, String s2) {
        String temp1 = s1.substring(0, 4);
        String temp2 = s2.substring(0, 4);
        String temp3 = s1.substring(4, 6);
        String temp4 = s2.substring(4, 6);

        if (Integer.parseInt(temp1) > Integer.parseInt(temp2))
            return true;
        if (Integer.parseInt(temp1) == Integer.parseInt(temp2)) {
            if (Integer.parseInt(temp3) >= Integer.parseInt(temp4)) {
                return true;
            }
            return false;
        }

        return false;
    }

    public static boolean yearMonthGreater(String s1, String s2) {
        String temp1 = s1.substring(0, 4);
        String temp2 = s2.substring(0, 4);
        String temp3 = s1.substring(4, 6);
        String temp4 = s2.substring(4, 6);

        if (Integer.parseInt(temp1) > Integer.parseInt(temp2))
            return true;
        if (Integer.parseInt(temp1) == Integer.parseInt(temp2)) {
            if (Integer.parseInt(temp3) > Integer.parseInt(temp4)) {
                return true;
            }
            return false;
        }

        return false;
    }

    public static String getOracleFormatDateStr(java.util.Date date) {
        return getDate(date, "YYYY-MM-DD HH24:MI:SS");
    }

    public static String getLastDay(String term) {
        int getYear = Integer.parseInt(term.substring(0, 4));
        int getMonth = Integer.parseInt(term.substring(4, 6));

        String getLastDay = "";

        if (getMonth == 2) {
            if (((getYear % 4 == 0) && (getYear % 100 != 0)) || (getYear % 400 == 0)) {
                getLastDay = "29";
            } else {
                getLastDay = "28";
            }
        } else if ((getMonth == 4) || (getMonth == 6) || (getMonth == 9) || (getMonth == 11)) {
            getLastDay = "30";
        } else {
            getLastDay = "31";
        }
        return String.valueOf(getYear) + "年" + String.valueOf(getMonth) + "月" + getLastDay + "日";
    }

    public static String getMonthBetween(String strDateBegin, String strDateEnd) {
        try {
            String strOut = "";
            int intMonthBegin = 0;
            int intMonthEnd = 0;
            if ((strDateBegin.equals("")) || (strDateEnd.equals("")) || (strDateBegin.length() != 6)
                    || (strDateEnd.length() != 6)) {
                strOut = "";
            } else {
                intMonthBegin = Integer.parseInt(strDateBegin.substring(0, 4)) * 12
                        + Integer.parseInt(strDateBegin.substring(4, 6));
                intMonthEnd = Integer.parseInt(strDateEnd.substring(0, 4)) * 12
                        + Integer.parseInt(strDateEnd.substring(4, 6));
            }
            return String.valueOf(intMonthBegin - intMonthEnd);
        } catch (Exception localException) {
        }

        return "0";
    }

    public static String getStrHaveAcross(String strDate) {
        try {
            return strDate.substring(0, 4) + "-" + strDate.substring(4, 6) + "-" + strDate.substring(6, 8);
        } catch (Exception localException) {
        }
        return strDate;
    }

    public static String getFirstDayOfNextMonth() {
        try {
            return increaseYearMonth(getNowShortDate().substring(0, 6)) + "01";
        } catch (Exception localException) {
        }
        return "";
    }

    public static String getFirstDayOfThisMonth() {
        try {
            return getNowShortDate().substring(0, 6) + "01";
        } catch (Exception localException) {
        }
        return "";
    }

    public static String getYearAndMonth(String yearMonth) {
        if (yearMonth == null)
            return "";
        String ym = yearMonth.trim();
        if (6 != ym.length())
            return ym;
        String year = ym.substring(0, 4);
        String month = ym.substring(4);
        return year + "年" + month + "月";
    }

    public static String month2YearMonth(String month) {
        String yearMonth = "";
        int smonth = 0;
        int year = 0;
        int rmonth = 0;

        if ((month == null) || ("0".equals(month)) || ("".equals(month.trim()))) {
            return "0月";
        }

        smonth = Integer.parseInt(month);
        year = smonth / 12;
        rmonth = smonth % 12;

        if (year > 0) {
            yearMonth = year + "年";
        }
        if (rmonth > 0) {
            yearMonth = yearMonth + rmonth + "个月";
        }

        return yearMonth;
    }

    public static String getYearMonthByMonth(String month) {
        if (month == null)
            return null;
        String ym = month.trim();
        if (6 != ym.length())
            return ym;
        String year = ym.substring(0, 4);
        ym.substring(4);
        return year + "年" + month + "月";
    }

    public static java.util.Date increaseMonth(java.util.Date date, int intBetween) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(date);
        calo.add(2, intBetween);
        return calo.getTime();
    }

    public static java.util.Date increaseDay(java.util.Date date, int intBetween) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(date);
        calo.add(5, intBetween);
        return calo.getTime();
    }

    public static java.util.Date increaseYear(java.util.Date date, int intBetween) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(date);
        calo.add(1, intBetween);
        return calo.getTime();
    }

    public static int compareDate(String str1, String str2) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date1 = null;
        java.util.Date date2 = null;
        try {
            date1 = formatter.parse(str1);
            date2 = formatter.parse(str2);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date1.compareTo(date2);
    }

    public static int compareDate(String str1, java.util.Date date2) {
        java.util.Date date1 = getDateByString(str1);
        return date1.compareTo(date2);
    }

    public static int compareDate(String format, String str1, java.util.Date date2) {
        java.util.Date date1 = null;
        try {
            date1 = fromStringToDate(format, str1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1.compareTo(date2);
    }

    public static Timestamp getDateByString(String strDate) {
        if (strDate.trim().equals("")) {
            return new Timestamp(System.currentTimeMillis());
        }
        try {
            strDate = getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss") + ".000000000";
            return Timestamp.valueOf(strDate);
        } catch (Exception localException) {
        }
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp getNextMonyDate(String strDate) {
        try {
            int iYear = StringUtil.getStrToInt(getFormattedDate(strDate, "yyyy"));
            int iMonth = StringUtil.getStrToInt(getFormattedDate(strDate, "MM"));
            if (iMonth == 12) {
                iYear++;
            } else {
                iMonth++;
            }
            String strMonth = Integer.toString(iMonth);
            if (strMonth.length() == 1) {
                strMonth = "0" + strMonth;
            }
            strDate = Integer.toString(iYear) + "/" + strMonth + "/01";
            return getDateByString(strDate);
        } catch (Exception localException) {
        }
        return getDateByString(strDate);
    }

    public static String getCurrDate() {
        return getFormattedDate(getDateByString(""));
    }

    public static String getToday() {
        java.util.Date cDate = new java.util.Date();
        SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return cSimpleDateFormat.format(cDate);
    }

    public static String getYesterday() {
        java.util.Date cDate = new java.util.Date();
        cDate.setTime(cDate.getTime() - 86400000L);
        SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return cSimpleDateFormat.format(cDate);
    }

    public static String getTomorrow() {
        java.util.Date cDate = new java.util.Date();
        cDate.setTime(cDate.getTime() + 86400000L);
        SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return cSimpleDateFormat.format(cDate);
    }

    public static String getDefaultValidDate() {
        return "1900-01-01";
    }

    public static String getDefaultExpireDate() {
        return "2099-12-31";
    }

    public static String getCurrDateTime() {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return formatter.format(date);
    }

    public static String getSpecDate(String strFormat, int iYear, int iMonth, int iDate) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.set(1, rightNow.get(1) + iYear);
        rightNow.set(2, rightNow.get(2) + iMonth);
        rightNow.set(5, rightNow.get(5) + iDate);
        SimpleDateFormat df = new SimpleDateFormat(strFormat);
        return df.format(rightNow.getTime());
    }

    public static String getDefaultFormattedDate(String strDate) {
        return getFormattedDate(strDate, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDefaultFormattedDate(Timestamp dtDate) {
        return getFormattedDate(dtDate, "yyyy-MM-dd HH:mm:ss");
    }

    public static Timestamp getNullBirthDay() {
        return new Timestamp(0L);
    }

    public static String getFormattedDate(String strDate) {
        return getFormattedDate(strDate, "yyyy-MM-dd");
    }

    public static String getFormattedDate(String strDate, String strFormatTo) {
        if ((strDate == null) || (strDate.trim().equals(""))) {
            return "";
        }
        strDate = strDate.replace('/', '-');
        strFormatTo = strFormatTo.replace('/', '-');
        if ((strDate.equals("0000-00-00 00:00:00")) || (strDate.equals("1800-01-01 00:00:00"))) {
            return "";
        }
        String formatStr = strFormatTo;
        if ((strDate == null) || (strDate.trim().equals(""))) {
            return "";
        }
        switch (strDate.trim().length()) {
        case 6:
            if (strDate.substring(0, 1).equals("0")) {
                formatStr = "yyMMdd";
            } else {
                formatStr = "yyyyMM";
            }
            break;
        case 8:
            formatStr = "yyyyMMdd";
            break;
        case 10:
            if (strDate.indexOf("-") == -1) {
                formatStr = "yyyy/MM/dd";
            } else {
                formatStr = "yyyy-MM-dd";
            }
            break;
        case 11:
            if (strDate.getBytes().length == 14) {
                formatStr = "yyyy年MM月dd日";
            } else {
                return "";
            }
        case 14:
            formatStr = "yyyyMMddHHmmss";
            break;
        case 19:
            if (strDate.indexOf("-") == -1) {
                formatStr = "yyyy/MM/dd HH:mm:ss";
            } else {
                formatStr = "yyyy-MM-dd HH:mm:ss";
            }
            break;
        case 21:
            if (strDate.indexOf("-") == -1) {
                formatStr = "yyyy/MM/dd HH:mm:ss.S";
            } else {
                formatStr = "yyyy-MM-dd HH:mm:ss.S";
            }
            break;
        case 7:
        case 9:
        case 12:
        case 13:
        case 15:
        case 16:
        case 17:
        case 18:
        case 20:
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(strDate));
            formatter = new SimpleDateFormat(strFormatTo);
            return formatter.format(calendar.getTime());
        } catch (Exception localException) {
        }
        return "";
    }

    public static String getFormattedDate(Timestamp dtDate) {
        return getFormattedDate(dtDate, "yyyy-MM-dd");
    }

    public static String getFormattedDate(Timestamp dtDate, String strFormatTo) {
        if (dtDate == null) {
            return "";
        }
        if (dtDate.equals(new Timestamp(0L))) {
            return "";
        }
        strFormatTo = strFormatTo.replace('/', '-');
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
            if (Integer.parseInt(formatter.format(dtDate)) < 1900) {
                return "";
            }

            formatter = new SimpleDateFormat(strFormatTo);
            return formatter.format(dtDate);
        } catch (Exception localException) {
        }

        return "";
    }

    public static String getTimeFormat(long lSecond) {
        String szTime = new String();

        if (lSecond <= 0L) {
            szTime = "00:00:00";
        } else {
            long hour = lSecond / 3600L;
            long minute = (lSecond - hour * 3600L) / 60L;
            long second = lSecond - hour * 3600L - minute * 60L;

            if (hour <= 0L) {
                szTime = "00";
            } else if (hour < 10L) {
                szTime = "0" + String.valueOf(hour);
            } else {
                szTime = String.valueOf(hour);
            }
            szTime = szTime + ":";

            if (minute <= 0L) {
                szTime = szTime + "00";
            } else if (minute < 10L) {
                szTime = szTime + "0" + String.valueOf(minute);
            } else {
                szTime = szTime + String.valueOf(minute);
            }
            szTime = szTime + ":";

            if (second <= 0L) {
                szTime = szTime + "00";
            } else if (second < 10L) {
                szTime = szTime + "0" + String.valueOf(second);
            } else {
                szTime = szTime + String.valueOf(second);
            }
        }

        return szTime;
    }

    public static String getFormattedDateUtil(java.util.Date dtDate, String strFormatTo) {
        if (dtDate == null) {
            return "";
        }
        strFormatTo = strFormatTo.replace('/', '-');
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(strFormatTo);
            return formatter.format(dtDate);
        } catch (Exception localException) {
        }
        return "";
    }

    public static int getBetweenDays(String strFromDate, String strToDate) {
        try {
            Calendar clFrom = new GregorianCalendar();
            int iYear = Integer.parseInt(strFromDate.substring(0, 4));
            int iMonth = Integer.parseInt(strFromDate.substring(4, 6));
            int iDay = Integer.parseInt(strFromDate.substring(6, 8));
            clFrom.set(iYear, iMonth, iDay, 0, 0, 0);
            Calendar clTo = new GregorianCalendar();
            iYear = Integer.parseInt(strToDate.substring(0, 4));
            iMonth = Integer.parseInt(strToDate.substring(4, 6));
            iDay = Integer.parseInt(strToDate.substring(6, 8));
            clTo.set(iYear, iMonth, iDay, 0, 0, 0);
            long llTmp = clTo.getTime().getTime() - clFrom.getTime().getTime();
            return new Long(llTmp / 1000L / 3600L / 24L).intValue();
        } catch (Exception localException) {
        }
        return -2147483648;
    }

    public static synchronized DateUtil getInstance() {
        if (instance == null) {
            instance = new DateUtil();
        }
        return instance;
    }

    private static SimpleDateFormat[] loadDateFormats() {
        SimpleDateFormat[] temp = { new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy"),
                new SimpleDateFormat("M/d/yy hh:mm:ss"), new SimpleDateFormat("M/d/yyyy hh:mm:ss"),
                new SimpleDateFormat("M/d/yy hh:mm a"), new SimpleDateFormat("M/d/yyyy hh:mm a"),
                new SimpleDateFormat("M/d/yy HH:mm"), new SimpleDateFormat("M/d/yyyy HH:mm"),
                new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"), new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"), new SimpleDateFormat("M-d-yy HH:mm"),
                new SimpleDateFormat("M-d-yyyy HH:mm"), new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS"),
                new SimpleDateFormat("M/d/yy"), new SimpleDateFormat("M/d/yyyy"), new SimpleDateFormat("M-d-yy"),
                new SimpleDateFormat("M-d-yyyy"), new SimpleDateFormat("MMMM d, yyyyy"),
                new SimpleDateFormat("MMM d, yyyyy") };

        return temp;
    }

    private static SimpleDateFormat[] getFormats() {
        return mDateFormats;
    }

    public static java.util.Date getEndOfDay(java.util.Date day) {
        return getEndOfDay(day, Calendar.getInstance());
    }

    public static java.util.Date getEndOfDay(java.util.Date day, Calendar cal) {
        if (day == null)
            day = new java.util.Date();
        cal.setTime(day);
        cal.set(11, cal.getMaximum(11));
        cal.set(12, cal.getMaximum(12));
        cal.set(13, cal.getMaximum(13));
        cal.set(14, cal.getMaximum(14));
        return cal.getTime();
    }

    public static java.util.Date getStartOfDay(java.util.Date day) {
        return getStartOfDay(day, Calendar.getInstance());
    }

    public static java.util.Date getStartOfDay(java.util.Date day, Calendar cal) {
        if (day == null)
            day = new java.util.Date();
        cal.setTime(day);
        cal.set(11, cal.getMinimum(11));
        cal.set(12, cal.getMinimum(12));
        cal.set(13, cal.getMinimum(13));
        cal.set(14, cal.getMinimum(14));
        return cal.getTime();
    }

    public static java.util.Date getNoonOfDay(java.util.Date day, Calendar cal) {
        if (day == null)
            day = new java.util.Date();
        cal.setTime(day);
        cal.set(11, 12);
        cal.set(12, cal.getMinimum(12));
        cal.set(13, cal.getMinimum(13));
        cal.set(14, cal.getMinimum(14));
        return cal.getTime();
    }

    public static java.util.Date getDateFromString(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return new java.util.Date(System.currentTimeMillis());
        }
        try {
            return sdfLongTimePlus.parse(strDate);
        } catch (Exception localException) {
        }
        return new Timestamp(System.currentTimeMillis());
    }

    public static java.util.Date parseFromFormats(String aValue) {
        if (StringUtil.isEmpty(aValue)) {
            return null;
        }

        SimpleDateFormat[] formats = getFormats();
        if (formats == null) {
            return null;
        }

        java.util.Date myDate = null;
        for (int i = 0; i < formats.length; i++) {
            try {
                return parse(aValue, formats[i]);
            } catch (Exception localException) {
            }

        }

        return null;
    }

    public static Timestamp parseTimestampFromFormats(String aValue) {
        if (StringUtil.isEmpty(aValue)) {
            return null;
        }

        java.util.Date myDate = parseFromFormats(aValue);
        if (myDate != null)
            return new Timestamp(myDate.getTime());
        return null;
    }

    public static Timestamp now() {
        return new Timestamp(new java.util.Date().getTime());
    }

    public static String format(java.util.Date aDate, SimpleDateFormat aFormat) {
        if ((aDate == null) || (aFormat == null)) {
            return "";
        }
        synchronized (aFormat) {
            return aFormat.format(aDate);
        }
    }

    public static String formatDateString(String aString, SimpleDateFormat aFormat) {
        if ((StringUtil.isEmpty(aString)) || (aFormat == null))
            return "";
        try {
            Timestamp aDate = parseTimestampFromFormats(aString);
            if (aDate != null) {
                return format(aDate, aFormat);
            }
        } catch (Exception localException) {
        }

        return "";
    }

    public static java.util.Date parse(String aValue, SimpleDateFormat aFormat) throws ParseException {
        if ((StringUtil.isEmpty(aValue)) || (aFormat == null)) {
            return null;
        }

        return aFormat.parse(aValue);
    }

    public static boolean isValidDateRange(java.util.Date startDate, java.util.Date endDate) {
        return isValidDateRange(startDate, endDate, true);
    }

    public static boolean isValidDateRange(java.util.Date startDate, java.util.Date endDate, boolean equalOK) {
        if ((startDate == null) || (endDate == null)) {
            return false;
        }

        if (equalOK) {
            if (startDate.equals(endDate)) {
                return true;
            }

        }

        if (endDate.after(startDate)) {
            return true;
        }

        return false;
    }

    public static SimpleDateFormat defaultTimestampFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static SimpleDateFormat get8charDateFormat() {
        return mFormat8chars;
    }

    public static SimpleDateFormat defaultDateFormat() {
        return friendlyDateFormat(true);
    }

    public static String defaultTimestamp(java.util.Date date) {
        return format(date, defaultTimestampFormat());
    }

    public static String defaultDate(java.util.Date date) {
        return format(date, defaultDateFormat());
    }

    public static SimpleDateFormat friendlyTimestampFormat() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    }

    public static String friendlyTimestamp(java.util.Date date) {
        return format(date, friendlyTimestampFormat());
    }

    public static String format8chars(java.util.Date date) {
        return format(date, mFormat8chars);
    }

    public static String formatIso8601Day(java.util.Date date) {
        return format(date, mFormatIso8601Day);
    }

    public static String formatIso8601Day(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        return format(calendar.getTime(), mFormatIso8601Day);
    }

    public static String formatTradeEasy(java.util.Date date) {
        return format(date, mFormatTradeEasy);
    }

    public static String formatTradeEasyProduct(java.util.Date date) {
        return format(date, mFormatTradeEasyProduct);
    }

    public static String formatFormatTradeEasyMMddyyyy(java.util.Date date) {
        return format(date, mFormatTradeEasyMMddyyyy);
    }

    public static String formatTradeEasy(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        return format(calendar.getTime(), mFormatTradeEasy);
    }

    public static String formatRfc822(java.util.Date date) {
        return format(date, mFormatRfc822);
    }

    public static String formatExpire(java.util.Date date) {
        return format(date, mFormatExpire);
    }

    public static String formatIso8601(java.util.Date date) {
        if (date == null) {
            return "";
        }

        String str = format(date, mFormatIso8601);
        StringBuffer sb = new StringBuffer();
        sb.append(str.substring(0, str.length() - 2));
        sb.append(":");
        sb.append(str.substring(str.length() - 2));
        return sb.toString();
    }

    public static SimpleDateFormat minimalDateFormat() {
        return friendlyDateFormat(true);
    }

    public static String minimalDate(java.util.Date date) {
        return format(date, minimalDateFormat());
    }

    public static SimpleDateFormat fullDateFormat() {
        return friendlyDateFormat(false);
    }

    public static String fullDate(java.util.Date date) {
        return format(date, fullDateFormat());
    }

    public static SimpleDateFormat friendlyDateFormat(boolean minimalFormat) {
        if (minimalFormat) {
            return new SimpleDateFormat("d.M.yy");
        }

        return new SimpleDateFormat("dd.MM.yyyy");
    }

    public static String friendlyDate(java.util.Date date, boolean minimalFormat) {
        return format(date, friendlyDateFormat(minimalFormat));
    }

    public static String friendlyDate(java.util.Date date) {
        return format(date, friendlyDateFormat(true));
    }

    public static java.util.Date parseFormatIso8601Date(String date) throws Exception {
        java.util.Date returnDate = null;
        try {
            returnDate = mFormatIso8601Day.parse(date);
        } catch (Exception e) {
            throw e;
        }
        return returnDate;
    }

    public static String addDate(String date, String type, int into) throws Exception {
        String Sdate = "";
        try {
            GregorianCalendar grc = new GregorianCalendar();
            grc.setTime(new java.util.Date(date));
            if (type.equals("D")) {
                grc.add(5, into);
            } else if (type.equals("M")) {
                grc.add(2, into);
            } else if (type.equals("Y")) {
                grc.add(1, into);
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Sdate = new String(formatter.format(grc.getTime()));
        } catch (Exception e) {
            throw e;
        }
        return Sdate;
    }

    public static String addDate(String date, String into) throws Exception {
        String Sdate = "";
        try {
            date = date.replaceAll("-", "/");
            date = date.substring(0, date.length() - 2);
            GregorianCalendar grc = new GregorianCalendar();
            grc.setTime(new java.util.Date(date));
            grc.add(5, Integer.parseInt(into));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Sdate = new String(formatter.format(grc.getTime()));
        } catch (Exception e) {
            throw e;
        }
        return Sdate;
    }

    public static String formatDate(java.util.Date date, String pattern) {
        if ((pattern == null) || (pattern.equals("")) || (pattern.equals("null"))) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String addValidateDate(String date, String into) throws Exception {
        String Sdate = "";
        try {
            date = date.replaceAll("-", "/");
            date = date.substring(0, date.length() - 2);
            GregorianCalendar grc = new GregorianCalendar();
            grc.setTime(new java.util.Date(date));
            grc.add(5, Integer.parseInt(into));
            Sdate = new String(mFormatExpire.format(grc.getTime()));
        } catch (Exception e) {
            throw e;
        }
        return Sdate;
    }

    public static String addDayToStringDate(String formate, String strDate, String days) {
        String stringDate = null;
        try {
            java.util.Date date = fromStringToDate(formate, strDate);
            long now = date.getTime() + Integer.parseInt(days) * DAY_IN_MILLISECOND;

            stringDate = getFomartDate(new java.util.Date(now), formate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return stringDate;
    }

    public static java.util.Date addDayToStringDate2(String formate, String strDate, String days) {
        java.util.Date date = null;
        try {
            date = fromStringToDate(formate, strDate);
            long now = date.getTime() + Integer.parseInt(days) * DAY_IN_MILLISECOND;

            date = new java.util.Date(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static java.util.Date dateDayAdd(java.util.Date date, int days) {
        long now = date.getTime() + days * DAY_IN_MILLISECOND;
        return new java.util.Date(now);
    }

    public static java.util.Date fromStringToDate(String format, String dateTime) throws ParseException {
        java.util.Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        date = sdf.parse(dateTime);
        return date;
    }

    public static java.util.Date fromStringToDate(java.util.Date date) throws ParseException {
        return sdfLongTimePlus.parse(sdfLongTimePlus.format(date));
    }

    public static void main(String[] args) {
        try {
            System.out.println(toDayToStr("yyyy年MM月dd日"));

            System.out.println(getDateShortLongTimeCn(new java.util.Date()));
            System.out.println(null + "1");
            System.out.println(convertDateToDay(new java.util.Date()));
            java.util.Date nows = new java.util.Date();
            System.out.println("============" + getDateLongCn(nows));
            System.out.println("============0000000000000000000000000000000");
            System.out
                    .println("============stringToDate=" + stringToDate("2009-11-18 19:14:31", "yyyy-MM-dd h24:mi:ss"));

            System.out.println("============getDateFromString=" + getDateFromString("2009-11-18 19:14:31"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Integer getTimeFormatIntger(java.util.Date date) {
        if (date == null) {
            return Integer.valueOf(0);
        }
        String strTemp = getFomartDate(date, "yyyyMMddHHmmss");
        String nowTime = strTemp.substring(8, 14);
        return Integer.valueOf(nowTime);
    }

    public static String getNowDayStr(java.util.Date date) {
        if (date == null) {
            return "";
        }

        Calendar c = Calendar.getInstance();
        int i = c.get(7);
        System.out.println(i);

        return "";
    }

    public static String toDayToStr(String format) {
        try {
            java.util.Date now = new java.util.Date();
            return DateToStr(now, format) + " " + getWeekOfDate(now);
        } catch (Exception e) {
            System.out.println("Date 转 String 类型失败: " + e);
        }
        return null;
    }

    public static String DateToStr(java.util.Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            System.out.println("Date 转 String 类型失败: " + e);
        }
        return null;
    }

    public static java.util.Date dateAddDays(java.util.Date date, int days) {
        long now = date.getTime() + days * DAY_IN_MILLISECOND;
        return new java.util.Date(now);
    }

    public static String dateTypeToString(java.util.Date date, String fFormatStr) {
        SimpleDateFormat dateformat = new SimpleDateFormat(fFormatStr);
        String strDate = dateformat.format(date);
        return strDate;
    }

    public static String getStringOfNowDate(String fFormatStr) {
        String nowDateString = dateTypeToString(new java.util.Date(), fFormatStr);
        return nowDateString;
    }

    public static String getStringOfFirstDayInMonth() {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String temp = sdf.format(date);
        String firstDayInMoth = "";
        firstDayInMoth = temp + "-01";

        return firstDayInMoth;
    }

    public static String getWeekOfDate(java.util.Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(7) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }
}