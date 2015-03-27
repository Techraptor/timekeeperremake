package com.falconraptor.timekeeper.settings;

import com.falconraptor.timekeeper.other.*;
import com.falconraptor.timekeeper.schedule.*;
import com.falconraptor.timekeeper.school.*;
import com.falconraptor.timekeeper.school.schools.*;

import java.awt.*;
import java.util.*;

public class Settings {
	private static final String[] schoolsList = {"ATECH"};
	public static School[] schools = new School[schoolsList.length];
	public final Color defaultForeground = References.colors.black;
	public final Color defaultBackground = References.colors.white;
	public final int defaultLunch = 1;
	public final String defaultSchool = "ATECH";
	public final Atech atech = new Atech();
	public final ArrayList<Holidays> usHolidays = new ArrayList<>(0);
	public Color foreground = defaultForeground;
	public Color background = defaultBackground;
	public int lunch = defaultLunch;
	public String school = defaultSchool;
}
