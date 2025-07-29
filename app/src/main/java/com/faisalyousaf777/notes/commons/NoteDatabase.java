package com.faisalyousaf777.notes.commons;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.faisalyousaf777.notes.commons.dao.NoteDAO;
import com.faisalyousaf777.notes.commons.entity.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class NoteDatabase extends RoomDatabase {
    private static volatile NoteDatabase INSTANCE;
    public static final String DATABASE_NAME = "notes_database";
    public abstract NoteDAO noteDAO();

    public static NoteDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (NoteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
