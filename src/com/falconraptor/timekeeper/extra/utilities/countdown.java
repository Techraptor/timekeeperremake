package com.falconraptor.timekeeper.extra.utilities;

import com.falconraptor.timekeeper.extra.extras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

public class countdown extends JFrame {
    public ArrayList<JPanel> p = new ArrayList<JPanel>(0);
    public ArrayList<JButton> b = new ArrayList<JButton>(0);
    public ArrayList<JLabel> l = new ArrayList<JLabel>(0);
    public ArrayList<JTextField> t = new ArrayList<JTextField>(0);
    boolean running = false;

    public countdown() {
        super("Countdown Timer");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(false);
        super.setContentPane(setgui());
        super.pack();
        new java.util.Timer().schedule(new UpdateUITask(), 0, 1000);
    }

    private void update() {
        if (lessthanzero(t.get(2).getText()) || lessthanzero(t.get(1).getText()) || lessthanzero(t.get(0).getText())) {
            running = false;
            new extras().beep();
            JOptionPane.showMessageDialog(null, "The countdown has finished", "DONE", JOptionPane.INFORMATION_MESSAGE);
            b.get(1).setText("Stop");
            b.get(1).setEnabled(false);
            b.get(0).setEnabled(true);
            for (int i = 0; i < 3; i++) t.get(i).setText("0");
            for (int i = 0; i < 3; i++) t.get(i).setEnabled(true);
        }
    }

    public void appear() {
        super.setVisible(true);
    }

    private void packframe() {
        super.pack();
    }

    private boolean lessthanzero(String test) {
        try {
            int a = Integer.parseInt(test);
            if (a < 0) return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean teststring(String test) {
        try {
            int a = Integer.parseInt(test);
            if (a < 0) {
                if (running) {
                    running = false;
                    new extras().beep();
                    JOptionPane.showMessageDialog(null, "The countdown has finished", "DONE", JOptionPane.INFORMATION_MESSAGE);
                    b.get(1).setText("Stop");
                    b.get(1).setEnabled(false);
                    b.get(0).setEnabled(true);
                    for (int i = 0; i < 3; i++) t.get(i).setText("0");
                    for (int i = 0; i < 3; i++) t.get(i).setEnabled(true);
                } else {
                    for (int i = 0; i < 3; i++) t.get(i).setText("0");
                    JOptionPane.showMessageDialog(null, "You inputed a negative number", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private JPanel setgui() {
        p.add(new JPanel(new GridLayout(4, 2, 0, 0)));
        l.add(new JLabel("Hours:", 0));
        for (int i = 0; i < 3; i++) t.add(new JTextField("0", 2));
        l.add(new JLabel("Minutes:", 0));
        l.add(new JLabel("Seconds:", 0));
        for (int i = 0; i < 3; i++) {
            p.get(0).add(l.get(i));
            p.get(0).add(t.get(i));
        }
        b.add(new JButton("Start"));
        b.add(new JButton("Stop"));
        b.get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (teststring(t.get(0).getText()) && teststring(t.get(1).getText()) && teststring(t.get(2).getText())) {
                    b.get(0).setEnabled(false);
                    b.get(1).setEnabled(true);
                    for (int i = 0; i < 3; i++) t.get(i).setEnabled(false);
                    running = true;
                    b.get(1).setText("Stop");
                    return;
                }
                JOptionPane.showMessageDialog(null, "You did not input a number", "ERROR", JOptionPane.ERROR_MESSAGE);
                if (running) {
                    running = false;
                    new extras().beep();
                    JOptionPane.showMessageDialog(null, "The countdown has finished", "DONE", JOptionPane.INFORMATION_MESSAGE);
                    b.get(1).setText("Stop");
                    b.get(1).setEnabled(false);
                    b.get(0).setEnabled(true);
                    for (int i = 0; i < 3; i++) t.get(i).setText("0");
                    for (int i = 0; i < 3; i++) t.get(i).setEnabled(true);
                } else for (int i = 0; i < 3; i++) t.get(i).setText("0");
            }
        });
        b.get(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (b.get(1).getText().equals("Reset")) {
                    b.get(1).setText("Stop");
                    b.get(1).setEnabled(false);
                    for (int i = 0; i < 3; i++) t.get(i).setText("0");
                    for (int i = 0; i < 3; i++) t.get(i).setEnabled(true);
                } else {
                    b.get(0).setEnabled(true);
                    b.get(1).setText("Reset");
                    running = false;
                }
            }
        });
        b.get(1).setEnabled(false);
        for (int i = 0; i < 2; i++) p.get(0).add(b.get(i));
        return p.get(0);
    }

    private class UpdateUITask extends TimerTask {
        @Override
        public void run() {
            if (!running) return;
            if (teststring(t.get(0).getText()) && teststring(t.get(1).getText()) && teststring(t.get(2).getText())) {
                update();
                t.get(2).setText(Integer.parseInt(t.get(2).getText()) - 1 + "");
                if (Integer.parseInt(t.get(2).getText()) < 0) {
                    t.get(2).setText("60");
                    t.get(1).setText(Integer.parseInt(t.get(1).getText()) - 1 + "");
                }
                if (Integer.parseInt(t.get(1).getText()) < 0) {
                    t.get(1).setText("60");
                    t.get(0).setText(Integer.parseInt(t.get(0).getText()) - 1 + "");
                }
                update();
                packframe();
            }
        }
    }
}
