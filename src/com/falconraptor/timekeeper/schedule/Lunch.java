package com.falconraptor.timekeeper.schedule;
public class Lunch{
    private Time start;
    private Time end;
    private int lunch;
    private int length;

    public Lunch(int l, Time s, Time e) {
        lunch = l;
        start = s;
        end = e;
    }

    public Lunch(int l, int sh, int sm, int eh, int em) {
        lunch = l;
        start = new Time(sh, sm);
        end = new Time(eh, em);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setStart(int hour, int minute) {
        start = new Time(hour, minute);
    }

    public void setEnd(int hour, int minute) {
        end = new Time(hour, minute);
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time s) {
        start = s;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time s) {
        end = s;
    }

    public int getLunch() {
        return lunch;
    }

    public void setLunch(int l) {
        lunch = l;
    }
}
