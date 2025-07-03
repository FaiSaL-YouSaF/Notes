package com.faisalyousaf777.notes.dao;

import static com.faisalyousaf777.notes.DbHelper.COLUMN_CATEGORY;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_COLOR_CODE;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_CONTENT;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_CREATED_AT;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_ID;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_IS_FAVORITE;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_TITLE;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_UPDATED_AT;
import static com.faisalyousaf777.notes.DbHelper.SELECT_ALL_NOTES;
import static com.faisalyousaf777.notes.DbHelper.TABLE_NOTES;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.faisalyousaf777.notes.DbHelper;
import com.faisalyousaf777.notes.entity.Note;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotesDAO {

    private DbHelper dbHelper;

    public NotesDAO(Context context) {
        this.dbHelper = DbHelper.getInstance(context);
    }


    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL_NOTES, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT));
                boolean isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE)) == 1;
                String category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY));
                String colorCode = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COLOR_CODE));
                String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT));
                String updatedAt = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UPDATED_AT));
                notes.add(new Note.Builder()
                        .setId(id).setTitle(title)
                        .setContent(content)
                        .setIsFavorite(isFavorite)
                        .setCategory(category)
                        .setColorCode(colorCode)
                        .setCreatedAt(createdAt != null ? LocalDateTime.parse(createdAt) : null)
                        .setUpdatedAt(updatedAt != null ? LocalDateTime.parse(updatedAt) : null)
                        .build());
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }


    public boolean insertNote(final Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, note.getTitle());
        contentValues.put(COLUMN_CONTENT, note.getContent());
        contentValues.put(COLUMN_IS_FAVORITE, note.isFavorite() ? 1 : 0);
        contentValues.put(COLUMN_CATEGORY, note.getCategory());
        contentValues.put(COLUMN_COLOR_CODE, note.getColorCode());
        contentValues.put(COLUMN_CREATED_AT, note.getCreatedAt().toString());
        try {
            long noOfRowsAffected = db.insert(TABLE_NOTES, null, contentValues);
            return noOfRowsAffected != -1;
        } catch (Exception e) {
            Log.d("InsertNote", "insertNote: failed to insert note" + e.getMessage());
            return false;
        }
    }

    public Note getNoteById(final int noteId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NOTES + " WHERE " + COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT));
            boolean isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE)) == 1;
            String category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY));
            String colorCode = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COLOR_CODE));
            String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT));
            String updatedAt = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UPDATED_AT));
            cursor.close();
            db.close();
            return new Note.Builder()
                    .setId(noteId)
                    .setTitle(title)
                    .setContent(content)
                    .setIsFavorite(isFavorite)
                    .setCategory(category)
                    .setCreatedAt(createdAt != null ? LocalDateTime.parse(createdAt) : null)
                    .setUpdatedAt(updatedAt != null ? LocalDateTime.parse(updatedAt) : null)
                    .setColorCode(colorCode)
                    .build();
        }
        cursor.close();
        db.close();
        return null;
    }

    public void updateNoteById(final int noteId, final Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, note.getTitle());
        contentValues.put(COLUMN_CONTENT, note.getContent());
        contentValues.put(COLUMN_IS_FAVORITE, note.isFavorite() ? 1 : 0);
        contentValues.put(COLUMN_CATEGORY, note.getCategory());
        contentValues.put(COLUMN_COLOR_CODE, note.getColorCode());
        contentValues.put(COLUMN_UPDATED_AT, note.getUpdatedAt().toString());

        try {
            int numberOfRowsAffected = db.update(TABLE_NOTES, contentValues, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
        } catch (Exception e) {
            Log.d("UpdateNote", "updateNoteById: failed to update note" + e.getMessage());
        }
    }

    public boolean deleteNoteById(final int noteId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            int numberOfRowsAffected = db.delete(TABLE_NOTES, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
            return numberOfRowsAffected > 0;
        } catch (Exception e) {
            Log.d("DeleteNote", "deleteNoteById: failed to delete note" + e.getMessage());
            return false;
        }
    }

    public void markNoteAsFavorite(final int noteId, final boolean isFavorite) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IS_FAVORITE, isFavorite ? 1 : 0);
        try {
            int numberOfRowsAffected = db.update(TABLE_NOTES, contentValues, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
        } catch (final Exception ex) {
            Log.d("UpdateNote", "markNoteAsFavorite: failed to update note");
        }
        db.close();
    }

    public List<Note> getFavoriteNotes() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<Note> favoriteNotes = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NOTES, null, COLUMN_IS_FAVORITE + " = ?", new String[]{String.valueOf(1)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int noteId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT));
                boolean isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE)) == 1;
                String category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY));
                String colorCode = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COLOR_CODE));
                String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT));
                String updatedAt = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UPDATED_AT));

                favoriteNotes.add(new Note.Builder()
                        .setId(noteId).setTitle(title)
                        .setContent(content)
                        .setIsFavorite(isFavorite)
                        .setCategory(category)
                        .setColorCode(colorCode)
                        .setCreatedAt(createdAt != null ? LocalDateTime.parse(createdAt) : null)
                        .setUpdatedAt(updatedAt != null ? LocalDateTime.parse(updatedAt) : null)
                        .build());
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favoriteNotes;
    }

    public List<Note> getNotesByCategory(final String category) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<Note> notes = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NOTES, null, COLUMN_CATEGORY + " = ?", new String[]{category}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int noteId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT));
                boolean isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE)) == 1;
                String colorCode = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COLOR_CODE));
                String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT));
                String updatedAt = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UPDATED_AT));

                notes.add(new Note.Builder()
                        .setId(noteId).setTitle(title)
                        .setContent(content)
                        .setIsFavorite(isFavorite)
                        .setCategory(category)
                        .setColorCode(colorCode)
                        .setCreatedAt(createdAt != null ? LocalDateTime.parse(createdAt) : null)
                        .setUpdatedAt(updatedAt != null ? LocalDateTime.parse(updatedAt) : null)
                        .build());
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }
}
