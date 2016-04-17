package com.mysotd.android.myshaveoftheday.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mysotd.android.myshaveoftheday.database.ItemDbSchema.ItemTable;

/**
 * Created by Adam Chase on 4/17/2016.
 */
public class ItemBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "itemBase.db";

    public ItemBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + ItemTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ItemTable.Cols.UUID + ", " +
                ItemTable.Cols.NAME + ", " +
                ItemTable.Cols.TYPE_INDEX + ", " +
                ItemTable.Cols.TYPE_TEXT + ", " +
                ItemTable.Cols.BRAND_INDEX + ", " +
                ItemTable.Cols.BRAND_TEXT + ", " +
                ItemTable.Cols.BRANDS + ", " +
                ItemTable.Cols.PURCHASE_DATE + ", " +
                ItemTable.Cols.PRICE + ", " +
                ItemTable.Cols.DISPOSABLE + ", " +
                ItemTable.Cols.ITEM_COUNT + ", " +
                ItemTable.Cols.LAST_USE + ", " +
                ItemTable.Cols.NUM_USES + ", " +
                ItemTable.Cols.COMMENTS + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
