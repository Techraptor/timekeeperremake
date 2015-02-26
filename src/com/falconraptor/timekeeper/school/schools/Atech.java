package com.falconraptor.timekeeper.school.schools;

import com.falconraptor.timekeeper.references.*;
import com.falconraptor.timekeeper.schedule.*;
import com.falconraptor.timekeeper.school.*;
import com.falconraptor.utilities.files.*;
import com.falconraptor.utilities.logger.*;
import org.w3c.dom.*;

import java.time.*;
import java.util.*;

import static com.falconraptor.timekeeper.references.References.*;

public class Atech extends School {
	public Schedule normal = new Schedule(15), wednesday = new Schedule(10), thursday = new Schedule(10), assembly = new Schedule(15);
	public Lunch normalfirst, normalsecond, wednesdayfirst, wednesdaysecond, thursdayfirst, thursdaysecond, assemblyfirst, assemblysecond;
	public ArrayList<Holidays> holidays = new ArrayList<>(0);
	public ArrayList<Teacher> teachers = new ArrayList<>(0);
	private String schoolPhone, ccsdEmail;

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
		Document doc = xml.readXMLDocFromJar("resources/schools/ATECH2015.xml");
		loading.addProgress();
		Node docnode = doc.getDocumentElement();
		loadAtechSchedule(docnode.getFirstChild().getNextSibling().getNextSibling().getNextSibling());
		loading.addProgress();
		loadAtechHolidays(docnode.getFirstChild().getNextSibling());
		loading.addProgress();
		loadAtechTeachers(docnode.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling());
		loading.addProgress();
	}

	private void loadAtechSchedule (Node s) {
		Logger.logINFO("Loading ATECH Schedule");
		loadAtechScheduleNormal(s.getFirstChild().getNextSibling());
		loading.addProgress();
		loadAtechScheduleWednesday(s.getFirstChild().getNextSibling().getNextSibling().getNextSibling());
		loading.addProgress();
		loadAtechScheduleThursday(s.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling());
		loading.addProgress();
		loadAtechScheduleAssembly(s.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling());
		loading.addProgress();
	}

	private void loadAtechScheduleNormal (Node n) {
		Logger.logINFO("Loading ATECH Normal Schedule");
		loadschedule("normal", n);
		loading.addProgress();
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
					LocalTime s = LocalTime.of(sti(stuff)[0], sti(stuff)[1]), e;
					stuff = loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent().split(":");
					e = LocalTime.of(sti(stuff)[0], sti(stuff)[1]);
					if (day.equals("normal")) normal.setClass(index, s, e);
					else if (day.equals("wednesday")) wednesday.setClass(index, s, e);
					else if (day.equals("thursday")) thursday.setClass(index, s, e);
					else if (day.equals("assembly")) assembly.setClass(index, s, e);
				} else addclass(day, loop, index);
				logSchedule(day, index);
				loading.addProgress();
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
				loading.addProgress();
			}
			index++;
			loop = loop.getNextSibling().getNextSibling();
			loading.addProgress();
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
		LocalTime s = LocalTime.of(sti(stuff)[0], sti(stuff)[1]), e;
		stuff = n.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent().split(":");
		e = LocalTime.of(sti(stuff)[0], sti(stuff)[1]);
		loading.addProgress();
		return new Lunch(Integer.parseInt(n.getAttributes().getNamedItem("lunch").getNodeValue()), s, e);
	}

	private void addclass (String day, Node c, int index) {
		Node child = c.getFirstChild().getNextSibling();
		String[] stuff = child.getTextContent().split(":");
		LocalTime s = LocalTime.of(sti(stuff)[0], sti(stuff)[1]), e;
		loading.addProgress();
		stuff = child.getNextSibling().getNextSibling().getTextContent().split(":");
		e = LocalTime.of(sti(stuff)[0], sti(stuff)[1]);
		loading.addProgress();
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
		loading.addProgress();
	}

	private void loadAtechScheduleWednesday (Node w) {
		Logger.logINFO("Loading ATECH Wednesday Schedule");
		loadschedule("wednesday", w);
		wednesday.calcLengths();
		wednesday.setLunch("wednesday", 2);
		loading.addProgress();
		Logger.logINFO("Done loading ATECH Wednesday Schedule");
	}

	private void loadAtechScheduleThursday (Node t) {
		Logger.logINFO("Loading ATECH Thursday Schedule");
		loadschedule("thursday", t);
		thursday.calcLengths();
		thursday.setLunch("thursday", 1);
		loading.addProgress();
		Logger.logINFO("Done loading ATECH Thursday Schedule");
	}

	private void loadAtechScheduleAssembly (Node a) {
		Logger.logINFO("Loading ATECH Assembly Schedule");
		loadschedule("assembly", a);
		assembly.calcLengths();
		assembly.setLunch("assembly", 1);
		loading.addProgress();
		Logger.logINFO("Done loading ATECH Assembly Schedule");
	}

	private void loadAtechHolidays (Node h) {
		Logger.logINFO("Loading ATECH Holidays");
		Node loop = h.getFirstChild().getNextSibling();
		while (loop != null) {
			String name = loop.getFirstChild().getNextSibling().getTextContent();
			int month = Integer.parseInt(loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			int day = Integer.parseInt(loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
			loop = loop.getNextSibling().getNextSibling();
			holidays.add(new Holidays(name, day, month));
			Logger.logALL("Holiday: " + holidays.get(holidays.size() - 1) + " Loaded");
			loading.addProgress();
		}
	}

	private void loadAtechTeachers (Node t) {
		Logger.logINFO("Loading ATECH Teachers");
		Node loop = t.getFirstChild().getNextSibling();
		schoolPhone = loop.getTextContent();
		loop = loop.getNextSibling().getNextSibling();
		ccsdEmail = loop.getTextContent();
		loop = loop.getNextSibling().getNextSibling();
		loading.addProgress();
		loading.addProgress();
		while (loop != null) {
			Node name = loop.getFirstChild().getNextSibling();
			Node n = name.getFirstChild().getNextSibling();
			Teacher temp = new Teacher(n.getNextSibling().getNextSibling().getTextContent(), n.getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent(), n.getTextContent());
			n = name.getNextSibling().getNextSibling();
			loading.addProgress();
			Node con = name.getNextSibling().getNextSibling().getFirstChild().getNextSibling();
			loading.addProgress();
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
			loading.addProgress();
			int[] lunches = new int[3];
			for (int i = 0; i < 3; i++) {
				lunches[i] = Integer.parseInt(lunch.getTextContent());
				lunch = lunch.getNextSibling().getNextSibling();
			}
			temp.setLunches(lunches);
			Node classes = n.getNextSibling().getNextSibling().getNextSibling().getNextSibling().getFirstChild().getNextSibling();
			loading.addProgress();
			while (classes != null) {
				temp.add(classes.getTextContent());
				classes = classes.getNextSibling().getNextSibling();
			}
			temp.setSchedules();
			teachers.add(temp);
			loading.addProgress();
			Logger.logALL("Teacher: " + teachers.get(teachers.size() - 1) + " Loaded");
			loop = loop.getNextSibling().getNextSibling();
		}
	}

	@Override
	public String toString () {
		return "Atech{" +
			  "assembly=" + assembly +
			  ", assemblyfirst=" + assemblyfirst +
			  ", assemblysecond=" + assemblysecond +
			  ", ccsdEmail='" + ccsdEmail + '\'' +
			  ", holidays=" + holidays +
			  ", normal=" + normal +
			  ", normalfirst=" + normalfirst +
			  ", normalsecond=" + normalsecond +
			  ", schoolPhone='" + schoolPhone + '\'' +
			  ", teachers=" + teachers +
			  ", thursday=" + thursday +
			  ", thursdayfirst=" + thursdayfirst +
			  ", thursdaysecond=" + thursdaysecond +
			  ", wednesday=" + wednesday +
			  ", wednesdayfirst=" + wednesdayfirst +
			  ", wednesdaysecond=" + wednesdaysecond +
			  '}';
	}
}
