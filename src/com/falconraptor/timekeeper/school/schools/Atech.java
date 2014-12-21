package com.falconraptor.timekeeper.school.schools;

import com.falconraptor.timekeeper.references.*;
import com.falconraptor.timekeeper.schedule.*;
import com.falconraptor.timekeeper.school.*;
import com.falconraptor.utilities.files.*;
import com.falconraptor.utilities.logger.*;
import org.w3c.dom.*;

import java.util.*;

public class Atech extends School {
	public Schedule normal = new Schedule(15);
	public Schedule wednesday = new Schedule(10);
	public Schedule thursday = new Schedule(10);
	public Schedule assembly = new Schedule(15);
	public Lunch normalfirst;
	public Lunch normalsecond;
	public Lunch wednesdayfirst;
	public Lunch wednesdaysecond;
	public Lunch thursdayfirst;
	public Lunch thursdaysecond;
	public Lunch assemblyfirst;
	public Lunch assemblysecond;
	public ArrayList<Holidays> holidays = new ArrayList<>(0);
	public ArrayList<Teacher> teachers = new ArrayList<>(0);
	private String schoolPhone;
	private String ccsdEmail;

	public Atech () {
		super("Atech", 8);
	}

	public String getCcsdEmail () {
		return ccsdEmail;
	}

	public String getSchoolPhone () {
		return schoolPhone;
	}

	public void loadAtech () {
		XML xml = References.xml;
		Logger.logINFO("Loading ATECH");
		String filename = "ATECH2015.xml";
		Document doc = xml.readXMLDocFromJar("resources/schools/" + filename);
		References.loading.addProgress();
		Node docnode = doc.getDocumentElement();
		loadAtechSchedule(docnode.getFirstChild().getNextSibling().getNextSibling().getNextSibling());
		References.loading.addProgress();
		loadAtechHolidays(docnode.getFirstChild().getNextSibling());
		References.loading.addProgress();
		loadAtechTeachers(docnode.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling());
		References.loading.addProgress();
	}

	private void loadAtechSchedule (Node s) {
		Logger.logINFO("Loading ATECH Schedule");
		loadAtechScheduleNormal(s.getFirstChild().getNextSibling());
		References.loading.addProgress();
		loadAtechScheduleWednesday(s.getFirstChild().getNextSibling().getNextSibling().getNextSibling());
		References.loading.addProgress();
		loadAtechScheduleThursday(s.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling());
		References.loading.addProgress();
		loadAtechScheduleAssembly(s.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling());
		References.loading.addProgress();
	}

	private void loadAtechScheduleNormal (Node n) {
		Logger.logINFO("Loading ATECH Normal Schedule");
		loadschedule("normal", n);
		References.loading.addProgress();
		normal.calcLengths();
		normal.setLunch("normal", 1);
		Logger.logINFO("Done loading ATECH Normal Schedule");
	}

