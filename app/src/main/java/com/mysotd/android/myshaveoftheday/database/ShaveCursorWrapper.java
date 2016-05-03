package com.mysotd.android.myshaveoftheday.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.mysotd.android.myshaveoftheday.database.ShaveDbSchema.ShaveTable;

import com.mysotd.android.myshaveoftheday.Shave;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Adam Chase on 4/28/2016.
 */
public class ShaveCursorWrapper extends CursorWrapper {
    public ShaveCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Shave getShave() {

        String uuidString = getString(getColumnIndex(ShaveTable.Cols.UUID));
        String name = getString(getColumnIndex(ShaveTable.Cols.NAME));
        String description = getString(getColumnIndex(ShaveTable.Cols.DESCRIPTION));
        long shaveDate = getLong(getColumnIndex(ShaveTable.Cols.SHAVE_DATE));
        String comments = getString(getColumnIndex(ShaveTable.Cols.COMMENTS));

        Shave shave = new Shave(UUID.fromString(uuidString));

        shave.setName(name);
        shave.setDescription(description);
        shave.setShaveDate(new Date(shaveDate));
        shave.setComment(comments);

        return shave;
    }
}