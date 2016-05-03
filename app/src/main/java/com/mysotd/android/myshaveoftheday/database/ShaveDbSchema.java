package com.mysotd.android.myshaveoftheday.database;

/**
 * Created by Adam Chase on 4/28/2016.
 */
public class ShaveDbSchema {

    public static final class ShaveTable{
        public static final String NAME = "shaves";

        public static final class Cols {
            public static String UUID = "uuid"; // A random id is assigned to each shave for use
            public static String NAME = "name"; //Name of shave
            public static String SHAVE_DATE = "shave_date"; //Date of shave
            public static String DESCRIPTION = "description"; //Description of shave
            public static String COMMENTS = "comments"; //Additional comments of shave
        }
    }
}
