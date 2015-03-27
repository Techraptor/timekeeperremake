package com.falconraptor.timekeeper.school;

import com.falconraptor.timekeeper.schedule.*;

public class School {
	private Schedule schedule;
	private String name;
	private int periods;

	public School (String name) {
		this.name = name;
	}

	protected School (String name, int periods) {
		this.name = name;
		this.periods = periods;
		schedule = new Schedule(periods);
	}

	public final String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public final int getPeriods () {
		return periods;
	}

	public void setPeriods (int periods) {
		this.periods = periods;
		schedule = new Schedule(periods);
	}
}
