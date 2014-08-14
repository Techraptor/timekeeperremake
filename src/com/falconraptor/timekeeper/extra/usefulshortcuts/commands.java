package com.falconraptor.timekeeper.extra.usefulshortcuts;

public abstract class commands implements faced {
    String url, command;

    public void seturl(String u) {
        url = u;
    }

    public void setcommand(String c) {
        command = c;
    }

    public String geturl() {
        return url;
    }

    public String getcommand() {
        return command;
    }

    public String toString() {
        return "URL: " + url + ", Command: " + command;
    }
}
