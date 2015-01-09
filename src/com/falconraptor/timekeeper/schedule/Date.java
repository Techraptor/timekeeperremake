package com.falconraptor.timekeeper.schedule;

import java.math.*;

public class Date {
	public int month, day, year, hour, minute;

	public int CompareTo (Date other) {
		int result, y = compare(year, other.year), m = compare(month, other.month), d = compare(day, other.day), h = compare(hour, other.hour), min = compare(minute, other.minute);
		if (y != 0) result = y;
		else if (m != 0) result = m;
		else if (d != 0) result = d;
		else if (h != 0) result = h;
		else result = min;
		return result;
	}

	private int compare (int a, int b) {
		if (a > b) return 1;
		else if (a < b) return -1;
		else return 0;
	}

	public Date findDifference (Date other) {
		Date result = new Date();
		BigInteger date = convertToMinutes(), otherDate = convertToMinutes();
		int stuff = date.subtract(otherDate).abs().intValueExact();
		result.minute = stuff % 60;
		stuff -= result.minute;
		result.hour = stuff % (60);
		stuff -= result.hour;
		result.day = stuff % (60 * 24);
		stuff -= result.day;
		return result;
	}

	public BigInteger convertToMinutes () {
		BigInteger result = new BigInteger("0");
		boolean leap = false;
		if (year % 4 == 0 && year % 100 == 0 && year % 400 == 0) leap = true;
		if (leap)
			result = result.add(new BigInteger(year + "").multiply(new BigInteger("366")).multiply(new BigInteger("1440")));
		else
			result = result.add(new BigInteger(year + "").multiply(new BigInteger("365")).multiply(new BigInteger("1440")));
		for (int i = 1; i <= month; i++) {
			if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12)
				result = result.add(new BigInteger("31").multiply(new BigInteger("1440")));
			else if (i == 2) {
				if (leap) result = result.add(new BigInteger("29").multiply(new BigInteger("1440")));
				else result = result.add(new BigInteger("28").multiply(new BigInteger("1440")));
			} else result = result.add(new BigInteger("30").multiply(new BigInteger("1440")));
		}
		result = result.add(new BigInteger(day + "").multiply(new BigInteger("1440")));
		result = result.add(new BigInteger(hour + "").multiply(new BigInteger("60")));
		result = result.add(new BigInteger(minute + ""));
		return result;
	}
}
