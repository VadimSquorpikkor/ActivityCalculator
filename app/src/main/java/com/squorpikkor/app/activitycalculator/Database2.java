package com.squorpikkor.app.activitycalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database2 extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "sourceManager";
    public static final String TABLE_SOURCES = "ra_sources";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ELEMENT = "element";
    public static final String COLUMN_A0 = "a0";
    public static final String COLUMN_HALF_LIFE = "half_life";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_MONTH = "month";
    public static final String COLUMN_DAY = "day";

    //По поводу ID: при создании нового RA_Source ID у него ещё нет, как только создается
    //экземпляр класса, он сразу же заносится в БД. ID объекта ещё нет, в базе ID уже есть
    //как же загрузить конкретный объект класса, если для этого нужно знать его ID?
    //Очень просто. После того, как объект загружается в БД, вызывается метод getAll
    //Все объекты загружаются в лист, а адаптер обновляет ListView, таким образом только что
    //созданный объект класса появляется в активити как элемент списка. При этом в момент загрузки
    //из БД методом getAll объект получает свой ID. Voila

    public Database2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SOURCES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ELEMENT + " TEXT,"
                + COLUMN_A0 + " REAL,"
                + COLUMN_HALF_LIFE + " REAL,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_MONTH + " INTERGER,"
                + COLUMN_DAY + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOURCES);
        onCreate(db);
    }

    //TODO убрать ненужный параметр метода
    public void addRA_Source(RA_Source ra_source) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, "Source");
        values.put(COLUMN_ELEMENT, "Cesium");
        values.put(COLUMN_A0, 90);
        values.put(COLUMN_HALF_LIFE, 30.17);
        values.put(COLUMN_YEAR, 2016);
        values.put(COLUMN_MONTH, 10);
        values.put(COLUMN_DAY, 17);
        db.insert(TABLE_SOURCES, null, values);
        db.close();
    }

    public RA_Source getRA_Source(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SOURCES, new String[] {COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_ELEMENT,
                        COLUMN_A0,
                        COLUMN_HALF_LIFE,
                        COLUMN_YEAR,
                        COLUMN_MONTH,
                        COLUMN_DAY
                }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        RA_Source ra_source = new RA_Source();
        if (cursor != null){
            cursor.moveToFirst();

            ra_source.setID(Integer.parseInt(cursor.getString(0)));
            ra_source.setName(cursor.getString(1));
            ra_source.setElement(cursor.getString(2));
            ra_source.setA0(Double.parseDouble(cursor.getString(3)));
            ra_source.setHalfLife(Double.parseDouble(cursor.getString(4)));
            ra_source.setYear(Integer.parseInt(cursor.getString(5)));
            ra_source.setMonth(Integer.parseInt(cursor.getString(6)));
            ra_source.setDay(Integer.parseInt(cursor.getString(7)));
        }

        /*RA_Source ra_source = new RA_Source(Integer.parseInt(
                cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));*/



        return ra_source;
    }

    public List<RA_Source> getAllRA_Sources() {
        List<RA_Source> sourceList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_SOURCES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                RA_Source ra_source = new RA_Source();
                ra_source.setID(Integer.parseInt(cursor.getString(0)));
                ra_source.setName(cursor.getString(1));
                ra_source.setElement(cursor.getString(2));
                ra_source.setA0(Double.parseDouble(cursor.getString(3)));
                ra_source.setHalfLife(Double.parseDouble(cursor.getString(4)));
                ra_source.setYear(Integer.parseInt(cursor.getString(5)));
                ra_source.setMonth(Integer.parseInt(cursor.getString(6)));
                ra_source.setDay(Integer.parseInt(cursor.getString(7)));
                sourceList.add(ra_source);
            } while (cursor.moveToNext());
        }

        return sourceList;
    }

    //TODO сделать void?
    public int updateRA_Source(RA_Source ra_source) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, ra_source.getName());
        values.put(COLUMN_ELEMENT, ra_source.getElement());
        values.put(COLUMN_A0, ra_source.getA0());
        values.put(COLUMN_HALF_LIFE, ra_source.getHalfLife());
        values.put(COLUMN_YEAR, ra_source.getYear());
        values.put(COLUMN_MONTH, ra_source.getMonth());
        values.put(COLUMN_DAY, ra_source.getDay());

        return db.update(TABLE_SOURCES, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(ra_source.getID()) });
    }

    public void deleteRA_Source(RA_Source ra_source) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SOURCES, COLUMN_ID + " = ?", new String[] { String.valueOf(ra_source.getID()) });
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SOURCES, null, null);
        db.close();
    }

    public int getRA_SourceCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SOURCES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
