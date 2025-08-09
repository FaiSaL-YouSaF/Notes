package com.faisalyousaf777.notes.utils;

import com.faisalyousaf777.notes.data.Note;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SampleData {
    public static List<Note> getSampleNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("Welcome to Notes!", "This is your first note", LocalDateTime.now()));
        notes.add(new Note("Shopping List", "Milk\nBread\nEggs", LocalDateTime.now()));
        notes.add(new Note("Work", "Prepare presentation for Monday", LocalDateTime.now()));
        return notes;
    }

}
