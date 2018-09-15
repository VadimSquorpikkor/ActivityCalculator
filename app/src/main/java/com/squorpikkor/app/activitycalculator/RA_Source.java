package com.squorpikkor.app.activitycalculator;

/**
 * Created by Squorpikkor 19.08.18
 **/

public class RA_Source {

    private int id;     //ID для нахождения объекта в БД и его апдейта или удаления
    private String name;    //Номер источника
    private String element; //Химический элемент
    private double a0;      //Активность в день поверки
    private double halfLife;    //Период полураспада
    private int year, month, day;   //Год, месяц и день поверки

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getMonth() { return month; }
    public void setMonth( int month) { this.month = month; }

    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }

    public double getHalfLife() { return halfLife; }
    public void setHalfLife(double halfLife) { this.halfLife = halfLife; }

    public double getA0() { return a0; }
    public void setA0(double a0) { this.a0 = a0; }

    public String getElement() { return element; }
    public void setElement(String element) { this.element = element; }

    public int getID() { return id; }
    public void setID(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    private ActivityCalculator activityCalculator = new ActivityCalculator();

    public double getActivity() {
        return activityCalculator.getActivity(a0, halfLife, year, month, day);
    }


}
