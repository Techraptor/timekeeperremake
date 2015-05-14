package com.darkleach7.extra.Calender;

import com.falconraptor.utilities.logger.*;

import java.time.*;
import java.time.temporal.*;

// Noninstantiable utility class
public class GetDate {
	// Suppress default constructor for noninstantiability
	private GetDate () {
		throw new AssertionError();
	}

	//event name is passed and preexisting events are hardcoded to get the date for events that don't have a coded date
	public static int reqDate (String name, int year, int month) {
		//Logger.logDEBUG("reqdate called "+name);
		//int retDate = 1;
		TemporalAdjuster nxtMO = TemporalAdjusters.next(DayOfWeek.MONDAY);
		LocalDate currentDate = LocalDate.now();
		String nameToComp1 = "Martin Luther King, Jr. Day";
		String nameToComp2 = "Washington's Birthday";
		String nameToComp3 = "Memorial Day";
		String nameToComp4 = "Labor Day";
		String nameToComp5 = "Thanksgiving";
		if (name.equals(nameToComp1) || name.equals(nameToComp2)) {
			//Logger.logDEBUG("namequivalence is true");
			currentDate = currentDate.withYear(year);
			currentDate = currentDate.withMonth(month);
			currentDate = currentDate.withDayOfMonth(1);
			if (currentDate.getDayOfWeek() == DayOfWeek.MONDAY) {
				currentDate = currentDate.with(nxtMO);
				currentDate = currentDate.with(nxtMO);
			} else {
				currentDate = currentDate.with(nxtMO);
				currentDate = currentDate.with(nxtMO);
				currentDate = currentDate.with(nxtMO);
				//System.out.println(currentDate);
			}
		} else if (name.equals(nameToComp3)) {
			currentDate = currentDate.withYear(year);
			currentDate = currentDate.withMonth(month);
			currentDate = currentDate.withDayOfMonth(1);
			TemporalAdjuster lstMO = TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY);
			currentDate = currentDate.with(lstMO);
		} else if (name.equals(nameToComp4)) {
			currentDate = currentDate.withYear(year);
			currentDate = currentDate.withMonth(month);
			currentDate = currentDate.withDayOfMonth(1);
			TemporalAdjuster frstMO = TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY);
			currentDate = currentDate.with(frstMO);
		} else if (name.equals(nameToComp5)) {
			TemporalAdjuster lstTH = TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY);
			TemporalAdjuster prevTH = TemporalAdjusters.previous(DayOfWeek.THURSDAY);
			currentDate = currentDate.withYear(year);
			currentDate = currentDate.withMonth(month);
			currentDate = currentDate.withDayOfMonth(1);
			if (currentDate.getDayOfWeek() == DayOfWeek.WEDNESDAY || currentDate.getDayOfWeek() == DayOfWeek.THURSDAY || currentDate.getDayOfWeek() == DayOfWeek.FRIDAY || currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
				currentDate = currentDate.with(lstTH);
				currentDate = currentDate.with(prevTH);
			} else {
				currentDate = currentDate.with(lstTH);
			}
		} else {
			Logger.logDEBUG("no event was found");
			return 1;
		}
		return currentDate.getDayOfMonth();
	}
}
//[2015-1-30] [7:44:19] [DEBUG] New Year's Day
//        [2015-1-30] [7:44:19] [DEBUG] Martin Luther King, Jr. Day
//        [2015-1-30] [7:44:19] [DEBUG] Washington's Birthday
//        [2015-1-30] [7:44:19] [DEBUG] Memorial Day
//        [2015-1-30] [7:44:19] [DEBUG] Independence Day
//        [2015-1-30] [7:44:19] [DEBUG] Labor Day
//        [2015-1-30] [7:44:19] [DEBUG] Veterans Day
//        [2015-1-30] [7:44:19] [DEBUG] Thanksgiving
//        [2015-1-30] [7:44:19] [DEBUG] Christmas Day
