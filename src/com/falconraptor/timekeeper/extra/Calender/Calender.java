package com.falconraptor.timekeeper.extra.Calender;

import com.falconraptor.timekeeper.references.References;
import com.falconraptor.utilities.logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class Calender extends JFrame {
    public final static String log = References.log + ".extra.utilities.Calender.Calender.";
    public ArrayList<JPanel> p = new ArrayList<>(0);
    public ArrayList<JButton> b = new ArrayList<>(0);
    public ArrayList<JButton> d = new ArrayList<>(0);
    //public ArrayList<JLabel> l = new ArrayList<>(0);
    public ArrayList<JMenu> m = new ArrayList<>(0);
    public ArrayList<JMenuItem> mi = new ArrayList<>(0);
    public ArrayList<ActionListener> a = new ArrayList<>(0);
    public JMenuBar menu = new JMenuBar();
    public JLabel[] ld = new JLabel[7];
    public boolean[] nm = new boolean[43];
    //public editdays ed = new editdays();
    public JButton left = new JButton(""), right = new JButton(""), selectDate = new JButton("Go to");
    int firstDayOfMonth, lastDayOfMonth, lengthOfMonth;
    LocalDate currentDate = LocalDate.now();
    LocalDate workingDate = currentDate.withDayOfMonth(1);

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
        setJMenuBar(menu); // adding the menuBar
        setFileMenu();
        setUtilitiesMenu();
        setCustomizeMenu();
        for (int i = 0; i <= 2; i++) {
            menu.add(m.get(i));
        }
        p.add(new JPanel(new GridLayout(8, 7, 0, 0))); //creating the layout of calendar, currently blank
        d.add(new JButton(currentDate.getMonth() + " " + currentDate.getYear())); //getting the name of the month to add to the calendar
        p.get(0).add(left); //adding the left arrow
        for (int i = 0; i < 2; i++) p.get(0).add(new JLabel("", 0));// adding blank spaces
        p.get(0).add(d.get(0));//adding the month name
        //p.get(0).add(selectDate);//adding the goto button
        for (int i = 0; i < 2; i++) p.get(0).add(new JLabel("", 0));//adding blank spaces
        p.get(0).add(right);//adding the right arrow
        //adding the names of the days to the calendar
        for (int i = 1; i < 8; i++) {
            ld[i - 1] = new JLabel(getDateLabel(i), 0);
            p.get(0).add(ld[i - 1]);
        }
        //adding the editable labels for the dates
        for (int i = 0; i < 42; i++) {
            p.add(new JPanel(new GridLayout(2, 1, 0, 0)));
            d.add(new JButton(""));
            d.get(i).setBorderPainted(false);
            d.get(i).setContentAreaFilled(false);

            b.add(new JButton("Edit"));
            p.get(i + 1).add(d.get(i + 1));
            p.get(0).add(p.get(i + 1));
        }

        setDates();
        setCalender();

        //adding action for the button to go back a month
        left.addActionListener(e -> {
            removeActionListeners();
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
            removeActionListeners();
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

        d.get(0).setText(workingDate.getMonth() + " " + workingDate.getYear());
        a.add(clicked(0, 0, 0));
        d.get(0).addActionListener(a.get(0));
        //adding blank (soon days of prev month) entries if the month doesn't start on 1 (Monday)
        for (int i = 1; i < firstDayOfMonth; i++) {
            d.get(i).setText(" ");
            a.add(clicked(0, 0, 0));
            d.get(i).addActionListener(a.get(i));
        }
        //adding the days of th month
        for (int i = firstDayOfMonth; i <= lengthOfMonth + firstDayOfMonth; i++) {
            if (i - firstDayOfMonth + 1 == currentDate.getDayOfMonth() && workingDate.getMonthValue() == currentDate.getMonthValue() && workingDate.getYear() == currentDate.getYear()) {
                d.get(i).setForeground(Color.CYAN);
                d.get(i).setText("" + (i - firstDayOfMonth + 1));
                a.add(clicked(i - firstDayOfMonth + 1, workingDate.getMonthValue(), workingDate.getYear()));
                d.get(i).addActionListener(a.get(i));

            } else {
                d.get(i).setForeground(Color.BLACK);
                d.get(i).setText("" + (i - firstDayOfMonth + 1));
                a.add(clicked(i - firstDayOfMonth + 1, workingDate.getMonthValue(), workingDate.getYear()));
                d.get(i).addActionListener(a.get(i));
            }
        }
        //adding the blank entries after the month (will be next months days)
        for (int i = lengthOfMonth + firstDayOfMonth; i < 42; i++) {
            d.get(i).setText(" ");
            a.add(clicked(0, 0, 0));
            d.get(i).addActionListener(a.get(i));
        }
        //pack();

    }

    private int fixday(int day) {
        day++;
        if (day == 8) day = 1;
        return day;
    }

    private void setDates() {
        firstDayOfMonth = fixday(workingDate.getDayOfWeek().getValue());
        lengthOfMonth = workingDate.lengthOfMonth();
        //workingDate = workingDate.withDayOfMonth(lengthOfMonth);
        lastDayOfMonth = workingDate.getDayOfWeek().getValue();

    }

    private void setFileMenu() {
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        //addActionListener
        JMenuItem save = new JMenuItem("Save");
        //addActionListener
        JMenuItem reset = new JMenuItem("Reset");
        //addActionListener
        JMenuItem exit = new JMenuItem("Exit");
        exit = setMenuItem(exit, KeyEvent.VK_W, "Exit Calendar", KeyEvent.VK_W, ActionEvent.CTRL_MASK);
        exit.addActionListener(e -> {
            Logger.logINFO("Exiting Calendar");
            dispose();
        });
        file.add(open);
        file.add(save);
        file.addSeparator();
        file.add(reset);
        file.addSeparator();
        file.add(exit);
        m.add(file);
    }


    private void setUtilitiesMenu() {
        JMenu utils = new JMenu("Utilities");
        JMenuItem goTo = new JMenuItem("Go To Date");
        //addActionListener


        utils.add(goTo);
        m.add(utils);
    }

    private void setCustomizeMenu() {
        JMenu custom = new JMenu("Customize");
        JMenuItem bgColor = new JMenuItem("Set Calendar Color");
        //addActionListener
        JMenuItem bgImage = new JMenuItem("Set Custom Calender Image");
        //addActionListener
        JMenuItem fontStyle = new JMenuItem("Set Font");
        //addActionListener
        JMenuItem fontColor = new JMenuItem("Set Font Color");
        //addActionListener
        JMenuItem fontSize = new JMenuItem("Set Font Size");
        //addActionListener
        custom.add(bgColor);
        custom.add(bgImage);
        custom.addSeparator();
        custom.add(fontStyle);
        custom.add(fontColor);
        custom.add(fontSize);
        m.add(custom);
    }

    public void appear() {
        setVisible(true);
    }

    private ActionListener clicked(final int date, final int month, final int year) {
        try {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Logger.logINFO("" + month + "/" + date + "/" + year);
                }
            };
        } catch (Exception e) {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Logger.logERROR(log + "clicked]" + e);
                }
            };
        }
    }

    private String getDateLabel(int day) {
        LocalDate date = currentDate.withYear(2014).withMonth(9).withDayOfMonth(7);
        date = date.withDayOfMonth(date.getDayOfMonth() + day - 1);
        return new String("" + date.getDayOfWeek());
    }

    private JMenuItem setMenuItem(JMenuItem j, int mn, String tt, int mn2, int ae) {
        JMenuItem i = j;
        i.setMnemonic(mn);
        i.setToolTipText(tt);
        i.setAccelerator(KeyStroke.getKeyStroke(mn2, ae));
        return i;
    }

    private void removeActionListeners() {
        for (int i = 0; i < 42; i++) {
            d.get(i).removeActionListener(a.get(i));
        }
    }

}