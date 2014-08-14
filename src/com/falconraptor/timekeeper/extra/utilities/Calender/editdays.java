package com.falconraptor.timekeeper.extra.utilities.Calender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class editdays extends JFrame {
    public ArrayList<JPanel> p = new ArrayList<JPanel>(0);
    public ArrayList<JButton> b = new ArrayList<JButton>(0);
    public events e = new events();
    public checklist c = new checklist();
    public File cal = new File("");
    int place = 0;

    public editdays() {
        super("Edit Day");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(false);
        super.setContentPane(setgui());
        super.pack();
    }

    private JPanel setgui() {
        p.add(new JPanel(new GridLayout(2, 1, 0, 0)));
        b.add(new JButton("Events"));
        b.add(new JButton("CheckList"));
        for (int i = 0; i < 2; i++) {
            p.get(0).add(b.get(i));
            b.get(i).addActionListener(clicked(place, b.get(i).getText()));
        }
        return p.get(0);
    }

    public void appear() {
        for (int i = 0; i < 2; i++) b.get(i).addActionListener(clicked(place, b.get(i).getText()));
        super.setVisible(true);
    }

    private ActionListener clicked(final int n, final String s) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent ea) {
                if (s.equals("Events")) {
                    checkfile(n + "");
                    checkfile(n + "\\Events.list");
                    e.place = n;
                    e.appear();
                } else if (s.equals("CheckList")) {
                    checkfile(n + "");
                    checkfile(n + "\\CheckList.list");
                    c.appear();
                }
                dispose();
            }
        };
    }

    private void checkfile(String filename) {
        try {
            cal = new File("resources\\calenders\\" + e.c.get(e.c.YEAR) + "\\" + (e.c.get(e.c.MONTH) + 1));
            cal = new File(cal, filename);
            if (filename.indexOf(".") == -1) cal.mkdirs();
            else cal.createNewFile();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}