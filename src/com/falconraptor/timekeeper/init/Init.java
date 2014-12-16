package com.falconraptor.timekeeper.init;

import com.falconraptor.timekeeper.Timekeeper;
import com.falconraptor.timekeeper.guis.*;
import com.falconraptor.timekeeper.references.*;
import com.falconraptor.timekeeper.settings.*;
import com.falconraptor.utilities.files.*;
import com.falconraptor.utilities.logger.*;

public class Init {
	public Init () {
		Logger.logINFO("Loading classes");
		References.colors = new Colors();
		References.xml = new XML();
		References.settings = new Settings();
		References.config = new Config();
		References.timekeeper = new Timekeeper();
		References.gui = new GUI();
		References.timekeepergui = new com.falconraptor.timekeeper.guis.Timekeeper();
		References.loading = new Loading();
	}

	public void start () {
		References.loading.setVisible(true);
		Logger.logINFO("Loading config files");
		References.config.loadConfig();
		References.config.load();
		Logger.logINFO("Starting GUIs");
		References.gui.start();
	}
}
