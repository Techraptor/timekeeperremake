package com.falconraptor.timekeeperold.options;

import com.falconraptor.timekeeperold.extra.utilities.*;
import com.falconraptor.timekeeperold.main.*;
import com.falconraptor.utilities.files.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class optionsdialog extends JFrame {
	public coloroptionsdialog rc = new coloroptionsdialog();
	public boolean animatedbackground = true, load = false, transparent = false, urlb = false, lunches = false;
	public ArrayList<String> out = new ArrayList<String>(0);
	public JButton colors = new JButton("Color Options"), background = new JButton("Toggle Animated Background"), trans = new JButton("Toggle Transparency");
	public JTextField urltext;
	public URL url;
	public File urlf = new File("resources\\images\\matrix.gif");
	public int withinsecs = 5;
	public JLabel urltextintro = new JLabel("Animated Background URL:", (int) JLabel.CENTER_ALIGNMENT);
	public ArrayList<JPanel> p = new ArrayList<JPanel>(0);
	checkversion run = new checkversion();
	String matrixurl = "https://dl.dropboxusercontent.com/u/109423311/timekeeperold/images/matrix.gif";

	public optionsdialog () {
		super("Options");
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setSize(500, 500);
		urltext = new JTextField(urlf + "");
		super.add(panel1());
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(false);
	}

	private JPanel panel1 () {
		colors.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				rc.appear();
			}
		});
		background.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				animatedbackground = !animatedbackground;
			}
		});
		urltext.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate (DocumentEvent e) {
				withinsecs = 0;
			}

			public void removeUpdate (DocumentEvent e) {
				withinsecs = 0;
			}

			public void changedUpdate (DocumentEvent e) {
				withinsecs = 0;
			}
		});
		trans.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				transparent = !transparent;
			}
		});
		p.add(new JPanel(new GridLayout(1, 3, 0, 0)));
		p.get(0).add(colors);
		p.get(0).add(background);
		p.get(0).add(trans);
		p.add(new JPanel(new GridLayout(1, 2, 0, 0)));
		p.get(1).add(urltextintro);
		p.get(1).add(urltext);
		p.add(new JPanel(new GridLayout(2, 1, 0, 0)));
		p.get(2).add(p.get(0));
		p.get(2).add(p.get(1));
		return p.get(2);
	}

	public void appear () {
		super.setVisible(true);
	}

	public void loadoptions () {
		File oldconfig = new File("resources\\config.dat"), users = new File("resources\\config.cfg");
		if (oldconfig.exists()) oldconfig.renameTo(users);
		try {
			if (!users.exists()) {
				users.createNewFile();
				saveoptions();
				load = true;
				warn();
				return;
			}
			BufferedReader reader = new BufferedReader(new FileReader(users));
			String line = reader.readLine();
			while (line != null) {
				out.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		for (int i = 5; i < out.size(); i++) out.set(i, out.get(i).substring(out.get(i).indexOf(": ") + 2));
		int[] indexes = util.checkerrors(out.get(5));
		try {
			rc.foreground = new Color(Integer.parseInt(out.get(5).substring(out.get(5).indexOf("r=") + 2, out.get(5).indexOf("r=") + indexes[0])), Integer.parseInt(out.get(5).substring(out.get(5).indexOf("g=") + 2, out.get(5).indexOf("g=") + indexes[1])), Integer.parseInt(out.get(5).substring(out.get(5).indexOf("b=") + 2, out.get(5).indexOf("b=") + indexes[2])));
		} catch (Exception e) {
			System.err.println(e);
		}
		indexes = util.checkerrors(out.get(6));
		try {
			rc.background = new Color(Integer.parseInt(out.get(6).substring(out.get(6).indexOf("r=") + 2, out.get(6).indexOf("r=") + indexes[0])), Integer.parseInt(out.get(6).substring(out.get(6).indexOf("g=") + 2, out.get(6).indexOf("g=") + indexes[1])), Integer.parseInt(out.get(6).substring(out.get(6).indexOf("b=") + 2, out.get(6).indexOf("b=") + indexes[2])));
		} catch (Exception e) {
			System.err.println(e);
		}
		animatedbackground = Boolean.parseBoolean(out.get(7));
		urltext.setText(out.get(8));
		try {
			lunches = !out.get(9).equals("2");
			transparent = Boolean.parseBoolean(out.get(10));
		} catch (Exception e) {
			System.err.println(e);
			users.delete();
			lunches = false;
			transparent = false;
		}
		load = true;
		warn();
	}

	public void warn () {
		if (!load) return;
		if (urltext.getText().equals("resources\\matrix.gif")) urltext.setText("a");
		if (urltext.getText().equals("resources\\images\\matrix.gif") && (getResult() > 2256481 || getResult() == -1))
			urltext.setText("a");
		if (urltext.getText().indexOf(".") == -1) {
			urltext.setText("resources\\images\\matrix.gif");
			urlf = new File("resources\\images\\matrix.gif");
			Files.downloadfile(matrixurl, "resources\\images\\matrix.gif");
			return;
		}
		try {
			url = new URL(urltext.getText());
			urlb = false;
			int min = 0;
			while (urltext.getText().indexOf("/", min) != -1) min = urltext.getText().indexOf("/", min) + 1;
			urlf = new File("resources\\images\\" + urltext.getText().substring(min));
			if (!urlf.exists())
				Files.downloadfile(urltext.getText(), "resources\\images\\" + urltext.getText().substring(min));
		} catch (Exception ea) {
			urlb = false;
			try {
				urlf = new File(urltext.getText());
				if (!urlf.exists()) {
					Files.downloadfile(matrixurl, "resources\\images\\matrix.gif");
					urltext.setText("resources\\images\\matrix.gif");
				}
			} catch (Exception ec) {
				urlf = new File("resources\\images\\matrix.gif");
				if (!urlf.exists()) Files.downloadfile(matrixurl, "resources\\images\\matrix.gif");
			}
		}
		if (!urltext.getText().equals(urlf + "")) urltext.setText(urlf + "");
	}

	public long getResult () {
		try {
			return new File("resources\\images\\matrix.gif").length();
		} catch (Exception e) {
			System.err.println(e);
			return -1;
		}
	}

	public void saveoptions () {
		try {
			File userdata = new File("resources\\config.cfg");
			BufferedWriter writer = new BufferedWriter(new FileWriter(userdata));
			writer.write("#This is the config file for the Timekeeper.");
			writer.newLine();
			writer.write("#Timekeeper was made by falconraptor.");
			writer.newLine();
			writer.write("#Please only modify the booleans (true/false) and please do not touch the color options and let the program handle it");
			for (int i = 0; i < 3; i++) writer.newLine();
			writer.write("Foreground Color: " + rc.foreground);
			writer.newLine();
			writer.write("Background Color: " + rc.background);
			writer.newLine();
			writer.write("Animation On: " + animatedbackground);
			writer.newLine();
			if (urlb) writer.write("Animation URL: " + url);
			else writer.write("Animation URL: " + urlf);
			writer.newLine();
			int lunch = 2;
			if (lunches) lunch = 1;
			else lunch = 2;
			writer.write("Default Lunch: " + lunch);
			writer.newLine();
			writer.write("Transparent: " + transparent);
			writer.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}