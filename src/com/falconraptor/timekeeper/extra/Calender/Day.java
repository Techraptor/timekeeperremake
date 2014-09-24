package com.falconraptor.timekeeper.extra.Calender;

/**
 * Created by Kyle on 9/17/2014.
 */
public class Day {
    private String day;
    private int date;
    //private Event event

    public Day() {
        //creates a blank day
        day = new String("");
        date = 0;
    }

    public Day(String d, int da) {
        day = d;
        date = da;
    }
}
