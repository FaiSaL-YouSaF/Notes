package com.faisalyousaf777.notes.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.faisalyousaf777.notes.data.Note;
import com.faisalyousaf777.notes.data.NoteRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NoteViewModel extends AndroidViewModel {

    private final NoteRepository repository;
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public NoteViewModel(@NonNull Application application) {
        super(application);
        this.repository = new NoteRepository(application);
    }

    public LiveData<Note> getById(int noteId) {
        return repository.findById(noteId);
    }

    // ---------- LiveData getters ----------
    public LiveData<List<Note>> getAllNotes() {
        return repository.getAllNotes();
    }

    public LiveData<List<Note>> getNotesByCategory(@NonNull String category) {
        return repository.getNotesByCategory(category);
    }

    public LiveData<List<Note>> getFavoriteNotes() {
        return repository.getFavoriteNotes();
    }

    public LiveData<List<Note>> getArchivedNotes() {
        return repository.getArchivedNotes();
    }

    public LiveData<List<Note>> getPinnedNotes() {
        return repository.getPinnedNotes();
    }

    public LiveData<Note> findById(int noteId) {
        return repository.findById(noteId);
    }

    // ---------- Async write operations ----------
    public void insert(@NonNull Note note) {
        try {
            repository.insert(note);
        } catch (Exception ex) {
            errorMessage.postValue("Error inserting note: " + ex.getMessage());
        }
    }

    public void insertAll(@NonNull List<Note> notes) {
        try {
            repository.insertAll(notes);
        } catch (Exception ex) {
            errorMessage.postValue("Error inserting multiple notes: " + ex.getMessage());
        }
    }

    public void update(@NonNull Note note) {
        try {
            repository.update(note);
        } catch (Exception ex) {
            errorMessage.postValue("Error updating note: " + ex.getMessage());
        }
    }

    public void delete(@NonNull Note note) {
        try {
            repository.delete(note);
        } catch (Exception ex) {
            errorMessage.postValue("Error deleting note: " + ex.getMessage());
        }
    }

    public void deleteById(int noteId) {
        try {
            repository.deleteById(noteId);
        } catch (Exception ex) {
            errorMessage.postValue("Error deleting note by ID: " + ex.getMessage());
        }
    }

    public void deleteAllNotes() {
        try {
            repository.deleteAllNotes();
        } catch (Exception ex) {
            errorMessage.postValue("Error deleting all notes: " + ex.getMessage());
        }
    }

    // ---------- Async + return results ----------
    public CompletableFuture<Long> insertAndReturnId(@NonNull Note note) {
        return repository.insertAndReturnId(note)
                .exceptionally(ex -> {
                    errorMessage.postValue("Error inserting note: " + ex.getMessage());
                    return -1L;
                });
    }

    public CompletableFuture<Integer> updateAndReturnCount(@NonNull Note note) {
        return repository.updateAndReturnCount(note)
                .exceptionally(ex -> {
                    errorMessage.postValue("Error updating note: " + ex.getMessage());
                    return 0;
                });
    }

    public CompletableFuture<Integer> deleteAndReturnCount(@NonNull Note note) {
        return repository.deleteAndReturnCount(note)
                .exceptionally(ex -> {
                    errorMessage.postValue("Error deleting note: " + ex.getMessage());
                    return 0;
                });
    }

    // ---------- Error state ----------
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}

