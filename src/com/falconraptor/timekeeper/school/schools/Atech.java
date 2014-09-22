package com.falconraptor.timekeeper.school.schools;

import com.falconraptor.timekeeper.references.References;
import com.falconraptor.timekeeper.schedule.Holidays;
import com.falconraptor.timekeeper.schedule.Lunch;
import com.falconraptor.timekeeper.schedule.Schedule;
import com.falconraptor.timekeeper.schedule.Time;
import com.falconraptor.timekeeper.school.School;
import com.falconraptor.utilities.files.XML;
import com.falconraptor.utilities.logger.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class Atech extends School {
    private final String filename = "ATECH2015.xml";
    public Schedule normal = new Schedule(11);
    public Schedule wednesday = new Schedule(8);
    public Schedule thursday = new Schedule(8);
    private Lunch normalfirst;
    private Lunch normalsecond;
    private Lunch wednesdayfirst;
    private Lunch wednesdaysecond;
    private Lunch thursdayfirst;
    private Lunch thursdaysecond;
    private ArrayList<Holidays> holidays = new ArrayList<>(0);

    public Atech() {
        super("Atech", 8);
    }

    public void loadAtech() {
        XML xml = References.xml;
        Logger.logINFO("Loading ATECH");
        Document doc = xml.readXMLDocFromJar("resources/schools/" + filename);
        Node docnode = doc.getDocumentElement();
        loadAtechSchedule(docnode.getFirstChild().getNextSibling().getNextSibling().getNextSibling());
        loadAtechHolidays(docnode.getFirstChild().getNextSibling());
        loadAtechTeachers(docnode.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling());
    }

    private void loadAtechSchedule(Node s) {
        Logger.logINFO("Loading ATECH Schedule");
        loadAtechScheduleNormal(s.getFirstChild().getNextSibling());
        loadAtechScheduleWednesday(s.getFirstChild().getNextSibling().getNextSibling().getNextSibling());
        loadAtechScheduleThursday(s.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling());
        loadAtechScheduleAssembly(s.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling());
    }

    private void loadAtechScheduleNormal(Node n) {
        Logger.logINFO("Loading ATECH Normal Schedule");
        
    }
    private void loadschedule(String day,Node n){
        Node loop = n.getFirstChild().getNextSibling();
        int index = 0;
        while (loop != null) {
            if (loop.getAttributes().getNamedItem("period") != null) {
                if (loop.getAttributes().getNamedItem("period").getNodeValue().equals("6")) {
                    addclass(day,loop);
                    index++;
                    normal.addClass(index, loop.getAttributes().getNamedItem("period").getNodeValue());
                    stuff = loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent().split(":");
                    s = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
                    stuff = loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent().split(":");
                    e = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
                    normal.setClass(index, s, e);
                } else {
                    addclass(day,loop);
                }
            } else if (loop.getAttributes().getNamedItem("lunch") != null) {
                if (loop.getAttributes().getNamedItem("lunch").getNodeValue().equals("1")) {
                    normalfirst = getlunch(loop);
                    normalfirst.calcLength();
                } else if (loop.getAttributes().getNamedItem("lunch").getNodeValue().equals("2")) {
                    String[] stuff = loop.getFirstChild().getNextSibling().getTextContent().split(":");
                    Time s = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
                    stuff = loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent().split(":");
                    Time e = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
                    normalsecond = new Lunch(1, s, e);
                    normalsecond.setLength(Integer.parseInt(loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent()));
                }
            }
            index++;
            loop = loop.getNextSibling().getNextSibling();
        }
    }
    private Lunch getlunch(Node n){
        String[] stuff = loop.getFirstChild().getNextSibling().getTextContent().split(":");
        Time s = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
        stuff = loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent().split(":");
        Time e = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
        return new Lunch(integer.parseInt(loop.getAttributes().getNamedItem("lunch").getNodeValue()),s,e)
    }
    private void addclass(String day,Node c){
        Node child=loop.getFirstChild().getNextSibling();
        String[] stuff = child.getTextContent().split(":");
        Time s = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
        stuff = child.getNextSibling().getNextSibling().getTextContent().split(":");
        Time e = new Time(Integer.parseInt(stuff[0]), Integer.parseInt(stuff[1]));
        if (day.equals("normal")){
            normal.addClass(index, c.getAttributes().getNamedItem("period").getNodeValue());
            normal.setClass(index, s, e);
            normal.aClass[index].calcLength();
            Logger.logALL("Class Loaded for Normal Schedule: " + normal.aClass[index].toString());
        } else if (day.equals("wednesday")){
            wednesday.addClass(index, c.getAttributes().getNamedItem("period").getNodeValue());
            wednesday.setClass(index, s, e);
            wednesday.aClass[index].calcLength();
            Logger.logALL("Class Loaded for Wednesday Schedule: " + wednesday.aClass[index].toString());
        } else if (day.equals("thursday")){
            thursday.addClass(index, c.getAttributes().getNamedItem("period").getNodeValue());
            thursday.setClass(index, s, e);
            thursday.aClass[index].calcLength();
            Logger.logALL("Class Loaded for Thurdays Schedule: " + thursday.aClass[index].toString());
        } else if (day.equals("assembly")){
            assembly.addClass(index, c.getAttributes().getNamedItem("period").getNodeValue());
            assembly.setClass(index, s, e);
            assembly.aClass[index].calcLength();
            Logger.logALL("Class Loaded for Assembly Schedule: " + assembly.aClass[index].toString());
        }
    }

    private void loadAtechScheduleWednesday(Node w) {
        Logger.logINFO("Loading ATECH Wednesday Schedule");
    }

    private void loadAtechScheduleThursday(Node t) {
        Logger.logINFO("Loading ATECH Thursday Schedule");
    }

    private void loadAtechScheduleAssembly(Node a) {
        Logger.logINFO("Loading ATECH Assembly Schedule");
    }

    private void loadAtechHolidays(Node h) {
        Logger.logINFO("Loading ATECH Holidays");
        Node loop = h.getFirstChild().getNextSibling();
        while (loop != null) {
            Logger.logALL("Holiday: " + loop.getFirstChild().getNextSibling().getTextContent() + " Loaded");
            String name = loop.getFirstChild().getNextSibling().getTextContent();
            int day = Integer.parseInt(loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent());
            int month = Integer.parseInt(loop.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
            loop = loop.getNextSibling().getNextSibling();
            holidays.add(new Holidays(name, day, month));
        }
    }

    private void loadAtechTeachers(Node t) {
        Logger.logINFO("Loading ATECH Teachers");
    }
}
