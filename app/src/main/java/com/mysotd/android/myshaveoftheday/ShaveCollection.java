package com.mysotd.android.myshaveoftheday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mysotd.android.myshaveoftheday.database.ShaveBaseHelper;
import com.mysotd.android.myshaveoftheday.database.ShaveCursorWrapper;
import com.mysotd.android.myshaveoftheday.database.ShaveDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Adam Chase on 4/28/2016.
 */
public class ShaveCollection {


    private static ShaveCollection sShaveCollection;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ShaveCollection get(Context context){
        if(sShaveCollection == null){
            sShaveCollection = new ShaveCollection(context);
        }
        return sShaveCollection;
    }

    private ShaveCollection(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ShaveBaseHelper(mContext).getWritableDatabase();
    }

    public void addShave(Shave s){
        ContentValues values = getContentValues(s);

        mDatabase.insert(ShaveDbSchema.ShaveTable.NAME, null, values);
    }

    public void deleteShave(Shave s){
        mDatabase.delete(ShaveDbSchema.ShaveTable.NAME, ShaveDbSchema.ShaveTable.Cols.UUID + " = ?", new String[] {s.getId().toString()});
    }

    public List<Shave> getShaves(){
        List<Shave> shaves = new ArrayList<>();

        ShaveCursorWrapper cursor = queryShaves(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                shaves.add(cursor.getShave());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return shaves;
    }

    public Shave getShave(UUID id){
        ShaveCursorWrapper cursor = queryShaves(ShaveDbSchema.ShaveTable.Cols.UUID +
                " = ?", new String[] {id.toString()});

        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getShave();
        } finally {
            cursor.close();
        }
    }

    public void updateShave(Shave shave){
        String uuidString = shave.getId().toString();
        ContentValues values = getContentValues(shave);

        mDatabase.update(ShaveDbSchema.ShaveTable.NAME, values,
                ShaveDbSchema.ShaveTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    private static ContentValues getContentValues(Shave shave){
        ContentValues values = new ContentValues();
        values.put(ShaveDbSchema.ShaveTable.Cols.UUID, shave.getId().toString());
        values.put(ShaveDbSchema.ShaveTable.Cols.NAME, shave.getName());
        values.put(ShaveDbSchema.ShaveTable.Cols.SHAVE_DATE, shave.getShaveDate().getTime());
        values.put(ShaveDbSchema.ShaveTable.Cols.DESCRIPTION, shave.getDescription());
        values.put(ShaveDbSchema.ShaveTable.Cols.COMMENTS, shave.getComment());

        return values;
    }

    private ShaveCursorWrapper queryShaves(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                ShaveDbSchema.ShaveTable.NAME,
                null, //Columns - null selects all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                null //orderBy
        );

        return new ShaveCursorWrapper(cursor);
    }
}
