package com.squorpikkor.app.activitycalculator;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Squorpikkor 19.08.18
 **/

class ActivityCalculator {

    private double CS137_TPOL = 365.25 * 30.17;

    double getActivity(double a0, double t_pol) {
        return a0 * Math.exp(-0.693 / t_pol * days_left());
    }

    double getActivity(double t_pol, double a0, int year, int month, int day) {
        return a0 * Math.exp(-0.693 / t_pol * days_left(year, month, day));
    }

    double get_cs137_activity(double a0, double days_left) {
        return getActivity(a0, CS137_TPOL);
    }

    /*private double days_left() {
        GregorianCalendar povDate = new GregorianCalendar(2016,
                Calendar.OCTOBER, 12);
        GregorianCalendar nowDate = new GregorianCalendar();
        return ((double) nowDate.getTimeInMillis() - (double) povDate.getTimeInMillis()) / 1000 / 60 / 60 / 24;
    }*/

    private double days_left() {
      return days_left(2016, 10,12);
    }

    private double days_left(int year, int month, int day) {
        GregorianCalendar povDate = new GregorianCalendar(year, month - 1, day);
        GregorianCalendar nowDate = new GregorianCalendar();
        return ((double) nowDate.getTimeInMillis() - (double) povDate.getTimeInMillis()) / 1000 / 60 / 60 / 24;
    }
}