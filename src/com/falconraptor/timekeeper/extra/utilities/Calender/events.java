package com.falconraptor.timekeeper.extra.utilities.Calender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class events extends JFrame {
    public ArrayList<JPanel> p = new ArrayList<JPanel>(0);
    public ArrayList<JButton> b = new ArrayList<JButton>(0);
    public ArrayList<JLabel> l = new ArrayList<JLabel>(0);
    public ArrayList<JTextField> t = new ArrayList<JTextField>(0);
    public int place = 0;
    ArrayList<String> data = new ArrayList<String>(0);
    Calendar c = Calendar.getInstance();

    public events() {
        super("Events");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(false);
        super.pack();
    }

    private JPanel setgui() {
        p.add(new JPanel(new GridLayout(3 + data.size(), 2, 0, 0)));
        l.add(new JLabel("", 0));
        l.add(new JLabel(c.getDisplayName(c.MONTH, c.LONG, Locale.getDefault()) + " " + c.get(5) + ", " + c.get(c.YEAR)));
        l.add(new JLabel("Hour:Minute", 0));
        l.add(new JLabel("Event", 0));
        for (String s : data) {
            String[] sd = s.split(":");
            for (String hi : sd) l.add(new JLabel(hi, 0));
        }
        l.add(new JLabel("", 0));
        for (JLabel jl : l) p.get(0).add(jl);
        b.add(new JButton("Create New Event"));
        b.get(0).addActionListener(clicked());
        p.get(0).add(b.get(0));
        return p.get(0);
    }

    private ActionListener clicked() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent ea) {

            }
        };
    }

    private void loadfile() {
        try {
            File e = new File("resources\\calenders\\" + c.get(c.YEAR) + "\\" + (c.get(c.MONTH) + 1) + "\\" + place + "\\Events.list");
            BufferedReader reader = new BufferedReader(new FileReader(e));
            String line = reader.readLine();
            while (line != null) {
                data.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void appear() {
        p.clear();
        b.clear();
        l.clear();
        t.clear();
        loadfile();
        super.setContentPane(setgui());
        super.pack();
        super.setVisible(true);
    }
}
