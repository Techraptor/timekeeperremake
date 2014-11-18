package com.falconraptor.timekeeper.init;

import com.falconraptor.timekeeper.Timekeeper;
import com.falconraptor.timekeeper.guis.Loading;
import com.falconraptor.timekeeper.references.References;
import com.falconraptor.timekeeper.settings.Colors;
import com.falconraptor.timekeeper.settings.Settings;
import com.falconraptor.utilities.files.XML;
import com.falconraptor.utilities.logger.Logger;

public class Init {
    public Init() {
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

    public void start() {
        References.loading.setVisible(true);
        Logger.logINFO("Loading config files");
        References.config.loadConfig();
        References.config.load();
        Logger.logINFO("Starting GUIs");
        References.gui.start();
    }
}
