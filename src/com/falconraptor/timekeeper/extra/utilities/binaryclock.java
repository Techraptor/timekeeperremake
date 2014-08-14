package com.falconraptor.timekeeper.extra.utilities;

import javax.swing.*;
import java.util.Calendar;
import java.util.TimerTask;

public class binaryclock extends JFrame {
    public JLabel binary = new JLabel("", (int) JLabel.CENTER_ALIGNMENT), decimal = new JLabel("", (int) JLabel.CENTER_ALIGNMENT), hex = new JLabel("", (int) JLabel.CENTER_ALIGNMENT), base = new JLabel("", (int) JLabel.CENTER_ALIGNMENT);
    public JPanel panel = new JPanel(), p1 = new JPanel(), p2 = new JPanel(), p3 = new JPanel(), p4 = new JPanel();
    Box box = Box.createVerticalBox();

    public binaryclock() {
        super("BINARYCLOCK");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        p1.add(decimal);
        p2.add(binary);
        p3.add(hex);
        p4.add(base);
        box.add(p1);
        box.add(p2);
        box.add(p3);
        box.add(p4);
        panel.add(box);
        super.setContentPane(panel);
        //f.size(100,100);
        super.setLocationRelativeTo(null);
        super.setVisible(false);
        super.pack();
        new java.util.Timer().schedule(new UpdateUITask(), 0, 1000);
    }

    public void appear() {
        super.setVisible(true);
    }

    private void framepack() {
        super.pack();
    }

    private String contobin(int num) {
        String test = "";
        int spaces = 0;
        for (int i = 128; i >= 1; i = i / 2) {
            if (num / i == 1) test += 1;
            else test += 0;
            if ((test.length() - spaces) % 4 == 0) {
                test += " ";
                spaces += 1;
            }
            num = num % i;
        }
        if (test.substring(test.length() - 1).equals(" ")) test = test.substring(0, test.length() - 1);
        return test;
    }

    private String contohex(int num) {
        String test = "";
        for (int i = 16; i >= 1; i = i / 16) {
            if (num / i < 10) test += num / i;
            else if (num / i == 10) test += "A";
            else if (num / i == 11) test += "B";
            else if (num / i == 12) test += "C";
            else if (num / i == 13) test += "D";
            else if (num / i == 14) test += "E";
            else if (num / i == 15) test += "F";
            else test += 0;
            num = num % i;
        }
        return test;
    }

    private String contobase36(int num) {
        String test = "";
        for (int i = 36; i >= 1; i = i / 36) {
            if (num / i < 10) test += num / i;
            else if (num / i == 10) test += "A";
            else if (num / i == 11) test += "B";
            else if (num / i == 12) test += "C";
            else if (num / i == 13) test += "D";
            else if (num / i == 14) test += "E";
            else if (num / i == 15) test += "F";
            else if (num / i == 16) test += "G";
            else if (num / i == 17) test += "H";
            else if (num / i == 18) test += "I";
            else if (num / i == 19) test += "J";
            else if (num / i == 20) test += "K";
            else if (num / i == 21) test += "L";
            else if (num / i == 22) test += "M";
            else if (num / i == 23) test += "N";
            else if (num / i == 24) test += "O";
            else if (num / i == 25) test += "P";
            else if (num / i == 26) test += "Q";
            else if (num / i == 27) test += "R";
            else if (num / i == 28) test += "S";
            else if (num / i == 29) test += "T";
            else if (num / i == 30) test += "U";
            else if (num / i == 31) test += "V";
            else if (num / i == 32) test += "W";
            else if (num / i == 33) test += "X";
            else if (num / i == 34) test += "Y";
            else if (num / i == 35) test += "Z";
            else test += 0;
            num = num % i;
        }
        return test;
    }

    private class UpdateUITask extends TimerTask {
        @Override
        public void run() {
            Calendar time = Calendar.getInstance();
            int hour = time.get(time.HOUR_OF_DAY), minute = time.get(time.MINUTE), second = time.get(time.SECOND);
            binary.setText(contobin(hour) + ":" + contobin(minute) + ":" + contobin(second));
            decimal.setText(hour + ":" + minute + ":" + second);
            hex.setText(contohex(hour) + ":" + contohex(minute) + ":" + contohex(second));
            base.setText(contobase36(hour) + ":" + contobase36(minute) + ":" + contobase36(second));
            framepack();
        }
    }
}