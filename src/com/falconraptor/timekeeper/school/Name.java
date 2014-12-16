package com.falconraptor.timekeeper.school;

public class Name {
	private String first;
	private String last;
	private String middle;
	private String title;

	public Name (String first, String last, String title) {
		this.first = first;
		this.last = last;
		this.title = title;
	}

	@Override
	public String toString () {
		return "Name{" +
			  "first='" + first + '\'' +
			  ", last='" + last + '\'' +
			  ", middle='" + middle + '\'' +
			  ", title='" + title + '\'' +
			  '}';
	}

	public String getFirst () {
		return first;
	}

	public void setFirst (String first) {
		this.first = first;
	}

	public String getLast () {
		return last;
	}

	public void setLast (String last) {
		this.last = last;
	}

	public String getMiddle () {
		return middle;
	}

	public void setMiddle (String middle) {
		this.middle = middle;
	}

	public String getTitle () {
		return title;
	}

	public void setTitle (String title) {
		this.title = title;
	}
}
