package com.falconraptor.timekeeperold.extra;

import com.falconraptor.timekeeperold.extra.utilities.Calender.*;
import com.falconraptor.timekeeperold.extra.utilities.*;
import com.falconraptor.utilities.files.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class extras extends JFrame {
	public ArrayList<JButton> b = new ArrayList<JButton>(0);
	public ArrayList<JPanel> p = new ArrayList<JPanel>(0);
	public Box box = Box.createVerticalBox();
	public boolean firstmine = true, firstlaunch = true;
	public basenums bn = new basenums();
	public binaryclock bc = new binaryclock();
	public stopwatch sw = new stopwatch();
	public countdown cd = new countdown();
	public calculator c = new calculator();
	public calender cal = new calender();

	public extras () {
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
		b.add(new JButton("TimeGame"));
		b.add(new JButton("UsefulShortcuts"));
		b.add(new JButton("Binary Clock"));
		b.add(new JButton("Base Conversion"));
		b.add(new JButton("Stopwatch"));
		b.add(new JButton("Countdown"));
		b.add(new JButton("Calculator"));
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
				if (button.equals("Calculator")) c.appear();
				else if (button.equals("Countdown")) cd.appear();
				else if (button.equals("Stopwatch")) sw.appear();
				else if (button.equals("Base Conversion")) bn.appear();
				else if (button.equals("Binary Clock")) bc.appear();
				else if (button.equals("MineSweeper")) try {
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

	public void appear () {
		if (firstlaunch) {
			Files.downloadfile("https://dl.dropboxusercontent.com/u/109423311/timekeeperold/extras/minesweeper.jar", "resources\\jars\\minesweeper.jar");
			Files.downloadfile("https://dl.dropboxusercontent.com/u/109423311/timekeeperold/extras/timegame.jar", "resources\\jars\\timegame.jar");
			Files.downloadfile("https://dl.dropboxusercontent.com/u/109423311/timekeeperold/extras/usefulshortcuts.jar", "usefulshortcuts.jar");
			firstlaunch = false;
		}
		super.setVisible(true);
	}

	public void beep () {
		Toolkit.getDefaultToolkit().beep();
		try {
			Runtime.getRuntime().exec("cmd /c start echo ");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}