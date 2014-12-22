package com.falconraptor.timekeeper.references;

import com.darkleach7.extra.*;
import com.falconraptor.timekeeper.Timekeeper;
import com.falconraptor.timekeeper.guis.*;
import com.falconraptor.timekeeper.init.*;
import com.falconraptor.timekeeper.licensing.*;
import com.falconraptor.timekeeper.settings.Colors;
import com.falconraptor.timekeeper.settings.*;
import com.falconraptor.utilities.Shutdown;
import com.falconraptor.utilities.files.*;
import com.falconraptor.utilities.logger.*;

import java.awt.event.*;
import java.util.*;

public class References {
	public static final String log = "[com.falconraptor.timekeeper.";
	public static Console console = Logger.console;
	public static Init init;
	public static Config config;
	public static Settings settings;
	public static Colors colors;
	public static XML xml;
	public static Shutdown shutdown = new Shutdown();
	public static Timekeeper timekeeper;
	public static GUI gui;
	public static com.falconraptor.timekeeper.guis.Timekeeper timekeepergui;
	public static Loading loading;
	public static Extras extras;
	public static ConnectionToDatabase connectionToDatabase;
	public static Create create;
	public static Login login;
	public static ArrayList<Thread> threads = new ArrayList<>(0);
	public static Users users=new Users();

	public static WindowListener shutdownProgram () {
		return new WindowListener() {
			@Override
			public void windowOpened (WindowEvent e) {

			}

			@Override
			public void windowClosing (WindowEvent e) {

			}

			@Override
			public void windowClosed (WindowEvent e) {
				checkForShutdown();
			}

			@Override
			public void windowIconified (WindowEvent e) {

			}

			@Override
			public void windowDeiconified (WindowEvent e) {

			}

			@Override
			public void windowActivated (WindowEvent e) {

			}

			@Override
			public void windowDeactivated (WindowEvent e) {

			}
		};
	}

	public static void checkForShutdown () {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			Logger.logERROR(log + e);
		}
		ArrayList<Boolean> disposed = new ArrayList<>(0);
		if (console != null) disposed.add(!console.isVisible());
		else disposed.add(true);
		if (timekeepergui != null) disposed.add(!timekeepergui.isVisible());
		else disposed.add(true);
		if (loading != null) disposed.add(!loading.isVisible());
		else disposed.add(true);
		if (extras != null) {
			disposed.add(!extras.isVisible());
			if (extras.cal != null) disposed.add(!extras.cal.isVisible());
			else disposed.add(true);
		} else disposed.add(true);
		for (Boolean b : disposed) if (!b) return;
		threads.forEach(java.lang.Thread::interrupt);
		System.exit(0);
	}
}
