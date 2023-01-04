package com.bignerdranch.android.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class MealRaterDataSource {

    // Declare database helper
    private final DBMealRaterHelper mDBHelper;

    public MealRaterDataSource(DBMealRaterHelper dbHelper) {
        mDBHelper = dbHelper;
    }

    public void insertMeal(String dish, String restaurant, int rating) {
        // Get writable database
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(MealRaterContract.MealEntry.COLUMN_NAME_DISH, dish);
        values.put(MealRaterContract.MealEntry.COLUMN_NAME_RESTAURANT, restaurant);
        values.put(MealRaterContract.MealEntry.COLUMN_NAME_RATING, rating);

        // Insert the new row
        db.insert(MealRaterContract.MealEntry.TABLE_NAME, null, values);
    }

    public void close() {
    }
}
