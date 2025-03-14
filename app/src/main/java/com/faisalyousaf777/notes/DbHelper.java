package com.faisalyousaf777.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "notes_db";
    public static final int DATABASE_VERSION = 1;

    // Table name
    public static final String TABLE_NOTES = "notes_table";
    public static final String TABLE_CATEGORIES = "categories_table";

    // Common columns
    public static final String COLUMN_ID = "id";

    // Categories table columns
    public static final String COLUMN_NAME = "name";

    // Notes table columns
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_IS_FAVORITE = "is_favorite";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_COLOR_CODE = "color_code";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";

    // Categories table queries
    public static final String CREATE_TABLE_CATEGORIES = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL UNIQUE)", TABLE_CATEGORIES, COLUMN_ID, COLUMN_NAME);
    public static final String DROP_TABLE_CATEGORIES = String.format("DROP TABLE IF EXISTS %s", TABLE_CATEGORIES);
    public static final String SELECT_ALL_CATEGORIES = String.format("SELECT * FROM %s", TABLE_CATEGORIES);

    // Notes table queries
    public static final String CREATE_TABLE_NOTES = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s TEXT)", TABLE_NOTES, COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_IS_FAVORITE, COLUMN_CATEGORY, COLUMN_COLOR_CODE, COLUMN_CREATED_AT, COLUMN_UPDATED_AT);
    public static final String DROP_TABLE_NOTES = String.format("DROP TABLE IF EXISTS %s", TABLE_NOTES);
    public static final String SELECT_ALL_NOTES = String.format("SELECT * FROM %s", TABLE_NOTES);
