package com.squorpikkor.app.activitycalculator;

import com.squorpikkor.app.activitycalculator.RA_Element.RA_Element;

import java.util.GregorianCalendar;

/**
 * Created by Squorpikkor 19.08.18
 **/

public class RA_Source {

    /**Номер источника**/
    private String name;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHalfLife(double halfLife) {

        this.halfLife = halfLife;
    }

    public String getPov_date() {
        return pov_date;
    }

    public void setPov_date(String pov_date) {
        this.pov_date = pov_date;
    }

    public void setA0(double a0) {

        this.a0 = a0;
    }

    /** Химический элемент**/
    private String element;

    /**Активность в день поверки**/
    private double a0;

    /**Период полураспада**/
    private double halfLife;

    /**Дата поверки**/
    private String pov_date;

    /**Дата поверки**/
    private GregorianCalendar greg_pov_date;

    /**ID для нахождения объекта в БД и его апдейта или удаления**/
    private int id;

    public void setElement(String element) {
        this.element = element;
    }

    private int year, month, day;


    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    private ActivityCalculator activityCalculator;

    ///Конструкторов нужно 2: для создания нового источника и для загрузки класса из БД

    //Конструктор для создания пустого класса. Вызывается при нажатии кнопки "добавить источник"
    //Возможно стоит инициализировать переменные по-умолчанию или в конструкторе или лучше
    //в объявлении переменных в классе, БД может не правильно понять переменные без значений
    public RA_Source() {
        activityCalculator = new ActivityCalculator();
    }

    public RA_Source(String name) {
        this.name = name;
        activityCalculator = new ActivityCalculator();
    }

    public RA_Source(String name, String element, double a0, double halfLife) {
        this.name = name;
        this.element = element;
        this.a0 = a0;
        this.halfLife = halfLife;
        activityCalculator = new ActivityCalculator();
    }

    public RA_Source(int id, String name, String element) {
        this.id = id;
        this.name = name;
        this.element = element;
    }

    public RA_Source(String name, String element) {
        this.name = name;
        this.element = element;
    }

    RA_Source(String name, RA_Element ra_element, double a0) {
        this.name = name;
        this.element = ra_element.getName();
        this.a0 = a0;
        this.halfLife = ra_element.getHalfLife();
        activityCalculator = new ActivityCalculator();
    }

    //конструктор для версии без sqlite и shPref. Запускается из мейн активити
    RA_Source(String name, RA_Element ra_element, double a0, int year, int month, int day) {
        this.name = name;
        this.element = ra_element.getName();
        this.a0 = a0;
        this.halfLife = ra_element.getHalfLife();
        activityCalculator = new ActivityCalculator();
        this.year = year;
        this.month = month;
        this.day = day;
    }

    //Конструктор для загрузки РА_источника из sqlite (что то же создание источника из данных из БД)
    RA_Source(String sourceName, String element, double a0, double halfLife, int year, int month, int day) {
        this.name = sourceName;
        this.element = element;
        this.a0 = a0;
        this.halfLife = halfLife;
        activityCalculator = new ActivityCalculator();
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public String getElement() {
        return element;
    }

    public double getA0() {
        return a0;
    }

    public double getHalfLife() {
        return halfLife;
    }



    /*public double getActivity() {
        return activityCalculator.getActivity(getA0(), getHalfLife());
    }*/

    public double getActivity() {
        return activityCalculator.getActivity(getA0(), getHalfLife(), year, month, day);
    }

    //В будущем этого сеттера не будет. Имя будет закрыто от изменения.
    //Сейчас этот сеттер оставлен толька для проверки работы БД
    public void setName(String name) {
        this.name = name;
    }

    //ненужный метод для стандартного лист итема
    @Override
    public String toString() {
        return name + " " + String.valueOf(getActivity());
    }
}
