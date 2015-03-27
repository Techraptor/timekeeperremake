package com.falconraptor.timekeeper.schedule;

public class Holidays {
	private String name;
	private int day;
	private int month;

	public Holidays (String name, int day, int month) {
		this.name = name;
		this.day = day;
		this.month = month;
	}

	@Override
	public final String toString () {
		return "Holidays{" +
			  "name='" + name + '\'' +
			  ", day=" + day +
			  ", month=" + month +
			  '}';
	}

	public final String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public final int getDay () {
		return day;
	}

	public void setDay (int day) {
		this.day = day;
	}

	public final int getMonth () {
		return month;
	}

	public void setMonth (int month) {
		this.month = month;

	}
}
