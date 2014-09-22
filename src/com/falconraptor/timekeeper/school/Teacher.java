package com.falconraptor.timekeeper.school;

import com.falconraptor.timekeeper.schedule.Schedule;

public class Teacher {
    public Schedule schedule;
    private String name;

    public Teacher(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }
}
