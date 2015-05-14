package com.darkleach7.extra.Calender;

public class Event {
	private DateTime start, end;
	private double lengthOfTime;
	private String eventName;
	private boolean isPreloaded;
	//string for name and description

	//how long before reminding blah
	public Event (int m, int d, int y, String n, boolean i) {
		setStart(new DateTime(d, m, y, 0, 0));
		setEventName(n);
		isPreloaded = i;
	}

	public double getLengthOfTime () {
		return lengthOfTime;
	}

	public void setLengthOfTime (double lengthOfTime) {
		this.lengthOfTime = lengthOfTime;
	}

	public void calcLengthOfTime () {
		DateTime temp = end.subtract(start);
		lengthOfTime = temp.getHour() * 60 + temp.getMinute();
	}

	public DateTime getStart () {
		return start;
	}

	public void setStart (DateTime start) {
		this.start = start;
	}

	public DateTime getEnd () {
		return end;
	}

	public void setEnd (DateTime end) {
		this.end = end;
	}

	public boolean isPreloaded () {
		return isPreloaded;
	}

	public void setPreloaded (boolean isPreloaded) {
		this.isPreloaded = isPreloaded;
	}

	public String getEventName () {
		return eventName;
	}

	public void setEventName (String eventName) {
		this.eventName = eventName;
	}

}

