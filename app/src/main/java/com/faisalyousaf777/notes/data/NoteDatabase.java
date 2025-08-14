package com.faisalyousaf777.notes.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.faisalyousaf777.notes.utils.Converter;
import com.faisalyousaf777.notes.utils.NoteUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class NoteDatabase extends RoomDatabase {
    private static volatile NoteDatabase INSTANCE;
    public static final String DATABASE_NAME = "notes_database";

    public abstract NoteDao noteDao();

    public static NoteDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (NoteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, DATABASE_NAME)
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
                executor.execute(() -> {
                    NoteDao noteDao = INSTANCE.noteDao();
                    noteDao.insertAll(NoteUtils.getSampleNotes());
                    Log.d("DB", "Database pre-populated with sample notes");
                });
            }
        }
    };

}