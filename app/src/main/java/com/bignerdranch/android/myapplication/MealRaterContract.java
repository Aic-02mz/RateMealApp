package com.bignerdranch.android.myapplication;

import android.provider.BaseColumns;

public final class MealRaterContract {

    MealRaterContract() {}

    public static class MealEntry implements BaseColumns {
        public static final String TABLE_NAME = "meals";
        public static final String COLUMN_NAME_DISH = "dish";
        public static final String COLUMN_NAME_RESTAURANT = "restaurant";
        public static final String COLUMN_NAME_RATING = "rating";
    }
}
