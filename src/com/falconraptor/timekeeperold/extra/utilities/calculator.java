package com.falconraptor.timekeeperold.extra.utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class calculator extends JFrame {
    public ArrayList<JPanel> p = new ArrayList<JPanel>(0);
    public ArrayList<JButton> b = new ArrayList<JButton>(0);
    public ArrayList<JLabel> l = new ArrayList<JLabel>(0);
    boolean negative = false, add = false, sub = false, multi = false, divide = false, firstbool = true;
    BigDecimal first, second;

    public calculator() {
        super("Calculator");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(false);
        super.setContentPane(setgui());
        super.pack();
    }

    private JPanel setgui() {
        p.add(new JPanel(new GridLayout(1, 1, 0, 0)));
        l.add(new JLabel("0", 0));
        p.get(0).add(l.get(0));
        p.add(new JPanel(new GridLayout(1, 4, 0, 0)));
        b.add(new JButton("←"));
        b.add(new JButton("CE"));
        b.add(new JButton("C"));
        b.add(new JButton("*"));
        for (int i = 0; i < 4; i++) p.get(1).add(b.get(i));
        p.add(new JPanel(new GridLayout(1, 4, 0, 0)));
        b.add(new JButton("7"));
        b.add(new JButton("8"));
        b.add(new JButton("9"));
        b.add(new JButton("\\"));
        for (int i = 4; i < 8; i++) p.get(2).add(b.get(i));
        p.add(new JPanel(new GridLayout(1, 4, 0, 0)));
        b.add(new JButton("4"));
        b.add(new JButton("5"));
        b.add(new JButton("6"));
        b.add(new JButton("-"));
        for (int i = 8; i < 12; i++) p.get(3).add(b.get(i));
        p.add(new JPanel(new GridLayout(1, 4, 0, 0)));
        b.add(new JButton("3"));
        b.add(new JButton("2"));
        b.add(new JButton("1"));
        b.add(new JButton("+"));
        for (int i = 12; i < 16; i++) p.get(4).add(b.get(i));
        p.add(new JPanel(new GridLayout(1, 4, 0, 0)));
        b.add(new JButton("0"));
        b.add(new JButton("."));
        b.add(new JButton("+-"));
        b.add(new JButton("="));
        for (int i = 16; i < 20; i++) p.get(5).add(b.get(i));
        p.add(new JPanel(new GridLayout(6, 1, 0, 0)));
        for (int i = 0; i < 6; i++) p.get(6).add(p.get(i));
        for (int i = 0; i < 20; i++) b.get(i).addActionListener(clicked(b.get(i).getText()));
        return p.get(6);
    }

    private ActionListener clicked(final String button) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    int a = Integer.parseInt(button);
                    if (l.get(0).getText().equals("0")) l.get(0).setText("");
                    l.get(0).setText(l.get(0).getText() + button);
                } catch (Exception e) {
                    //System.err.println(e);
                    if (button.equals("←"))
                        l.get(0).setText(l.get(0).getText().substring(0, l.get(0).getText().length() - 1));
                    else if (button.equals(".")) l.get(0).setText(l.get(0).getText() + ".");
                    else if (button.equals("+-")) {
                        if (negative) l.get(0).setText(l.get(0).getText().substring(1, l.get(0).getText().length()));
                        else l.get(0).setText("-" + l.get(0).getText());
                        negative = !negative;
                    } else if (button.equals("C")) l.get(0).setText("");
                    else if (button.equals("+")) {
                        resetbools();
                        setvars();
                        add = true;
                    } else if (button.equals("-")) {
                        resetbools();
                        setvars();
                        sub = true;
                    } else if (button.equals("*")) {
                        resetbools();
                        setvars();
                        multi = true;
                    } else if (button.equals("\\")) {
                        resetbools();
                        setvars();
                        divide = true;
                    } else if (button.equals("=")) {
                        setvars();
                        calc();
                    } else if (button.equals("CE")) {
                        l.get(0).setText("");
                        firstbool = true;
                        resetbools();
                        second = null;
                    }
                }
                if (l.get(0).getText().length() == 0) l.get(0).setText("0");
                packframe();
            }
        };
    }

    public void appear() {
        super.setVisible(true);
    }

    private void packframe() {
        super.pack();
    }

    private void calc() {
        if (second == null || first == null) return;
        firstbool = true;
        if (add) first = first.add(second);
        else if (sub) first = first.subtract(second);
        else if (multi) first = first.multiply(second);
        else if (divide) first = first.divide(second, 75, RoundingMode.HALF_UP);
        l.get(0).setText(first.toString());
        resetbools();
        second = null;
    }

    private void setvars() {
        if (firstbool) {
            first = new BigDecimal(l.get(0).getText());
            firstbool = false;
        } else if (second == null) {
            second = new BigDecimal(l.get(0).getText());
            firstbool = true;
        } else {
            calc();
            setvars();
        }
        l.get(0).setText("");
    }

    private void resetbools() {
        add = false;
        sub = false;
        multi = false;
        divide = false;
    }
}