package com.bignerdranch.android.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare views as instance variables
    private EditText mEditTextDish;
    private EditText mEditTextRestaurant;
    private TextView mTextViewRating;

    private MealRaterDataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind views to layout
        mEditTextDish = findViewById(R.id.edit_text_dish);
        mEditTextRestaurant = findViewById(R.id.edit_text_restaurant);
        mTextViewRating = findViewById(R.id.text_view_rating);
        Button mButtonRate = findViewById(R.id.button_rate);
        Button mButtonSave = findViewById(R.id.button_save);


        // Create database helper and data source
        // Declare database helper and data source as instance variables
        DBMealRaterHelper mDBHelper = new DBMealRaterHelper(this);
        mDataSource = new MealRaterDataSource(mDBHelper);

        // Set listener for rate Button
        mButtonRate.setOnClickListener(view -> {
            // Create and show rating dialog
            showRatingDialog();
        });

        // Set listener for save Button
        mButtonSave.setOnClickListener(view -> {
            // Save meal and rating to database
            saveMeal();
        });
    }

    private void showRatingDialog() {
// Create a new AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
// Set the title and message
        builder.setTitle(R.string.rate_meal);
        builder.setMessage(R.string.rate_meal_prompt);
// Add the RatingBar to the dialog
        View ratingView = getLayoutInflater().inflate(R.layout.dialog_rating, null);
        final RatingBar ratingBar = ratingView.findViewById(R.id.rating_bar1);
        builder.setView(ratingView);
// Add the save Button
        builder.setPositiveButton(R.string.save, (dialogInterface, i) -> {
// Get the rating from the RatingBar
            ratingBar.getRating();
// Update the rating TextView
            // Update the rating TextView
            mTextViewRating.setText(getString(R.string.rating));


// Close the dialog
            dialogInterface.dismiss();
        });
// Create and show the dialog
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void saveMeal() {
        // Get the dish and restaurant names from the EditTexts
        String dish = mEditTextDish.getText().toString();
        String restaurant = mEditTextRestaurant.getText().toString();
        // Get the rating from the rating TextView
        String ratingString = mTextViewRating.getText().toString();
        int rating = Integer.parseInt(ratingString.substring(ratingString.indexOf(": ") + 2));
        // Insert the meal and rating into the database
        mDataSource.insertMeal(dish, restaurant, rating);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataSource.close();
    }

}
