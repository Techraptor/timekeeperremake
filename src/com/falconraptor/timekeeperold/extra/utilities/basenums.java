package com.falconraptor.timekeeperold.extra.utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class basenums extends JFrame {
	public ArrayList<JPanel> p = new ArrayList<JPanel>(0);
	public ArrayList<JTextField> t = new ArrayList<JTextField>(0);
	public ArrayList<JLabel> l = new ArrayList<JLabel>(0);
	public JButton calc = new JButton("Calculate");

	public basenums () {
		super("Base Conversion");
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setSize(500, 500);
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(false);
		gui();
	}

	public void gui () {
		p.add(new JPanel(new GridLayout(4, 2, 0, 0)));
		l.add(new JLabel("Number: ", (int) JLabel.CENTER_ALIGNMENT));
		t.add(new JTextField());
		l.add(new JLabel("Base: ", (int) JLabel.CENTER_ALIGNMENT));
		t.add(new JTextField());
		l.add(new JLabel("Base to Convert to: ", (int) JLabel.CENTER_ALIGNMENT));
		t.add(new JTextField());
		l.add(new JLabel("Anwser: ", (int) JLabel.CENTER_ALIGNMENT));
		l.add(new JLabel("", (int) JLabel.CENTER_ALIGNMENT));
		for (int i = 0; i < 3; i++) {
			p.get(0).add(l.get(i));
			p.get(0).add(t.get(i));
		}
		for (int i = 3; i < l.size(); i++) p.get(0).add(l.get(i));
		p.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		calc.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				String num = contobase(t.get(0).getText(), Integer.parseInt(t.get(1).getText()), Integer.parseInt(t.get(2).getText()));
				l.get(4).setText(num);
			}
		});
		p.get(1).add(calc);
		p.add(new JPanel(new GridLayout(2, 1, 0, 0)));
		p.get(2).add(p.get(0));
		p.get(2).add(p.get(1));
		super.add(p.get(2));
		super.pack();
	}

	public String contobase (String nums, int base, int baseto) {
		nums = nums.toUpperCase();
		boolean negative = false;
		try {
			nums.charAt(0);
		} catch (Exception e) {
			return "";
		}
		if (nums.charAt(0) == '-') {
			negative = true;
			nums = nums.substring(1);
		} else if (nums.charAt(0) == ' ') return nums;
		int decimal = nums.indexOf(".");
		if (baseto == 1 || baseto > 36) return "Max base is 36 and the minium is 2";
		if (base == 1 || base > 36) return "Max base is 36 and the minium is 2";
		for (int i = 0; i < nums.length(); i++) {
			if (val(nums.charAt(i)) > base && i != decimal)
				return "The number is greater than the base please try again";
		}
		if (decimal != -1) {
			int stuffo = 0, max = nums.length() - 1 - (nums.length() - decimal);
			for (int i = 0; i < nums.length() - (nums.length() - decimal); i++) {
				if (i == decimal) continue;
				if (i == nums.length()) break;
				stuffo += val(nums.charAt(i)) * Math.pow(base, max);
				max--;
			}
			int stufft = Integer.parseInt(nums.substring(decimal + 1));
			if (negative) return "-" + trimzeros(contobase(stuffo + "", baseto) + "." + stufft);
			return trimzeros(contobase(stuffo + "", baseto) + "." + stufft);
		} else {
			int stuff = 0, max = nums.length() - 1;
			for (int i = 0; i < nums.length(); i++) {
				if (i == decimal) continue;
				if (i == nums.length()) break;
				stuff += val(nums.charAt(i)) * Math.pow(base, max);
				max--;
			}
			if (negative) return "-" + trimzeros(contobase(stuff + "", baseto));
			return trimzeros(contobase(stuff + "", baseto));
		}
	}

	public int val (char c) {
		if (c >= 48 && c < 58) return ((int) c) - 48;
		else if (c >= 65 && c < 91) return ((int) c) - 55;
		else return -1;
	}

	public String trimzeros (String num) {
		while (num.charAt(0) == '0' && num.length() != 1) num = num.replaceFirst("0", "");
		return num;
	}

	public String contobase (String nums, int base) {
		if (base == 1 || base > 36) return "Max base is 36 and the minium is 2";
		String test = "";
		int power = 0, num = 0;
		try {
			num = Integer.parseInt(nums);
		} catch (Exception e) {
			System.out.println(e);
		}
		while (num > Math.pow(base, power)) power++;
		for (int i = (int) Math.pow(base, power); i >= 1; i = i / base) {
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

	public static void main (String[] args) {
		new basenums();
	}

	public void appear () {
		super.setVisible(true);
	}

	public String contobase (double num, int base, int baseto) {
		return contobase(num + "", base, baseto);
	}

	public String contobase (int num, int base, int baseto) {
		return contobase(num + "", base, baseto);
	}

	public char val (int a) {
		if (a >= 0 && a < 10) return (char) (48 + a);
		else if (a >= 10 && a < 36) return (char) (55 + a);
		else return '?';
	}
}