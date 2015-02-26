package com.falconraptor.timekeeper.schedule;

import java.time.*;

public class Lunch {
	private LocalTime start;
	private LocalTime end;
	private int lunch;
	private int length;

	public Lunch (int l, LocalTime s, LocalTime e) {
		lunch = l;
		start = s;
		end = e;
	}

	public Lunch (int l, int sh, int sm, int eh, int em) {
		lunch = l;
		start = LocalTime.of(sh, sm);
		end = LocalTime.of(eh, em);
	}

	@Override
	public String toString () {
		return "Lunch{" +
			  "start=" + start +
			  ", end=" + end +
			  ", lunch=" + lunch +
			  ", length=" + length +
			  '}';
	}

	public int getLength () {
		return length;
	}

	public void setLength (int length) {
		this.length = length;
	}

	public void setStart (int hour, int minute) {
		start = LocalTime.of(hour, minute);
	}

	public void setEnd (int hour, int minute) {
		end = LocalTime.of(hour, minute);
	}

	public int getLunch () {
		return lunch;
	}

	public void setLunch (int l) {
		lunch = l;
	}

	public void calcLength () {
		length = (getEnd().getHour() - getStart().getHour()) * 60 + getEnd().getMinute() - getStart().getMinute();
	}

	public LocalTime getStart () {
		return start;
	}

	public void setStart (LocalTime s) {
		start = s;
	}

	public LocalTime getEnd () {
		return end;
	}

	public void setEnd (LocalTime s) {
		end = s;
	}
}
