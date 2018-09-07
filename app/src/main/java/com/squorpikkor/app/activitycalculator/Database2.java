package com.squorpikkor.app.activitycalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database2 extends SQLiteOpenHelper implements IDatabaseHandler {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sourceManager";
    private static final String TABLE_SOURCES = "ra_sources";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ELEMENT = "element";

    public Database2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SOURCES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_ELEMENT + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        /*ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, "№516");
        contentValues.put(KEY_ELEMENT, "Cesium");

        db.insert(TABLE_SOURCES, null, contentValues);
        db.close();*/

        //addRA_Source(new RA_Source());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOURCES);

        onCreate(db);
    }

    @Override
    public void addRA_Source(RA_Source ra_source) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, ra_source.getName());
        values.put(KEY_ELEMENT, ra_source.getElement());

        db.insert(TABLE_SOURCES, null, values);
        db.close();
    }

    @Override
    public RA_Source getRA_Source(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SOURCES, new String[] { KEY_ID,
                        KEY_NAME, KEY_ELEMENT}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        RA_Source ra_source = new RA_Source(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return ra_source;
    }

    @Override
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
                sourceList.add(ra_source);
            } while (cursor.moveToNext());
        }

        return sourceList;
    }

    @Override
    public int updateRA_Source(RA_Source ra_source) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, ra_source.getName());
        values.put(KEY_ELEMENT, ra_source.getElement());

        return db.update(TABLE_SOURCES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(ra_source.getID()) });
    }

    @Override
    public void deleteRA_Source(RA_Source ra_source) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SOURCES, KEY_ID + " = ?", new String[] { String.valueOf(ra_source.getID()) });
        db.close();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SOURCES, null, null);
        db.close();
    }

    @Override
    public int getRA_SourceCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SOURCES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
