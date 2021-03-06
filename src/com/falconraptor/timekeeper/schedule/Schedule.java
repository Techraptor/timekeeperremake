package com.falconraptor.timekeeper.schedule;

import com.falconraptor.timekeeper.references.References;
import com.falconraptor.timekeeper.school.Class;
import com.falconraptor.utilities.logger.Logger;

import java.util.Arrays;

public class Schedule {
    public com.falconraptor.timekeeper.school.Class[] aClass;
    private Lunch lunch;

    public Schedule(int classes) {
        aClass = new Class[classes];
    }

    public void calcLengths() {
        for (Class c : aClass) if (c != null) c.calcLength();
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

    public void setLunch(String schedule, int l) {
        if (schedule.equals("normal")) {
            if (l == 1) lunch = References.settings.atech.normalfirst;
            else if (l == 2) lunch = References.settings.atech.normalsecond;
        } else if (schedule.equals("wednesday")) {
            if (l == 1) lunch = References.settings.atech.normalfirst;
            else if (l == 2) lunch = References.settings.atech.normalsecond;
        } else if (schedule.equals("thursday")) {
            if (l == 1) lunch = References.settings.atech.normalfirst;
            else if (l == 2) lunch = References.settings.atech.normalsecond;
        } else if (schedule.equals("assembly")) {
            if (l == 1) lunch = References.settings.atech.normalfirst;
            else if (l == 2) lunch = References.settings.atech.normalsecond;
        }
    }
}
