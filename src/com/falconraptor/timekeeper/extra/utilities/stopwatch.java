package com.falconraptor.timekeeper.extra.utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

public class stopwatch extends JFrame {
    public ArrayList<JPanel> p = new ArrayList<JPanel>(0);
    public ArrayList<JButton> b = new ArrayList<JButton>(0);
    public ArrayList<JLabel> l = new ArrayList<JLabel>(0);
    boolean running = false;

    public stopwatch() {
        super("Stop Watch");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(false);
        super.setContentPane(setgui());
        super.pack();
        new java.util.Timer().schedule(new UpdateUITask(), 0, 1000);
    }

    public void appear() {
        super.setVisible(true);
    }

    private void packframe() {
        super.pack();
    }

    private JPanel setgui() {
        p.add(new JPanel(new GridLayout(1, 1, 0, 0)));
        for (int i = 0; i < 3; i++) l.add(new JLabel("0", 0));
        l.add(new JLabel(l.get(0).getText() + " hours, " + l.get(1).getText() + " minutes, " + l.get(2).getText() + " seconds", 0));
        p.get(0).add(l.get(3));
        p.add(new JPanel(new GridLayout(1, 2, 0, 0)));
        b.add(new JButton("Start"));
        b.add(new JButton("Stop"));
        b.get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                b.get(0).setEnabled(false);
                b.get(1).setEnabled(true);
                running = true;
                b.get(1).setText("Stop");
            }
        });
        b.get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (b.get(1).getText().equals("Reset")) {
                    for (int i = 0; i < 4; i++) l.get(i).setText("0");
                    l.get(3).setText((l.get(0).getText() + " hours, " + l.get(1).getText() + " minutes, " + l.get(2).getText() + " seconds"));
                    b.get(1).setText("Stop");
                    b.get(1).setEnabled(false);
                } else {
                    b.get(0).setEnabled(true);
                    b.get(1).setText("Reset");
                    running = false;
                }
            }
        });
        b.get(1).setEnabled(false);
        for (int i = 0; i < 2; i++) p.get(1).add(b.get(i));
        p.add(new JPanel(new GridLayout(2, 1, 0, 0)));
        for (int i = 0; i < 2; i++) p.get(2).add(p.get(i));
        return p.get(2);
    }

    private class UpdateUITask extends TimerTask {
        @Override
        public void run() {
            if (!running) return;
            l.get(2).setText(Integer.parseInt(l.get(2).getText()) + 1 + "");
            if (l.get(2).getText().equals("60")) {
                l.get(2).setText("0");
                l.get(1).setText(Integer.parseInt(l.get(1).getText()) + 1 + "");
            }
            if (l.get(1).getText().equals("60")) {
                l.get(1).setText("0");
                l.get(0).setText(Integer.parseInt(l.get(0).getText()) + 1 + "");
            }
            l.get(3).setText((l.get(0).getText() + " hours, " + l.get(1).getText() + " minutes, " + l.get(2).getText() + " seconds"));
            packframe();
        }
    }
}
