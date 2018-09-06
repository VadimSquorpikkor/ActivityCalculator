package com.squorpikkor.app.activitycalculator;

import java.util.List;

public interface IDatabaseHandler {
    public void addRA_Source(RA_Source contact);
    public RA_Source getRA_Source(int id);
    public List<RA_Source> getAllRA_Sources();
    public int getRA_SourceCount();
    public int updateRA_Source(RA_Source contact);
    public void deleteRA_Source(RA_Source contact);
    public void deleteAll();

    /*
    void addContact(Contact contact) – позволяет сохранять в базу данных новые контакты пользователей;
    Contact getContact(int id) – позволяет получить контакты по id;
    List getAllContacts() – позволяет получить все контакты с БД;
    int getContactsCount() – позволяет получить количество контактов находящиеся в БД;
    int updateContact(Contact contact) – позволяет обновить контакт;
    void deleteContact(Contact contact) – позволяет удалить контакт;
    void deleteAll() – позволяет удалить все контакты.
    */

}
