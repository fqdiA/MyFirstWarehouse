package com.fq.superparking.utils;

import java.util.Calendar;

public class TimeUtil {

    private static Calendar calendar = Calendar.getInstance();
    // 获取当前年
    private static int year = calendar.get(Calendar.YEAR);
    // 获取当前月
    private static int month = calendar.get(Calendar.MONTH) + 1;
    // 获取当前日
    private static int day = calendar.get(Calendar.DATE);

    public static int getYear() {
        return year;
    }

    public static int getMonth() {
        return month;
    }

    public static int getDay() {
        return day;
    }
}
