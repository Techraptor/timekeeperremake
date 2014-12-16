package com.falconraptor.timekeeperold.extra.timegame;

import com.falconraptor.timekeeperold.extra.utilities.*;
import com.falconraptor.timekeeperold.options.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class timegamegui extends JFrame implements Runnable {
	public static double wood = 0, gold = 0, food = 0, metal = 0, stone = 0, wph = 0, fph = 0, gph = 0, mph = 0, sph = 0;
	public static int population = 5, level = 0, wlvl = 0, hlvl = 0, flvl = 0, mlvl = 0, qlvl = 0, sclvl = 0, xp = 0, xpn = 0, buildingseconds = -1, b = 0, r = 0, researchingseconds = -1, rwl = 0, rml = 0, rql = 0, rfl = 0;
	public static Scanner in = new Scanner(System.in);
	public static boolean upgrading = false, done = false, researching = false, yes = false;
	public static String buildingupgrading = "", user = "", researchupgrading = "";
	public static File users, userdata;
	public static BufferedReader reader;
	public static ArrayList<String> out = new ArrayList<String>(1), data = new ArrayList<String>(1);
	public static FileWriter writer;
	JButton sb = new JButton("Stats"), bb = new JButton("Building Menu"), rb = new JButton("Research Menu"), lb = new JButton("Log Out"), ob = new JButton("Options");
	ArrayList<JPanel> pt = new ArrayList<JPanel>(0), ps = new ArrayList<JPanel>(0), pb = new ArrayList<JPanel>(0), nbp = new ArrayList<JPanel>(0), hp = new ArrayList<JPanel>(0), mp = new ArrayList<JPanel>(0), qp = new ArrayList<JPanel>(0), lp = new ArrayList<JPanel>(0), scp = new ArrayList<JPanel>(0), fp = new ArrayList<JPanel>(0), rp = new ArrayList<JPanel>(0);
	coloroptionsdialog cop = new coloroptionsdialog();
	JLabel welcome;
	JFrame stats = new JFrame("Stats"), buildings = new JFrame("Buildings"), newbuilding = new JFrame("New Building"), home = new JFrame("Home"), lumbermill = new JFrame("Lumbermill"), mine = new JFrame("Mine"), quarry = new JFrame("Quarry"), sciencecenter = new JFrame("Science Center"), farm = new JFrame("Farm"), research = new JFrame("Research");
	ArrayList<JLabel> ls = new ArrayList<JLabel>(0), nbl = new ArrayList<JLabel>(0), ml = new ArrayList<JLabel>(0), hl = new ArrayList<JLabel>(0), ql = new ArrayList<JLabel>(0), scl = new ArrayList<JLabel>(0), fl = new ArrayList<JLabel>(0), mgl = new ArrayList<JLabel>(0), ll = new ArrayList<JLabel>(0), rl = new ArrayList<JLabel>(0);
	ArrayList<JButton> bbl = new ArrayList<JButton>(0), nbb = new ArrayList<JButton>(0), hb = new ArrayList<JButton>(0), mb = new ArrayList<JButton>(0), lmb = new ArrayList<JButton>(0), scb = new ArrayList<JButton>(0), fb = new ArrayList<JButton>(0), qb = new ArrayList<JButton>(0), rmb = new ArrayList<JButton>(0);
	String[] bl = new String[26];
	int[] bll = new int[26];
	int buildingplace = -1;
	boolean saved = false;

	public timegamegui () {
		super("Time Game");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(500, 500);
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(false);
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing (WindowEvent we) {
				if (!saved) {
					save();
					saved = true;
				}
			}
		});
		stats.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		stats.setSize(500, 500);
		stats.pack();
		stats.setLocationRelativeTo(null);
		stats.setVisible(false);
		buildings.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		buildings.setSize(500, 500);
		buildings.pack();
		buildings.setLocationRelativeTo(null);
		buildings.setVisible(false);
		newbuilding.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		newbuilding.setSize(500, 500);
		newbuilding.pack();
		newbuilding.setLocationRelativeTo(null);
		newbuilding.setVisible(false);
		home.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		home.setSize(500, 500);
		home.pack();
		home.setLocationRelativeTo(null);
		home.setVisible(false);
		lumbermill.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		lumbermill.setSize(500, 500);
		lumbermill.pack();
		lumbermill.setLocationRelativeTo(null);
		lumbermill.setVisible(false);
		mine.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		mine.setSize(500, 500);
		mine.pack();
		mine.setLocationRelativeTo(null);
		mine.setVisible(false);
		quarry.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		quarry.setSize(500, 500);
		quarry.pack();
		quarry.setLocationRelativeTo(null);
		quarry.setVisible(false);
		farm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		farm.setSize(500, 500);
		farm.pack();
		farm.setLocationRelativeTo(null);
		farm.setVisible(false);
		sciencecenter.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		sciencecenter.setSize(500, 500);
		sciencecenter.pack();
		sciencecenter.setLocationRelativeTo(null);
		sciencecenter.setVisible(false);
		research.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		research.setSize(500, 500);
		research.pack();
		research.setLocationRelativeTo(null);
		research.setVisible(false);
	}

	private void save () {
		try {
			userdata = new File("timekeeperusers\\" + user + ".user");
			if (userdata.exists()) {
				userdata.renameTo(new File("timekeeperusers\\test.txt"));
				new File("timekeeperusers\\test.txt").delete();
				userdata.createNewFile();
			} else userdata.createNewFile();
			writer = new FileWriter(userdata);
			writer.write(wood + "" + System.getProperty("line.separator"));
			writer.write(gold + "" + System.getProperty("line.separator"));
			writer.write(food + "" + System.getProperty("line.separator"));
			writer.write(metal + "" + System.getProperty("line.separator"));
			writer.write(stone + "" + System.getProperty("line.separator"));
			writer.write(wph + "" + System.getProperty("line.separator"));
			writer.write(fph + "" + System.getProperty("line.separator"));
			writer.write(gph + "" + System.getProperty("line.separator"));
			writer.write(mph + "" + System.getProperty("line.separator"));
			writer.write(sph + "" + System.getProperty("line.separator"));
			writer.write(population + "" + System.getProperty("line.separator"));
			writer.write(level + "" + System.getProperty("line.separator"));
			writer.write(wlvl + "" + System.getProperty("line.separator"));
			writer.write(hlvl + "" + System.getProperty("line.separator"));
			writer.write(flvl + "" + System.getProperty("line.separator"));
			writer.write(mlvl + "" + System.getProperty("line.separator"));
			writer.write(qlvl + "" + System.getProperty("line.separator"));
			writer.write(sclvl + "" + System.getProperty("line.separator"));
			writer.write(xp + "" + System.getProperty("line.separator"));
			writer.write(xpn + "" + System.getProperty("line.separator"));
			writer.write(buildingseconds + "" + System.getProperty("line.separator"));
			writer.write(b + "" + System.getProperty("line.separator"));
			writer.write(buildingupgrading + System.getProperty("line.separator"));
			writer.write(researchupgrading + System.getProperty("line.separator"));
			writer.write(rwl + "" + System.getProperty("line.separator"));
			writer.write(rml + "" + System.getProperty("line.separator"));
			writer.write(rql + "" + System.getProperty("line.separator"));
			writer.write(rfl + "" + System.getProperty("line.separator"));
			writer.write(r + "" + System.getProperty("line.separator"));
			writer.write(researchingseconds + "" + System.getProperty("line.separator"));
			writer.write(cop.foreground + "" + System.getProperty("line.separator"));
			writer.write(cop.background + "" + System.getProperty("line.separator"));
			for (int i = 0; i < 26; i++) writer.write(bl[i] + System.getProperty("line.separator"));
			for (int i = 0; i < 26; i++) writer.write(bll[i] + "" + System.getProperty("line.separator"));
			writer.write(buildingplace + "" + System.getProperty("line.separator"));
			writer.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static void main (String[] args) {
		String user = JOptionPane.showInputDialog("Username: ");
		if (user == null || user == "") {
			JOptionPane.showMessageDialog(null, "Username cannot be blank", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		new timegamegui().main(user);
	}

	public void appear () {
		super.setVisible(true);
	}

	private void setmaingui () {
		pt.clear();
		ml.clear();
		welcome = new JLabel("Welcome to Time Game: " + user, (int) JLabel.CENTER_ALIGNMENT);
		pt.add(new JPanel(new GridLayout(1, 6, 0, 0)));
		pt.get(0).add(welcome);
		pt.add(new JPanel(new GridLayout(1, 5, 0, 0)));
		sb.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				stats.setVisible(true);
			}
		});
		pt.get(1).add(sb);
		bb.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				buildings.setVisible(true);
			}
		});
		pt.get(1).add(bb);
		rb.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				research.setVisible(true);
			}
		});
		rb.setEnabled(false);
		pt.get(1).add(rb);
		ob.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				cop.appear();
			}
		});
		pt.get(1).add(ob);
		lb.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				String user = JOptionPane.showInputDialog("Username: ");
				if (user == null || user == "") return;
				save();
				dispose();
				new timegamegui().main(user);
			}
		});
		pt.get(1).add(lb);
		ml.add(new JLabel("Level: " + level, (int) JLabel.CENTER_ALIGNMENT));
		ml.add(new JLabel("XP: " + xp, (int) JLabel.CENTER_ALIGNMENT));
		ml.add(new JLabel("XP Need: " + xpn, (int) JLabel.CENTER_ALIGNMENT));
		ml.add(new JLabel("Time Left for Building: " + (buildingseconds - b), (int) JLabel.CENTER_ALIGNMENT));
		ml.add(new JLabel("Time Left for Researching: " + (researchingseconds - r), (int) JLabel.CENTER_ALIGNMENT));
		for (int i = 0; i < 5; i++) pt.get(0).add(ml.get(i));
		pt.add(new JPanel(new GridLayout(2, 1, 0, 0)));
		pt.get(2).add(pt.get(0));
		pt.get(2).add(pt.get(1));
		super.setContentPane(pt.get(2));
		super.pack();
	}

	private void setstatsgui () {
		ps.clear();
		ps.add(new JPanel(new GridLayout(5, 2, 0, 0)));
		for (int i = 0; i < 10; i++) {
			ls.add(new JLabel("", (int) JLabel.CENTER_ALIGNMENT));
			ps.get(0).add(ls.get(i));
		}
		stats.setContentPane(ps.get(0));
		stats.pack();
	}

	private void setbuildinggui () {
		pb.clear();
		bbl.clear();
		pb.add(new JPanel(new GridLayout(5, 5, 0, 0)));
		for (int i = 0; i < 25; i++) {
			if (!bl[i].equals(" ")) bbl.add(new JButton(bl[i] + " : " + bll[i]));
			else bbl.add(new JButton(bl[i]));
			bbl.get(i).addActionListener(e(i));
			pb.get(0).add(bbl.get(i));
		}
		buildings.setContentPane(pb.get(0));
		buildings.pack();
	}

	private ActionListener e (final int i) {
		return new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (buildingupgrading.length() != 0) {
					JOptionPane.showMessageDialog(null, "You can only build one building at a time", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				buildingplace = i;
				if (bl[i].equals("Lumbermill")) setlumbermillgui();
				else if (bl[i].equals("Farm")) setfarmgui();
				else if (bl[i].equals("Mine")) setminegui();
				else if (bl[i].equals("Quarry")) setquarrygui();
				else if (bl[i].equals("Home")) sethomegui();
				else if (bl[i].equals("Science Center")) setsciencecentergui();
				else if (bl[i].equals(" ")) newbuilding.setVisible(true);
			}
		};
	}

	private void setresearchgui () {
		rp.clear();
		rl.clear();
		rmb.clear();
		rp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		rp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		rl.add(new JLabel("", (int) JLabel.CENTER_ALIGNMENT));
		rp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		rl.add(new JLabel("Materials Needed to Research Research", (int) JLabel.CENTER_ALIGNMENT));
		rp.get(1).add(rl.get(0));
		rp.get(2).add(rl.get(1));
		rp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		rmb.add(new JButton("Alloys : " + rml));
		rp.get(3).add(rmb.get(0));
		rp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		rl.add(new JLabel("Gold: " + 7, (int) JLabel.CENTER_ALIGNMENT));
		rp.get(4).add(rl.get(2));
		rp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		rmb.add(new JButton("Agriculture : " + rfl));
		rp.get(5).add(rmb.get(1));
		rp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		rl.add(new JLabel("Gold: " + 5, (int) JLabel.CENTER_ALIGNMENT));
		rp.get(6).add(rl.get(3));
		rp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		rmb.add(new JButton("Masonry : " + rql));
		rp.get(7).add(rmb.get(2));
		rp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		rl.add(new JLabel("Gold: " + 5, (int) JLabel.CENTER_ALIGNMENT));
		rp.get(8).add(rl.get(4));
		rp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		rmb.add(new JButton("Woodcraft : " + rwl));
		rp.get(9).add(rmb.get(3));
		rp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		rl.add(new JLabel("Gold: " + 3, (int) JLabel.CENTER_ALIGNMENT));
		rp.get(10).add(rl.get(5));
		rp.add(new JPanel(new GridLayout(5, 2, 0, 0)));
		for (int i = 1; i < 11; i++) rp.get(11).add(rp.get(i));
		research.setContentPane(rp.get(rp.size() - 1));
		research.pack();
		for (int i = 0; i < rmb.size(); i++) rmb.get(i).addActionListener(research(i));
	}

	private ActionListener research (final int i) {
		return new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (researchupgrading.length() != 0) {
					JOptionPane.showMessageDialog(null, "You can only research one research at a time", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (i == 0 && checkresources(0, 0, 0, 0, 7 * (rml + 1))) {
					researchupgrading = "alloys";
					researchingseconds = 65 * (rml + 1);
					gold -= 7 * (rml + 1);
				} else if (i == 1 && checkresources(0, 0, 0, 0, 5 * (rfl + 1))) {
					researchupgrading = "agriculture";
					researchingseconds = 55 * (rfl + 1);
					gold -= 5 * (rfl + 1);
				} else if (i == 2 && checkresources(0, 0, 0, 0, 5 * (rql + 1))) {
					researchupgrading = "masonry";
					researchingseconds = 55 * (rql + 1);
					gold -= 5 * (rql + 1);
				} else if (i == 3 && checkresources(0, 0, 0, 0, 3 * (rwl + 1))) {
					researchupgrading = "woodcraft";
					researchingseconds = 45 * (rql + 1);
					gold -= 3 * (rwl + 1);
				} else return;
				researching = true;
			}
		};
	}

	private void setlumbermillgui () {
		lp.clear();
		lmb.clear();
		ll.clear();
		lp.add(new JPanel(new GridLayout(1, 2, 0, 0)));
		ll.add(new JLabel("Level: " + bll[buildingplace], 0));
		ll.add(new JLabel("Location: " + (buildingplace % 5 - 5 + "").substring(1) + ", " + (buildingplace / 5 + 1), 0));
		for (int i = 0; i < 2; i++) lp.get(0).add(ll.get(i));
		lp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		lmb.add(new JButton("Upgrade"));
		lmb.get(0).addActionListener(upgrade(0));
		lp.get(1).add(lmb.get(0));
		lp.add(new JPanel(new GridLayout(5, 1, 0, 0)));
		ll.add(new JLabel("Materials needed to upgrade", 0));
		ll.add(new JLabel("Wood: " + 3 * (bll[buildingplace] + 1), 0));
		ll.add(new JLabel("Food: " + 4 * (bll[buildingplace] + 1), 0));
		ll.add(new JLabel("Metal: " + 3 * (bll[buildingplace] + 1), 0));
		ll.add(new JLabel("Stone: " + 2 * (bll[buildingplace] + 1), 0));
		for (int i = 2; i < 7; i++) lp.get(2).add(ll.get(i));
		lp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		lmb.add(new JButton("Destroy"));
		lmb.get(1).addActionListener(destroy(0));
		lp.get(3).add(lmb.get(1));
		lp.add(new JPanel(new GridLayout(5, 1, 0, 0)));
		ll.add(new JLabel("Materials going to get", 0));
		ll.add(new JLabel("Wood: " + (int) (1.5 * bll[buildingplace]), 0));
		ll.add(new JLabel("Food: " + 2 * bll[buildingplace], 0));
		ll.add(new JLabel("Metal: " + (int) (1.5 * bll[buildingplace]), 0));
		ll.add(new JLabel("Stone: " + 1 * bll[buildingplace], 0));
		for (int i = 7; i < 12; i++) lp.get(4).add(ll.get(i));
		lp.add(new JPanel(new GridLayout(3, 2, 0, 0)));
		for (int i = 0; i < 2; i++) lp.get(5).add(ll.get(i));
		for (int i = 1; i < 5; i++) lp.get(5).add(lp.get(i));
		lumbermill.setContentPane(lp.get(5));
		lumbermill.pack();
		lumbermill.setVisible(true);
	}

	private void setfarmgui () {
		fp.clear();
		fb.clear();
		fl.clear();
		fp.add(new JPanel(new GridLayout(1, 2, 0, 0)));
		fl.add(new JLabel("Level: " + bll[buildingplace], 0));
		fl.add(new JLabel("Location: " + (buildingplace % 5 - 5 + "").substring(1) + ", " + (buildingplace / 5 + 1), 0));
		for (int i = 0; i < 2; i++) fp.get(0).add(fl.get(i));
		fp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		fb.add(new JButton("Upgrade"));
		fb.get(0).addActionListener(upgrade(1));
		fp.get(1).add(fb.get(0));
		fp.add(new JPanel(new GridLayout(5, 1, 0, 0)));
		fl.add(new JLabel("Materials needed to upgrade", 0));
		fl.add(new JLabel("Wood: " + 1 * (bll[buildingplace] + 1), 0));
		fl.add(new JLabel("Food: " + 5 * (bll[buildingplace] + 1), 0));
		fl.add(new JLabel("Metal: " + 2 * (bll[buildingplace] + 1), 0));
		fl.add(new JLabel("Stone: " + 0 * (bll[buildingplace] + 1), 0));
		for (int i = 2; i < 7; i++) fp.get(2).add(fl.get(i));
		fp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		fb.add(new JButton("Destroy"));
		fb.get(1).addActionListener(destroy(1));
		fp.get(3).add(fb.get(1));
		fp.add(new JPanel(new GridLayout(5, 1, 0, 0)));
		fl.add(new JLabel("Materials going to get", 0));
		fl.add(new JLabel("Wood: " + (int) (.5 * bll[buildingplace]), 0));
		fl.add(new JLabel("Food: " + (int) (2.5 * bll[buildingplace]), 0));
		fl.add(new JLabel("Metal: " + 1 * bll[buildingplace], 0));
		fl.add(new JLabel("Stone: " + 0 * bll[buildingplace], 0));
		for (int i = 7; i < 12; i++) fp.get(4).add(fl.get(i));
		fp.add(new JPanel(new GridLayout(3, 2, 0, 0)));
		for (int i = 0; i < 2; i++) fp.get(5).add(fl.get(i));
		for (int i = 1; i < 5; i++) fp.get(5).add(fp.get(i));
		farm.setContentPane(fp.get(5));
		farm.pack();
		farm.setVisible(true);
	}

	private void setminegui () {
		mp.clear();
		mb.clear();
		mgl.clear();
		mp.add(new JPanel(new GridLayout(1, 2, 0, 0)));
		mgl.add(new JLabel("Level: " + bll[buildingplace], 0));
		mgl.add(new JLabel("Location: " + (buildingplace % 5 - 5 + "").substring(1) + ", " + (buildingplace / 5 + 1), 0));
		for (int i = 0; i < 2; i++) mp.get(0).add(mgl.get(i));
		mp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		mb.add(new JButton("Upgrade"));
		mb.get(0).addActionListener(upgrade(2));
		mp.get(1).add(mb.get(0));
		mp.add(new JPanel(new GridLayout(5, 1, 0, 0)));
		mgl.add(new JLabel("Materials needed to upgrade", 0));
		mgl.add(new JLabel("Wood: " + 4 * (bll[buildingplace] + 1), 0));
		mgl.add(new JLabel("Food: " + 2 * (bll[buildingplace] + 1), 0));
		mgl.add(new JLabel("Metal: " + 4 * (bll[buildingplace] + 1), 0));
		mgl.add(new JLabel("Stone: " + 3 * (bll[buildingplace] + 1), 0));
		for (int i = 2; i < 7; i++) mp.get(2).add(mgl.get(i));
		mp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		mb.add(new JButton("Destroy"));
		mb.get(1).addActionListener(destroy(2));
		mp.get(3).add(mb.get(1));
		mp.add(new JPanel(new GridLayout(5, 1, 0, 0)));
		mgl.add(new JLabel("Materials going to get", 0));
		mgl.add(new JLabel("Wood: " + 2 * bll[buildingplace], 0));
		mgl.add(new JLabel("Food: " + 1 * bll[buildingplace], 0));
		mgl.add(new JLabel("Metal: " + 2 * bll[buildingplace], 0));
		mgl.add(new JLabel("Stone: " + (int) (1.5 * bll[buildingplace]), 0));
		for (int i = 7; i < 12; i++) mp.get(4).add(mgl.get(i));
		mp.add(new JPanel(new GridLayout(3, 2, 0, 0)));
		for (int i = 0; i < 2; i++) mp.get(5).add(mgl.get(i));
		for (int i = 1; i < 5; i++) mp.get(5).add(mp.get(i));
		mine.setContentPane(mp.get(5));
		mine.pack();
		mine.setVisible(true);
	}

	private void setquarrygui () {
		qp.clear();
		qb.clear();
		ql.clear();
		qp.add(new JPanel(new GridLayout(1, 2, 0, 0)));
		ql.add(new JLabel("Level: " + bll[buildingplace], 0));
		ql.add(new JLabel("Location: " + (buildingplace % 5 - 5 + "").substring(1) + ", " + (buildingplace / 5 + 1), 0));
		for (int i = 0; i < 2; i++) qp.get(0).add(ql.get(i));
		qp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		qb.add(new JButton("Upgrade"));
		qb.get(0).addActionListener(upgrade(3));
		qp.get(1).add(qb.get(0));
		qp.add(new JPanel(new GridLayout(5, 1, 0, 0)));
		ql.add(new JLabel("Materials needed to upgrade", 0));
		ql.add(new JLabel("Wood: " + 0 * (bll[buildingplace] + 1), 0));
		ql.add(new JLabel("Food: " + 4 * (bll[buildingplace] + 1), 0));
		ql.add(new JLabel("Metal: " + 3 * (bll[buildingplace] + 1), 0));
		ql.add(new JLabel("Stone: " + 2 * (bll[buildingplace] + 1), 0));
		for (int i = 2; i < 7; i++) qp.get(2).add(ql.get(i));
		qp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		qb.add(new JButton("Destroy"));
		qb.get(1).addActionListener(destroy(3));
		qp.get(3).add(qb.get(1));
		qp.add(new JPanel(new GridLayout(5, 1, 0, 0)));
		ql.add(new JLabel("Materials going to get", 0));
		ql.add(new JLabel("Wood: " + 0 * bll[buildingplace], 0));
		ql.add(new JLabel("Food: " + 2 * bll[buildingplace], 0));
		ql.add(new JLabel("Metal: " + (int) (1.5 * bll[buildingplace]), 0));
		ql.add(new JLabel("Stone: " + 1 * bll[buildingplace], 0));
		for (int i = 7; i < 12; i++) qp.get(4).add(ql.get(i));
		qp.add(new JPanel(new GridLayout(3, 2, 0, 0)));
		for (int i = 0; i < 2; i++) qp.get(5).add(ql.get(i));
		for (int i = 1; i < 5; i++) qp.get(5).add(qp.get(i));
		quarry.setContentPane(qp.get(5));
		quarry.pack();
		quarry.setVisible(true);
	}

	private void sethomegui () {
		hp.clear();
		hb.clear();
		hl.clear();
		hp.add(new JPanel(new GridLayout(1, 2, 0, 0)));
		hl.add(new JLabel("Level: " + bll[buildingplace], 0));
		hl.add(new JLabel("Location: " + (buildingplace % 5 - 5 + "").substring(1) + ", " + (buildingplace / 5 + 1), 0));
		for (int i = 0; i < 2; i++) hp.get(0).add(hl.get(i));
		hp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		hb.add(new JButton("Upgrade"));
		hb.get(0).addActionListener(upgrade(4));
		hp.get(1).add(hb.get(0));
		hp.add(new JPanel(new GridLayout(5, 1, 0, 0)));
		hl.add(new JLabel("Materials needed to upgrade", 0));
		hl.add(new JLabel("Wood: " + 3 * (bll[buildingplace] + 1), 0));
		hl.add(new JLabel("Food: " + 4 * (bll[buildingplace] + 1), 0));
		hl.add(new JLabel("Metal: " + 3 * (bll[buildingplace] + 1), 0));
		hl.add(new JLabel("Stone: " + 2 * (bll[buildingplace] + 1), 0));
		for (int i = 2; i < 7; i++) hp.get(2).add(hl.get(i));
		hp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		hb.add(new JButton("Destroy"));
		hb.get(1).addActionListener(destroy(4));
		hp.get(3).add(hb.get(1));
		hp.add(new JPanel(new GridLayout(5, 1, 0, 0)));
		hl.add(new JLabel("Materials going to get", 0));
		hl.add(new JLabel("Wood: " + (int) (1.5 * bll[buildingplace]), 0));
		hl.add(new JLabel("Food: " + 2 * bll[buildingplace], 0));
		hl.add(new JLabel("Metal: " + (int) (1.5 * bll[buildingplace]), 0));
		hl.add(new JLabel("Stone: " + 1 * bll[buildingplace], 0));
		for (int i = 7; i < 12; i++) hp.get(4).add(hl.get(i));
		hp.add(new JPanel(new GridLayout(3, 2, 0, 0)));
		for (int i = 0; i < 2; i++) hp.get(5).add(hl.get(i));
		for (int i = 1; i < 5; i++) hp.get(5).add(hp.get(i));
		home.setContentPane(hp.get(5));
		home.pack();
		home.setVisible(true);
	}

	private void setsciencecentergui () {
		scp.clear();
		scb.clear();
		scl.clear();
		scp.add(new JPanel(new GridLayout(1, 2, 0, 0)));
		scl.add(new JLabel("Level: " + bll[buildingplace], 0));
		scl.add(new JLabel("Location: " + (buildingplace % 5 + 1 + "") + ", " + (buildingplace / 5 + 1), 0));
		for (int i = 0; i < 2; i++) scp.get(0).add(scl.get(i));
		scp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		scb.add(new JButton("Upgrade"));
		scb.get(0).addActionListener(upgrade(5));
		scp.get(1).add(scb.get(0));
		scp.add(new JPanel(new GridLayout(6, 1, 0, 0)));
		scl.add(new JLabel("Materials needed to upgrade", 0));
		scl.add(new JLabel("Wood: " + 10 * (bll[buildingplace] + 1), 0));
		scl.add(new JLabel("Food: " + 10 * (bll[buildingplace] + 1), 0));
		scl.add(new JLabel("Metal: " + 10 * (bll[buildingplace] + 1), 0));
		scl.add(new JLabel("Stone: " + 10 * (bll[buildingplace] + 1), 0));
		scl.add(new JLabel("Gold: " + 10 * (bll[buildingplace] + 1), 0));
		for (int i = 2; i < 8; i++) scp.get(2).add(scl.get(i));
		scp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		scb.add(new JButton("Destroy"));
		scb.get(1).addActionListener(destroy(5));
		scp.get(3).add(scb.get(1));
		scp.add(new JPanel(new GridLayout(6, 1, 0, 0)));
		scl.add(new JLabel("Materials going to get", 0));
		scl.add(new JLabel("Wood: " + 5 * bll[buildingplace], 0));
		scl.add(new JLabel("Food: " + 5 * bll[buildingplace], 0));
		scl.add(new JLabel("Metal: " + 5 * bll[buildingplace], 0));
		scl.add(new JLabel("Stone: " + 5 * bll[buildingplace], 0));
		scl.add(new JLabel("Gold: " + 5 * bll[buildingplace], 0));
		for (int i = 8; i < 14; i++) scp.get(4).add(scl.get(i));
		scp.add(new JPanel(new GridLayout(3, 2, 0, 0)));
		for (int i = 0; i < 2; i++) scp.get(5).add(scl.get(i));
		for (int i = 1; i < 5; i++) scp.get(5).add(scp.get(i));
		sciencecenter.setContentPane(scp.get(5));
		sciencecenter.pack();
		sciencecenter.setVisible(true);
	}

	private void setnewbuildingsgui () {
		nbp.clear();
		nbl.clear();
		nbb.clear();
		nbp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		nbp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		nbl.add(new JLabel("", (int) JLabel.CENTER_ALIGNMENT));
		nbp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		nbl.add(new JLabel("Materials Needed to Build Building", (int) JLabel.CENTER_ALIGNMENT));
		nbp.get(1).add(nbl.get(0));
		nbp.get(2).add(nbl.get(1));
		nbp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		nbb.add(new JButton("Lumbermill"));
		nbp.get(3).add(nbb.get(0));
		nbp.add(new JPanel(new GridLayout(4, 1, 0, 0)));
		nbl.add(new JLabel("Wood: " + 3, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Food: " + 4, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Metal: " + 3, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Stone: " + 2, (int) JLabel.CENTER_ALIGNMENT));
		for (int i = 2; i < 6; i++) nbp.get(4).add(nbl.get(i));
		nbp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		nbb.add(new JButton("Farm"));
		nbp.get(5).add(nbb.get(1));
		nbp.add(new JPanel(new GridLayout(4, 1, 0, 0)));
		nbl.add(new JLabel("Wood: " + 1, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Food: " + 5, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Metal: " + 2, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Stone: " + 0, (int) JLabel.CENTER_ALIGNMENT));
		for (int i = 6; i < 10; i++) nbp.get(6).add(nbl.get(i));
		nbp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		nbb.add(new JButton("Mine"));
		nbp.get(7).add(nbb.get(2));
		nbp.add(new JPanel(new GridLayout(4, 1, 0, 0)));
		nbl.add(new JLabel("Wood: " + 4, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Food: " + 2, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Metal: " + 4, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Stone: " + 3, (int) JLabel.CENTER_ALIGNMENT));
		for (int i = 10; i < 14; i++) nbp.get(8).add(nbl.get(i));
		nbp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		nbb.add(new JButton("Quarry"));
		nbp.get(9).add(nbb.get(3));
		nbp.add(new JPanel(new GridLayout(4, 1, 0, 0)));
		nbl.add(new JLabel("Wood: " + 0, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Food: " + 2, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Metal: " + 4, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Stone: " + 1, (int) JLabel.CENTER_ALIGNMENT));
		for (int i = 14; i < 18; i++) nbp.get(10).add(nbl.get(i));
		nbp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
		nbb.add(new JButton("Home"));
		nbp.get(11).add(nbb.get(4));
		nbp.add(new JPanel(new GridLayout(4, 1, 0, 0)));
		nbl.add(new JLabel("Wood: " + 3, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Food: " + 4, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Metal: " + 3, (int) JLabel.CENTER_ALIGNMENT));
		nbl.add(new JLabel("Stone: " + 2, (int) JLabel.CENTER_ALIGNMENT));
		for (int i = 18; i < 22; i++) nbp.get(12).add(nbl.get(i));
		if (!findsc()) {
			nbp.add(new JPanel(new GridLayout(1, 1, 0, 0)));
			nbb.add(new JButton("Science Center"));
			nbp.get(13).add(nbb.get(5));
			nbp.add(new JPanel(new GridLayout(5, 1, 0, 0)));
			nbl.add(new JLabel("Wood: " + 10, (int) JLabel.CENTER_ALIGNMENT));
			nbl.add(new JLabel("Food: " + 10, (int) JLabel.CENTER_ALIGNMENT));
			nbl.add(new JLabel("Metal: " + 10, (int) JLabel.CENTER_ALIGNMENT));
			nbl.add(new JLabel("Stone: " + 10, (int) JLabel.CENTER_ALIGNMENT));
			nbl.add(new JLabel("Gold: " + 10, (int) JLabel.CENTER_ALIGNMENT));
			for (int i = 22; i < 27; i++) nbp.get(14).add(nbl.get(i));
			nbp.add(new JPanel(new GridLayout(7, 2, 0, 0)));
			for (int i = 1; i < 15; i++) nbp.get(15).add(nbp.get(i));
		} else {
			nbp.add(new JPanel(new GridLayout(6, 2, 0, 0)));
			for (int i = 1; i < 13; i++) nbp.get(13).add(nbp.get(i));
		}
		newbuilding.setContentPane(nbp.get(nbp.size() - 1));
		newbuilding.pack();
		for (int i = 0; i < nbb.size(); i++) nbb.get(i).addActionListener(upgrade(i));
	}

	private void setgui () {
		setmaingui();
		setstatsgui();
		setbuildinggui();
		setnewbuildingsgui();
		setresearchgui();
	}

	private boolean findsc () {
		for (int i = 0; i < 26; i++) if (bl[i].equals("Science Center")) return true;
		return false;
	}

	private boolean checkresources (int w, int f, int m, int s) {
		return checkresources(w, f, m, s, 0);
	}

	private boolean checkresources (int w, int f, int m, int s, int g) {
		if (!(w <= wood && f <= food && m <= metal && s <= stone && g <= gold)) {
			JOptionPane.showMessageDialog(null, "You are missing one or more resources", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			wood -= w;
			food -= f;
			metal -= m;
			stone -= s;
			gold -= g;
			return true;
		}
	}

	private ActionListener destroy (final int i) {
		return new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (i == 0) {
					wood += 1.5 * bll[buildingplace];
					food += 2 * bll[buildingplace];
					metal += 1.5 * bll[buildingplace];
					stone += 1 * bll[buildingplace];
					lumbermill.dispose();
				} else if (i == 1) {
					wood += .5 * bll[buildingplace];
					food += 2.5 * bll[buildingplace];
					metal += 1 * bll[buildingplace];
					stone += 0 * bll[buildingplace];
					farm.dispose();
				} else if (i == 2) {
					wood += 2 * bll[buildingplace];
					food += 1 * bll[buildingplace];
					metal += 2 * bll[buildingplace];
					stone += 1.5 * bll[buildingplace];
					mine.dispose();
				} else if (i == 3) {
					wood += 0 * bll[buildingplace];
					food += 2 * bll[buildingplace];
					metal += 1.5 * bll[buildingplace];
					stone += 1 * bll[buildingplace];
					quarry.dispose();
				} else if (i == 4) {
					wood += 1.5 * bll[buildingplace];
					food += 2 * bll[buildingplace];
					metal += 1.5 * bll[buildingplace];
					stone += 1 * bll[buildingplace];
					home.dispose();
				} else if (i == 5) {
					wood += 5 * bll[buildingplace];
					food += 5 * bll[buildingplace];
					metal += 5 * bll[buildingplace];
					stone += 5 * bll[buildingplace];
					gold += 5 * bll[buildingplace];
					sciencecenter.dispose();
				} else return;
				bl[buildingplace] = " ";
				setbuildinggui();
				xp--;
				bll[buildingplace] = 0;
			}
		};
	}

	private ActionListener upgrade (final int i) {
		return new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (buildingupgrading.length() != 0) {
					JOptionPane.showMessageDialog(null, "You can only build one building at a time", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (i == 0 && checkresources(3 * (bll[buildingplace] + 1), 4 * (bll[buildingplace] + 1), 3 * (bll[buildingplace] + 1), 2 * (bll[buildingplace] + 1))) {
					buildingupgrading = "lumbermill";
					buildingseconds = 15 * (bll[buildingplace] + 1);
					lumbermill.dispose();
				} else if (i == 1 && checkresources(1 * (bll[buildingplace] + 1), 5 * (bll[buildingplace] + 1), 2 * (bll[buildingplace] + 1), 0)) {
					buildingupgrading = "farm";
					buildingseconds = 15 * (bll[buildingplace] + 1);
					farm.dispose();
				} else if (i == 2 && checkresources(4 * (bll[buildingplace] + 1), 2 * (bll[buildingplace] + 1), 4 * (bll[buildingplace] + 1), 3 * (bll[buildingplace] + 1))) {
					buildingupgrading = "mine";
					buildingseconds = 10 * (bll[buildingplace] + 1);
					mine.dispose();
				} else if (i == 3 && checkresources(0, 4 * (bll[buildingplace] + 1), 3 * (bll[buildingplace] + 1), 2 * (bll[buildingplace] + 1))) {
					buildingupgrading = "quarry";
					buildingseconds = 17 * (bll[buildingplace] + 1);
					quarry.dispose();
				} else if (i == 4 && checkresources(3 * (bll[buildingplace] + 1), 4 * (bll[buildingplace] + 1), 3 * (bll[buildingplace] + 1), 2 * (bll[buildingplace] + 1))) {
					buildingupgrading = "home";
					buildingseconds = 20 * (bll[buildingplace] + 1);
					home.dispose();
				} else if (i == 5 && checkresources(10 * (bll[buildingplace] + 1), 10 * (bll[buildingplace] + 1), 10 * (bll[buildingplace] + 1), 10 * (bll[buildingplace] + 1), 10 * (bll[buildingplace] + 1))) {
					buildingupgrading = "sciencecenter";
					buildingseconds = 25 * (bll[buildingplace] + 1);
					sciencecenter.dispose();
				} else return;
				upgrading = true;
				newbuilding.dispose();
			}
		};
	}

	public void main (String username) {
		saved = false;
		pt.clear();
		ps.clear();
		pb.clear();
		ls.clear();
		bbl.clear();
		nbb.clear();
		nbl.clear();
		nbp.clear();
		bl = new String[26];
		bll = new int[26];
		cop = new coloroptionsdialog();
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed (WindowEvent we) {
				done = true;
				cop.first = true;
				save();
				dispose();
				buildings.dispose();
				stats.dispose();
				cop.dispose();
				newbuilding.dispose();
				home.dispose();
				sciencecenter.dispose();
				farm.dispose();
				lumbermill.dispose();
				mine.dispose();
				quarry.dispose();
				research.dispose();
			}
		});
		user = username;
		for (int i = 0; i < 26; i++) bl[i] = " ";
		load();
		appear();
		setgui();
		fixcolors();
		new Thread(this).start();
	}

	private void load () {
		try {
			File folder = new File("timekeeperusers");
			if (!folder.exists()) folder.mkdirs();
			users = new File("timekeeperusers\\users.list");
			if (!users.exists()) users.createNewFile();
			reader = new BufferedReader(new FileReader(users));
			String line = reader.readLine();
			while (line != null) {
				out.add(line);
				line = reader.readLine();
			}
			reader.close();
			if (out.indexOf(user) != -1) {
				userdata = new File("timekeeperusers\\" + user + ".user");
				reader = new BufferedReader(new FileReader(userdata));
				line = reader.readLine();
				while (line != null) {
					data.add(line);
					line = reader.readLine();
				}
				reader.close();
				if (data.size() == 85) {
					wood = Double.parseDouble(data.get(0));
					gold = Double.parseDouble(data.get(1));
					food = Double.parseDouble(data.get(2));
					metal = Double.parseDouble(data.get(3));
					stone = Double.parseDouble(data.get(4));
					wph = Double.parseDouble(data.get(5));
					fph = Double.parseDouble(data.get(6));
					gph = Double.parseDouble(data.get(7));
					mph = Double.parseDouble(data.get(8));
					sph = Double.parseDouble(data.get(9));
					population = Integer.parseInt(data.get(10));
					level = Integer.parseInt(data.get(11));
					wlvl = Integer.parseInt(data.get(12));
					hlvl = Integer.parseInt(data.get(13));
					flvl = Integer.parseInt(data.get(14));
					mlvl = Integer.parseInt(data.get(15));
					qlvl = Integer.parseInt(data.get(16));
					sclvl = Integer.parseInt(data.get(17));
					xp = Integer.parseInt(data.get(18));
					xpn = Integer.parseInt(data.get(19));
					buildingseconds = Integer.parseInt(data.get(20));
					b = Integer.parseInt(data.get(21));
					buildingupgrading = data.get(22).toLowerCase();
					researchupgrading = data.get(23).toLowerCase();
					rwl = Integer.parseInt(data.get(24));
					rml = Integer.parseInt(data.get(25));
					rql = Integer.parseInt(data.get(26));
					rfl = Integer.parseInt(data.get(27));
					r = Integer.parseInt(data.get(28));
					researchingseconds = Integer.parseInt(data.get(29));
					String dtz = data.get(30);
					int[] i = util.checkerrors(dtz);
					try {
						cop.foreground = new Color(Integer.parseInt(dtz.substring(dtz.indexOf("r=") + 2, dtz.indexOf("r=") + i[0])), Integer.parseInt(dtz.substring(dtz.indexOf("g=") + 2, dtz.indexOf("g=") + i[1])), Integer.parseInt(dtz.substring(dtz.indexOf("b=") + 2, dtz.indexOf("b=") + i[2])));
					} catch (Exception e) {
						System.out.println(e);
					}
					String dto = data.get(31);
					i = util.checkerrors(dto);
					try {
						cop.background = new Color(Integer.parseInt(dto.substring(dto.indexOf("r=") + 2, dto.indexOf("r=") + i[0])), Integer.parseInt(dto.substring(dto.indexOf("g=") + 2, dto.indexOf("g=") + i[1])), Integer.parseInt(dto.substring(dto.indexOf("b=") + 2, dto.indexOf("b=") + i[2])));
					} catch (Exception e) {
						System.out.println(e);
					}
					for (int a = 0; a < 26; a++) {
						bl[a] = data.get(a + 32);
						if (bl[a].equals("") || bl[a].equals(null)) bl[a] = " ";
					}
					for (int a = 0; a < 26; a++) bll[a] = Integer.parseInt(data.get(a + 58));
					buildingplace = Integer.parseInt(data.get(84));
					if (researchupgrading.length() != 0) researching = true;
					if (buildingupgrading.length() != 0) upgrading = true;
				} else System.out.println(data.size());
			} else out.add(user);
			users.delete();
			users.createNewFile();
			writer = new FileWriter(users);
			for (int i = 0; i < out.size(); i++) {
				writer.write(out.get(i) + System.getProperty("line.separator"));
			}
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private String round (double num) {
		try {
			return (num + "").substring(0, (num + "").indexOf(".") + 3);
		} catch (Exception e) {
			try {
				return (num + "").substring(0, (num + "").indexOf(".") + 2);
			} catch (Exception ea) {
				return num + "";
			}
		}
	}

	public void run () {
		while (true) {
			if (done) break;
			for (int i = 0; i < 26; i++) if (bl[i].equals("Science Center")) rb.setEnabled(true);
			fixcolors();
			ls.get(0).setText("Wood: " + round(wood));
			ls.get(1).setText("Wood per hour: " + round(wph));
			ls.get(2).setText("Food: " + round(food));
			ls.get(3).setText("Food per hour: " + round(fph));
			ls.get(4).setText("Metal: " + round(metal));
			ls.get(5).setText("Metal per hour: " + round(mph));
			ls.get(6).setText("Stone: " + round(stone));
			ls.get(7).setText("Stone per hour: " + round(sph));
			ls.get(8).setText("Gold: " + round(gold));
			ls.get(9).setText("Gold per hour: " + round(gph));
			stats.pack();
			ml.get(0).setText("Level: " + level);
			ml.get(1).setText("XP: " + xp);
			ml.get(2).setText("XP need: " + xpn);
			ml.get(3).setText("Time Left for Building: " + (buildingseconds - b));
			ml.get(4).setText("Time Left for Researching: " + (researchingseconds - r));
			rl.get(2).setText("Gold: " + 7 * (rml + 1));
			rl.get(3).setText("Gold: " + 5 * (rfl + 1));
			rl.get(4).setText("Gold: " + 5 * (rql + 1));
			rl.get(5).setText("Gold: " + 3 * (rwl + 1));
			rmb.get(0).setText("Alloys : " + rml);
			rmb.get(1).setText("Agriculture : " + rfl);
			rmb.get(2).setText("Masonry : " + rql);
			rmb.get(3).setText("Woodcraft : " + rwl);
			research.pack();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			if (level == 1) {
				if (rwl == 0) wph = wlvl * 4;
				else wph = wlvl * 4 * (1.025 * rwl);
				if (rml == 0) mph = mlvl * 5;
				else mph = mlvl * 5 * (1.025 * rml);
				if (rql == 0) sph = qlvl * 3;
				else sph = qlvl * 3 * (1.025 * rql);
			} else {
				if (rwl == 0) wph = wlvl * 4 * (1.025 * level);
				else wph = wlvl * 4 * (1.025 * rwl) * (1.025 * level);
				if (rml == 0) mph = mlvl * 5 * (1.025 * level);
				else mph = mlvl * 5 * (1.025 * rml) * (1.025 * level);
				if (rql == 0) sph = qlvl * 3 * (1.025 * level);
				else sph = qlvl * 3 * (1.025 * rql) * (1.025 * level);
			}
			if (!upgrading) {
				if (level == 1) population = hlvl * 5;
				else population = (int) (hlvl * 5 * (1.025 * level));
			} else {
				int secsleft = buildingseconds - b;
				if (secsleft > 0) b++;
				else if (secsleft <= 0) {
					System.out.println("Done building!");
					b -= buildingseconds;
					if (b <= 0) b = 0;
					buildingseconds = (-1);
					upgrading = false;
					if (buildingupgrading.substring(0, 4).equals("home")) {
						buildingupgrading = buildingupgrading.replaceFirst("home", "");
						hlvl += 1;
						bl[buildingplace] = "Home";
					} else if (buildingupgrading.substring(0, 4).equals("farm")) {
						buildingupgrading = buildingupgrading.replaceFirst("farm", "");
						flvl += 1;
						bl[buildingplace] = "Farm";
					} else if (buildingupgrading.substring(0, 4).equals("mine")) {
						buildingupgrading = buildingupgrading.replaceFirst("mine", "");
						mlvl += 1;
						bl[buildingplace] = "Mine";
					} else if (buildingupgrading.substring(0, 6).equals("quarry")) {
						buildingupgrading = buildingupgrading.replaceFirst("quarry", "");
						qlvl += 1;
						bl[buildingplace] = "Quarry";
					} else if (buildingupgrading.substring(0, 10).equals("lumbermill")) {
						buildingupgrading = buildingupgrading.replaceFirst("lumbermill", "");
						wlvl += 1;
						bl[buildingplace] = "Lumbermill";
					} else if (buildingupgrading.substring(0, 13).equals("sciencecenter")) {
						buildingupgrading = buildingupgrading.replaceFirst("sciencecenter", "");
						sclvl += 1;
						bl[buildingplace] = "Science Center";
					}
					bll[buildingplace] += 1;
					setbuildinggui();
					xp++;
					buildingplace = -1;
				} else if (buildingseconds == -1) {
					upgrading = false;
					buildingplace = -1;
					buildingupgrading = "";
				}
			}
			if (researching) {
				int researchingsecsleft = researchingseconds - r;
				if (researchingsecsleft > 0) r++;
				else if (researchingsecsleft <= 0) {
					System.out.println("Done Researching!");
					r -= researchingseconds;
					if (r <= 0) r = 0;
					researchingseconds = (-1);
					researching = false;
					xp++;
					if (researchupgrading.substring(0, 6).equals("alloys")) {
						researchupgrading = researchupgrading.replaceFirst("alloys", "");
						rml += 1;
					} else if (researchupgrading.substring(0, 7).equals("masonry")) {
						researchupgrading = researchupgrading.replaceFirst("masonry", "");
						rql += 1;
					} else if (researchupgrading.substring(0, 9).equals("woodcraft")) {
						researchupgrading = researchupgrading.replaceFirst("woodcraft", "");
						rwl += 1;
					} else if (researchupgrading.substring(0, 11).equals("agriculture")) {
						researchupgrading = researchupgrading.replaceFirst("agriculture", "");
						rfl += 1;
					}
				} else if (researchingseconds == -1) {
					researching = false;
					researchupgrading = "";
				}
			}
			if (level == 1) {
				if (rfl == 0) fph = (flvl * 5) - population / 10.0;
				else fph = (flvl * 5 * (rfl * 1.025)) - population / 10.0;
				gph = (population / 5.0);
			} else {
				if (rfl == 0) fph = (flvl * 5) * (1.025 * level) - population / 10.0;
				else fph = (flvl * 5) * (1.025 * level) * (rfl * 1.025) - population / 10.0;
				gph = (population / 5.0) * (1.025 * level);
			}
			wood += wph / 3600;
			gold += gph / 3600;
			food += fph / 3600;
			metal += mph / 3600;
			stone += sph / 3600;
			if (xp >= xpn) {
				xp -= xpn;
				if (xp < 0) {
					xp += xpn;
					continue;
				}
				level += 1;
				xpn = level * 10;
				wood += 50 * level;
				metal += 50 * level;
				stone += 50 * level;
				gold += 50 * level;
				food += 50 * level;
			}
		}
	}

	private void setcolor (JLabel l) {
		l.setBackground(cop.background);
		l.setForeground(cop.foreground);
	}

	private void setcolor (JButton l) {
		l.setBackground(cop.background);
		l.setForeground(cop.foreground);
	}

	private void fixcolors () {
		cop.checkcolors();
		boolean[] done = new boolean[24];
		boolean complete = false;
		int i = 0;
		while (!complete) {
			if (i < pt.size()) pt.get(i).setBackground(cop.background);
			else done[0] = true;
			if (i < ps.size()) ps.get(i).setBackground(cop.background);
			else done[1] = true;
			if (i < pb.size()) pb.get(i).setBackground(cop.background);
			else done[2] = true;
			if (i < hp.size()) hp.get(i).setBackground(cop.background);
			else done[3] = true;
			if (i < scp.size()) scp.get(i).setBackground(cop.background);
			else done[4] = true;
			if (i < lp.size()) lp.get(i).setBackground(cop.background);
			else done[5] = true;
			if (i < fp.size()) fp.get(i).setBackground(cop.background);
			else done[6] = true;
			if (i < rp.size()) {
				rp.get(i).setBackground(cop.background);
				if (i % 2 == 0) rp.get(i).setBorder(BorderFactory.createLineBorder(cop.foreground));
			} else done[7] = true;
			if (i < nbp.size()) {
				nbp.get(i).setBackground(cop.background);
				if (i % 2 == 0) nbp.get(i).setBorder(BorderFactory.createLineBorder(cop.foreground));
			} else done[8] = true;
			if (i < ls.size()) setcolor(ls.get(i));
			else done[9] = true;
			if (i < bbl.size()) setcolor(bbl.get(i));
			else done[10] = true;
			if (i < nbl.size()) setcolor(nbl.get(i));
			else done[11] = true;
			if (i < nbb.size()) setcolor(nbb.get(i));
			else done[12] = true;
			if (i < ml.size()) setcolor(ml.get(i));
			else done[13] = true;
			if (i < hb.size()) setcolor(hb.get(i));
			else done[14] = true;
			if (i < hl.size()) setcolor(hl.get(i));
			else done[15] = true;
			if (i < scb.size()) setcolor(scb.get(i));
			else done[16] = true;
			if (i < scl.size()) setcolor(scl.get(i));
			else done[17] = true;
			if (i < lmb.size()) setcolor(lmb.get(i));
			else done[18] = true;
			if (i < ll.size()) setcolor(ll.get(i));
			else done[19] = true;
			if (i < fb.size()) setcolor(fb.get(i));
			else done[20] = true;
			if (i < fl.size()) setcolor(fl.get(i));
			else done[21] = true;
			if (i < rmb.size()) setcolor(rmb.get(i));
			else done[22] = true;
			if (i < rl.size()) setcolor(rl.get(i));
			else done[23] = true;
			for (boolean b : done) {
				if (!b) {
					complete = false;
					break;
				} else complete = true;
			}
			if (complete) break;
			i++;
		}
		setcolor(welcome);
		setcolor(sb);
		setcolor(bb);
		setcolor(rb);
		setcolor(ob);
		setcolor(lb);
	}
}