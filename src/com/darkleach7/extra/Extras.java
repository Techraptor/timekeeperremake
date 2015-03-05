package com.darkleach7.extra;

import com.darkleach7.extra.Calender.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Extras extends JFrame {
	public ArrayList<JButton> b = new ArrayList<JButton>(0);
	public ArrayList<JPanel> p = new ArrayList<JPanel>(0);
	public Box box = Box.createVerticalBox();
	public boolean firstmine = true, firstlaunch = true;
	public Calender cal = new Calender();

	public Extras () {
		super("Extras");
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setSize(500, 500);
		addpanels();
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(false);
	}

	private void addpanels () {
		b.add(new JButton("MineSweeper"));
		b.get(b.size() - 1).setEnabled(false);
		b.add(new JButton("TimeGame"));
		b.get(b.size() - 1).setEnabled(false);
		b.add(new JButton("UsefulShortcuts"));
		b.get(b.size() - 1).setEnabled(false);
		b.add(new JButton("Binary Clock"));
		b.get(b.size() - 1).setEnabled(false);
		b.add(new JButton("Base Conversion"));
		b.get(b.size() - 1).setEnabled(false);
		b.add(new JButton("Stopwatch"));
		b.get(b.size() - 1).setEnabled(false);
		b.add(new JButton("Countdown"));
		b.get(b.size() - 1).setEnabled(false);
		b.add(new JButton("Calculator"));
		b.get(b.size() - 1).setEnabled(false);
		b.add(new JButton("Calender"));
		p.add(new JPanel(new GridLayout(5, 2, 0, 0)));
		for (int i = 0; i < b.size(); i++) {
			b.get(i).addActionListener(clicked(b.get(i).getText()));
			p.get(0).add(b.get(i));
		}
		super.add(p.get(0));
		super.pack();
	}

	private ActionListener clicked (final String button) {
		return new ActionListener() {
			public void actionPerformed (ActionEvent ae) {
				if (button.equals("MineSweeper")) try {
					Desktop.getDesktop().open(new File("resources\\jars\\minesweeper.jar"));
				} catch (Exception e) {
					System.err.println(e);
				}
				else if (button.equals("TimeGame")) try {
					Desktop.getDesktop().open(new File("resources\\jars\\timegame.jar"));
				} catch (Exception e) {
					System.err.println(e);
				}
				else if (button.equals("Calender")) cal.appear();
				else if (button.equals("UsefulShortcuts")) try {
					Desktop.getDesktop().open(new File("usefulshortcuts.jar"));
				} catch (Exception e) {
					System.err.println(e);
				}
				dispose();
			}
		};
	}
}
