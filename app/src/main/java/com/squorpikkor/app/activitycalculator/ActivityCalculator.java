package com.squorpikkor.app.activitycalculator;

/**
 * Created by Squorpikkor 19.08.18
 **/

class ActivityCalculator {

    private double CS137_TPOL = 365.25 * 30.17;

    double getActivity(double a0, double days_left, double t_pol) {
        return a0 * Math.exp(-0.693 / t_pol * days_left);
    }

    double get_cs137_activity(double a0, double days_left) {
        return getActivity(a0, days_left, CS137_TPOL);
    }
}
