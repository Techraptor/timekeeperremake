package com.falconraptor.timekeeper.init;

import com.falconraptor.timekeeper.Timekeeper;
import com.falconraptor.timekeeper.references.References;
import com.falconraptor.timekeeper.settings.Colors;
import com.falconraptor.timekeeper.settings.Settings;
import com.falconraptor.utilities.files.XML;

public class Init {
    public Init() {
        References.colors = new Colors();
        References.xml = new XML();
        References.settings = new Settings();
        References.config = new Config();
        References.timekeeper = new Timekeeper();
    }

    public void start() {
        References.config.loadConfig();
        Gui g = new Gui();
    }
}
