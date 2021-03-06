package com.falconraptor.timekeeper.references;

import com.darkleach7.extra.Extras;
import com.falconraptor.timekeeper.Timekeeper;
import com.falconraptor.timekeeper.guis.Loading;
import com.falconraptor.timekeeper.init.Config;
import com.falconraptor.timekeeper.init.GUI;
import com.falconraptor.timekeeper.init.Init;
import com.falconraptor.timekeeper.settings.Colors;
import com.falconraptor.timekeeper.settings.Settings;
import com.falconraptor.utilities.Shutdown;
import com.falconraptor.utilities.files.XML;
import com.falconraptor.utilities.logger.Console;
import com.falconraptor.utilities.logger.Logger;

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
}
