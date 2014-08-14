package com.falconraptor.timekeeper.main;

import com.falconraptor.timekeeper.extra.extras;
import com.falconraptor.timekeeper.options.optionsdialog;
import com.falconraptor.utilities.logger.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimerTask;

public class Main extends JFrame {
    public static String log = "[com.falconraptor.timekeeper.main.Main.";
    ArrayList<JLabel> labels = new ArrayList<JLabel>(0);
    boolean firstlunch = false, assemblybool = false, holidaybool = false, firstbeep = false, testing = false;
    ButtonGroup group = new ButtonGroup();
    optionsdialog ro = new optionsdialog();
    JPanel p1 = new JPanel(new GridLayout(1, 1, 0, 0)), p2 = new JPanel(new GridLayout(2, 1, 0, 0)), p3 = new JPanel(new GridLayout(1, 2, 0, 0));
    ArrayList<JPanel> p = new ArrayList<JPanel>(0);
    JRadioButton firstlunchrb = new JRadioButton("First Lunch"), secondlunch = new JRadioButton("Second Lunch");
    JButton assembly = new JButton("Assembly Day Toggle"), options = new JButton("Options"), feedback = new JButton("Feedback"), extras = new JButton("Extras");
    ArrayList<Holiday> holidays = new ArrayList<Holiday>(0);
    ArrayList<Schedule> schedulenormal = new ArrayList<Schedule>(0), schedulewednesday = new ArrayList<Schedule>(0), schedulethursday = new ArrayList<Schedule>(0), scheduleassembly = new ArrayList<Schedule>(0), scheduleseniorexam = new ArrayList<Schedule>(0), schedulefinalone = new ArrayList<Schedule>(0), schedulefinaltwo = new ArrayList<Schedule>(0), schedulefinalthree = new ArrayList<Schedule>(0), schedulelastday = new ArrayList<Schedule>(0);
    Box box = Box.createVerticalBox();
    String[] schools = new String[]{"ATECH"};
    JComboBox school = new JComboBox(schools);
    String loadschool = "atech";
    int donechecking = 0;
    extras ex;
    checkversion run = new checkversion();

