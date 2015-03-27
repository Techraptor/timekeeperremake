package com.falconraptor.timekeeper.guis;

import com.darkleach7.extra.*;
import com.falconraptor.timekeeper.other.*;
import com.falconraptor.timekeeper.school.Class;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.util.*;

import static com.falconraptor.timekeeper.other.References.*;

public class Timekeeper extends JFrame {
	private JPanel panel1;
	private JLabel date;
	private JLabel time;
	private JButton extras;
	private JButton options;
	private JLabel left;
	private JLabel leftTime;
	private JLabel since;
	private JLabel sinceTime;
	private int lastSecond = -1, lastMinute = -1;

	public Timekeeper () {
		super("Timekeeper");
		setContentPane(panel1);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		extras.addActionListener(e -> {
			References.extras = new Extras();
			References.extras.setVisible(true);
		});
		addWindowListener(shutdownProgram());
		setMaximumSize(new Dimension(200, 80));
		setMinimumSize(new Dimension(200, 80));
	}

	@Override
	public void setVisible (boolean b) {
		loading.setVisible(false);
		System.out.println(loading.getValue());
		loading.dispose();
		super.setVisible(b);
		threads.add(new Thread(threadForTime()));
		threads.get(threads.size() - 1).setName("DateTime");
		threads.get(threads.size() - 1).start();
	}

	private Runnable threadForTime () {
		return () -> new java.util.Timer().schedule(new ChangeTime(), 0, 900);
	}

	private void doTime () {
		leftTime.setText("");
		LocalTime temp = checkTime();
		if (temp == null) temp = LocalTime.of(0, 0);
		else if (temp.getHour() == 0 && temp.getMinute() == 0 && temp.getSecond() == 0 || temp.getSecond() == 1) {
			if (temp.getSecond() == 1 && temp.getNano() < settings.usHolidays.size())
				leftTime.setText(settings.usHolidays.get(temp.getNano()).getName());
			else if (temp.getNano() < settings.atech.holidays.size())
				leftTime.setText(settings.atech.holidays.get(temp.getNano()).getName());
		}
		if (leftTime.getText().equals("")) {
			if (temp.getHour() == 0) leftTime.setText(temp.getMinute() + "");
			else leftTime.setText(temp.getHour() + ":" + temp.getMinute());
		}
		sinceTime.setText("");
		temp = checkTime2();
		if (temp == null) temp = LocalTime.of(0, 0);
		else if (temp.getHour() == 0 && temp.getMinute() == 0 && temp.getSecond() == 0 || temp.getSecond() == 1) {
			if (temp.getSecond() == 1 && temp.getNano() < settings.usHolidays.size())
				sinceTime.setText(settings.usHolidays.get(temp.getNano()).getName());
			else if (temp.getNano() < settings.atech.holidays.size())
				sinceTime.setText(settings.atech.holidays.get(temp.getNano()).getName());
		}
		if (sinceTime.getText().equals("")) {
			if (temp.getHour() == 0) sinceTime.setText(temp.getMinute() + "");
			else sinceTime.setText(temp.getHour() + ":" + temp.getMinute());
		}
	}

	private LocalTime checkTime () {
		LocalDate date = LocalDate.now();
		for (int i = 0; i < settings.usHolidays.size(); i++) {
			if (settings.usHolidays.get(i).getMonth() == date.getMonthValue() && settings.usHolidays.get(i).getDay() == date.getDayOfMonth())
				return LocalTime.of(0, 0, 1, i);
		}
		for (int i = 0; i < settings.atech.holidays.size(); i++) {
			if (settings.atech.holidays.get(i).getMonth() == date.getMonthValue() && settings.atech.holidays.get(i).getDay() == date.getDayOfMonth())
				return LocalTime.of(0, 0, 0, i);
		}
		if (Objects.equals(date.getDayOfWeek().name(), "WEDNESDAY"))
			for (com.falconraptor.timekeeper.school.Class a : settings.atech.wednesday.aClass) {
				if (checkTimeLeft(a) != null) return checkTimeLeft(a);
			}
		else if (date.getDayOfWeek().name().equals("THURSDAY"))
			for (com.falconraptor.timekeeper.school.Class a : settings.atech.thursday.aClass) {
				if (checkTimeLeft(a) != null) return checkTimeLeft(a);
			}
		else for (com.falconraptor.timekeeper.school.Class a : settings.atech.normal.aClass) {
				if (checkTimeLeft(a) != null) return checkTimeLeft(a);
			}
		return null;
	}

