package com.faisalyousaf777.notes.commons.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.faisalyousaf777.notes.commons.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Note note);     // Returns the row ID of the newly inserted note

    @Update
    void update(Note note);

    @Delete
    int delete(Note note);

    @Query("DELETE FROM notes_table WHERE note_id = :noteId")
    int deleteById(int noteId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Note> notes);     // Returns a list of row IDs of the newly inserted notes

    @Query("SELECT * FROM notes_table ORDER BY created_at DESC")
    List<Note> getAllNotes();

    @Query("SELECT * FROM notes_table WHERE is_deleted = 1 ORDER BY deleted_at DESC")
    List<Note> getArchivedNotes();


    @Query("SELECT * FROM notes_table WHERE is_favorite = 1 ORDER BY updated_at DESC")
    List<Note> getFavoriteNotes();

    @Query("SELECT * FROM notes_table WHERE is_pinned = 1 ORDER BY updated_at DESC")
    List<Note> getPinnedNotes();
}
