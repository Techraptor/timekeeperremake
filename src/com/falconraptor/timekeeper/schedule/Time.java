package com.falconraptor.timekeeper.schedule;

public class Time {
	private int hour;
	private int minute;

	public Time (int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	public Time (int hour) {
		this.hour = hour;
		minute = 0;
	}

	public int convert () {
		if (hour < 4) return (hour + 12) * 60 + minute;
		return hour * 60 + minute;
	}

	public int getHour () {
		return hour;
	}

	public void setHour (int hour) {
		this.hour = hour;
	}

	@Override
	public String toString () {
		return "Time{" + "hour=" + hour + ", minute=" + minute + '}';
	}

	public int getMinute () {
		return minute;
	}

	public void setMinute (int minute) {
		this.minute = minute;
	}


}
