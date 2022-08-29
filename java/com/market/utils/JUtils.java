package com.market.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Elias on 2019/7/13
 */
public class JUtils {
    private static final DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date getDateAMonthBefore() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date d = c.getTime();
        return d;
    }

    public static String dateFormat(Date date){
        return format.format(date);
    }
}
