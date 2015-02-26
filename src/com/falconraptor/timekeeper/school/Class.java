package com.falconraptor.timekeeper.school;

import java.time.*;

public class Class {
	private String name;
	private LocalTime start;
	private LocalTime end;
	private int lunch;
	private int length;

	public Class (String name, LocalTime start, LocalTime end) {
		this.name = name;
		this.start = start;
		this.end = end;
	}

	public Class (String n, int sh, int sm, int eh, int em) {
		name = n;
		start = LocalTime.of(sh, sm);
		end = LocalTime.of(eh, em);
	}

	public Class (String name) {
		this.name = name;
	}

	public int getLunch () {
		return lunch;
	}

	public void setLunch (int lunch) {
		this.lunch = lunch;
	}

	public int getLength () {
		return length;
	}

	public void setLength (int length) {
		this.length = length;
	}

	public void calcLength () {
		length = end.minusHours(start.getHour()).minusMinutes(start.getMinute()).getHour() * 60 + end.minusHours(start.getHour()).minusMinutes(start.getMinute()).getMinute();
	}

	@Override
	public String toString () {
		return "Class{" + "name='" + name + '\'' + ", start=" + start + ", end=" + end + ", lunch=" + lunch + ", length=" + length + '}';
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public LocalTime getStart () {
		return start;
	}

	public void setStart (LocalTime start) {
		this.start = start;
	}

	public LocalTime getEnd () {
		return end;
	}

	public void setEnd (LocalTime end) {
		this.end = end;
	}
}
