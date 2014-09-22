package com.falconraptor.timekeeper.school;

import com.falconraptor.timekeeper.schedule.Time;

public class Class {
    private String name;
    private Time start;
    private Time end;
    private int lunch;
    private int length;

    public Class(String name, Time start, Time end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public Class(String n, int sh, int sm, int eh, int em) {
        name = n;
        start = new Time(sh, sm);
        end = new Time(eh, em);

    }

    public Class(String name) {
        this.name = name;
    }

    public int getLunch() {
        return lunch;
    }

    public void setLunch(int lunch) {
        this.lunch = lunch;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void calcLength() {

    }

    @Override
    public String toString() {
        return "Class{" + "name='" + name + '\'' + ", start=" + start + ", end=" + end + ", lunch=" + lunch + ", length=" + length + '}';
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
