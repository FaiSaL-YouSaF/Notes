package com.faisalyousaf777.notes.dao;

import static com.faisalyousaf777.notes.DbHelper.COLUMN_CATEGORY;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_COLOR_CODE;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_CONTENT;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_CREATED_AT;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_ID;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_IS_FAVORITE;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_TITLE;
import static com.faisalyousaf777.notes.DbHelper.COLUMN_UPDATED_AT;
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

    private final DbHelper dbHelper;

    public NotesDAO(Context context) {
        this.dbHelper = DbHelper.getInstance(context);
    }


    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query(TABLE_NOTES, null, null, null, null, null, COLUMN_CREATED_AT + " DESC");

            if (cursor != null && cursor.moveToFirst()) {
                // Cache the column indices to avoid repeated calls to getColumnIndexOrThrow
                final int idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
                final int titleIndex = cursor.getColumnIndexOrThrow(COLUMN_TITLE);
                final int contentIndex = cursor.getColumnIndexOrThrow(COLUMN_CONTENT);
                final int isFavoriteIndex = cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE);
                final int categoryIndex = cursor.getColumnIndexOrThrow(COLUMN_CATEGORY);
                final int colorCodeIndex = cursor.getColumnIndexOrThrow(COLUMN_COLOR_CODE);
                final int createdAtIndex = cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT);
                final int updatedAtIndex = cursor.getColumnIndexOrThrow(COLUMN_UPDATED_AT);

                do {
                    notes.add(
                            new Note.Builder()
                                    .setId(cursor.getInt(idIndex))
                                    .setTitle(cursor.getString(titleIndex))
                                    .setContent(cursor.getString(contentIndex))
                                    .setIsFavorite(cursor.getInt(isFavoriteIndex) == 1)
                                    .setCategory(cursor.getString(categoryIndex))
                                    .setColorCode(cursor.getString(colorCodeIndex))
                                    .setCreatedAt(LocalDateTime.parse(cursor.getString(createdAtIndex)))
                                    .setUpdatedAt(cursor.getString(updatedAtIndex) != null ? LocalDateTime.parse(cursor.getString(updatedAtIndex)) : null)
                                    .build()
                    );
                } while (cursor.moveToNext());
            }
        } catch (final Exception ex) {
            Log.d("GetAllNotes", "getAllNotes: failed to retrieve notes" + ex.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return notes;
    }


    public void insertNote(final Note note) {
        SQLiteDatabase db = null;

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, note.getTitle());
        contentValues.put(COLUMN_CONTENT, note.getContent());
        contentValues.put(COLUMN_IS_FAVORITE, note.isFavorite() ? 1 : 0);
        contentValues.put(COLUMN_CATEGORY, note.getCategory());
        contentValues.put(COLUMN_COLOR_CODE, note.getColorCode());
        contentValues.put(COLUMN_CREATED_AT, note.getCreatedAt().toString());
        try {
            db = dbHelper.getWritableDatabase();
            db.insert(TABLE_NOTES, null, contentValues);
        } catch (Exception ex) {
            Log.d("InsertNote", "insertNote: failed to insert note" + ex.getMessage());
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    public Note getNoteById(final int noteId) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        Note note = null;

        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query(
                    TABLE_NOTES,
                    null,
                    COLUMN_ID + " = ?",
                    new String[]{String.valueOf(noteId)},
                    null, null, null,
                    "1"
            );
            if (cursor != null && cursor.moveToFirst()) {
                // Cache the column indices to avoid repeated calls to getColumnIndexOrThrow
                final int idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
                final int titleIndex = cursor.getColumnIndexOrThrow(COLUMN_TITLE);
                final int contentIndex = cursor.getColumnIndexOrThrow(COLUMN_CONTENT);
                final int isFavoriteIndex = cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE);
                final int categoryIndex = cursor.getColumnIndexOrThrow(COLUMN_CATEGORY);
                final int colorCodeIndex = cursor.getColumnIndexOrThrow(COLUMN_COLOR_CODE);
                final int createdAtIndex = cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT);
                final int updatedAtIndex = cursor.getColumnIndexOrThrow(COLUMN_UPDATED_AT);

                note = new Note.Builder()
                        .setId(cursor.getInt(idIndex))
                        .setTitle(cursor.getString(titleIndex))
                        .setContent(cursor.getString(contentIndex))
                        .setIsFavorite(cursor.getInt(isFavoriteIndex) == 1)
                        .setCategory(cursor.getString(categoryIndex))
                        .setColorCode(cursor.getString(colorCodeIndex))
                        .setCreatedAt(LocalDateTime.parse(cursor.getString(createdAtIndex)))
                        .setUpdatedAt(cursor.getString(updatedAtIndex) != null ? LocalDateTime.parse(cursor.getString(updatedAtIndex)) : null)
                        .build();
            }
        } catch (final Exception ex) {
            Log.d("GetNoteById", "getNoteById: failed to retrieve note" + ex.getMessage());
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return note;
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
            db.update(TABLE_NOTES, contentValues, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
        } catch (Exception e) {
            Log.d("UpdateNote", "updateNoteById: failed to update note" + e.getMessage());
        }
    }

    public void deleteNoteById(final int noteId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.delete(TABLE_NOTES, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
        } catch (Exception e) {
            Log.d("DeleteNote", "deleteNoteById: failed to delete note" + e.getMessage());
        }
    }

    public void markNoteAsFavorite(final int noteId, final boolean isFavorite) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IS_FAVORITE, isFavorite ? 1 : 0);
        try {
            db.update(TABLE_NOTES, contentValues, COLUMN_ID + " = ?", new String[]{String.valueOf(noteId)});
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
                        .build()
                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }
}