    public Main() {
        super("TIMEKEEPER");
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                ro.saveoptions();
                Logger.saveLog("timekeeperlog.log");
            }
        });
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(500, 500);
        super.pack();
        super.setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        Main time = new Main();
        checkversion run = new checkversion();
        run.checkversiontime();
        if (run.updated) {
            try {
                Thread.sleep(1000000);
                System.exit(0);
            } catch (Exception e) {
                if (Logger.level <= 5) Logger.logERROR(e);
            }
        }
        try {
            File folder = new File("resources");
            if (!folder.exists()) folder.mkdir();
            folder = new File("resources\\images");
            if (!folder.exists()) folder.mkdir();
            folder = new File("resources\\jars");
            if (!folder.exists()) folder.mkdir();
            folder = new File("resources");
            new File(folder, "timekeeper" + time.loadschool + "holidays.txt").delete();
            new File(folder, "timekeeper" + time.loadschool + "schedule.txt").delete();
            if (new File(folder, "matrix.gif").exists())
                new File(folder, "matrix.gif").renameTo(new File(folder, "images\\matrix.gif"));
        } catch (Exception e) {
            if (Logger.level <= 5) Logger.logERROR(log + "main] " + e);
        }
        time.getholidays();
        time.getschedule();
        time.clockmain();
    }

    public void clockmain() {
        try {
            ex = new extras();
        } catch (Exception e) {
            if (Logger.level <= 5) Logger.logWARNING(log + "clockmain] " + e);
        }
        ro.loadoptions();
        fixcolor();
        for (int i = 0; i < 6; i++) labels.add(new JLabel("", 0));
        for (int i = 0; i < 15; i++) p.add(new JPanel(new FlowLayout()));
        assembly.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                assemblybool = (!assemblybool);
            }
        });
        options.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ro.appear();
            }
        });
        feedback.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/falconraptor/timekeeperremake/issues").toURI());
                } catch (Exception e) {
                    if (Logger.level <= 5) Logger.logERROR(log + "clockmain] " + e);
                }
            }
        });
        extras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ex.appear();
            }
        });
        if (ro.lunches) firstlunchrb.setSelected(true);
        else secondlunch.setSelected(true);
        secondlunch.setActionCommand("2");
        firstlunchrb.setActionCommand("1");
        group.add(firstlunchrb);
        group.add(secondlunch);
        p.get(1).add(labels.get(1));
        p.get(1).add(labels.get(0));
        p1.add(school);
        school.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                String sc = (String) cb.getSelectedItem();
                loadschool = sc.toLowerCase();
                getholidays();
                getschedule();
            }
        });
        p2.add(firstlunchrb);
        p2.add(secondlunch);
        p3.add(p1);
        p3.add(p2);
        p.get(2).add(p3);
        labels.add(new JLabel("Time Left: ", 0));
        p.get(4).add(labels.get(6));
        p.get(4).add(labels.get(2));
        labels.add(new JLabel(" Minutes", 0));
        p.get(4).add(labels.get(7));
        Calendar.getInstance();
        if (Calendar.getInstance().get(Calendar.getInstance().DAY_OF_WEEK) == 7 || Calendar.getInstance().get(Calendar.getInstance().DAY_OF_WEEK) == 1) {
            labels.add(new JLabel("", (int) JLabel.CENTER_ALIGNMENT));
            labels.add(new JLabel("", (int) JLabel.CENTER_ALIGNMENT));
        } else {
            labels.add(new JLabel("Class Time: ", (int) JLabel.CENTER_ALIGNMENT));
            p.get(5).add(labels.get(8));
            p.get(5).add(labels.get(5));
            labels.add(new JLabel("Next: ", (int) JLabel.CENTER_ALIGNMENT));
            p.get(6).add(labels.get(9));
        }
        labels.add(new JLabel("Time Since Class Started: ", 0));
        p.get(7).add(labels.get(10));
        p.get(7).add(labels.get(3));
        labels.add(new JLabel(" Minutes", 0));
        p.get(7).add(labels.get(11));
        p.get(8).add(labels.get(4));
        labels.add(new JLabel("", 0));
        labels.add(new JLabel("", 0));
        labels.add(new JLabel("Next: ", 0));
        p.get(9).add(labels.get(14));
        p.get(9).add(labels.get(13));
        p.get(6).add(labels.get(12));
        p.set(10, new JPanel(new GridLayout(1, 1, 0, 0)));
        p.get(10).add(assembly);
        p.set(11, new JPanel(new GridLayout(1, 3, 0, 0)));
        p.get(11).add(extras);
        p.get(11).add(options);
        p.get(11).add(feedback);
        for (int i = 1; i < 12; i++) {
            if (((Calendar.getInstance().get(Calendar.getInstance().DAY_OF_WEEK) == 7 || Calendar.getInstance().get(Calendar.getInstance().DAY_OF_WEEK) == 1) && (i == 5 || i == 6)) || i == 3)
                continue;
            box.add(p.get(i));
        }
        p.get(0).add(box);
        checkbackground();
        packframe();
        super.setVisible(true);
        new java.util.Timer().schedule(new UpdateUITask(), 0, 1000);
        for (JPanel jp : ro.p) jp.setOpaque(true);
        for (JPanel jp : ex.p) jp.setOpaque(true);
        for (JButton b : ex.b) b.setOpaque(true);
    }

    private void checkbackground() {
        if (!ro.animatedbackground) {
            super.setContentPane(p.get(0));
            return;
        }
        try {
            ro.warn();
            final Image image;
            image = new ImageIcon(ro.urlf + "").getImage();
            ImagePanel imagePanel = new ImagePanel(image);
            imagePanel.setLayout(new GridLayout(1, 1, 5, 5));
            imagePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
            imagePanel.add(p.get(0));
            super.setContentPane(imagePanel);
        } catch (Exception e) {
            if (Logger.level <= 5) Logger.logERROR(log + "checkbackground] " + e);
            super.setContentPane(p.get(0));
        }
    }

    private void checkradio() {
        if (group.getSelection().getActionCommand().equals("1")) firstlunch = true;
        else firstlunch = false;
        p.get(3).validate();
        p.get(3).repaint();
    }

    private void packframe() {
        super.pack();
    }

    private void weekends(int daynum, Calendar time) {
        if (daynum == 7 || daynum == 1) {
            int hour = time.get(time.HOUR_OF_DAY), minute = time.get(time.MINUTE);
            int[] times = new int[]{0, 0, 0, 23, 59};
            boolean first = checkam(times[0]), second = checkam(times[3]);
            String timec = "";
            if (first) timec += addzeros(times[0]) + ":" + addzeros(times[1]) + "AM -";
            else timec += addzeros(times[0] - 12) + ":" + addzeros(times[1]) + "PM -";
            if (second) timec += addzeros(times[3]) + ":" + addzeros(times[4]) + "AM";
            else timec += addzeros(times[3] - 12) + ":" + addzeros(times[4]) + "PM";
            labels.get(5).setText(timec);
            labels.get(3).setText((60 * (hour - times[0]) + minute - times[1]) + "");
            labels.get(2).setText((60 * (times[3] - hour) + times[4] - minute) + "");
        }
        if (daynum == 7) {
            labels.get(4).setText("Not A School Day");
            labels.get(13).setText("Not A School Day");
        } else if (daynum == 1) {
            labels.get(4).setText("Not A School Day");
            donechecking = 1;
            calctime(time.get(time.HOUR_OF_DAY), time.get(time.MINUTE), time.get(time.DAY_OF_WEEK) + 1, true);
        } else calctime(time.get(time.HOUR_OF_DAY), time.get(time.MINUTE), time.get(time.DAY_OF_WEEK), false);
    }

    private Boolean checkam(int hour) {
        if (hour >= 12) {
            if (hour != 12) return false;
            else return true;
        } else return true;
    }

    private void calctime(int hour, int minute, int day, boolean skip) {
        String[] holiday = checkholidays();
        if (holiday[0].equals("") || holiday[0].equals(null)) holidaybool = false;
        else if (holiday[0].equals("Assembly Day")) assemblybool = true;
        else if (holiday[0].equals("Senior Exams")) testing = true;
        else if (holiday[0].indexOf("Final Exam Day") != -1) testing = true;
        else if (holiday[0].equals("Last Day Of School")) testing = true;
        else if (holiday[0].equals("Full Day")) testing = false;
        else holidaybool = true;
        if (holidaybool) {
            labels.get(4).setText(holiday[0]);
            labels.get(13).setText(holiday[1]);
        } else if (!skip) checktime(hour, minute, day);
        else {
            Schedule test = schedulenormal.get(0);
            int[] times = new int[6];
            times[0] = test.getstarthour();
            times[1] = test.getstartminute();
            times[3] = test.getendhour();
            times[4] = test.getendminute();
            times[5] = test.getlunch();
            if (donechecking == 1) {
                boolean first = checkam(times[0]), second = checkam(times[3]);
                String time = "";
                if (first) time += addzeros(times[0]) + ":" + addzeros(times[1]) + "AM -";
                else time += addzeros(times[0] - 12) + ":" + addzeros(times[1]) + "PM -";
                if (second) time += addzeros(times[3]) + ":" + addzeros(times[4]) + "AM";
                else time += addzeros(times[3] - 12) + ":" + addzeros(times[4]) + "PM";
                labels.get(12).setText(time);
                labels.get(13).setText(test.getclass());
            }
        }
        donechecking = 0;
    }

    private String addzeros(int num) {
        if (num < 10 && num >= 0) return "0" + num;
        else return num + "";
    }

    private void getholidays() {
        String filename = "resources/holidays/" + loadschool + "holidays.holidays";
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String out = reader.readLine();
            while (out != null) {
                String[] test = out.split(":");
                holidays.add(new Holiday(Integer.parseInt(test[0]), Integer.parseInt(test[1]), test[2]));
                out = reader.readLine();
            }
        } catch (Exception e) {
            if (Logger.level <= 5) Logger.logERROR(e);
        }
    }

    private void getschedule() {
        String filename = "resources/schedules/" + loadschool + "schedule.schedule";
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String out = reader.readLine();
            boolean normal = false, wednesday = false, thursday = false, assembly = false, senior = false, finalone = false, finaltwo = false, finalthree = false, lastday = false;
            while (out != null) {
                if (out.equals("normal")) {
                    normal = false;
                    wednesday = false;
                    thursday = false;
                    assembly = false;
                    senior = false;
                    finalone = false;
                    finaltwo = false;
                    finalthree = false;
                    lastday = false;
                    normal = true;
                } else if (out.equals("wednesday")) {
                    normal = false;
                    wednesday = false;
                    thursday = false;
                    assembly = false;
                    senior = false;
                    finalone = false;
                    finaltwo = false;
                    finalthree = false;
                    lastday = false;
                    wednesday = true;
                } else if (out.equals("thursday")) {
                    normal = false;
                    wednesday = false;
                    thursday = false;
                    assembly = false;
                    senior = false;
                    finalone = false;
                    finaltwo = false;
                    finalthree = false;
                    lastday = false;
                    thursday = true;
                } else if (out.equals("assembly")) {
                    normal = false;
                    wednesday = false;
                    thursday = false;
                    assembly = false;
                    senior = false;
                    finalone = false;
                    finaltwo = false;
                    finalthree = false;
                    lastday = false;
                    assembly = true;
                } else if (out.equals("Senior")) {
                    normal = false;
                    wednesday = false;
                    thursday = false;
                    assembly = false;
                    senior = false;
                    finalone = false;
                    finaltwo = false;
                    finalthree = false;
                    lastday = false;
                    senior = true;
                } else if (out.equals("Final1")) {
                    normal = false;
                    wednesday = false;
                    thursday = false;
                    assembly = false;
                    senior = false;
                    finalone = false;
                    finaltwo = false;
                    finalthree = false;
                    lastday = false;
                    finalone = true;
                } else if (out.equals("Final2")) {
                    normal = false;
                    wednesday = false;
                    thursday = false;
                    assembly = false;
                    senior = false;
                    finalone = false;
                    finaltwo = false;
                    finalthree = false;
                    lastday = false;
                    finaltwo = true;
                } else if (out.equals("Final3")) {
                    normal = false;
                    wednesday = false;
                    thursday = false;
                    assembly = false;
                    senior = false;
                    finalone = false;
                    finaltwo = false;
                    finalthree = false;
                    lastday = false;
                    finalthree = true;
                } else if (out.equals("Last")) {
                    normal = false;
                    wednesday = false;
                    thursday = false;
                    assembly = false;
                    senior = false;
                    finalone = false;
                    finaltwo = false;
                    finalthree = false;
                    lastday = false;
                    lastday = true;
                } else {
                    String[] test = out.split(":");
                    int[] nums = {Integer.parseInt(test[0]), Integer.parseInt(test[1]), Integer.parseInt(test[3]), Integer.parseInt(test[4]), 0};
                    if (test.length > 5) nums[4] = Integer.parseInt(test[5]);
                    if (normal) schedulenormal.add(new Schedule(nums[0], nums[1], test[2], nums[2], nums[3], nums[4]));
                    else if (wednesday)
                        schedulewednesday.add(new Schedule(nums[0], nums[1], test[2], nums[2], nums[3], nums[4]));
                    else if (thursday)
                        schedulethursday.add(new Schedule(nums[0], nums[1], test[2], nums[2], nums[3], nums[4]));
                    else if (assembly)
                        scheduleassembly.add(new Schedule(nums[0], nums[1], test[2], nums[2], nums[3], nums[4]));
                    else if (senior)
                        scheduleseniorexam.add(new Schedule(nums[0], nums[1], test[2], nums[2], nums[3], nums[4]));
                    else if (finalone)
                        schedulefinalone.add(new Schedule(nums[0], nums[1], test[2], nums[2], nums[3], nums[4]));
                    else if (finaltwo)
                        schedulefinaltwo.add(new Schedule(nums[0], nums[1], test[2], nums[2], nums[3], nums[4]));
                    else if (finalthree)
                        schedulefinalthree.add(new Schedule(nums[0], nums[1], test[2], nums[2], nums[3], nums[4]));
                    else if (lastday)
                        schedulelastday.add(new Schedule(nums[0], nums[1], test[2], nums[2], nums[3], nums[4]));
                }
                out = reader.readLine();
            }
        } catch (Exception e) {
            if (Logger.level <= 5) Logger.logERROR(log + "getschedule] " + e);
        }
    }

    private void checktime(int hour, int minute, int daynum) {
        if (assemblybool) for (Schedule s : scheduleassembly) startcheckingtime(s, hour, minute);
            //else if (testing) {
        else if (checkholidays()[0].equals("Senior Exams"))
            for (Schedule s : scheduleseniorexam) startcheckingtime(s, hour, minute);
        else if (checkholidays()[0].equals("Final Exam Day 1"))
            for (Schedule s : schedulefinalone) startcheckingtime(s, hour, minute);
        else if (checkholidays()[0].equals("Final Exam Day 2"))
            for (Schedule s : schedulefinaltwo) startcheckingtime(s, hour, minute);
        else if (checkholidays()[0].equals("Final Exam Day 3"))
            for (Schedule s : schedulefinalthree) startcheckingtime(s, hour, minute);
        else if (checkholidays()[0].equals("Last Day Of School"))
            for (Schedule s : schedulelastday) startcheckingtime(s, hour, minute);
        else if (checkholidays()[0].equals("Full Day") || daynum == 2 || daynum == 3 || daynum == 6)
            for (Schedule s : schedulenormal) startcheckingtime(s, hour, minute);
        else if (daynum == 4) for (Schedule s : schedulewednesday) startcheckingtime(s, hour, minute);
        else if (daynum == 5) for (Schedule s : schedulethursday) startcheckingtime(s, hour, minute);
    }

    private void startcheckingtime(Schedule test, int hour, int minute) {
        if (donechecking == 2) return;
        checklunches(test, hour, minute);
    }

    private void checklunches(Schedule test, int hour, int minute) {
        int[] times = new int[6];
        times[0] = test.getstarthour();
        times[1] = test.getstartminute();
        times[3] = test.getendhour();
        times[4] = test.getendminute();
        times[5] = test.getlunch();
        if ((firstlunch && times[5] == 1) || (!firstlunch && times[5] == 2) || times[5] == 0)
            donelunches(test, times, minute, hour);
    }

    private void donelunches(Schedule test, int[] times, int minute, int hour) {
        if (donechecking == 1) {
            printtimes(test, times, minute, hour);
            return;
        }
        checktimes(hour, minute, test, times);
    }

    private void printtimes(Schedule test, int[] times, int minute, int hour) {
        if (donechecking == 1) {
            if (test.getclass().equals("Passing Period")) return;
            donechecking = 2;
            boolean first = checkam(times[0]), second = checkam(times[3]);
            String time = "";
            if (first) time += addzeros(times[0]) + ":" + addzeros(times[1]) + "AM -";
            else time += addzeros(times[0] - 12) + ":" + addzeros(times[1]) + "PM -";
            if (second) time += addzeros(times[3]) + ":" + addzeros(times[4]) + "AM";
            else time += addzeros(times[3] - 12) + ":" + addzeros(times[4]) + "PM";
            labels.get(12).setText(time);
            labels.get(13).setText(test.getclass());
            return;
        }
        labels.get(4).setText(test.getclass());
        donechecking = 1;
        boolean first = checkam(times[0]), second = checkam(times[3]);
        String time = "";
        if (first) time += addzeros(times[0]) + ":" + addzeros(times[1]) + "AM -";
        else time += addzeros(times[0] - 12) + ":" + addzeros(times[1]) + "PM -";
        if (second) time += addzeros(times[3]) + ":" + addzeros(times[4]) + "AM";
        else time += addzeros(times[3] - 12) + ":" + addzeros(times[4]) + "PM";
        labels.get(5).setText(time);
        labels.get(3).setText((60 * (hour - times[0]) + minute - times[1]) + "");
        labels.get(2).setText((60 * (times[3] - hour) + times[4] - minute) + "");
        if (60 * (times[3] - hour) + times[4] - minute == 1 && !firstbeep) {
            ex.beep();
            firstbeep = true;
        } else if (!(60 * (times[3] - hour) + times[4] - minute == 1) && firstbeep) firstbeep = false;
    }

    private void checktimes(int hour, int minute, Schedule test, int[] times) {
        if (hour >= times[0] && hour <= times[3]) {
            if (times[1] > times[4]) {
                if ((minute >= times[1] && hour == times[0]) || (minute <= times[4] && hour == times[3]))
                    printtimes(test, times, minute, hour);
                else if (hour >= times[0] + 1 && hour <= times[3] - 1) printtimes(test, times, minute, hour);
            } else {
                if (minute >= times[1] && minute <= times[4] && times[0] == times[3])
                    printtimes(test, times, minute, hour);
                else if (times[0] < times[3]) {
                    if (hour == times[3]) {
                        if (minute >= 0 && minute <= times[4]) printtimes(test, times, minute, hour);
                    } else if (hour <= times[3] && hour >= times[0]) printtimes(test, times, minute, hour);
                    else {
                        if (minute >= times[1] && minute <= 60) printtimes(test, times, minute, hour);
                    }
                }
            }
        }
    }

    private String[] checkholidays() {
        Calendar time = Calendar.getInstance();
        int month = time.get(time.MONTH) + 1, daymonth = time.get(time.DAY_OF_MONTH);
        String[] test = new String[2];
        for (int i = 0; i < holidays.size(); i++) {
            if (month == holidays.get(i).getmonth() && daymonth == holidays.get(i).getday()) {
                test[0] = holidays.get(i).getname();
                if (i != holidays.size() - 1) test[1] = holidays.get(i + 1).getname();
                return test;
            }
        }
        return new String[]{""};
    }

    private void setcolors(JLabel l) {
        l.setForeground(ro.rc.foreground);
        l.setBackground(ro.rc.background);
    }

    private void setcolors(JButton b) {
        b.setForeground(ro.rc.foreground);
        b.setBackground(ro.rc.background);
    }

    private void setcolors(JTextField t) {
        t.setForeground(ro.rc.foreground);
        t.setBackground(ro.rc.background);
    }

    private void setcolors(JComboBox c) {
        c.setForeground(ro.rc.foreground);
        c.setBackground(ro.rc.background);
    }

    private void setopaque(boolean b) {
        for (JPanel jp : p) jp.setOpaque(b);
        firstlunchrb.setOpaque(b);
        secondlunch.setOpaque(b);
        assembly.setOpaque(b);
        options.setOpaque(b);
        feedback.setOpaque(b);
        school.setOpaque(b);
        p1.setOpaque(b);
        p2.setOpaque(b);
        p3.setOpaque(b);
        school.setRenderer(render(b));
        box.setOpaque(b);
        extras.setOpaque(b);
    }

    private DefaultListCellRenderer render(final boolean b) {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JComponent result = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                result.setOpaque(b);
                return result;
            }
        };
    }

    private void fixcolor() {
        ro.rc.checkcolors();
        for (JLabel l : labels) {
            if (!ro.transparent) l.setOpaque(true);
            else l.setOpaque(false);
            setcolors(l);
        }
        if (!ro.transparent) setopaque(true);
        else setopaque(false);
        firstlunchrb.setForeground(ro.rc.foreground);
        firstlunchrb.setBackground(ro.rc.background);
        secondlunch.setForeground(ro.rc.foreground);
        secondlunch.setBackground(ro.rc.background);
        setcolors(assembly);
        setcolors(options);
        setcolors(feedback);
        setcolors(school);
        setcolors(extras);
        box.setBackground(ro.rc.background);
        setcolors(ro.colors);
        setcolors(ro.background);
        setcolors(ro.urltextintro);
        setcolors(ro.urltext);
        setcolors(ro.trans);
        ex.bc.panel.setBackground(ro.rc.background);
        ex.bc.p1.setBackground(ro.rc.background);
        ex.bc.p2.setBackground(ro.rc.background);
        ex.bc.p3.setBackground(ro.rc.background);
        ex.bc.p4.setBackground(ro.rc.background);
        setcolors(ex.bc.decimal);
        setcolors(ex.bc.binary);
        setcolors(ex.bc.base);
        setcolors(ex.bc.hex);
        int i = 0;
        boolean[] done = new boolean[21];
        boolean complete = false;
        while (true) {
            if (i < ex.sw.p.size()) ex.sw.p.get(i).setBackground(ro.rc.background);
            else done[0] = true;
            if (i < ex.sw.b.size()) setcolors(ex.sw.b.get(i));
            else done[1] = true;
            if (i < ex.sw.l.size()) setcolors(ex.sw.l.get(i));
            else done[2] = true;
            if (i < ex.cd.p.size()) ex.cd.p.get(i).setBackground(ro.rc.background);
            else done[3] = true;
            if (i < ex.cd.b.size()) setcolors(ex.cd.b.get(i));
            else done[4] = true;
            if (i < ex.cd.l.size()) setcolors(ex.cd.l.get(i));
            else done[5] = true;
            if (i < ex.cd.t.size()) setcolors(ex.cd.t.get(i));
            else done[6] = true;
            if (i < ex.c.p.size()) ex.c.p.get(i).setBackground(ro.rc.background);
            else done[7] = true;
            if (i < ex.c.b.size()) setcolors(ex.c.b.get(i));
            else done[8] = true;
            if (i < ex.c.l.size()) setcolors(ex.c.l.get(i));
            else done[9] = true;
            if (i < ex.bn.p.size()) ex.bn.p.get(i).setBackground(ro.rc.background);
            else done[10] = true;
            if (i < ex.bn.l.size()) setcolors(ex.bn.l.get(i));
            else done[11] = true;
            if (i < ex.bn.t.size()) setcolors(ex.bn.t.get(i));
            else done[12] = true;
            if (i < ex.cal.p.size()) ex.cal.p.get(i).setBackground(ro.rc.background);
            else done[13] = true;
            if (i < ex.cal.b.size()) setcolors(ex.cal.b.get(i));
            else done[14] = true;
            if (i < ex.cal.l.size()) {
                setcolors(ex.cal.l.get(i));
                if (ex.cal.nm[i]) ex.cal.l.get(i).setForeground(ro.rc.foreground.darker());
                else if (ex.cal.place == i) ex.cal.l.get(i).setForeground(ro.rc.foreground.brighter().brighter());
            } else done[15] = true;
            if (i < ex.cal.ld.length) setcolors(ex.cal.ld[i]);
            else done[16] = true;
            if (i < ex.p.size()) ex.p.get(i).setBackground(ro.rc.background);
            else done[17] = true;
            if (i < ex.b.size()) setcolors(ex.b.get(i));
            else done[18] = true;
            if (i < ro.p.size()) ro.p.get(i).setBackground(ro.rc.background);
            else done[19] = true;
            if (i < p.size()) p.get(i).setBackground(ro.rc.background);
            else done[20] = true;
            for (boolean b : done) {
                if (!b) {
                    complete = false;
                    break;
                } else complete = true;
            }
            if (complete) break;
            i++;
        }
        setcolors(ex.bn.calc);
        ex.cal.updatecolor(ro.rc.foreground, ro.rc.background);
    }

    private class UpdateUITask extends TimerTask {
        @Override
        public void run() {
            Calendar time = Calendar.getInstance();
            String month = addzeros(time.get(2) + 1), daymonth = addzeros(time.get(5)), hour = addzeros(time.get(11)), minute = addzeros(time.get(12)), second = addzeros(time.get(13));
            boolean am = checkam(Integer.parseInt(hour)).booleanValue();
            if (!am) hour = addzeros(Integer.parseInt(hour) - 12) + "";
            String times = month + "/" + daymonth + "/" + time.get(1) + "     " + hour + ":" + minute + ":" + second;
            if (am) times = times + " AM";
            else times = times + " PM";
            String day = "";
            int daynum = time.get(7);
            if (daynum == 2) day = "Monday";
            else if (daynum == 3) day = "Tuesday";
            else if (daynum == 4) day = "Wednesday";
            else if (daynum == 5) day = "Thursday";
            else if (daynum == 6) day = "Friday";
            else if (daynum == 7) day = "Saturday";
            else if (daynum == 1) day = "Sunday";
            if (assemblybool) day += "-Assembly";
            day += "     " + times;
            labels.get(1).setText(day);
            firstlunch = ro.lunches;
            checkradio();
            ro.lunches = firstlunch;
            weekends(daynum, time);
            fixcolor();
            checkbackground();
            packframe();
            if (ro.withinsecs == 5) ro.warn();
            ro.withinsecs++;
        }
    }
}