//    public static final String SELECT_BY_ID = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
//    public static final String INSERT = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)", TABLE_NAME, COLUMN_TITLE, COLUMN_CONTENT);
//    public static final String UPDATE = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?", TABLE_NAME, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_ID);
//    public static final String DELETE = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);

    private static DbHelper instance;

    private DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_NOTES);
        sqLiteDatabase.execSQL(CREATE_TABLE_CATEGORIES);
        sqLiteDatabase.execSQL(String.format("INSERT INTO %s (%s, %s) VALUES (1, 'Work'), (2, 'Personal'), (3, 'Ideas'), (4, 'Shopping'), (5, 'Travel'), (6, 'Health'), (7, 'Finance')", TABLE_CATEGORIES, COLUMN_ID, COLUMN_NAME));

        sqLiteDatabase.execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) VALUES \n" +
                        "(1, 'Meeting Notes', 'Project timeline discussion.', 1, 'Work', '#FF5733', '2024-03-14T09:00:00', '2024-03-14T10:00:00'),\n" +
                        "(2, 'Grocery List', 'Buy milk, eggs, and bread.', 0, 'Shopping', '#33FF57', '2024-03-14T11:00:00', '2024-03-14T11:30:00'),\n" +
                        "(3, 'Travel Plans', 'Book flight tickets.', 1, 'Travel', '#5733FF', '2024-03-14T12:00:00', '2024-03-14T12:30:00'),\n" +
                        "(4, 'Workout Routine', 'Morning yoga & strength training.', 0, 'Fitness', '#FFC300', '2024-03-14T13:00:00', '2024-03-14T13:30:00'),\n" +
                        "(5, 'Investment Strategy', 'Research stock market.', 1, 'Finance', '#FF33A1', '2024-03-14T14:00:00', '2024-03-14T14:30:00'),\n" +
                        "(6, 'App Ideas', 'Brainstorm new Android app features.', 0, 'Ideas', '#A133FF', '2024-03-14T15:00:00', '2024-03-14T15:30:00'),\n" +
                        "(7, 'Birthday Reminder', 'Plan a surprise birthday party.', 1, 'Personal', '#FF5733', '2024-03-14T16:00:00', '2024-03-14T16:30:00'),\n" +
                        "(8, 'Work Presentation', 'Prepare slides for next meeting.', 0, 'Work', '#33FF57', '2024-03-14T17:00:00', '2024-03-14T17:30:00'),\n" +
                        "(9, 'Shopping Wishlist', 'List of new gadgets to buy.', 1, 'Shopping', '#5733FF', '2024-03-14T18:00:00', '2024-03-14T18:30:00'),\n" +
                        "(10, 'Healthy Diet Plan', 'Prepare a meal plan.', 0, 'Health', '#FFC300', '2024-03-14T19:00:00', '2024-03-14T19:30:00'),\n" +
                        "(11, 'Monthly Budget', 'Plan expenses for the month.', 1, 'Finance', '#FF33A1', '2024-03-14T20:00:00', '2024-03-14T20:30:00'),\n" +
                        "(12, 'Programming Notes', 'Learn Java 8 features.', 0, 'Education', '#A133FF', '2024-03-14T21:00:00', '2024-03-14T21:30:00'),\n" +
                        "(13, 'Weekend Plans', 'Visit museum and try new restaurant.', 1, 'Personal', '#FF5733', '2024-03-14T22:00:00', '2024-03-14T22:30:00'),\n" +
                        "(14, 'Project Deadline', 'Submit app project by Friday.', 0, 'Work', '#33FF57', '2024-03-15T09:00:00', '2024-03-15T09:30:00'),\n" +
                        "(15, 'Online Shopping', 'Check Amazon deals.', 1, 'Shopping', '#5733FF', '2024-03-15T10:00:00', '2024-03-15T10:30:00'),\n" +
                        "(16, 'Exercise Plan', '30-minute morning run.', 0, 'Fitness', '#FFC300', '2024-03-15T11:00:00', '2024-03-15T11:30:00'),\n" +
                        "(17, 'Savings Goal', 'Save money for a trip.', 1, 'Finance', '#FF33A1', '2024-03-15T12:00:00', '2024-03-15T12:30:00'),\n" +
                        "(18, 'LeetCode Challenge', 'Solve 2 coding problems.', 0, 'Education', '#A133FF', '2024-03-15T13:00:00', '2024-03-15T13:30:00'),\n" +
                        "(19, 'Movie Night', 'Watch new Marvel movie.', 1, 'Entertainment', '#FF5733', '2024-03-15T14:00:00', '2024-03-15T14:30:00'),\n" +
                        "(20, 'Team Meeting', 'Weekly project discussion.', 0, 'Work', '#33FF57', '2024-03-15T15:00:00', '2024-03-15T15:30:00'),\n" +
                        "(21, 'Latest Tech Trends', 'Read about AI advancements.', 1, 'Technology', '#5733FF', '2024-03-15T16:00:00', '2024-03-15T16:30:00'),\n" +
                        "(22, 'Music Playlist', 'Create a relaxing playlist.', 0, 'Music', '#FFC300', '2024-03-15T17:00:00', '2024-03-15T17:30:00'),\n" +
                        "(23, 'Crypto Research', 'Check latest Bitcoin trends.', 1, 'Finance', '#FF33A1', '2024-03-15T18:00:00', '2024-03-15T18:30:00'),\n" +
                        "(24, 'Side Project', 'Plan a new portfolio project.', 0, 'Ideas', '#A133FF', '2024-03-15T19:00:00', '2024-03-15T19:30:00'),\n" +
                        "(25, 'Family Dinner', 'Reserve a restaurant.', 1, 'Relationships', '#FF5733', '2024-03-15T20:00:00', '2024-03-15T20:30:00'),\n" +
                        "(26, 'Office Tasks', 'Complete pending tasks.', 0, 'Work', '#33FF57', '2024-03-15T21:00:00', '2024-03-15T21:30:00'),\n" +
                        "(27, 'Cooking Recipe', 'Try a new pasta dish.', 1, 'Food', '#5733FF', '2024-03-15T22:00:00', '2024-03-15T22:30:00'),\n" +
                        "(28, 'Workout Plan', 'Increase strength training.', 0, 'Fitness', '#FFC300', '2024-03-16T09:00:00', '2024-03-16T09:30:00'),\n" +
                        "(29, 'Java Course', 'Watch OOP tutorials.', 1, 'Education', '#FF33A1', '2024-03-16T10:00:00', '2024-03-16T10:30:00'),\n" +
                        "(30, 'Book Notes', 'Summarize key takeaways.', 0, 'Ideas', '#A133FF', '2024-03-16T11:00:00', '2024-03-16T11:30:00'),\n" +
                        "(31, 'Podcast Notes', 'Listen to productivity tips.', 1, 'Hobbies', '#FF5733', '2024-03-16T12:00:00', '2024-03-16T12:30:00'),\n" +
                        "(32, 'Weekend Hike', 'Plan a trekking trip.', 0, 'Travel', '#33FF57', '2024-03-16T13:00:00', '2024-03-16T13:30:00'),\n" +
                        "(33, 'Photography Ideas', 'Capture cityscapes.', 1, 'Hobbies', '#5733FF', '2024-03-16T14:00:00', '2024-03-16T14:30:00'),\n" +
                        "(34, 'Date Night', 'Book a movie & dinner.', 0, 'Relationships', '#FFC300', '2024-03-16T15:00:00', '2024-03-16T15:30:00'),\n" +
                        "(35, 'Coding Practice', 'Work on problem-solving skills.', 1, 'Education', '#FF33A1', '2024-03-16T16:00:00', '2024-03-16T16:30:00');",
                TABLE_NOTES, COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_IS_FAVORITE, COLUMN_CATEGORY, COLUMN_COLOR_CODE, COLUMN_CREATED_AT, COLUMN_UPDATED_AT));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL(DROP_TABLE_NOTES);
            sqLiteDatabase.execSQL(DROP_TABLE_CATEGORIES);
            sqLiteDatabase.execSQL(CREATE_TABLE_NOTES);
            sqLiteDatabase.execSQL(CREATE_TABLE_CATEGORIES);
        }
    }





}
