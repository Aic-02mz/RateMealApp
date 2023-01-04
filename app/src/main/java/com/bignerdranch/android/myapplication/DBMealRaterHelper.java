package com.bignerdranch.android.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMealRaterHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MealRater.db";

    public DBMealRaterHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a table to hold meals
        String SQL_CREATE_MEALS_TABLE =  "CREATE TABLE " + MealRaterContract.MealEntry.TABLE_NAME + " (" +
                MealRaterContract.MealEntry._ID + " INTEGER PRIMARY KEY," +
                MealRaterContract.MealEntry.COLUMN_NAME_DISH + " TEXT," +
                MealRaterContract.MealEntry.COLUMN_NAME_RESTAURANT + " TEXT," +
                MealRaterContract.MealEntry.COLUMN_NAME_RATING + " INTEGER" + ")";

        db.execSQL(SQL_CREATE_MEALS_TABLE);
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        String SQL_DELETE_MEALS_TABLE = "DROP TABLE IF EXISTS " + MealRaterContract.MealEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_MEALS_TABLE);
        onCreate(db);
    }
}
