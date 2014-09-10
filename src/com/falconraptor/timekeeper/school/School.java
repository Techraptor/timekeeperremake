package com.falconraptor.timekeeper.school;

import com.falconraptor.timekeeper.schedule.Schedule;

public class School {
    public Schedule schedule;
    private String name;
    private int periods;

    public School(String name) {
        this.name = name;
    }

    public School(String name, int periods) {
        this.name = name;
        this.periods = periods;
        schedule = new Schedule(periods);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
        schedule = new Schedule(periods);
    }
}
