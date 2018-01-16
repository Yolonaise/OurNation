package com.example.arnaudschaal.ournation.Tools;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by arnaud.schaal on 12-01-18.
 */

public class ToolsBox {
    public static String getDateFormat(Date date){
        return (String) android.text.format.DateFormat.format("yyyy-MM-dd", new Date());
    }

    public static Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }

    public static Date addYear(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, i);
        return cal.getTime();
    }

    public static int getSeason(int month){
        switch (month){
            case 11:
            case 0:
            case 1:
                return 0;
            case 2:
            case 3:
            case 4:
                return 1;
            case 5:
            case 6:
            case 7:
                return 2;
            case 8:
            case 9:
            case 10:
                return 3;
            default:
                return -1;
        }
    }
}
