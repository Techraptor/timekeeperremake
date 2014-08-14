package com.falconraptor.timekeeper.main;

public class Holiday {
    int month, day;
    String name;

    public Holiday(int m, int d, String n) {
        month = m;
        day = d;
        name = n;
    }

    public void setmonth(int m) {
        month = m;
    }

    public void setday(int d) {
        day = d;
    }

    public void setname(String n) {
        name = n;
    }

    public int getmonth() {
        return month;
    }

    public int getday() {
        return day;
    }

    public String getname() {
        return name;
    }

    public String toString() {
        return "Month: " + month + ", Day: " + day + ", Name: " + name;
    }
}
