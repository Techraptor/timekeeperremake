package com.falconraptor.timekeeper.guis;

import javax.swing.*;

public class Loading extends JFrame {
    private JLabel label1;
    private JLabel loading;
    private JPanel jpanel;
    private JProgressBar progressBar1;

    public Loading() {
        super("Loading");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(jpanel);
        pack();
        setVisible(true);
    }

    public void setLoading(String l) {
        loading.setText(l);
    }

    public void addProgress() {
        progressBar1.setValue(progressBar1.getValue() + 1);
    }

    public int getValue() {
        return progressBar1.getValue();
    }
}
