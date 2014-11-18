package com.falconraptor.timekeeper.school;

import com.falconraptor.timekeeper.references.References;
import com.falconraptor.timekeeper.schedule.Schedule;
import com.falconraptor.timekeeper.school.schools.Atech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class Teacher {
    public Schedule normal;
    public Schedule wednesday;
    public Schedule thursday;
    public Schedule assembly;
    private Name name;
    private ArrayList<String> classes = new ArrayList<>(0);
    private String room;
    private String department;
    private int extension;
    private String email;
    private String website;
    private int[] lunches;

    public Teacher(String first, String last, String title) {
        name = new Name(first, last, title);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name=" + name +
                ", classes=" + classes +
                ", room='" + room + '\'' +
                ", department='" + department + '\'' +
                ", extension=" + extension +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", lunches=" + Arrays.toString(lunches) +
                '}';
    }

    public void setSchedules() {
        normal = new Schedule(8);
        wednesday = new Schedule(4);
        thursday = new Schedule(4);
        assembly = new Schedule(8);
        Atech a = References.settings.atech;
        for (int i = 1; i < classes.size(); i++) {
            normal.addClass(i - 1, classes.get(i - 1));
            normal.setClass(i - 1, a.normal.aClass[i - 1].getStart(), a.normal.aClass[i - 1].getEnd());
            assembly.addClass(i - 1, classes.get(i - 1));
            assembly.setClass(i - 1, a.assembly.aClass[i - 1].getStart(), a.assembly.aClass[i - 1].getEnd());
            if (i % 2 == 0) {
                thursday.addClass(i / 2 - 1, classes.get(i - 1));
                thursday.setClass(i / 2 - 1, a.thursday.aClass[i / 2].getStart(), a.thursday.aClass[i / 2].getEnd());
            } else if (i % 2 == 1) {
                wednesday.addClass(i / 2, classes.get(i - 1));
                wednesday.setClass(i / 2, a.wednesday.aClass[i / 2].getStart(), a.wednesday.aClass[i / 2].getEnd());
            }
        }
        normal.setLunch("normal", lunches[0]);
        wednesday.setLunch("wednesday", lunches[1]);
        thursday.setLunch("thursday", lunches[2]);
        normal.calcLengths();
        wednesday.calcLengths();
        thursday.calcLengths();
    }

    public int[] getLunches() {
        return lunches;
    }

    public void setLunches(int[] lunches) {
        this.lunches = lunches;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setName(String first, String last, String title) {
        name = new Name(first, last, title);
    }

    public String getDepartment() {

        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean contains(Object o) {
        return classes.contains(o);
    }

    public int indexOf(Object o) {
        return classes.indexOf(o);
    }

    public int size() {
        return classes.size();
    }

    public boolean isEmpty() {
        return classes.isEmpty();
    }

    public Object[] toArray() {
        return classes.toArray();
    }

    public String get(int index) {
        return classes.get(index);
    }

    public String set(int index, String element) {
        return classes.set(index, element);
    }

    public boolean add(String s) {
        return classes.add(s);
    }

    public String remove(int index) {
        return classes.remove(index);
    }

    public boolean remove(Object o) {
        return classes.remove(o);
    }

    public void clear() {
        classes.clear();
    }

    public void forEach(Consumer<? super String> action) {
        classes.forEach(action);
    }
}
