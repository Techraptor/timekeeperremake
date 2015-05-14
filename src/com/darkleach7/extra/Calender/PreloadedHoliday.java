package com.darkleach7.extra.Calender;

public class PreloadedHoliday {
	private String name;
	private int day;
	private int month;

	public PreloadedHoliday (String name, int day, int month) {
		this.name = name;
		this.day = day;
		this.month = month;
	}

	@Override
	public String toString () {
		return "Holidays{" +
			  "name='" + name + '\'' +
			  ", day=" + day +
			  ", month=" + month +
			  '}';
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public int getDay () {
		return day;
	}

	public void setDay (int day) {
		this.day = day;
	}

	public int getMonth () {
		return month;
	}

	public void setMonth (int month) {
		this.month = month;

	}

	private void calcDateOfEvent (String name) {

	}
}
