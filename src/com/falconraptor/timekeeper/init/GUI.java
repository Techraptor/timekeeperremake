package com.falconraptor.timekeeper.init;

import com.falconraptor.timekeeper.references.*;
import com.falconraptor.utilities.logger.*;

public class GUI {
	public GUI () {
	}

	public void start () {
		Logger.logINFO("Opening main gui");
		References.timekeepergui.setVisible(true);
	}
}
