package com.example.arnaudschaal.ournation.Tools;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by arnaud.schaal on 12-01-18.
 */

public class ToolsBox {
    public static long secondsInMilli = 1000;
    public static long minutesInMilli = secondsInMilli * 60;
    public static long hoursInMilli = minutesInMilli * 60;
    public static long daysInMilli = hoursInMilli * 24;

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

    public static String getMonth(int month){
        switch (month){
            case 0:
                return "Janvier";
            case 1:
                return "Fevrier";
            case 2:
                return "Mars";
            case 3:
                return "Avril";
            case 4:
                return "Mai";
            case 5:
                return "Juin";
            case 6:
                return "Juillet";
            case 7:
                return "Aout";
            case 8:
                return "Septembre";
            case 9:
                return "Octobre";
            case 10:
                return "Novembre";
            case 11:
                return "Decembre";
            default:
                return "No Month";
        }
    }
    // The public static function which can be called from other classes
    public static void darkenStatusBar(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            activity.getWindow().setStatusBarColor(
                    darkenColor(
                            color));
        }
    }


    // Code to darken the color supplied (mostly color of toolbar)
    public static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }

    public static int darkenColor(int color, float ratio) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= ratio;
        return Color.HSVToColor(hsv);
    }


    public static long getTimeDivisor(long time){
        if(time >= daysInMilli)
            return daysInMilli;
        if(time >= hoursInMilli)
            return hoursInMilli;
        if(time >= minutesInMilli)
            return minutesInMilli;
        return secondsInMilli;
    }

    public static String getTimeString(long time){
        if(time >= daysInMilli)
            return "day";
        if(time >= hoursInMilli)
            return "hour";
        if(time >= minutesInMilli)
            return "min";
        return "sec";
    }
}