	private void loadschedule (String day, Node n) {
		Node loop = n.getFirstChild().getNextSibling();
		int index = 0;
		while (loop != null) {
			if (loop.getAttributes().getNamedItem("period") != null) {
				if (loop.getAttributes().getNamedItem("period").getNodeValue().equals("6")) {
					addclass(day, loop, index);
					index++;
					if (day.equals("normal"))
						normal.addClass(index, loop.getAttributes().getNamedItem("period").getNodeValue());
					else if (day.equals("wednesday"))
						wednesday.addClass(index, loop.getAttributes().getNamedItem("period").getNodeValue());
					else if (day.equals("thursday"))
						thursday.addClass(index, loop.getAttributes().getNamedItem("period").getNodeValue());
					else if (day.equals("assembly"))
						assembly.addClass(index, loop.getAttributes().getNamedItem("period").getNodeValue());
					String[] stuff = loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent().split(":");
					Time s = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
					stuff = loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent().split(":");
					Time e = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
					if (day.equals("normal")) normal.setClass(index, s, e);
					else if (day.equals("wednesday")) wednesday.setClass(index, s, e);
					else if (day.equals("thursday")) thursday.setClass(index, s, e);
					else if (day.equals("assembly")) assembly.setClass(index, s, e);
				} else addclass(day, loop, index);
				logSchedule(day, index);
				References.loading.addProgress();
			} else if (loop.getAttributes().getNamedItem("lunch") != null) {
				int l = Integer.parseInt(loop.getAttributes().getNamedItem("lunch").getNodeValue());
				if (day.equals("normal") && l == 1) {
					normalfirst = getlunch(loop);
					normalfirst.calcLength();
				} else if (day.equals("wednesday") && l == 1) {
					wednesdayfirst = getlunch(loop);
					wednesdayfirst.calcLength();
				} else if (day.equals("thursday") && l == 1) {
					thursdayfirst = getlunch(loop);
					thursdayfirst.calcLength();
				} else if (day.equals("assembly") && l == 1) {
					assemblyfirst = getlunch(loop);
					assemblyfirst.calcLength();
				} else if (day.equals("normal") && l == 2) {
					normalsecond = getlunch(loop);
					normalsecond.calcLength();
				} else if (day.equals("wednesday") && l == 2) {
					wednesdaysecond = getlunch(loop);
					wednesdaysecond.calcLength();
				} else if (day.equals("thursday") && l == 2) {
					thursdaysecond = getlunch(loop);
					thursdaysecond.calcLength();
				} else if (day.equals("assembly") && l == 2) {
					assemblysecond = getlunch(loop);
					assemblysecond.calcLength();
				}
				References.loading.addProgress();
			}
			index++;
			loop = loop.getNextSibling().getNextSibling();
			References.loading.addProgress();
		}
	}

	private void logSchedule (String day, int index) {
		if (day.equals("normal")) Logger.logALL("Normal Class: " + normal.aClass[index] + " Loaded");
		else if (day.equals("wednesday")) Logger.logALL("Wednesday Class: " + wednesday.aClass[index] + " Loaded");
		else if (day.equals("thursday")) Logger.logALL("Thursday Class: " + thursday.aClass[index] + " Loaded");
		else if (day.equals("assembly")) Logger.logALL("Assembly Class: " + assembly.aClass[index] + " Loaded");
	}

	private Lunch getlunch (Node n) {
		String[] stuff = n.getFirstChild().getNextSibling().getTextContent().split(":");
		Time s = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
		stuff = n.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent().split(":");
		Time e = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
		References.loading.addProgress();
		return new Lunch(Integer.parseInt(n.getAttributes().getNamedItem("lunch").getNodeValue()), s, e);
	}

	private void addclass (String day, Node c, int index) {
		Node child = c.getFirstChild().getNextSibling();
		String[] stuff = child.getTextContent().split(":");
		Time s = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
		References.loading.addProgress();
		stuff = child.getNextSibling().getNextSibling().getTextContent().split(":");
		Time e = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
		References.loading.addProgress();
		if (day.equals("normal")) {
			normal.addClass(index, c.getAttributes().getNamedItem("period").getNodeValue());
			normal.setClass(index, s, e);
			normal.aClass[index].calcLength();
		} else if (day.equals("wednesday")) {
			wednesday.addClass(index, c.getAttributes().getNamedItem("period").getNodeValue());
			wednesday.setClass(index, s, e);
			wednesday.aClass[index].calcLength();
		} else if (day.equals("thursday")) {
			thursday.addClass(index, c.getAttributes().getNamedItem("period").getNodeValue());
			thursday.setClass(index, s, e);
			thursday.aClass[index].calcLength();
		} else if (day.equals("assembly")) {
			assembly.addClass(index, c.getAttributes().getNamedItem("period").getNodeValue());
			assembly.setClass(index, s, e);
			assembly.aClass[index].calcLength();
		}
		References.loading.addProgress();
	}

