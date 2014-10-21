package com.falconraptor.timekeeper.init;

import com.falconraptor.timekeeper.references.References;
import com.falconraptor.timekeeper.school.schools.Atech;
import com.falconraptor.timekeeper.settings.Settings;
import com.falconraptor.utilities.Colors;
import com.falconraptor.utilities.files.XML;
import com.falconraptor.utilities.logger.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.awt.*;
import java.io.File;

public class Config {
    private final String log = References.log + "init.Config.";
    public void loadConfig() {
        XML xml = References.xml;
        Settings settings = References.settings;
        Logger.logINFO("Loading Config");
        Document doc = xml.readXMLDoc("Timekeeper.xml");
        References.loading.addProgress();
        Node docnode = doc.getDocumentElement();
        Node colors = docnode.getFirstChild().getNextSibling();
        Node foreground = colors.getFirstChild().getNextSibling();
        Node background = foreground.getNextSibling().getNextSibling();
        Node lunch = colors.getNextSibling().getNextSibling();
        Node school = lunch.getNextSibling().getNextSibling();
        References.loading.addProgress();
        Node defaultatt = foreground.getAttributes().getNamedItem("Default");
        if (defaultatt.getNodeValue().equals("true")) settings.foreground = settings.defaultForeground;
        else {
            int[] color = Colors.checkerrors(foreground.getTextContent());
            settings.foreground = new Color(color[0], color[1], color[2]);
        }
        References.loading.addProgress();
        defaultatt = background.getAttributes().getNamedItem("Default");
        if (defaultatt.getNodeValue().equals("true")) settings.background = settings.defaultBackground;
        else {
            int[] color = Colors.checkerrors(foreground.getTextContent());
            settings.background = new Color(color[0], color[1], color[2]);
        }
        References.loading.addProgress();
        defaultatt = lunch.getAttributes().getNamedItem("Default");
        if (defaultatt.getNodeValue().equals("true")) settings.lunch = settings.defaultLunch;
        else {
            try {
                settings.lunch = Integer.parseInt(lunch.getTextContent());
            } catch (Exception e) {
                settings.lunch = settings.defaultLunch;
                Logger.logERROR(log + "loadConfig] " + e);
            }
        }
        References.loading.addProgress();
        defaultatt = school.getAttributes().getNamedItem("Default");
        if (defaultatt.getNodeValue().equals("true")) {
            settings.school = settings.defaultSchool;
            References.settings.atech = new Atech();
            References.settings.atech.loadAtech();
        } else settings.school = school.getTextContent();
        References.loading.addProgress();
        References.settings = settings;
        References.xml = xml;
    }

    public void saveConfig() {
        XML xml = References.xml;
        Settings settings = References.settings;
        Logger.logINFO("Saving Config");
        File file = new File("Timekeeper.xml");
        if (file.exists()) file.delete();
        xml.setNewFile();
        xml.addElement("Timekeeper");
        xml.appendToDoc(0);
        xml.addElement("Colors");
        xml.appendElement(0, 1);
        xml.addElement("Foreground");
        xml.appendElement(1, 2);
        xml.addTextToElement(2, settings.foreground.toString());
        String defualt;
        if (settings.foreground == settings.defaultForeground) defualt = "true";
        else defualt = "false";
        xml.setAttribute(2, "Default", defualt);
        xml.addElement("Background");
        xml.appendElement(1, 3);
        xml.addTextToElement(3, settings.background.toString());
        if (settings.background == settings.defaultBackground) defualt = "true";
        else defualt = "false";
        xml.setAttribute(3, "Default", defualt);
        xml.addElement("Lunch");
        xml.appendElement(0, 4);
        xml.addTextToElement(4, settings.lunch + "");
        if (settings.lunch == settings.defaultLunch) defualt = "true";
        else defualt = "false";
        xml.setAttribute(4, "Default", defualt);
        xml.addElement("School");
        xml.appendElement(0, 5);
        xml.addTextToElement(5, settings.school);
        if (settings.school == settings.defaultSchool) defualt = "true";
        else defualt = "false";
        xml.setAttribute(5, "Default", defualt);
        xml.addElement("Schools");
        xml.appendElement(0, 6);
        xml.saveFile("Timekeeper.xml");
        References.settings = settings;
        References.xml = xml;
    }

    private void setDefaults() {

    }

    public void load() {

    }
}
