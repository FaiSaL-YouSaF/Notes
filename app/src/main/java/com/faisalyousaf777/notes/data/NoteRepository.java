package com.faisalyousaf777.notes.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {
    private final NoteDao noteDao;
    private final ExecutorService executorService;

    public NoteRepository(@NonNull Application application) {
        NoteDatabase db = NoteDatabase.getInstance(application);
        this.noteDao = db.noteDao();
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public LiveData<List<Note>> getAllNotes() {
        return noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getNotesByCategory(final String category) {
        return noteDao.getNotesByCategory(category);
    }

    public LiveData<List<Note>> getFavoriteNotes() {
        return noteDao.getFavoriteNotes();
    }

    public LiveData<List<Note>> getArchivedNotes() {
        return noteDao.getArchivedNotes();
    }

    public LiveData<List<Note>> getPinnedNotes() {
        return noteDao.getPinnedNotes();
    }

    public LiveData<Note> findById(int noteId) {
        return noteDao.findById(noteId);
    }


    public void insert(Note note) {
        executorService.execute(() -> noteDao.insert(note));
    }

    public void insertAll(List<Note> notes) {
        executorService.execute(() -> noteDao.insertAll(notes));
    }

    public void update(Note note) {
        executorService.execute(() -> noteDao.update(note));
    }

    public void delete(Note note) {
        executorService.execute(() -> noteDao.delete(note));
    }

    public void deleteById(int noteId) {
        executorService.execute(() -> noteDao.deleteById(noteId));
    }

    public void deleteAllNotes() {
        executorService.execute(noteDao::deleteAll);
    }


    public CompletableFuture<Long> insertAndReturnId(Note note) {
        return CompletableFuture.supplyAsync(() -> noteDao.insert(note), executorService);
    }

    public CompletableFuture<Integer> updateAndReturnCount(Note note) {
        return CompletableFuture.supplyAsync(() -> noteDao.update(note), executorService);
    }

    public CompletableFuture<Integer> deleteAndReturnCount(Note note) {
        return CompletableFuture.supplyAsync(() -> noteDao.delete(note), executorService);
    }

}
