package com.squorpikkor.app.activitycalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**Другая версия БД с немного другой логикой
 * пример взял отсюда: https://www.androidauthority.com/sqlite-primer-for-android-811987/
 * **/

public class Database extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyDatabase.db";
    public static final String COLUMN_ID = "ID";
    public static final String TABLE_NAME = "SUBSCRIBERS";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_MAGAZINE_TITLE = "MAGAZINE_TITLE";
    public static final String COLUMN_RENEWAL_DATE= "RENEWAL_DATE";
    public static final String COLUMN_PHONE = "PHONE_NUMBER";


    public Database(Context context) {
        super(context,DATABASE_NAME,null, DATABASE_VERSION);

    }
//            //Такой сразу был конструктор
//    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context,name,factory, version);
//    }

    //To build this table, we need to use execSQL. This lets us talk to our database and execute
    // any SQL command that doesn’t return data. So it’s perfect for building our table to begin
    // with. We’re going to use this in the onCreate() method, which will be called right away
    // when our object is created.
    //What’s happening here is we’re talking to our database and telling it to create a new table
    // with a specific table name, which we’ve defined in our string.
    //SQLite will also add another column implicitly called rowid, which acts as a kind of index
    // for retrieving records and increases incrementally in value with each new entry. The first
    // record will have the rowid ‘0’, the second will be ‘1’, and so on. We don’t need to add this
    // ourselves but we can refer to it whenever we want. If we wanted to change the name of a
    // column, we would manually create one with the variable INTEGER PRIMARY KEY . That way, we
    // could turn our ‘rowid’ into ‘subscriber_id’ or something similar.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " VARCHAR, " +
                COLUMN_MAGAZINE_TITLE + " VARCHAR, " +
                COLUMN_RENEWAL_DATE + " VARCHAR, " +
                COLUMN_PHONE + " VARCHAR);");

        //To insert new data as a row, simply use db.insert(String table, String nullColumnHack,
        // ContentValues). But what are ContentValues? This is a class used by Android that can
        // store values to be resolved by the ContentResolver.
        //If we create a ContentValues object and fill it with our data, we can pass that to our
        // database for assimilation. It looks like this:
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, "Adam516");
        contentValues.put(COLUMN_MAGAZINE_TITLE, "Women's World");
        contentValues.put(COLUMN_RENEWAL_DATE, "11/11/2018");
        contentValues.put(COLUMN_PHONE, "00011102");

        //Another option would be to use database.execSQL() and input the data manually:
        //
        //db.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + ","
        //           + COLUMN_MAGAZINE_TITLE + "," + COLUMN_RENEWAL_DATE + ","
        //           + COLUMN_PHONE + ") VALUES('Adam','Women's World','11/11/2018','00011102')");
        //db.close();
        //
        //This does the exact same thing. Remember to always close the database when you’re finished with it.

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    //The other method, onUpgrade, is required for when the database version is changed. This will
    // drop or add tables to upgrade to the new schema version. Just populate it and don’t worry
    // about it:
    //DROP TABLE is used to delete the existing data. Here we’re deleting the table if it already
    // exists before rebuilding it.
    //In future, if we to refer to a database that was already created, then we would use
    // getReadableDatabase() or getWriteableDatabase() to open the database ready for reading-from
    // or writing-to.
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    //Раньше методы загрузки данных из БД (и из Преференс) я размещал в Активити, теперь, скорее
    // всего, буду размещать прямо в классе БД, так гораздо удобнее, всё в одном месте
    public ArrayList<RA_Source> getSourceList() {   //Имя не правильное, должно быть что-то типа SourceList или loadedSourceList
        SQLiteDatabase db = this.getWritableDatabase();
//        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<RA_Source> sourceArray = new ArrayList<>();
        RA_Source ra_source;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                ra_source = new RA_Source();
                ra_source.setName(cursor.getString(1));
                //ra_source.setMagazine(cursor.getString(2));
                sourceArray.add(ra_source);
            }
        }
        cursor.close();
        db.close();
        return sourceArray;
    }


}
