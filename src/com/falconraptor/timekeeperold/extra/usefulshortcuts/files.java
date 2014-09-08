package com.falconraptor.timekeeperold.extra.usefulshortcuts;

public class files extends commands {
    String path;

    public files(String p, String c, String u) {
        path = p;
        command = c;
        url = u;
    }

    public files() {
        path = "";
        command = "";
        url = "";
    }

    public void setpath(String u) {
        path = u;
    }

    public String getpath() {
        return path;
    }

    public String toString() {
        if (url.equals("")) return "Path: " + path + ", Command: " + command;
        else return "Path: " + path + ", Command: " + command + ", URL: " + url;
    }
}