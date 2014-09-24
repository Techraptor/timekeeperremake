package com.falconraptor.timekeeper.extra.Calender;

import com.falconraptor.timekeeper.references.References;
import com.falconraptor.utilities.logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

//import java.util.Calendar;

public class Calender extends JFrame {
    public final static String log = References.log + ".extra.utilities.Calender.Calender.";
    public ArrayList<JPanel> p = new ArrayList<>(0);
    public ArrayList<JButton> b = new ArrayList<>(0);
    public ArrayList<JLabel> l = new ArrayList<>(0);
    public JLabel[] ld = new JLabel[7];
    public boolean[] nm = new boolean[43];
    //public editdays ed = new editdays();
    public JButton left = new JButton(""), right = new JButton("");
    String[] days = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    int firstDayOfMonth, lastDayOfMonth, lengthOfMonth;
    LocalDate currentDate = LocalDate.now();
    LocalDate workingDate = currentDate;
/*  public int curDate = currentDate.getDayOfMonth();
    public int curMonth = currentDate.getMonthValue();
    public int curYear = currentDate.getYear(); */
    //public==public==public==public==public==public==public==public==public==public==public==public==public==public==public
    //Calendar c = Calendar.getInstance();
    //public int place = c.get(5);

    public Calender() {
        super("Calender");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(false);
        setContentPane(setgui());
        pack();
    }

    private JPanel setgui() {
        Logger.logINFO("Starting Calendar...");
        p.add(new JPanel(new GridLayout(8, 7, 0, 0))); //creating the layout of calendar, currently blank
        l.add(new JLabel(months[(currentDate.getMonth().getValue()) - 1] + " " + currentDate.getYear())); //getting the name of the month to add to the calendar
        p.get(0).add(left); //adding the left arrow
        for (int i = 0; i < 2; i++) p.get(0).add(new JLabel("", 0));// adding blank spaces
        p.get(0).add(l.get(0));//adding the month name
        for (int i = 0; i < 2; i++) p.get(0).add(new JLabel("", 0));//adding blank spaces
        p.get(0).add(right);//adding the right arrow
        //adding the names of the days to the calendar
        for (int i = 1; i < 8; i++) {
            ld[i - 1] = new JLabel(days[i], 0);
            p.get(0).add(ld[i - 1]);
        }
        //adding the editable labels for the dates
        for (int i = 0; i < 42; i++) {
            p.add(new JPanel(new GridLayout(2, 1, 0, 0)));
            l.add(new JLabel("", 0));
            b.add(new JButton("Edit"));
            p.get(i + 1).add(l.get(i + 1));
            p.get(0).add(p.get(i + 1));
        }

        setDates();
        setCalender();

        //adding action for the button to go back a month
        left.addActionListener(e -> {
            if (workingDate.getMonthValue() == 1) {
                workingDate = workingDate.withYear(workingDate.getYear() - 1);
                workingDate = workingDate.withMonth(12);
            } else {
                workingDate = workingDate.withMonth(workingDate.getMonthValue() - 1);
            }
            setDates();
            setCalender();

            //this is a comment
        });
        right.addActionListener(e -> {
            if (workingDate.getMonthValue() + 1 == 13) {
                workingDate = workingDate.withMonth(1);
                workingDate = workingDate.withYear(workingDate.getYear() + 1);
            } else {
                workingDate = workingDate.withMonth(workingDate.getMonthValue() + 1);
            }
            setDates();
            setCalender();
        });

        return p.get(0);
    }

    private void setCalender() {
        l.get(0).setText(months[(workingDate.getMonth().getValue()) - 1] + " " + workingDate.getYear());
        //adding blank (soon days of prev month) entries if the month doesn't start on 0 (Monday)
        for (int i = 1; i < firstDayOfMonth; i++) {
            l.get(i).setText(" ");
        }
        //adding the days of th month
        for (int i = firstDayOfMonth; i <= lengthOfMonth + firstDayOfMonth; i++) {
            //CALENDAR DOES NOT WORK IF MONTH STARTS ON SATURDAY
            if (i - firstDayOfMonth + 1 == currentDate.getDayOfMonth() && workingDate.getMonthValue() == currentDate.getMonthValue() && workingDate.getYear() == currentDate.getYear()) {
                l.get(i - 1).setForeground(Color.CYAN);
                l.get(i - 1).setText("" + (i - firstDayOfMonth + 1));
            } else {
                l.get(i - 1).setForeground(Color.BLACK);
                l.get(i - 1).setText("" + (i - firstDayOfMonth + 1));
            }
        }
        //adding the blank entries after the month (will be next months days)
        for (int i = lengthOfMonth + firstDayOfMonth; i < 42; i++) {
            l.get(i - 1).setText(" ");
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////pack();

    }

    private void setDates() {
        firstDayOfMonth = workingDate.getDayOfWeek().getValue();
        lengthOfMonth = workingDate.lengthOfMonth();
        //workingDate = workingDate.withDayOfMonth(lengthOfMonth);
        lastDayOfMonth = workingDate.getDayOfWeek().getValue();

    }

    public void appear() {
        setVisible(true);
    }
}