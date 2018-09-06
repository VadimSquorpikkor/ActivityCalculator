package com.squorpikkor.app.activitycalculator;

import com.squorpikkor.app.activitycalculator.RA_Element.RA_Element;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Squorpikkor 19.08.18
 **/

public class RA_Source {

    /**Номер источника**/
    private String name;

    public void setElement(String element) {
        this.element = element;
    }

    /** Химический элемент**/
    private String element;

    /**Активность в день поверки**/
    private double a0;

    /**Период полураспада**/
    private double halfLife;

    /**Дата поверки**/
    private String pov_date;

    /**ID  для проверки только**/
    private int id;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    private ActivityCalculator activityCalculator;

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

    /**Каждый экземпляр класса сохраняет свои параметры через SaveStringArray метод,
     * именем для префНейм является имя экземпляра класса. Каждое имя хранится в Set
     * Все префНэймы беруться из этого сета и по ним загружаются экз класса
     * Если создать источник с уже существующим именем, метод не сможет сохранить
     * имя в сет (такое там уже есть) метод завершиться с ошибкой и экз класса не будет сохранен
     *
     *
     * А можно для имени префНейм использовать порядковый номер
     * тогда вместо Set можно хранить только число последнего сохраненного префа (например 5)
     * тогда при загрузке будут загружаться имена с 1 до сохраненного числа (1, 2, 3, 4, 5)
     * Плюс подхода: при удалении останется в памяти телефона файл без доступа -- мусор(как и в случае
     * использования имени), но при создании нового экземпляра, мусор перезапишется в полезный файл
     * При удалении...  ...при удалении НЕ последнего префа возникнут проблемы...
     * останется 4 префа (удален был например 2-й) загружаться будут с 1 по 4
     *
     *
     * Возможно лучшим способом будет использование SQLite
     * **/



    /**Возможно есть смысл инкапсулировать save/load методы**/

    /*void save() {
        String prefName = name;
    }*/

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


    public double getActivity() {
        return activityCalculator.getActivity(getA0(), getHalfLife());
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
