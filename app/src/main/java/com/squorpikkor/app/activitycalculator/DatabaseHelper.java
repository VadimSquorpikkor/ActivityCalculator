package com.squorpikkor.app.activitycalculator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userstore.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "users"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final int COLUMN_VERSION = 1;
    public static final String COLUMN_T_POL = "t_pol";
    public static final String COLUMN_A0 = "a0";
    public static final String COLUMN_A0_YEAR = "a0_year";
    public static final String COLUMN_A0_MONTH = "a0_month";
    public static final String COLUMN_A0_DAY = "a0_day";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
//        super(context,DATABASE_NAME,null, DATABASE_VERSION);
        /*(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context,name,factory, version);*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_T_POL + " INTEGER, " + COLUMN_A0 + " INTEGER, "
                + COLUMN_A0_YEAR + " INTEGER, "
                + COLUMN_A0_MONTH + " INTEGER, " + COLUMN_A0_DAY + " INTEGER);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_T_POL + ", " + COLUMN_A0 + ", " + COLUMN_A0_YEAR
                + ", " + COLUMN_A0_MONTH
                + ", " + COLUMN_A0_DAY + ") VALUES ('№516', 90, 11000, 2016, 5, 15);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}