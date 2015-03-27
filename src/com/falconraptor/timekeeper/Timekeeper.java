package com.falconraptor.timekeeper;

import com.falconraptor.timekeeper.init.*;
import com.falconraptor.timekeeper.other.*;
import com.falconraptor.utilities.files.*;
import com.falconraptor.utilities.logger.*;

import static com.falconraptor.timekeeper.other.References.*;

public class Timekeeper {
	public static final String log = References.log + "Timekeeper.";

	public static void main (String[] args) {
		for (String s : args) {
			try {
				Logger.level = Integer.parseInt(args[0]);
			} catch (Exception e) {
				if (s.equals("console")) console.setVisible(true);
			}
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

	void shutdown () {
		Logger.logINFO("Shutting Down");
		config.saveConfig();
		Logger.logINFO("Config Saved");
		Write.makeDir("logs");
		Logger.saveLog("logs/" + shutdown.packagename + "Log.log");
	}
}
