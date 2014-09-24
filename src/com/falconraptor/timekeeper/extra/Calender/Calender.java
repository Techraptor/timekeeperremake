package com.falconraptor.timekeeper.extra.Calender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;

//import java.util.Calendar;

public class calender extends JFrame {
    public static String log = "[com.falconraptor.timekeeper.extra.utilities.Calender.calender.";
    public ArrayList<JPanel> p = new ArrayList<JPanel>(0);
    public ArrayList<JButton> b = new ArrayList<JButton>(0);
    public ArrayList<JLabel> l = new ArrayList<JLabel>(0);
    public ArrayList<Day> d = new ArrayList<Day>(0);
    public JLabel[] ld = new JLabel[7];
    public boolean[] nm = new boolean[43];
    //public editdays ed = new editdays();
    public JButton left = new JButton(""), right = new JButton("");
    String[] days = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    int firstDayOfMonth;
    int lastDayOfMonth;
    int lengthOfMonth;
    LocalDate currentDate = LocalDate.now().withMonth(12);
    LocalDate workingDate = currentDate.withDayOfMonth(1);
/*  public int curDate = currentDate.getDayOfMonth();
    public int curMonth = currentDate.getMonthValue();
    public int curYear = currentDate.getYear(); */

    //Calendar c = Calendar.getInstance();
    //public int place = c.get(5);

    public calender() {
        super("Calender");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(false);
        setDates();
        super.setContentPane(setgui());
        super.pack();
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                //   ed.dispose();
                //   ed.e.dispose();
                //   ed.c.dispose();
            }
        });

    }

    private JPanel setgui() {
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
        left.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (workingDate.getMonthValue() == 1) {
                    workingDate = workingDate.withYear(workingDate.getYear() - 1);
                    workingDate = workingDate.withMonth(12);
                } else {
                    workingDate = workingDate.withMonth(workingDate.getMonthValue() - 1);
                }
                setDates();
                setCalender();

                //this is a comment
            }
        });
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workingDate = workingDate.withMonth(workingDate.getMonthValue() + 1);
                if (workingDate.getMonthValue() == 1) {
                    workingDate = workingDate.withYear(workingDate.getYear() + 1);
                }
                setDates();
                setCalender();
            }
        });

        return p.get(0);
    }

    private void setCalender() {
        l.get(0).setText(months[(workingDate.getMonth().getValue()) - 1] + " " + workingDate.getYear());
        //adding blank (soon days of prev month) entries if the month doesn't start on 0 (Monday)
        for (int i = 1; i <= firstDayOfMonth; i++) {
            l.get(i).setText("-");
        }
        //adding the days of th month
        for (int i = firstDayOfMonth/* + 1*/; i <= lengthOfMonth + firstDayOfMonth; i++) {
            l.get(i).setText("" + (i - firstDayOfMonth + 1));
        }
        //adding the blank entries after the month (will be next months days)
        for (int i = lengthOfMonth + firstDayOfMonth; i < 43; i++) {
            //l.get(i).setText("-");
        }
    }

    private void setDates() {
        firstDayOfMonth = workingDate.getDayOfWeek().getValue();
        lengthOfMonth = workingDate.lengthOfMonth();
        workingDate = workingDate.withDayOfMonth(lengthOfMonth);
        lastDayOfMonth = workingDate.getDayOfWeek().getValue();
        int p = 4;
    }

    public void appear() {
        super.setVisible(true);
    }
}