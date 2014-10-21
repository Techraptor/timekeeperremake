package com.falconraptor.timekeeper.init;

import com.falconraptor.timekeeper.references.References;
import com.falconraptor.utilities.logger.Logger;

public class GUI {
    public GUI() {
    }

    public void start() {
        Logger.logINFO("Opening main gui");
        References.timekeepergui.setVisible(true);
    }
}
