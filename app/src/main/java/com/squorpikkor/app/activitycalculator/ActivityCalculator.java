package com.squorpikkor.app.activitycalculator;

import java.util.GregorianCalendar;

/**
 * Created by Squorpikkor 19.08.18
 **/

class ActivityCalculator {

    double getActivity(double a0, double t_pol, int year, int month, int day) {
        return a0 * Math.exp(-0.693 / (t_pol*365.25) * days_left(year, month, day));
    }

    private double days_left(int year, int month, int day) {
        GregorianCalendar povDate = new GregorianCalendar(year, month - 1, day);
        GregorianCalendar nowDate = new GregorianCalendar();
        return ((double) nowDate.getTimeInMillis() - (double) povDate.getTimeInMillis()) / 1000 / 60 / 60 / 24;
    }

}
