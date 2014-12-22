package com.falconraptor.timekeeper.init;

import com.falconraptor.timekeeper.Timekeeper;
import com.falconraptor.timekeeper.guis.*;
import com.falconraptor.timekeeper.settings.*;
import com.falconraptor.utilities.files.*;
import com.falconraptor.utilities.logger.*;

import static com.falconraptor.timekeeper.references.References.*;

public class Init {
	public Init () {
		Logger.logINFO("Loading classes");
		colors = new Colors();
		xml = new XML();
		settings = new Settings();
		config = new Config();
		timekeeper = new Timekeeper();
		gui = new GUI();
		timekeepergui = new com.falconraptor.timekeeper.guis.Timekeeper();
		loading = new Loading();
	}

	public void start () {
		loading.setVisible(true);
		Logger.logINFO("Loading config files");
		config.loadConfig();
		config.loadUSHolidays();
		Logger.logINFO("Starting GUIs");
		gui.start();
	}
}
