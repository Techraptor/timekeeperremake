package com.falconraptor.timekeeper.init;

import com.falconraptor.timekeeper.references.References;
import com.falconraptor.utilities.logger.Logger;

public class Config {
    public void loadConfig() {
        Logger.logINFO("Loading Config");
    }

    public void saveConfig() {
        Logger.logINFO("Saving Config");
        References.xml.setNewFile();
        References.xml.addElement("Timekeeper");
        References.xml.appendToDoc(0);
        References.xml.addElement("Colors");
        References.xml.appendElement(0, 1);
        References.xml.addElement("Foreground");
        References.xml.appendElement(1, 2);
        References.xml.addTextToElement(2, References.settings.foreground.toString());
        References.xml.addElement("Background");
        References.xml.appendElement(1, 3);
        References.xml.addTextToElement(3, References.settings.background.toString());
        References.xml.saveFile("Timekeeper.xml");
    }

    private void setDefualts() {

    }
}
