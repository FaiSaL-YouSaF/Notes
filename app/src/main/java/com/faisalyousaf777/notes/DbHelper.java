package com.faisalyousaf777.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    public static final String COLUMN_IS_FAVORITE = "is_favorite";

    public static final String CREATE_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s INTEGER)", TABLE_NAME, COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_IS_FAVORITE);
    public static final String DROP_TABLE = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    public static final String SELECT_ALL = String.format("SELECT * FROM %s", TABLE_NAME);
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
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL(DROP_TABLE);
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT));
                int isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE));
                notes.add(new Note(id, title, content, isFavorite == 1));
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
        contentValues.put(COLUMN_IS_FAVORITE, note.isFavorite() ? 1 : 0);
        try {
            long noOfRowsAffected = db.insert(TABLE_NAME, null, contentValues);
            return noOfRowsAffected != -1;
        } catch (Exception e) {
            Log.d("InsertNote", "insertNote: failed to insert note" + e.getMessage());
            return false;
        }
    }

    public Note getNoteById(final int noteId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT));
            int isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE));
            cursor.close();
            db.close();
            return new Note(noteId, title, content, isFavorite == 1);
        }
        cursor.close();
        db.close();
        return null;
    }

    public void updateNoteById(final int noteId, final Note note) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, note.getTitle());
        contentValues.put(COLUMN_CONTENT, note.getContent());
        contentValues.put(COLUMN_IS_FAVORITE, note.isFavorite() ? 1 : 0);
        try {
            int numberOfRowsAffected = db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
        } catch (Exception e) {
            Log.d("UpdateNote", "updateNoteById: failed to update note" + e.getMessage());
        }
    }

    public boolean deleteNoteById(final int noteId) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            int numberOfRowsAffected = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
            return numberOfRowsAffected > 0;
        } catch (Exception e) {
            Log.d("DeleteNote", "deleteNoteById: failed to delete note" + e.getMessage());
            return false;
        }
    }

    public void markNoteAsFavorite(final int noteId, final boolean isFavorite) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IS_FAVORITE, isFavorite ? 1 : 0);
        try {
            int numberOfRowsAffected = db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
        } catch (final Exception ex) {
            Log.d("UpdateNote", "markNoteAsFavorite: failed to update note");
        }
        db.close();
    }

    public List<Note> getFavoriteNotes() {
        List<Note> favoriteNotes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_IS_FAVORITE + " = ?", new String[]{String.valueOf(1)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int noteId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT));
                boolean isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE)) == 1;

                favoriteNotes.add(new Note(noteId, title, content, isFavorite));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favoriteNotes;
    }

}
