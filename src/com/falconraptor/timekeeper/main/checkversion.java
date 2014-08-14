package com.falconraptor.timekeeper.main;

import com.falconraptor.utilities.files.Files;
import com.falconraptor.utilities.logger.Logger;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class checkversion {
    public static String log = "[com.falconraptor.timekeeper.main.checkversion.";
    double version = 3.63;
    double read = 0.0;
    boolean updated = false;

    public void checkversiontime() {
        String url = "https://dl.dropboxusercontent.com/u/109423311/timekeeper/timekeeperversion.txt";
        String filename = "resources\\timekeeperversion.txt";
        Files.downloadfile(url, filename);
        try {
            File file = new File("resources\\timekeeperversion.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String out = reader.readLine();
            reader.close();
            read = Double.parseDouble(out);
            file.delete();
            if (read > version) {
                int r = JOptionPane.showConfirmDialog(null, "Do you want to update to version " + read + "?", "UPDATE!", JOptionPane.YES_NO_OPTION);
                if (r == JOptionPane.NO_OPTION) return;
                updated = true;
                url = "https://dl.dropboxusercontent.com/u/109423311/timekeeper/timekeeperchangelog.txt";
                filename = "resources\\changelog.txt";
                Files.downloadfile(url, filename);
                url = "https://dl.dropboxusercontent.com/u/109423311/timekeeper/timekeepercredits.txt";
                filename = "resources\\credits.txt";
                Files.downloadfile(url, filename);
                url = "https://dl.dropboxusercontent.com/u/109423311/timekeeper/timekeeper.jar";
                filename = "timekeeper" + read + ".jar";
                Files.downloadfile(url, filename);
                Runtime.getRuntime().exec("java -jar timekeeper" + read + ".jar");
                System.exit(0);
            }
        } catch (Exception e) {
            if (Logger.level <= 5) Logger.logERROR(log + "checkversiontime] " + e);
        }
    }
}