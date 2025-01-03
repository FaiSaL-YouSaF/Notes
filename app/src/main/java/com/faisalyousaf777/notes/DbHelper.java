package com.faisalyousaf777.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
    public static final String INSERT_SAMPLE_DATA = "INSERT INTO notes_table (title, content) VALUES \n" +
        "('Title 100', 'This is the content one'),\n" +
        "('Title Two', 'This is the content two'),\n" +
        "('Title Three', 'This is the content three'),\n" +
        "('Title Four', 'This is the content four'),\n" +
        "('Title Five', 'This is the content five'),\n" +
        "('Title Six', 'This is the content six'),\n" +
        "('Title Seven', 'This is the content seven'),\n" +
        "('Title Eight', 'This is the content eight'),\n" +
        "('Title Nine', 'This is the content nine'),\n" +
        "('Title Ten', 'This is the content ten'),\n" +
        "('Title Eleven', 'This is the content eleven'),\n" +
        "('Title Twelve', 'This is the content twelve'),\n" +
        "('Title Thirteen', 'This is the content thirteen'),\n" +
        "('Title Fourteen', 'This is the content fourteen'),\n" +
        "('Title Fifteen', 'This is the content fifteen'),\n" +
        "('Title Sixteen', 'This is the content sixteen'),\n" +
        "('Title Seventeen', 'This is the content seventeen'),\n" +
        "('Title Eighteen', 'This is the content eighteen'),\n" +
        "('Title Nineteen', 'This is the content nineteen'),\n" +
        "('Title Twenty', 'This is the content twenty'),\n" +
        "('Title Twenty-One', 'This is the content twenty-one'),\n" +
        "('Title Twenty-Two', 'This is the content twenty-two'),\n" +
        "('Title Twenty-Three', 'This is the content twenty-three'),\n" +
        "('Title Twenty-Four', 'This is the content twenty-four'),\n" +
        "('Title Twenty-Five', 'This is the content twenty-five'),\n" +
        "('Title Twenty-Six', 'This is the content twenty-six'),\n" +
        "('Title Twenty-Seven', 'This is the content twenty-seven'),\n" +
        "('Title Twenty-Eight', 'This is the content twenty-eight'),\n" +
        "('Title Twenty-Nine', 'This is the content twenty-nine'),\n" +
        "('Title Thirty', 'This is the content thirty')";

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
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(INSERT_SAMPLE_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL(DROP_TABLE);
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
    }

//    public void insertSampleData() {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL(INSERT_SAMPLE_DATA);
//    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT));
                notes.add(new Note(id, title, content));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
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
