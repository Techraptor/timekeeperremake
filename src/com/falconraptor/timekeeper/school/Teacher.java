package com.falconraptor.timekeeper.school;

import com.falconraptor.timekeeper.schedule.Schedule;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Teacher {
    public Schedule normal;
    public Schedule wednesday;
    public Schedule thursday;
    private Name name;
    private ArrayList<String> classes = new ArrayList<>(0);
    private String room;
    private String department;
    private int extension;
    private String email;
    private String website;

    public Teacher(String first,String last,String title){
        name = new Name(first, last, title);
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

    @Override
    public String toString() {
        return "Teacher{" +
                "normal=" + normal +
                ", wednesday=" + wednesday +
                ", thursday=" + thursday +
                ", name='" + name + '\'' +
                ", classes=" + classes +
                ", room='" + room + '\'' +
                '}';
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
