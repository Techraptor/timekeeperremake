package com.falconraptor.timekeeper.schedule;

import com.falconraptor.timekeeper.school.Class;
import com.falconraptor.utilities.logger.Logger;

import java.util.Arrays;

public class Schedule {
    public com.falconraptor.timekeeper.school.Class[] aClass;
    private Lunch lunch;

    public Schedule(int classes) {
        aClass = new Class[classes];
    }

    public void setLunch(int i) {

    }

    @Override
    public String toString() {
        return "Schedule{" +
                "aClass=" + Arrays.toString(aClass) +
                ", lunch=" + lunch +
                '}';
    }

    public int getAmountOfClasses() {
        return aClass.length - 1;
    }

    public void addClass(int index, String name) {
        if (index > getAmountOfClasses()) {
            Logger.logERROR("Trying to add more classes than set in school");
            return;
        }
        aClass[index] = new Class(name);
    }

    public void setClass(int index, Time start, Time end) {
        if (index > getAmountOfClasses()) {
            Logger.logERROR("Trying to change class that is not set in school");
            return;
        }
        aClass[index].setStart(start);
        aClass[index].setEnd(end);
    }
}
