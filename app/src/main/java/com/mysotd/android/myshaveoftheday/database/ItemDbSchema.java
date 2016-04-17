package com.mysotd.android.myshaveoftheday.database;

/**
 * Created by Adam Chase on 4/17/2016.
 */
public class ItemDbSchema {

    public static final class ItemTable{
        public static final String NAME = "items";

        public static final class Cols {
            public static String UUID = "uuid"; // A random id is assigned to each item for use
            public static String NAME = "name"; //Name of item
            public static String TYPE_INDEX = "type_index"; //Type of item based on the index of the spinner
            public static String TYPE_TEXT = "type_text";
            public static String BRAND_INDEX = "brand_index";// Brand of item based on the index of the spinner
            public static String BRAND_TEXT = "brand_text";
            public static String BRANDS = "brands";
            public static String PURCHASE_DATE = "purchase_date"; //Date of purchase of item
            public static String PRICE = "price";
            public static String DISPOSABLE = "disposable"; //Is it disposable?
            public static String ITEM_COUNT = "item_count"; //Number of item
            public static String LAST_USE = "last_use"; //Last time item was used
            //public static String NUM_USES = "nume_uses"; //Number of times each item was used
            public static String COMMENTS = "comments"; //General notes of item
        }
    }
}
