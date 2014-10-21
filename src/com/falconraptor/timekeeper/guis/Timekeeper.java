package com.falconraptor.timekeeper.guis;

import com.falconraptor.timekeeper.references.References;

import javax.swing.*;

public class Timekeeper extends JFrame {
    private JPanel panel1;
    private JLabel date;
    private JLabel time;

    public Timekeeper() {
        super("Timekeeper");
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
    }

    @Override
    public void setVisible(boolean b) {
        References.loading.setVisible(false);
        System.out.println(References.loading.getValue());
        super.setVisible(b);
    }
}
