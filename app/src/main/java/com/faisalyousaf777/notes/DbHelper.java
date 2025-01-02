package com.faisalyousaf777.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "notes_db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "notes_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";

    public static final String CREATE_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)", TABLE_NAME, COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT);
    public static final String DROP_TABLE = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    public static final String SELECT_ALL = String.format("SELECT * FROM %s", TABLE_NAME);
//    public static final String SELECT_BY_ID = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
//    public static final String INSERT = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)", TABLE_NAME, COLUMN_TITLE, COLUMN_CONTENT);
//    public static final String UPDATE = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?", TABLE_NAME, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_ID);
//    public static final String DELETE = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);


    private DbHelper instance;

    private DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL(DROP_TABLE);
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
    }

    public boolean insertNote(final Note note) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, note.getTitle());
        contentValues.put(COLUMN_CONTENT, note.getContent());
        try {
            long noOfRowsAffected = db.insert(TABLE_NAME, null, contentValues);
            return noOfRowsAffected != -1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

//    public boolean updateNote(final Note note) {
//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_TITLE, note.getTitle());
//        contentValues.put(COLUMN_CONTENT, note.getContent());
//        try {
//            int numberOfRowsAffected = db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{String.valueOf(note.getId())});
//            return numberOfRowsAffected > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error: " + e.getMessage());
//            return false;
//        }
//    }

    public boolean deleteNote(final int noteId) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            int numberOfRowsAffected = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
            return numberOfRowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
