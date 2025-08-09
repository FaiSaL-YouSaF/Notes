package com.faisalyousaf777.notes.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes_table WHERE note_id = :noteId")
    LiveData<Note> findById(int noteId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Note note);    // Returns inserted rowId

    @Update
    int update(Note note);    // Returns number of rows updated

    @Delete
    int delete(Note note);    // Returns number of rows deleted

    @Query("DELETE FROM notes_table WHERE note_id = :noteId")
    int deleteById(int noteId);    // Returns number of rows deleted

    @Query("DELETE FROM notes_table")
    int deleteAll();    // Returns number of rows deleted

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Note> notes);     // Batch insert with rowIds returned

    @Query("SELECT * FROM notes_table WHERE is_deleted = 0 ORDER BY created_at DESC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes_table WHERE is_deleted = 0 AND category = :category ORDER BY updated_at DESC")
    LiveData<List<Note>> getNotesByCategory(final String category);

    @Query("SELECT * FROM notes_table WHERE is_deleted = 0 AND is_favorite = 1 ORDER BY updated_at DESC")
    LiveData<List<Note>> getFavoriteNotes();

    @Query("SELECT * FROM notes_table WHERE is_deleted = 0 AND is_archived = 1 ORDER BY deleted_at DESC")
    LiveData<List<Note>> getArchivedNotes();

    @Query("SELECT * FROM notes_table WHERE is_deleted = 0 AND is_pinned = 1 ORDER BY updated_at DESC")
    LiveData<List<Note>> getPinnedNotes();
}
