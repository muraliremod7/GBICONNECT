package com.brain.revanth.sampleapplication2.chat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hari Prahlad on 08-06-2016.
 */
public class CommonMethods {
    private static DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
    private static DateFormat timeFormat = new SimpleDateFormat("K:mma");

    public static String getCurrentTime() {

        Date today = Calendar.getInstance().getTime();
        return timeFormat.format(today);
    }

    public static String getCurrentDate() {

        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }

}
