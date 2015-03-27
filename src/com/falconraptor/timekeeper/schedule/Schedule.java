package com.falconraptor.timekeeper.schedule;

import com.falconraptor.timekeeper.other.*;
import com.falconraptor.timekeeper.school.Class;
import com.falconraptor.utilities.logger.*;

import java.time.*;
import java.util.*;

public class Schedule {
	public final com.falconraptor.timekeeper.school.Class[] aClass;
	private Lunch lunch;

	public Schedule (int classes) {
		aClass = new Class[classes];
	}

	public final void calcLengths () {
		for (Class c : aClass) if (c != null) c.calcLength();
	}

	@Override
	public final String toString () {
		return "Schedule{" +
			  "aClass=" + Arrays.toString(aClass) +
			  ", lunch=" + lunch +
			  '}';
	}

	public void addClass (int index, String name) {
		if (index > getAmountOfClasses()) {
			Logger.logERROR("Trying to add more classes than set in school");
			return;
		}
		aClass[index] = new Class(name);
	}

	final int getAmountOfClasses () {
		return aClass.length - 1;
	}

	public void setClass (int index, LocalTime start, LocalTime end) {
		if (index > getAmountOfClasses()) {
			Logger.logERROR("Trying to change class that is not set in school");
			return;
		}
		aClass[index].setStart(start);
		aClass[index].setEnd(end);
	}

	public void setLunch (String schedule, int l) {
		switch (schedule) {
			case "normal":
				if (l == 1) lunch = References.settings.atech.normalfirst;
				else if (l == 2) lunch = References.settings.atech.normalsecond;
				break;
			case "wednesday":
				if (l == 1) lunch = References.settings.atech.normalfirst;
				else if (l == 2) lunch = References.settings.atech.normalsecond;
				break;
			case "thursday":
				if (l == 1) lunch = References.settings.atech.normalfirst;
				else if (l == 2) lunch = References.settings.atech.normalsecond;
				break;
			case "assembly":
				if (l == 1) lunch = References.settings.atech.normalfirst;
				else if (l == 2) lunch = References.settings.atech.normalsecond;
				break;
		}
	}
}
