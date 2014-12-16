package com.falconraptor.timekeeper.guis;

import com.darkleach7.extra.*;
import com.falconraptor.timekeeper.references.*;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.util.*;

public class Timekeeper extends JFrame {
	private JPanel panel1;
	private JLabel date;
	private JLabel time;
	private JButton extras;

	public Timekeeper () {
		super("Timekeeper");
		setContentPane(panel1);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		extras.addActionListener(e -> {
			References.extras = new Extras();
			References.extras.setVisible(true);
		});
		addWindowListener(References.shutdownProgram());
		//Timer timer=new Timer();
		//timer.schedule(new ChangeTime(),0,1000);
		References.threads.add(new Thread(threadForTime()));
		References.threads.get(References.threads.size() - 1).setName("DateTime");
		References.threads.get(References.threads.size() - 1).run();
		setMaximumSize(new Dimension(200, 80));
		setMinimumSize(new Dimension(200, 80));
	}

	private Runnable threadForTime () {
		return () -> {
			java.util.Timer timer = new java.util.Timer();
			timer.schedule(new ChangeTime(), 0, 1000);
		};
	}

	@Override
	public void setVisible (boolean b) {
		References.loading.setVisible(false);
		System.out.println(References.loading.getValue());
		References.loading.dispose();
		super.setVisible(b);
	}

	private class ChangeTime extends TimerTask {
		@Override
		public void run () {
			LocalDateTime localDateTime = LocalDateTime.now();
			String dates = "", times = "";
			int hour = localDateTime.toLocalTime().getHour();
			boolean am = true;
			if (hour > 12) {
				hour -= 12;
				am = false;
			}
			dates += localDateTime.toLocalDate().getMonthValue() + "\\";
			dates += localDateTime.toLocalDate().getDayOfMonth() + "\\";
			dates += localDateTime.toLocalDate().getYear();
			times += hour + ":";
			times += localDateTime.toLocalTime().getMinute() + ":";
			times += localDateTime.toLocalTime().getSecond() + " ";
			if (am) times += "AM";
			else times += "PM";
			date.setText(dates);
			time.setText(times);
			repaint();
			validate();
		}
	}
}
