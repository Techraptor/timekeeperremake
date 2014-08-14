package com.falconraptor.timekeeper.extra.utilities.Calender;

import com.falconraptor.utilities.logger.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class calender extends JFrame {
    public static String log = "[com.falconraptor.timekeeper.extra.utilities.Calender.calender.";
    public ArrayList<JPanel> p = new ArrayList<JPanel>(0);
    public ArrayList<JButton> b = new ArrayList<JButton>(0);
    public ArrayList<JLabel> l = new ArrayList<JLabel>(0);
    public JLabel[] ld = new JLabel[7];
    public boolean[] nm = new boolean[43];
    public editdays ed = new editdays();
    public JButton left = new JButton(""), right = new JButton("");
    public int place = c.get(5);
    String[] days = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saterday"};
    Calendar c = Calendar.getInstance();

    public calender() {
        super("Calender");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(false);
        super.setContentPane(setgui());
        super.pack();
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                ed.dispose();
                ed.e.dispose();
                ed.c.dispose();
            }
        });
    }

    private JPanel setgui() {
        p.add(new JPanel(new GridLayout(8, 7, 0, 0)));
        l.add(new JLabel(c.getDisplayName(c.MONTH, c.LONG, Locale.getDefault()), 0));
        p.get(0).add(left);
        for (int i = 0; i < 2; i++) p.get(0).add(new JLabel("", 0));
        p.get(0).add(l.get(0));
        for (int i = 0; i < 2; i++) p.get(0).add(new JLabel("", 0));
        p.get(0).add(right);
        for (int i = 1; i < 8; i++) {
            ld[i - 1] = new JLabel(days[i], 0);
            p.get(0).add(ld[i - 1]);
        }
        for (int i = 0; i < 42; i++) {
            p.add(new JPanel(new GridLayout(2, 1, 0, 0)));
            l.add(new JLabel("", 0));
            b.add(new JButton("Edit"));
            p.get(i + 1).add(l.get(i + 1));
            p.get(0).add(p.get(i + 1));
        }
        l.get(c.get(7) + ((c.get(c.WEEK_OF_MONTH) - 1) * 7)).setText(c.get(5) + "");
        int min = 0, max = 0, m = c.get(c.MONTH);
        if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) max = 31;
        else if (m == 2) {
            if (c.getActualMaximum(c.DAY_OF_YEAR) > 365) max = 29;
            else max = 28;
        } else max = 30;
        for (int i = c.get(5) - 1; i > 0; i--) {
            l.get(c.get(7) + ((c.get(c.WEEK_OF_MONTH) - 1) * 7) - (c.get(5) - i)).setText(i + "");
            if (i == 1) {
                min = c.get(7) + ((c.get(c.WEEK_OF_MONTH) - 1) * 7) - (c.get(5) - i) - 1;
                int a = 0;
                while (min - a > 0) {
                    try {
                        l.get(min - a).setText(max - a + "");
                    } catch (Exception e) {
                        if (Logger.level <= 5) Logger.logERROR(log + "setgui] " + e);
                    }
                    a++;
                    nm[min - a + 1] = true;
                }
            }
        }
        max = 0;
        m = c.get(c.MONTH) + 1;
        if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) max = 31;
        else if (m == 2) {
            if (c.getActualMaximum(c.DAY_OF_YEAR) > 365) max = 29;
            else max = 28;
        } else max = 30;
        for (int i = c.get(5) + 1; i < l.size(); i++) {
            if (i <= max) l.get(c.get(7) + ((c.get(c.WEEK_OF_MONTH) - 1) * 7) + (i - c.get(5))).setText(i + "");
            else try {
                l.get(c.get(7) + ((c.get(c.WEEK_OF_MONTH) - 1) * 7) + (i - c.get(5))).setText(i - max + "");
                nm[i + min] = true;
            } catch (Exception e) {
                if (Logger.level <= 5) Logger.logERROR(log + "setgui] " + e);
            }
        }
        for (int i = 0; i < 41; i++) {
            if (!nm[i] && i != 0) {
                p.get(i).add(b.get(i));
                try {
                    b.get(i).addActionListener(clicked(Integer.parseInt(l.get(i).getText())));
                } catch (Exception e) {
                    if (Logger.level <= 5) Logger.logERROR(log + "setgui] " + e);
                }
            }
        }
        updatecolor(Color.BLACK, Color.WHITE);
        return p.get(0);
    }

    public void appear() {
        super.setVisible(true);
    }

    public void updatecolor(Color foreground, Color background) {
        Color test = foreground;
        String color = "";
        if (test.equals(Color.black)) color = "black";
        else if (test.equals(Color.blue)) color = "blue";
        else if (test.equals(Color.cyan)) color = "cyan";
        else if (test.equals(Color.darkGray)) color = "darkgray";
        else if (test.equals(Color.gray)) color = "gray";
        else if (test.equals(Color.green)) color = "green";
        else if (test.equals(Color.lightGray)) color = "lightgray";
        else if (test.equals(Color.magenta)) color = "magenta";
        else if (test.equals(Color.orange)) color = "orange";
        else if (test.equals(Color.pink)) color = "pink";
        else if (test.equals(Color.red)) color = "red";
        else if (test.equals(Color.white)) color = "white";
        else if (test.equals(Color.yellow)) color = "yellow";
        else System.err.println(test);
        if (color.equals("")) return;
        try {
            BufferedImage lp = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/images/Calender/" + color + "left.png"));
            BufferedImage rp = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/images/Calender/" + color + "right.png"));
            left.setIcon(new ImageIcon(lp));
            right.setIcon(new ImageIcon(rp));
            left.setBackground(background);
            right.setBackground(background);
        } catch (Exception e) {
            if (Logger.level <= 5) Logger.logERROR(log + "updatecolor] " + e);
        }
        super.pack();
    }

    private ActionListener clicked(final int n) {
        try {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    File calfold = new File("resources\\calenders");
                    if (!calfold.exists()) calfold.mkdirs();
                    calfold = new File(calfold, c.get(c.YEAR) + "");
                    if (!calfold.exists()) calfold.mkdirs();
                    calfold = new File(calfold, c.get(c.MONTH) + 1 + "");
                    if (!calfold.exists()) calfold.mkdirs();
                    ed.place = n;
                    ed.cal = calfold;
                    ed.appear();
                }
            };
        } catch (Exception e) {
            if (Logger.level <= 5) Logger.logERROR(log + "clicked] " + e);
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                }
            };
        }
    }
}