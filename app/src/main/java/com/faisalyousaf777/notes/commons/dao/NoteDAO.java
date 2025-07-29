package com.faisalyousaf777.notes.commons.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.faisalyousaf777.notes.commons.entity.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note Note);

    @Query("SELECT * FROM notes_table ORDER BY createdAt DESC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes_table WHERE isDeleted = 1 ORDER BY deletedAt DESC")
    LiveData<List<Note>> getArchivedNotes();


    @Query("SELECT * FROM notes_table WHERE isFavorite = 1 ORDER BY updatedAt DESC")
    LiveData<List<Note>> getFavoriteNotes();

    @Query("SELECT * FROM notes_table WHERE isPinned = 1 ORDER BY updatedAt DESC")
    LiveData<List<Note>> getPinnedNotes();
}
