package com.mysotd.android.myshaveoftheday.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adam Chase on 4/28/2016.
 */
public class ShaveBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "shaveBase.db";

    public ShaveBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ShaveDbSchema.ShaveTable.NAME + "(" +
                " _id integer primary key autoincrement, " + ShaveDbSchema.ShaveTable.Cols.UUID + ", " +
                ShaveDbSchema.ShaveTable.Cols.NAME + ", " +
                ShaveDbSchema.ShaveTable.Cols.SHAVE_DATE + ", " +
                ShaveDbSchema.ShaveTable.Cols.DESCRIPTION + ", " +
                ShaveDbSchema.ShaveTable.Cols.COMMENTS + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
