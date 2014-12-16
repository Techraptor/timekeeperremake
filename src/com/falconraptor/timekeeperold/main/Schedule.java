package com.falconraptor.timekeeperold.main;

public class Schedule {
	int starthour, startminute, endhour, endminute, lunch;
	String clas;

	public Schedule (int sh, int sm, String c, int eh, int em, int l) {
		starthour = sh;
		startminute = sm;
		clas = c;
		endhour = eh;
		endminute = em;
		lunch = l;
	}

	public Schedule (int sh, int sm, String c, int eh, int em) {
		starthour = sh;
		startminute = sm;
		clas = c;
		endhour = eh;
		endminute = em;
		lunch = 0;
	}

	public void setstarthour (int sh) {
		starthour = sh;
	}

	public void setstartminute (int sm) {
		startminute = sm;
	}

	public void setclass (String c) {
		clas = c;
	}

	public void setendhour (int eh) {
		endhour = eh;
	}

	public void setendminute (int em) {
		endminute = em;
	}

	public void setlunch (int l) {
		lunch = l;
	}

	public int getstarthour () {
		return starthour;
	}

	public int getstartminute () {
		return startminute;
	}

	public String getclass () {
		return clas;
	}

	public int getendhour () {
		return endhour;
	}

	public int getendminute () {
		return endminute;
	}

	public int getlunch () {
		return lunch;
	}

	public String toString () {
		return "Starthour: " + starthour + ", Startminute: " + startminute + ", Class: " + clas + ", Endhour: " + endhour + ", Endminute: " + endminute + ", Lunch: " + lunch;
	}
}