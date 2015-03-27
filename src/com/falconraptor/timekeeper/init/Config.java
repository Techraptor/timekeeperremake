package com.falconraptor.timekeeper.init;

import com.falconraptor.timekeeper.other.*;
import com.falconraptor.timekeeper.schedule.*;
import com.falconraptor.timekeeper.settings.*;
import com.falconraptor.utilities.Colors;
import com.falconraptor.utilities.files.*;
import com.falconraptor.utilities.logger.*;
import org.w3c.dom.*;

import java.awt.*;
import java.io.*;
import java.util.*;

import static com.falconraptor.timekeeper.other.References.*;

public class Config {
	private final String log = References.log + "init.Config.";

	public void loadConfig () {
		XML xml = References.xml;
		Settings settings = References.settings;
		Logger.logINFO("Loading Config");
		Document doc;
		try {
			doc = xml.readXMLDoc("Timekeeper.xml");
			if (doc == null) throw new Exception();
		} catch (Exception e) {
			Logger.logERROR(log + "loadConfig] " + e);
			saveConfig();
			return;
		}
		loading.addProgress();
		Node docnode;
		try {
			docnode = doc.getDocumentElement();
		} catch (Exception e) {
			Logger.logERROR(log + "loadConfig] " + e);
			saveConfig();
			return;
		}
		Node colors = docnode.getFirstChild().getNextSibling();
		Node foreground = colors.getFirstChild().getNextSibling();
		Node background = foreground.getNextSibling().getNextSibling();
		Node lunch = colors.getNextSibling().getNextSibling();
		Node school = lunch.getNextSibling().getNextSibling();
		loading.addProgress();
		Node defaultatt = foreground.getAttributes().getNamedItem("Default");
		if (defaultatt.getNodeValue().equals("true")) settings.foreground = settings.defaultForeground;
		else {
			int[] color = Colors.checkerrors(foreground.getTextContent());
			settings.foreground = new Color(color[0], color[1], color[2]);
		}
		loading.addProgress();
		defaultatt = background.getAttributes().getNamedItem("Default");
		if (defaultatt.getNodeValue().equals("true")) settings.background = settings.defaultBackground;
		else {
			int[] color = Colors.checkerrors(foreground.getTextContent());
			settings.background = new Color(color[0], color[1], color[2]);
		}
		loading.addProgress();
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
		loading.addProgress();
		defaultatt = school.getAttributes().getNamedItem("Default");
		if (defaultatt.getNodeValue().equals("true")) {
			settings.school = settings.defaultSchool;
			References.settings.atech.loadAtech();
		} else settings.school = school.getTextContent();
		loading.addProgress();
		References.settings = settings;
	}

	public void saveConfig () {
		XML xml = References.xml;
		Settings settings = References.settings;
		Logger.logINFO("Saving Config");
		File file = new File("Timekeeper.xml");
		if (file.exists()) file.delete();
		xml.elements.clear();
		xml.setNewFile();
		xml.addElement("Timekeeper");
		xml.appendToDoc(0);
		xml.addElement("Colors");
		xml.appendElement(0, 1);
		xml.addElement("Foreground");
		xml.appendElement(1, 2);
		xml.addTextToElement(2, settings.foreground.toString());
		String defualt = (settings.foreground == settings.defaultForeground) + "";
		xml.setAttribute(2, "Default", defualt);
		xml.addElement("Background");
		xml.appendElement(1, 3);
		xml.addTextToElement(3, settings.background.toString());
		defualt = (settings.background == settings.defaultBackground) + "";
		xml.setAttribute(3, "Default", defualt);
		xml.addElement("Lunch");
		xml.appendElement(0, 4);
		xml.addTextToElement(4, settings.lunch + "");
		defualt = (settings.lunch == settings.defaultLunch) + "";
		xml.setAttribute(4, "Default", defualt);
		xml.addElement("School");
		xml.appendElement(0, 5);
		xml.addTextToElement(5, settings.school);
		defualt = Objects.equals(settings.school, settings.defaultSchool) + "";
		xml.setAttribute(5, "Default", defualt);
		xml.addElement("Schools");
		xml.appendElement(0, 6);
		xml.saveFile("Timekeeper.xml");
		References.settings = settings;
	}

	private void setDefaults () {

	}

	public void loadUSHolidays () {
		XML xml = References.xml;
		String filename = "USHolidays.xml";
		Document doc = xml.readXMLDocFromJar("resources/" + filename);
		References.loading.addProgress();
		Node docnode = doc.getDocumentElement();
		Logger.logINFO("Loading US Holidays");
		Node loop = docnode.getFirstChild().getNextSibling();
		while (loop != null) {
			String name = loop.getFirstChild().getNextSibling().getTextContent();
			int day = 0;
			try {
				day = Integer.parseInt(loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			} catch (Exception e) {
				Logger.logERROR(log + "loadUSHolidays] " + e);
			}
			int month = Integer.parseInt(loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			loop = loop.getNextSibling().getNextSibling();
			References.settings.usHolidays.add(new Holidays(name, day, month));
			Logger.logALL("Holiday: " + References.settings.usHolidays.get(References.settings.usHolidays.size() - 1) + " Loaded");
			References.loading.addProgress();
		}
	}
}
