package com.falconraptor.timekeeper;

import com.falconraptor.timekeeper.init.*;
import com.falconraptor.timekeeper.references.*;
import com.falconraptor.utilities.files.*;
import com.falconraptor.utilities.logger.*;

import static com.falconraptor.timekeeper.references.References.*;

public class Timekeeper {
	public static final String log = References.log + "Timekeeper.";

	public static void main (String[] args) {
		if (args.length >= 1) {
			try {
				Logger.level = Integer.parseInt(args[0]);
			} catch (Exception e) {
				Logger.logERROR(log + "main] " + e);
			}
		}
		Logger.logINFO("Log level set at: " + Logger.level);
		if (args.length >= 2) {
			if (args[1].toLowerCase().equals("console")) console.setVisible(true);
			Logger.logINFO("Console started");
		}
		shutdown.packagename = Timekeeper.class.getSimpleName();
		init = new Init();
		shutdown.attachShutDownHook(new Thread() {
			@Override
			public void run () {
				References.timekeeper.shutdown();
			}
		});
		init.start();
	}

	public void shutdown () {
		Logger.logINFO("Shutting Down");
		config.saveConfig();
		Logger.logINFO("Config Saved");
		Write.makeDir("logs");
		Logger.saveLog("logs/" + shutdown.packagename + "Log.log");
	}
}
