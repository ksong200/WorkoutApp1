package com.example.workoutapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private Button savedBtn;
    private Button startBtn;

    Toolbar toolbar;

    public ArrayList<Workout> workouts = new ArrayList<>();

    public File file;

    public static final String EXTRA_WORKOUTS = "com.example1.workoutapp1.EXTRA_WORKOUTS";
    public static final String SIMPLE_WORKOUTS = "simple_workouts";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //start a new workout button
        startBtn = findViewById(R.id.startNewButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewWorkoutScreen();
            }
        });
        //saved workouts button
        savedBtn = findViewById(R.id.savedButton);
        savedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSavedScreen();
            }


        });
        file = new File(this.getFilesDir(), SIMPLE_WORKOUTS);
        //read in saved workouts from internal storage
        populateWorkoutArray();
        toolbar = findViewById(R.id.toolbar);
        //set it as the supportActionBar
        setSupportActionBar(toolbar);

    }


    //launch navigation bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_nav_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_homeButton:
//                //do nothing
//                return true;
//            case R.id.nav_newWorkout:
//                openNewWorkoutScreen();
//
//                return true;
//            case R.id.nav_SavedWorkouts:
//                openSavedScreen();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }



    public void openNewWorkoutScreen() {
        Intent intentNewWorkoutScreen = new Intent(this, NewWorkoutActivity.class);
        startActivity(intentNewWorkoutScreen);
    }

    public void openSavedScreen() {
        Intent intentOpenSavedScreen = new Intent(this, SavedWorkoutsActivity.class);
        intentOpenSavedScreen.putExtra(EXTRA_WORKOUTS, workouts);

        startActivity(intentOpenSavedScreen);
    }



    public void populateWorkoutArray () {

        try {
            Scanner fileIn = new Scanner(file);
            String s;
            while(fileIn.hasNextLine()) {
                s = fileIn.nextLine();
                String[] split = s.split(",");
                Workout w = new Workout(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5]));
                workouts.add(w);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }





}
