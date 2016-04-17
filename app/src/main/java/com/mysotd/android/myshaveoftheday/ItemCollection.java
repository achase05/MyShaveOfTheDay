package com.mysotd.android.myshaveoftheday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mysotd.android.myshaveoftheday.database.ItemBaseHelper;
import com.mysotd.android.myshaveoftheday.database.ItemCursorWrapper;
import com.mysotd.android.myshaveoftheday.database.ItemDbSchema;
import com.mysotd.android.myshaveoftheday.database.ItemDbSchema.ItemTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Adam on 3/9/2016.
 */
public class ItemCollection {

    private static ItemCollection sItemCollection;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ItemCollection get(Context context){
        if(sItemCollection == null){
            sItemCollection = new ItemCollection(context);
        }
        return sItemCollection;
    }

    private ItemCollection(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ItemBaseHelper(mContext).getWritableDatabase();
    }

    public void addItem(Item i){
        ContentValues values = getContentValues(i);

        mDatabase.insert(ItemTable.NAME, null, values);
    }

    public void deleteItem(Item i){
    }

    public List<Item> getItems(){
        List<Item> items = new ArrayList<>();

        ItemCursorWrapper cursor = queryItems(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                items.add(cursor.getItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return items;
    }

    public Item getItem(UUID id){
        ItemCursorWrapper cursor = queryItems(ItemTable.Cols.UUID +
                " = ?", new String[] {id.toString()});

        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getItem();
        } finally {
            cursor.close();
        }
    }

    public void updateItem(Item item){
        String uuidString = item.getId().toString();
        ContentValues values = getContentValues(item);

        mDatabase.update(ItemTable.NAME, values,
                ItemTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    private static ContentValues getContentValues(Item item){
        ContentValues values = new ContentValues();
        values.put(ItemTable.Cols.UUID, item.getId().toString());
        values.put(ItemTable.Cols.NAME, item.getName());
        values.put(ItemTable.Cols.TYPE_INDEX, item.getTypeIndex().toString());
        values.put(ItemTable.Cols.TYPE_TEXT, item.getTypeText());
        values.put(ItemTable.Cols.BRAND_INDEX, item.getBrandIndex().toString());
        values.put(ItemTable.Cols.BRAND_TEXT, item.getBrandText());
        //values.put(ItemTable.Cols.BRANDS, item.convertArrayToString(item.getmBrands()));
        values.put(ItemTable.Cols.PURCHASE_DATE, item.getPurchaseDate().toString());
        values.put(ItemTable.Cols.PRICE, item.getPrice());
        values.put(ItemTable.Cols.DISPOSABLE, item.isDisposable() ? 1:0);
        values.put(ItemTable.Cols.ITEM_COUNT, item.getItemCount());
        values.put(ItemTable.Cols.LAST_USE, item.getLastUse().toString());
        values.put(ItemTable.Cols.NUM_USES, item.getNumUses());
        values.put(ItemTable.Cols.COMMENTS, item.getComments());

        return values;
    }

    private ItemCursorWrapper queryItems(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                ItemTable.NAME,
                null, //Columns - null selects all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                null //orderBy
        );

        return new ItemCursorWrapper(cursor);
    }
}
