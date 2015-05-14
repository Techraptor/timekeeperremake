package com.darkleach7.extra.Calender;

public class DateTime {
	private int day, month, year, hour, minute;

	public DateTime (int day, int month, int year, int hour, int minute) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public String toString () {
		return "DateTime{" + "day=" + day + ", month=" + month + ", year=" + year + ", hour=" + hour + ", minute=" + minute + '}';
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

	public int getYear () {
		return year;
	}

	public void setYear (int year) {
		this.year = year;
	}

	public DateTime subtract (DateTime o) {
		DateTime temp = new DateTime(day, month, year, hour - o.getHour(), 0);
		if (minute > o.getMinute()) temp.setMinute(minute - o.getMinute());
		else {
			temp.setMinute(o.getMinute() - minute);
			temp.setHour(temp.getHour() - 1);
		}
		if (temp.getHour() < 0) temp.setHour(0);
		return temp;
	}

	public int getHour () {
		return hour;
	}

	public void setHour (int hour) {
		this.hour = hour;
	}

	public int getMinute () {
		return minute;
	}

	public void setMinute (int minute) {
		this.minute = minute;
	}
}

