package com.darkleach7.extra.Calender;

import com.falconraptor.utilities.logger.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

//import com.falconraptor.timekeeper.school.schools.Atech;

public class Day extends JFrame {
	//    boolean hasEvents;
	final ArrayList<Event> events;
	final ArrayList<PreloadedHoliday> evntDebug;
	int month;
	int day;
	int year;

	public Day (int m, int d, int y, ArrayList<PreloadedHoliday> prld) {
		super("day gui test");
		//day = new String(s);
		month = m;
		day = d;
		year = y;
//        hasEvents = false;
		events = new ArrayList<>(0);
		evntDebug = prld;

		//Logger.logDEBUG(prld.size()+"");
		//Logger.logDEBUG(m + " " + d + " " + y);
		//Logger.logDEBUG(evntDebug.size()+"");

		for (PreloadedHoliday aPrld : prld) {
			if (aPrld.getMonth() == month && aPrld.getDay() == day) {
				events.add(new Event(m, d, y, aPrld.getName(), true));
			} else if (aPrld.getMonth() == month && aPrld.getDay() == 0) {
				events.add(new Event(m, GetDate.reqDate(aPrld.getName(), y, m), y, aPrld.getName(), true));
			}
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(false);
		setContentPane(setgui());
		pack();
	}

	private JPanel setgui () {
		JPanel frame = new JPanel(new GridLayout(1, 2, 0, 0));
		JLabel text = new JLabel("" + month + " " + day + " " + year);
		//JLabel test = new JLabel("");
		JButton eventCheck = new JButton("eventCheck");
		eventCheck.addActionListener(e -> {
		});
		JButton prldCheck = new JButton("prldCheck");
		prldCheck.addActionListener(e -> {
		});
		JButton passFailCheck = new JButton("passFailCheck");
		passFailCheck.addActionListener(e -> {
		});
		frame.add(text);
		//Preloaded event list
		int numOfEvents = 0;
		if (events.size() != 0) {
			if (events.get(0) != null) {
				//Logger.logDEBUG("events.get(0)!=null");
				//Logger.logDEBUG(events.get(0).getEventName());
			}
		}
		if (events.size() != 0) {
			//Logger.logDEBUG("event.size is larger than 0");
			for (Event event : events) {
				//
				if (event.getStart().getMonth() == month && event.getStart().getDay() == day) {
					//Logger.logDEBUG("event matches increasing event size");
					numOfEvents++;
					//Logger.logDEBUG(numOfEvents + "");
				}
			}
		}
		String[] prldEvents = new String[numOfEvents];

		//Logger.logDEBUG(prldEvents.length + "");
		for (int i = 0; i < prldEvents.length; i++) {
			Logger.logDEBUG("adding events");
			prldEvents[i] = events.get(i).getEventName();
		}

		JComboBox font;
		font = new JComboBox(prldEvents);
		font.setEditable(false);


		frame.add(new JLabel("Preloaded Events"));
		frame.add(font);
		//frame.add(eventCheck);
		//frame.add(prldCheck);
		//frame.add(passFailCheck);
		//frame.add(test);
		//display
		//User added event list
		//display and allow to add
		return frame;
	}

	private void debugCheck () {
		for (Event event : events) {
			Logger.logDEBUG(event.getEventName());
		}
	}

	private void stupidDebug (ArrayList<PreloadedHoliday> prld) {
		//IT WORKS RIGHT HERE
		Logger.logDEBUG(prld.size() + "");
		for (PreloadedHoliday aPrld : prld) {
			if (aPrld.getMonth() == month && aPrld.getDay() == day) {
				Logger.logDEBUG(month + " " + day + " " + year);
				Logger.logDEBUG(aPrld.getName());
				Logger.logDEBUG("PASS ALL");
			} else if (aPrld.getMonth() == month) {
				Logger.logDEBUG(month + " " + day + " " + year);
				Logger.logDEBUG(aPrld.getName());
				Logger.logDEBUG("PASS MONTH");
			} else if (aPrld.getDay() == day) {
				Logger.logDEBUG(month + " " + day + " " + year);
				Logger.logDEBUG(aPrld.getName());
				Logger.logDEBUG("PASS DAY");
			} else {
				Logger.logDEBUG(month + " " + day + " " + year);
				Logger.logDEBUG(aPrld.getName());
				Logger.logDEBUG("FAIL");
			}
		}
	}

	private void save () {
		try {

		} catch (Exception e) {

		}
	}

	public int getMonth () {
		return month;
	}

	public void setMonth (int month) {
		this.month = month;
	}

	public int getDay () {
		return day;
	}

	public void setDay (int day) {
		this.day = day;
	}

	public int getYear () {
		return year;
	}

	public void setYear (int year) {
		this.year = year;
	}
}
