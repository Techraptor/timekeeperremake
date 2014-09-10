package com.falconraptor.timekeeper.school;

import com.falconraptor.timekeeper.schedule.Time;

public class Class {
    private String name;
    private Time start;
    private Time end;

    public Class(String name, Time start, Time end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public Class(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }
}
