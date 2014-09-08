package com.falconraptor.timekeeper;

import com.falconraptor.timekeeper.init.Init;
import com.falconraptor.timekeeper.references.References;
import com.falconraptor.utilities.files.Write;
import com.falconraptor.utilities.logger.Logger;

public class Timekeeper {
    public static final String log = References.log + "Timekeeper.";

    public static void main(String[] args) {
        if (args.length >= 1) {
            try {
                Logger.level = Integer.parseInt(args[0]);
            } catch (Exception e) {
                Logger.logERROR(log + "main] " + e);
            }
        }
        Logger.logINFO("Log level set at: " + Logger.level);
        if (args.length >= 2) {
            if (args[1].toLowerCase().equals("console")) References.console.setVisible(true);
            Logger.logINFO("Console started");
        }
        References.shutdown.packagename = Timekeeper.class.getSimpleName();
        References.init = new Init();
        References.shutdown.attachShutDownHook(new Thread(() -> {
            References.timekeeper.shutdown();
        }));
        References.init.start();
    }

    public void shutdown() {
        Logger.logINFO("Shutting Down");
        References.config.saveConfig();
        Logger.logINFO("Config Saved");
        Write.makeDir("logs");
        Logger.saveLog("logs/" + References.shutdown.packagename + "Log.log");
    }
}