	private void loadAtechScheduleWednesday (Node w) {
		Logger.logINFO("Loading ATECH Wednesday Schedule");
		loadschedule("wednesday", w);
		wednesday.calcLengths();
		wednesday.setLunch("wednesday", 2);
		References.loading.addProgress();
		Logger.logINFO("Done loading ATECH Wednesday Schedule");
	}

	private void loadAtechScheduleThursday (Node t) {
		Logger.logINFO("Loading ATECH Thursday Schedule");
		loadschedule("thursday", t);
		thursday.calcLengths();
		thursday.setLunch("thursday", 1);
		References.loading.addProgress();
		Logger.logINFO("Done loading ATECH Thursday Schedule");
	}

	private void loadAtechScheduleAssembly (Node a) {
		Logger.logINFO("Loading ATECH Assembly Schedule");
		loadschedule("assembly", a);
		assembly.calcLengths();
		assembly.setLunch("assembly", 1);
		References.loading.addProgress();
		Logger.logINFO("Done loading ATECH Assembly Schedule");
	}

	private void loadAtechHolidays (Node h) {
		Logger.logINFO("Loading ATECH Holidays");
		Node loop = h.getFirstChild().getNextSibling();
		while (loop != null) {
			String name = loop.getFirstChild().getNextSibling().getTextContent();
			int day = Integer.parseInt(loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			int month = Integer.parseInt(loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			loop = loop.getNextSibling().getNextSibling();
			holidays.add(new Holidays(name, day, month));
			Logger.logALL("Holiday: " + holidays.get(holidays.size() - 1) + " Loaded");
			References.loading.addProgress();
		}
	}

	private void loadAtechTeachers (Node t) {
		Logger.logINFO("Loading ATECH Teachers");
		Node loop = t.getFirstChild().getNextSibling();
		schoolPhone = loop.getTextContent();
		loop = loop.getNextSibling().getNextSibling();
		ccsdEmail = loop.getTextContent();
		loop = loop.getNextSibling().getNextSibling();
		References.loading.addProgress();
		References.loading.addProgress();
		while (loop != null) {
			Node name = loop.getFirstChild().getNextSibling();
			Node n = name.getFirstChild().getNextSibling();
			Teacher temp = new Teacher(n.getNextSibling().getNextSibling().getTextContent(), n.getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent(), n.getTextContent());
			n = name.getNextSibling().getNextSibling();
			References.loading.addProgress();
			Node con = name.getNextSibling().getNextSibling().getFirstChild().getNextSibling();
			References.loading.addProgress();
			if (!con.getTextContent().equals("")) temp.setExtension(Integer.parseInt(con.getTextContent()));
			con = con.getNextSibling().getNextSibling();
			temp.setEmail(con.getTextContent());
			con = con.getNextSibling().getNextSibling();
			temp.setWebsite(con.getTextContent());
			n = name.getNextSibling().getNextSibling().getNextSibling().getNextSibling();
			temp.setRoom(n.getTextContent());
			n = n.getNextSibling().getNextSibling();
			temp.setDepartment(n.getTextContent());
			Node lunch = n.getNextSibling().getNextSibling().getFirstChild().getNextSibling();
			References.loading.addProgress();
			int[] lunches = new int[3];
			lunches[0] = Integer.parseInt(lunch.getTextContent());
			lunch = lunch.getNextSibling().getNextSibling();
			lunches[1] = Integer.parseInt(lunch.getTextContent());
			lunch = lunch.getNextSibling().getNextSibling();
			lunches[2] = Integer.parseInt(lunch.getTextContent());
			temp.setLunches(lunches);
			Node classes = n.getNextSibling().getNextSibling().getNextSibling().getNextSibling().getFirstChild().getNextSibling();
			References.loading.addProgress();
			while (classes != null) {
				temp.add(classes.getTextContent());
				classes = classes.getNextSibling().getNextSibling();
			}
			temp.setSchedules();
			teachers.add(temp);
			References.loading.addProgress();
			Logger.logALL("Teacher: " + teachers.get(teachers.size() - 1) + " Loaded");
			loop = loop.getNextSibling().getNextSibling();
		}
	}
}
