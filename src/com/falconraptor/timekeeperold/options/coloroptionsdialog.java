package com.falconraptor.timekeeperold.options;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class coloroptionsdialog extends JFrame {
	public ButtonGroup backgroundgroup = new ButtonGroup(), foregroundgroup = new ButtonGroup();
	public Color foreground = Color.black, background = Color.lightGray;
	public boolean first = true;
	public Box box1 = Box.createVerticalBox(), box2 = Box.createVerticalBox(), box3 = Box.createVerticalBox();
	public JPanel p1 = new JPanel(new FlowLayout()), p2 = new JPanel(new FlowLayout()), p3 = new JPanel(new FlowLayout());
	public ArrayList<JRadioButton> foregroundcolors = new ArrayList<JRadioButton>(0), backgroundcolors = new ArrayList<JRadioButton>(0);
	public JLabel backgroundtitle = new JLabel("Background Colors", (int) JLabel.CENTER_ALIGNMENT), foregroundtitle = new JLabel("Foreground (Text) Colors", (int) JLabel.CENTER_ALIGNMENT);
	public ArrayList<JPanel> p = new ArrayList<JPanel>(0);

	public coloroptionsdialog () {
		super("Color Options");
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setSize(500, 500);
		super.setLocationRelativeTo(null);
		super.setVisible(false);
		addcolors();
		addpanels();
		super.pack();
	}

	private void addcolors () {
		foregroundcolors.add(new JRadioButton("Black"));
		foregroundcolors.add(new JRadioButton("Blue"));
		foregroundcolors.add(new JRadioButton("Cyan"));
		foregroundcolors.add(new JRadioButton("Dark Gray"));
		foregroundcolors.add(new JRadioButton("Gray"));
		foregroundcolors.add(new JRadioButton("Green"));
		foregroundcolors.add(new JRadioButton("Light Gray"));
		foregroundcolors.add(new JRadioButton("Magenta"));
		foregroundcolors.add(new JRadioButton("Orange"));
		foregroundcolors.add(new JRadioButton("Pink"));
		foregroundcolors.add(new JRadioButton("Red"));
		foregroundcolors.add(new JRadioButton("White"));
		foregroundcolors.add(new JRadioButton("Yellow"));
		backgroundcolors.add(new JRadioButton("Black"));
		backgroundcolors.add(new JRadioButton("Blue"));
		backgroundcolors.add(new JRadioButton("Cyan"));
		backgroundcolors.add(new JRadioButton("Dark Gray"));
		backgroundcolors.add(new JRadioButton("Gray"));
		backgroundcolors.add(new JRadioButton("Green"));
		backgroundcolors.add(new JRadioButton("Light Gray"));
		backgroundcolors.add(new JRadioButton("Magenta"));
		backgroundcolors.add(new JRadioButton("Orange"));
		backgroundcolors.add(new JRadioButton("Pink"));
		backgroundcolors.add(new JRadioButton("Red"));
		backgroundcolors.add(new JRadioButton("White"));
		backgroundcolors.add(new JRadioButton("Yellow"));
		for (int i = 0; i < foregroundcolors.size(); i++) {
			foregroundcolors.get(i).setActionCommand(i + 1 + "");
			backgroundcolors.get(i).setActionCommand(i + 1 + "");
		}
	}

	private void addpanels () {
		p.add(new JPanel(new GridLayout(1, 2, 0, 0)));
		p.get(0).add(leftcolors());
		p.get(0).add(rightcolors());
		super.add(p.get(0));
	}

	private JPanel rightcolors () {
		backgroundcolors.get(11).setSelected(true);
		for (int i = 0; i < backgroundcolors.size(); i++) backgroundgroup.add(backgroundcolors.get(i));
		p.add(new JPanel(new GridLayout(backgroundcolors.size() + 1, 1, 0, 0)));
		p.get(2).add(backgroundtitle);
		for (int i = 0; i < backgroundcolors.size(); i++) p.get(2).add(backgroundcolors.get(i));
		return p.get(2);
	}

	private JPanel leftcolors () {
		foregroundcolors.get(0).setSelected(true);
		for (int i = 0; i < foregroundcolors.size(); i++) foregroundgroup.add(foregroundcolors.get(i));
		p.add(new JPanel(new GridLayout(foregroundcolors.size() + 1, 1, 0, 0)));
		p.get(1).add(foregroundtitle);
		for (int i = 0; i < foregroundcolors.size(); i++) p.get(1).add(foregroundcolors.get(i));
		return p.get(1);
	}

	public void appear () {
		super.setVisible(true);
	}

	public void checkcolors () {
		if (first) {
			Color test = foreground;
			if (test.equals(Color.black)) foregroundcolors.get(0).setSelected(true);
			else if (test.equals(Color.blue)) foregroundcolors.get(1).setSelected(true);
			else if (test.equals(Color.cyan)) foregroundcolors.get(2).setSelected(true);
			else if (test.equals(Color.darkGray)) foregroundcolors.get(3).setSelected(true);
			else if (test.equals(Color.gray)) foregroundcolors.get(4).setSelected(true);
			else if (test.equals(Color.green)) foregroundcolors.get(5).setSelected(true);
			else if (test.equals(Color.lightGray)) foregroundcolors.get(6).setSelected(true);
			else if (test.equals(Color.magenta)) foregroundcolors.get(7).setSelected(true);
			else if (test.equals(Color.orange)) foregroundcolors.get(8).setSelected(true);
			else if (test.equals(Color.pink)) foregroundcolors.get(9).setSelected(true);
			else if (test.equals(Color.red)) foregroundcolors.get(10).setSelected(true);
			else if (test.equals(Color.white)) foregroundcolors.get(11).setSelected(true);
			else if (test.equals(Color.yellow)) foregroundcolors.get(12).setSelected(true);
			else System.out.println(test);
			test = background;
			if (test.equals(Color.black)) backgroundcolors.get(0).setSelected(true);
			else if (test.equals(Color.blue)) backgroundcolors.get(1).setSelected(true);
			else if (test.equals(Color.cyan)) backgroundcolors.get(2).setSelected(true);
			else if (test.equals(Color.darkGray)) backgroundcolors.get(3).setSelected(true);
			else if (test.equals(Color.gray)) backgroundcolors.get(4).setSelected(true);
			else if (test.equals(Color.green)) backgroundcolors.get(5).setSelected(true);
			else if (test.equals(Color.lightGray)) backgroundcolors.get(6).setSelected(true);
			else if (test.equals(Color.magenta)) backgroundcolors.get(7).setSelected(true);
			else if (test.equals(Color.orange)) backgroundcolors.get(8).setSelected(true);
			else if (test.equals(Color.pink)) backgroundcolors.get(9).setSelected(true);
			else if (test.equals(Color.red)) backgroundcolors.get(10).setSelected(true);
			else if (test.equals(Color.white)) backgroundcolors.get(11).setSelected(true);
			else if (test.equals(Color.yellow)) backgroundcolors.get(12).setSelected(true);
			else System.out.println(test);
			first = false;
			return;
		}
		String command = foregroundgroup.getSelection().getActionCommand();
		if (command.equals("1")) foreground = Color.black;
		else if (command.equals("2")) foreground = Color.blue;
		else if (command.equals("3")) foreground = Color.cyan;
		else if (command.equals("4")) foreground = Color.darkGray;
		else if (command.equals("5")) foreground = Color.gray;
		else if (command.equals("6")) foreground = Color.green;
		else if (command.equals("7")) foreground = Color.lightGray;
		else if (command.equals("8")) foreground = Color.magenta;
		else if (command.equals("9")) foreground = Color.orange;
		else if (command.equals("10")) foreground = Color.pink;
		else if (command.equals("11")) foreground = Color.red;
		else if (command.equals("12")) foreground = Color.white;
		else if (command.equals("13")) foreground = Color.yellow;
		command = backgroundgroup.getSelection().getActionCommand();
		if (command.equals("1")) background = Color.black;
		else if (command.equals("2")) background = Color.blue;
		else if (command.equals("3")) background = Color.cyan;
		else if (command.equals("4")) background = Color.darkGray;
		else if (command.equals("5")) background = Color.gray;
		else if (command.equals("6")) background = Color.green;
		else if (command.equals("7")) background = Color.lightGray;
		else if (command.equals("8")) background = Color.magenta;
		else if (command.equals("9")) background = Color.orange;
		else if (command.equals("10")) background = Color.pink;
		else if (command.equals("11")) background = Color.red;
		else if (command.equals("12")) background = Color.white;
		else if (command.equals("13")) background = Color.yellow;
		for (int i = 0; i < foregroundcolors.size(); i++) {
			foregroundcolors.get(i).setForeground(foreground);
			foregroundcolors.get(i).setBackground(background);
			backgroundcolors.get(i).setForeground(foreground);
			backgroundcolors.get(i).setBackground(background);
		}
		foregroundtitle.setForeground(foreground);
		foregroundtitle.setBackground(background);
		foregroundtitle.setOpaque(true);
		backgroundtitle.setForeground(foreground);
		backgroundtitle.setBackground(background);
		backgroundtitle.setOpaque(true);
		for (int i = 0; i < p.size(); i++) p.get(i).setBackground(background);
		super.pack();
	}
}