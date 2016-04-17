package com.mysotd.android.myshaveoftheday.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.mysotd.android.myshaveoftheday.Item;
import com.mysotd.android.myshaveoftheday.database.ItemDbSchema.ItemTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Adam Chase on 4/17/2016.
 */
public class ItemCursorWrapper extends CursorWrapper {
    public ItemCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Item getItem(){

        String uuidString = getString(getColumnIndex(ItemTable.Cols.UUID));
        String name = getString(getColumnIndex(ItemTable.Cols.NAME));
        Integer typeIndex = getInt(getColumnIndex(ItemTable.Cols.TYPE_INDEX));
        String typeText = getString(getColumnIndex(ItemTable.Cols.TYPE_TEXT));
        Integer brandIndex = getInt(getColumnIndex(ItemTable.Cols.BRAND_INDEX));
        String brandText = getString(getColumnIndex(ItemTable.Cols.BRAND_TEXT));
        String brands = getString(getColumnIndex(ItemTable.Cols.BRANDS));
        long purchaseDate = getLong(getColumnIndex(ItemTable.Cols.PURCHASE_DATE));
        String price = getString(getColumnIndex(ItemTable.Cols.PRICE));
        int isDisposable = getInt(getColumnIndex(ItemTable.Cols.DISPOSABLE));
        String itemCount = getString(getColumnIndex(ItemTable.Cols.ITEM_COUNT));
        long lastUseDate = getLong(getColumnIndex(ItemTable.Cols.LAST_USE));
        String comments = getString(getColumnIndex(ItemTable.Cols.COMMENTS));

        Item item = new Item(UUID.fromString(uuidString));
        item.setName(name);
        item.setTypeIndex(typeIndex);
        item.setTypeText(typeText);
        item.setBrandIndex(brandIndex);
        item.setBrandText(brandText);
        //item.setmBrands(item.convertStringToArray(brands));
        item.setPurchaseDate(new Date(purchaseDate));
        item.setPrice(price);
        item.setDisposable(isDisposable != 0);
        item.setItemCount(itemCount);
        item.setLastUse(new Date(lastUseDate));
        item.setComments(comments);

        return item;
    }
}
