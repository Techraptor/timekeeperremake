package com.falconraptor.timekeeper.settings;

import com.falconraptor.timekeeper.references.*;
import com.falconraptor.timekeeper.schedule.*;
import com.falconraptor.timekeeper.school.*;
import com.falconraptor.timekeeper.school.schools.*;

import java.awt.*;
import java.util.*;

public class Settings {
	public static String[] schoolsList = {"ATECH"};
	public static School[] schools = new School[schoolsList.length];
	public final Color defaultForeground = References.colors.black;
	public final Color defaultBackground = References.colors.white;
	public final int defaultLunch = 1;
	public final String defaultSchool = "ATECH";
	public Color foreground = defaultForeground;
	public Color background = defaultBackground;
	public int lunch = defaultLunch;
	public String school = defaultSchool;
	public Atech atech = new Atech();
	public ArrayList<Holidays> usHolidays = new ArrayList<>(0);
}
