package com.falconraptor.timekeeper.extra.utilities.Calender;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class checklist extends JFrame {
    public ArrayList<JPanel> p = new ArrayList<JPanel>(0);
    public ArrayList<JButton> b = new ArrayList<JButton>(0);
    public ArrayList<JLabel> l = new ArrayList<JLabel>(0);
    public ArrayList<JTextField> t = new ArrayList<JTextField>(0);

    public checklist() {
        super("CheckList");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(false);
        super.setContentPane(setgui());
        super.pack();
    }

    private JPanel setgui() {
        p.add(new JPanel(new GridLayout(2, 1, 0, 0)));
        return p.get(0);
    }

    public void appear() {
        super.setVisible(true);
    }
}