	private LocalTime checkTimeLeft (Class a) {
		//60 * (times[3] - hour) + times[4] - minute
		if (a == null) return null;
		LocalTime temp = LocalTime.now(), left = LocalTime.of(0, 0, 0, 0);
		double time = 60 * (a.getEnd().getHour() - temp.getHour()) + (a.getEnd().getMinute() - temp.getMinute());
		if (time < 0) return null;
		left = left.withHour((int) time / 60);
		left = left.withMinute((int) time % 60);
		if (left.getHour() * 60 + left.getMinute() > a.getLength()) return null;
		else if (left.compareTo(LocalTime.of(0, 0, 0, 0)) == 0) return null;
		return left;
	}

	private LocalTime checkTime2 () {
		LocalDate date = LocalDate.now();
		for (int i = 0; i < settings.usHolidays.size(); i++) {
			if (settings.usHolidays.get(i).getMonth() == date.getMonthValue() && settings.usHolidays.get(i).getDay() == date.getDayOfMonth())
				return LocalTime.of(0, 0, 1, i);
		}
		for (int i = 0; i < settings.atech.holidays.size(); i++) {
			if (settings.atech.holidays.get(i).getMonth() == date.getMonthValue() && settings.atech.holidays.get(i).getDay() == date.getDayOfMonth())
				return LocalTime.of(0, 0, 0, i);
		}
		if (Objects.equals(date.getDayOfWeek().name(), "WEDNESDAY"))
			for (com.falconraptor.timekeeper.school.Class a : settings.atech.wednesday.aClass) {
				if (checkTimeSince(a) != null) return checkTimeSince(a);
			}
		else if (date.getDayOfWeek().name().equals("THURSDAY"))
			for (com.falconraptor.timekeeper.school.Class a : settings.atech.thursday.aClass) {
				if (checkTimeSince(a) != null) return checkTimeSince(a);
			}
		else for (com.falconraptor.timekeeper.school.Class a : settings.atech.normal.aClass) {
				if (checkTimeSince(a) != null) return checkTimeSince(a);
			}
		return null;
	}

	private LocalTime checkTimeSince (Class a) {
		//60 * (hour - times[0]) + minute - times[1]
		if (a == null) return null;
		LocalTime temp = LocalTime.now(), left = LocalTime.of(0, 0, 0, 0);
		double time = 60 * (temp.getHour() - a.getStart().getHour()) + (temp.getMinute() - a.getStart().getMinute());
		if (time < 0) return null;
		left = left.withHour((int) time / 60);
		left = left.withMinute((int) time % 60);
		if (left.getHour() * 60 + left.getMinute() > a.getLength()) return null;
		else if (left.compareTo(LocalTime.of(0, 0, 0, 0)) == 0) return null;
		return left;
	}

	private class ChangeTime extends TimerTask {
		@Override
		public final void run () {
			LocalDateTime localDateTime = LocalDateTime.now();
			if (lastSecond == localDateTime.getSecond()) return;
			lastSecond = localDateTime.getSecond();
			String dates = "", times = "";
			int hour = localDateTime.getHour();
			boolean am = true;
			if (hour > 12) {
				hour -= 12;
				am = false;
			}
			dates += localDateTime.getMonthValue() + "\\" + localDateTime.getDayOfMonth() + "\\" + localDateTime.getYear();
			times += hour + ":" + localDateTime.getMinute() + ":" + localDateTime.getSecond() + " ";
			if (am) times += "AM";
			else times += "PM";
			date.setText(dates);
			time.setText(times);
			if (lastMinute != localDateTime.getMinute() || lastMinute == -1) {
				doTime();
				lastMinute = localDateTime.getMinute();
			}
			repaint();
			validate();
		}
	}
}
