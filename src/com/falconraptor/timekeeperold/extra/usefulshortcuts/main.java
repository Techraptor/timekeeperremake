package com.falconraptor.timekeeperold.extra.usefulshortcuts;


import com.falconraptor.timekeeperold.extra.utilities.*;
import com.falconraptor.utilities.files.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class main {
	final TrayIcon trayIcon;
	final SystemTray tray = SystemTray.getSystemTray();
	Robot r;
	ArrayList<MenuItem> menuitems = new ArrayList<MenuItem>(0);
	ArrayList<Menu> menus = new ArrayList<Menu>(0);
	ArrayList<Integer> menuitemsinmenus = new ArrayList<Integer>(0);
	ArrayList<urls> url = new ArrayList<urls>(0);
	ArrayList<files> file = new ArrayList<files>(0);
	ArrayList<JPanel> p = new ArrayList<JPanel>(0);
	ArrayList<JList> lists = new ArrayList<JList>(0);
	ArrayList<JButton> buttons = new ArrayList<JButton>(0);
	ArrayList<Integer[]> menuitemindexes = new ArrayList<Integer[]>(0);
	boolean loaded = false, first = true;
	JFrame options = new JFrame("Options");
	PopupMenu popup = new PopupMenu();
	ActionListener listener;

	public main () {
		loadconfig();
		options.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		options.setSize(500, 500);
		options.setLocationRelativeTo(null);
		options.setVisible(false);
		if (!loaded) {
			menuitems.clear();
			menus.clear();
			menuitemsinmenus.clear();
			url.clear();
			file.clear();
		}
		final boolean chrome;
		chrome = new java.io.File("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe").exists() || new java.io.File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe").exists();
		File fold = new File("resources");
		if (!fold.exists()) fold.mkdir();
		if (!SystemTray.isSupported()) {
			JOptionPane.showMessageDialog(null, "SystemTray not supported", "ERROR", JOptionPane.ERROR_MESSAGE);
			trayIcon = null;
			return;
		}
		trayIcon = new TrayIcon(createImage());
		if (!loaded) {
			menus.add(new Menu("Timekeeper"));
			menuitems.add(new MenuItem("Timekeeper"));
			file.add(new files("timekeeperold.jar", "Timekeeper", "https://dl.dropboxusercontent.com/u/109423311/timekeeperold/timekeeperold.jar"));
			menuitems.add(new MenuItem("MineSweeper"));
			file.add(new files("resources\\jars\\minesweeper.jar", "MineSweeper", "https://dl.dropboxusercontent.com/u/109423311/timekeeperold/extras/minesweeper.jar"));
			menuitems.add(new MenuItem("TimeGame"));
			file.add(new files("resources\\jars\\timegame.jar", "TimeGame", "https://dl.dropboxusercontent.com/u/109423311/timekeeperold/extras/timegame.jar"));
			menuitemsinmenus.add(2);
			for (int i = 0; i < 3; i++) menus.get(0).add(menuitems.get(i));
			if (chrome) {
				menus.add(new Menu("Chrome"));
				menuitems.add(new MenuItem("Chrome"));
				url.add(new urls("google.com", "Chrome"));
				menuitems.add(new MenuItem("ParentLink"));
				url.add(new urls("parentlink.ccsd.net", "ParentLink"));
				menuitems.add(new MenuItem("Edmodo"));
				url.add(new urls("edmodo.com", "Edmodo"));
				menuitems.add(new MenuItem("Quia"));
				url.add(new urls("quia.com/web", "Quia"));
				menuitemsinmenus.add(4);
				for (int i = 3; i < 7; i++) menus.get(1).add(menuitems.get(i));
			} else {
				menus.add(new Menu("Internet Explorer"));
				menuitems.add(new MenuItem("Internet Explorer"));
				url.add(new urls("google.com", "Internet Explorer"));
				menuitems.add(new MenuItem("ParentLink"));
				url.add(new urls("parentlink.ccsd.net", "ParentLink"));
				menuitems.add(new MenuItem("Edmodo"));
				url.add(new urls("edmodo.com", "Edmodo"));
				menuitems.add(new MenuItem("Quia"));
				url.add(new urls("quia.com/web", "Quia"));
				menuitemsinmenus.add(4);
				for (int i = 3; i < 7; i++) menus.get(1).add(menuitems.get(i));
			}
			menus.add(new Menu("Folders"));
			File[] paths = File.listRoots();
			for (File f : paths) menuitems.add(new MenuItem(f + ""));
			for (int i = 7; i < menuitems.size(); i++) menus.get(2).add(menuitems.get(i));
			menuitemsinmenus.add(paths.length);
		}
		for (Menu m : menus) popup.add(m);
		menuitems.add(new MenuItem("Options"));
		menuitems.add(new MenuItem("Exit"));
		popup.addSeparator();
		popup.add(menuitems.get(menuitems.size() - 2));
		popup.add(menuitems.get(menuitems.size() - 1));
		trayIcon.setPopupMenu(popup);
		try {
			tray.add(trayIcon);
			trayIcon.setImageAutoSize(true);
			trayIcon.setToolTip("Useful Shortcuts");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Tray icon could not be added", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		listener = new ActionListener() {
			public void actionPerformed (ActionEvent ea) {
				MenuItem item = (MenuItem) ea.getSource();
				try {
					r = new Robot();
				} catch (Exception e) {
					System.err.println(e);
				}
				r.setAutoWaitForIdle(true);
				if ("Exit".equals(item.getLabel())) {
					saveconfig();
					tray.remove(trayIcon);
					options.dispose();
					System.exit(0);
				} else if ("Options".equals(item.getLabel())) options.setVisible(true);
				else {
					File[] paths = File.listRoots();
					for (File f : paths) {
						if ((f + "").equals(item.getLabel())) {
							try {
								if (Desktop.isDesktopSupported())
									Desktop.getDesktop().open(new File(f.getCanonicalPath()));
							} catch (Exception e) {
								System.err.println(e);
							}
						}
					}
					for (urls u : url) {
						if (item.getLabel().equals(u.getcommand())) {
							final String command;
							if (chrome) command = "chrome " + u.geturl();
							else command = "iexplore " + u.geturl();
							new Thread(new Runnable() {
								public void run () {
									util.windowsr(r);
									util.sendtext(command.toLowerCase(), r);
									util.enter(r);
								}
							}).start();
						}
					}
					for (files f : file) {
						if (item.getLabel().equals(f.getcommand())) {
							if (item.getLabel().equals("Timekeeper")) {
								Files.downloadfile("https://dl.dropboxusercontent.com/u/109423311/timekeeperold/timekeeperversion.txt", "timekeeperversion.txt");
								Double read = 0.0;
								try {
									File file = new File("timekeeperversion.txt");
									BufferedReader reader = new BufferedReader(new FileReader(file));
									String out = reader.readLine();
									reader.close();
									read = Double.parseDouble(out);
									file.delete();
								} catch (Exception e) {
									System.err.println(e);
								}
								if (!new File("timekeeperold" + read + ".jar").exists())
									Files.downloadfile(f.geturl(), "timekeeperold" + read + ".jar");
								try {
									Desktop.getDesktop().open(new File("timekeeperold" + read + ".jar"));
								} catch (Exception e) {
									System.err.println(e);
								}
								break;
							}
							if (!new File(f.getpath()).exists() && !f.geturl().equals(""))
								Files.downloadfile(f.geturl(), f.getpath());
							try {
								if (Desktop.isDesktopSupported())
									Desktop.getDesktop().open(new File(new File(f.getpath()).getCanonicalPath()));
							} catch (Exception e) {
								System.err.println(e);
							}
						}
					}
				}
			}
		};
		for (MenuItem m : menuitems) m.addActionListener(listener);
		updategui();
	}

	public static void main (String[] args) {
		new main();
	}

	private Image createImage () {
		try {
			return ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/images/usefulshortcutsicon.png"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image not loaded", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.err.println(e);
			return null;
		}
	}

	private void loadconfig () {
		try {
			File folder = new File("resources\\usefulshortcutsconfig.cfg");
			BufferedReader reader = new BufferedReader(new FileReader(folder));
			ArrayList<String> out = new ArrayList<String>(0);
			String line = reader.readLine();
			while (line != null) {
				out.add(line);
				line = reader.readLine();
			}
			reader.close();
			for (String s : out) {
				if (s.equals("Folders")) {
					menus.add(new Menu("Folders"));
					File[] paths = File.listRoots();
					menuitemindexes.add(new Integer[paths.length]);
					int index = menus.size() - 1, indexstart = -1, indexend = -1, j = 0;
					for (File f : paths) {
						menuitems.add(new MenuItem(f + ""));
						if (indexstart == -1) indexstart = menuitems.size() - 1;
						indexend = menuitems.size();
						menuitemindexes.get(index)[j] = menuitems.size() - 1;
						j++;
					}
					for (int i = indexstart; i < indexend; i++) menus.get(index).add(menuitems.get(i));
					menuitemsinmenus.add(paths.length);
				} else if (s.equals("Timekeeper")) {
					menus.add(new Menu("Timekeeper"));
					menuitemindexes.add(new Integer[3]);
					int index = menus.size() - 1, indexstart = -1, indexend = -1;
					indexstart = menuitems.size() - 1;
					menuitemindexes.get(index)[0] = menuitems.size() - 1;
					menuitems.add(new MenuItem("MineSweeper"));
					menuitemindexes.get(index)[1] = menuitems.size() - 1;
					file.add(new files("resources\\jars\\minesweeper.jar", "MineSweeper", "https://dl.dropboxusercontent.com/u/109423311/timekeeperold/extras/minesweeper.jar"));
					menuitems.add(new MenuItem("TimeGame"));
					menuitemindexes.get(index)[2] = menuitems.size() - 1;
					file.add(new files("resources\\jars\\timegame.jar", "TimeGame", "https://dl.dropboxusercontent.com/u/109423311/timekeeperold/extras/timegame.jar"));
					menuitemsinmenus.add(2);
					indexend = menuitems.size();
					for (int i = indexstart; i < indexend; i++) menus.get(index).add(menuitems.get(i));
				} else {
					String[] data = s.split(">")[1].split(",");
					menus.add(new Menu(s.split(">")[0]));
					menuitemsinmenus.add(data.length);
					int index = menus.size() - 1, indexstart = -1, indexend = -1, j = 0;
					menuitemindexes.add(new Integer[data.length]);
					for (String d : data) {
						String[] test = d.split("=");
						menuitems.add(new MenuItem(test[0]));
						menuitemindexes.get(index)[j] = menuitems.size() - 1;
						if (indexstart == -1) indexstart = menuitems.size() - 1;
						if (s.split(">")[0].equals("Chrome") || s.split(">")[0].equals("Internet Explorer"))
							url.add(new urls(test[1], test[0]));
						else {
							String[] stuff = new String[3];
							stuff[0] = test[0];
							if (test[1].indexOf("%") != -1) {
								String[] a = test[1].split("%");
								System.arraycopy(a, 0, stuff, 1, 2);
							} else {
								Files.getrelativepath(test[1]);
								stuff[1] = Files.getrelativepath(test[1]);
								stuff[2] = "";
							}
							file.add(new files(stuff[1], stuff[0], stuff[2]));
						}
						indexend = menuitems.size();
						j++;
					}
					for (int i = indexstart; i < indexend; i++) menus.get(index).add(menuitems.get(i));
				}
			}
			loaded = true;
		} catch (Exception e) {
			loaded = false;
			System.err.println("No file or Error loading config: " + e);
		}
	}

	private void saveconfig () {
		try {
			File folder = new File("resources\\usefulshortcutsconfig.cfg");
			if (!folder.exists()) folder.createNewFile();
			FileWriter writer = new FileWriter(folder);
			int min = 0, max, ui = 0;
			for (int i = 0; i < menus.size(); i++) {
				if (menus.get(i).getLabel().equals("Folders") || menus.get(i).getLabel().equals("Timekeeper")) {
					writer.write(menus.get(i).getLabel() + System.getProperty("line.separator"));
					max = menuitemsinmenus.get(i) + min;
					min = max;
					continue;
				}
				writer.write(menus.get(i).getLabel() + ">");
				max = menuitemsinmenus.get(i) + min;
				for (int j = min; j < max; j++) {
					writer.write(menuitems.get(j).getLabel());
					if (menus.get(i).getLabel().equals("Chrome") || menus.get(i).getLabel().equals("Internet Explorer"))
						for (urls u : url) {
							if (menuitems.get(j).getLabel().equals(u.getcommand()))
								writer.write("=" + u.geturl());
						}
					else {
						for (files u : file) {
							if (menuitems.get(j).getLabel().equals(u.getcommand())) {
								writer.write("=" + u.getpath());
								if (!u.geturl().equals("")) writer.write("%" + u.geturl());
							}
						}
					}
					if (j < max - 1) writer.write(",");
				}
				min = max;
				writer.write(System.getProperty("line.separator"));
			}
			writer.close();
		} catch (Exception e) {
			loaded = false;
			System.err.println("Error saving config: " + e);
		}
	}

	private void updategui () {
		options.setContentPane(setgui());
		options.pack();
	}

	private String[] getmenulabels () {
		String[] data = new String[menus.size()];
		for (int i = 0; i < menus.size(); i++) data[i] = menus.get(i).getLabel();
		return data;
	}

	private JPanel setgui () {
		p.clear();
		if (lists.size() < 1) {
			lists.add(new JList(getmenulabels()));
			lists.get(0).setSelectedIndex(0);
		}
		p.add(new JPanel(new GridLayout(1, 2, 0, 0)));
		Box b = Box.createVerticalBox(), ba = Box.createHorizontalBox(), bb = Box.createHorizontalBox();
		ba.add(lists.get(0));
		if (buttons.size() < 1) {
			buttons.add(new JButton("Add"));
			buttons.add(new JButton("Remove"));
		}
		for (int i = 0; i < 2; i++) bb.add(buttons.get(i));
		b.add(ba);
		b.add(bb);
		p.get(0).add(b);
		if (lists.size() < 2) lists.add(new JList(getmenuitems(getmenulabels()[lists.get(0).getSelectedIndex()])));
		else {
			try {
				lists.set(1, new JList(getmenuitems(getmenulabels()[lists.get(0).getSelectedIndex()])));
			} catch (Exception e) {
				System.err.println(e);
				lists.get(0).setSelectedIndex(lists.get(0).getSelectedIndex() - 1);
				lists.set(1, new JList(getmenuitems(getmenulabels()[lists.get(0).getSelectedIndex()])));
			}
		}
		if (first) lists.get(0).addListSelectionListener(new ListSelectionListener() {
			public void valueChanged (ListSelectionEvent e) {
				lists.remove(1);
				updategui();
			}
		});
		if (lists.size() < 2) lists.get(1).setSelectedIndex(0);
		if (buttons.size() < 3) {
			buttons.add(new JButton("Add "));
			buttons.add(new JButton("Remove "));
			for (JButton jb : buttons) jb.addActionListener(clicked(jb.getText()));
		}
		Box b2 = Box.createVerticalBox(), ba2 = Box.createHorizontalBox(), bb2 = Box.createHorizontalBox();
		ba2.add(lists.get(1));
		for (int i = 2; i < 4; i++) bb2.add(buttons.get(i));
		b2.add(ba2);
		b2.add(bb2);
		p.get(0).add(b2);
		first = false;
		return p.get(0);
	}

	private String[] getmenuitems (String menu) {
		for (Menu m : menus) {
			if (m.getLabel().equals(menu)) {
				String[] data = new String[m.getItemCount()];
				for (int i = 0; i < m.getItemCount(); i++) data[i] = m.getItem(i).getLabel();
				return data;
			}
		}
		return new String[]{""};
	}

	private ActionListener clicked (final String button) {
		return new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				if (button.equals("Add")) {
					String s = JOptionPane.showInputDialog("Name of Folder: ");
					menus.add(new Menu(s));
					lists.clear();
					menuitemsinmenus.add(0);
					first = true;
				} else if (button.equals("Remove")) {
					int r = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Warning", JOptionPane.YES_NO_OPTION);
					if (r == JOptionPane.YES_OPTION) {
						String[] labels = getmenuitems(getmenulabels()[lists.get(0).getSelectedIndex()]);
						for (String s : labels) {
							for (int i = 0; i < menuitems.size(); i++)
								if (menuitems.get(i).getLabel().equals(s)) menuitems.remove(i);
							for (int i = 0; i < file.size(); i++)
								if (file.get(i).getcommand().equals(s)) file.remove(i);
							for (int i = 0; i < url.size(); i++)
								if (url.get(i).getcommand().equals(s)) url.remove(i);
						}
						menus.remove(lists.get(0).getSelectedIndex());
						menuitemsinmenus.remove(lists.get(0).getSelectedIndex());
						lists.clear();
						first = true;
					}
				} else if (button.equals("Add ")) {
					String stuff = getmenulabels()[lists.get(0).getSelectedIndex()];
					if (stuff.equals("Chrome") || stuff.equals("Internet Explorer")) {
						String site = JOptionPane.showInputDialog("Name of Website: ");
						String u = JOptionPane.showInputDialog("URL: ");
						menuitems.add(new MenuItem(site));
						int added = menuitems.size() - 1;
						url.add(new urls(u, site));
						for (int i = 0; i < menus.size(); i++) {
							if (menus.get(i).getLabel().equals(stuff)) {
								menus.get(i).add(menuitems.get(added));
								menuitemsinmenus.set(i, menuitemsinmenus.get(i) + 1);
							}
						}
						lists.clear();
						first = true;
					} else {
						JFileChooser chooser = new JFileChooser(new File(".."));
						chooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
						int returnVal = chooser.showOpenDialog(lists.get(1).getComponentAt(0, 0));
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							String name = chooser.getSelectedFile().getName();
							if (name.indexOf(".") != -1) name = name.substring(0, name.indexOf("."));
							menuitems.add(new MenuItem(name));
							int added = menuitems.size() - 1;
							for (int i = 0; i < menus.size(); i++) {
								if (menus.get(i).getLabel().equals(stuff)) {
									menus.get(i).add(menuitems.get(added));
									menuitemsinmenus.set(i, menuitemsinmenus.get(i) + 1);
								}
							}
							try {
								file.add(new files(Files.getrelativepath(chooser.getSelectedFile().getAbsolutePath()), name, ""));
							} catch (Exception e) {
								System.err.println(e);
							}
							lists.clear();
							first = true;
						}
					}
				} else if (button.equals("Remove ")) {
					int r = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Warning", JOptionPane.YES_NO_OPTION);
					if (r == JOptionPane.YES_OPTION) {
						String stuff = getmenulabels()[lists.get(0).getSelectedIndex()];
						for (int i = 0; i < menus.size(); i++)
							if (menus.get(i).getLabel().equals(stuff))
								menuitemsinmenus.set(i, menuitemsinmenus.get(i) - 1);
						String s = getmenuitems(getmenulabels()[lists.get(0).getSelectedIndex()])[lists.get(1).getSelectedIndex()];
						int menuindex = -1;
						for (int i = 0; i < menus.size(); i++)
							for (int j = 0; j < menus.get(i).getItemCount(); j++)
								if (menus.get(i).getItem(j).getLabel().equals(s)) menuindex = i;
						for (int i = 0; i < menuitems.size(); i++) {
							if (menuitems.get(i).getLabel().equals(s)) {
								menus.get(menuindex).remove(menuitems.get(i));
								menuitems.remove(i);
							}
						}
						for (int i = 0; i < file.size(); i++)
							if (file.get(i).getcommand().equals(s)) file.remove(i);
						for (int i = 0; i < url.size(); i++) if (url.get(i).getcommand().equals(s)) url.remove(i);
					}
				}
				updategui();
				popup = new PopupMenu();
				for (Menu m : menus) popup.add(m);
				popup.addSeparator();
				int exit = 0, options = 0;
				for (int i = 0; i < menuitems.size(); i++) {
					if (menuitems.get(i).getLabel().equals("Exit")) exit = i;
					if (menuitems.get(i).getLabel().equals("Options")) options = i;
				}
				menuitems.remove(options);
				menuitems.remove(exit - 1);
				menuitems.add(new MenuItem("Options"));
				menuitems.add(new MenuItem("Exit"));
				popup.add(menuitems.get(menuitems.size() - 2));
				popup.add(menuitems.get(menuitems.size() - 1));
				trayIcon.setPopupMenu(popup);
				for (MenuItem m : menuitems) m.addActionListener(listener);
			}
		};
	}
}