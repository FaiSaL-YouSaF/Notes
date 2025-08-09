package com.faisalyousaf777.notes.utils;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.faisalyousaf777.notes.data.Note;
import com.faisalyousaf777.notes.viewmodel.NoteViewModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NoteUtils {
    public static final String TABLE_NOTES = "notes_table";
    public static final String NOTE_ID = "note_id";

    public static final String CATEGORY_ALL = "All";

    public static List<Note> getSampleNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("Welcome to Notes!", "This is your first note", LocalDateTime.now()));
        notes.add(new Note("Shopping List", "Milk\nBread\nEggs", LocalDateTime.now()));
        notes.add(new Note("Work", "Prepare presentation for Monday", LocalDateTime.now()));
        notes.add(new Note("Project Deadline", "Submit by end of week", LocalDateTime.of(2024, 6, 2, 10, 0), false, false, false, false, "Work", "#FF5733", null, null, null, LocalDateTime.of(2024, 6, 2, 9, 0), null));
        notes.add(new Note("Team Lunch", "Schedule with team", LocalDateTime.of(2024, 6, 3, 12, 30), false, false, false, false, "Personal", "#33C1FF", null, null, null, LocalDateTime.of(2024, 6, 3, 11, 0), null));
        notes.add(new Note("Personal Diary", "Write about today", LocalDateTime.of(2024, 6, 1, 20, 0), false, false, false, false, "Personal", "#8D33FF", null, null, null, LocalDateTime.of(2024, 6, 1, 19, 0), null));
        notes.add(new Note("Read Book", "Finish current chapter", LocalDateTime.of(2024, 6, 2, 21, 0), false, false, false, false, "Leisure", "#33FF57", null, null, null, LocalDateTime.of(2024, 6, 2, 20, 0), null));
        notes.add(new Note("Buy Groceries", "Milk, Bread, Eggs", LocalDateTime.of(2024, 6, 1, 17, 0), false, false, false, false, "Shopping", "#FFC300", null, null, null, LocalDateTime.of(2024, 6, 1, 16, 0), null));
        notes.add(new Note("Order Laptop", "Check online deals", LocalDateTime.of(2024, 6, 4, 15, 0), false, false, false, false, "Shopping", "#FF5733", null, null, null, LocalDateTime.of(2024, 6, 4, 14, 0), null));
        notes.add(new Note("Doctor Appointment", "Annual checkup", LocalDateTime.of(2024, 6, 5, 8, 30), false, false, false, false, "Health", "#C70039", null, null, null, LocalDateTime.of(2024, 6, 5, 7, 30), null));
        notes.add(new Note("Morning Run", "5km in the park", LocalDateTime.of(2024, 6, 2, 6, 0), false, false, false, false, "Health", "#33FFBD", null, null, null, LocalDateTime.of(2024, 6, 2, 5, 0), null));
        notes.add(new Note("Trip to Paris", "Book flights and hotel", LocalDateTime.of(2024, 6, 10, 11, 0), false, false, false, false, "Travel", "#FF33A6", null, null, null, LocalDateTime.of(2024, 6, 10, 10, 0), null));
        notes.add(new Note("Visa Application", "Submit documents", LocalDateTime.of(2024, 6, 11, 14, 0), false, false, false, false, "Travel", "#FF33A6", null, null, null, LocalDateTime.of(2024, 6, 11, 13, 0), null));
        notes.add(new Note("Salary Received", "Check bank statement", LocalDateTime.of(2024, 6, 1, 8, 0), false, false, false, false, "Finance", "#33FF57", null, null, null, LocalDateTime.of(2024, 6, 1, 7, 0), null));
        notes.add(new Note("Pay Bills", "Electricity and Internet", LocalDateTime.of(2024, 6, 3, 9, 0), false, false, false, false, "Finance", "#33FF57", null, null, null, LocalDateTime.of(2024, 6, 3, 8, 0), null));
        notes.add(new Note("Project Deadline", "Submit by end of week", LocalDateTime.of(2024, 6, 2, 10, 0)));
        notes.add(new Note("Team Lunch", "Schedule with team", LocalDateTime.of(2024, 6, 3, 12, 30)));
        notes.add(new Note("Personal Diary", "Write about today", LocalDateTime.of(2024, 6, 1, 20, 0)));
        notes.add(new Note("Read Book", "Finish current chapter", LocalDateTime.of(2024, 6, 2, 21, 0)));
        notes.add(new Note("Buy Groceries", "Milk, Bread, Eggs", LocalDateTime.of(2024, 6, 1, 17, 0)));
        notes.add(new Note("Order Laptop", "Check online deals", LocalDateTime.of(2024, 6, 4, 15, 0)));
        notes.add(new Note("Doctor Appointment", "Annual checkup", LocalDateTime.of(2024, 6, 5, 8, 30)));
        notes.add(new Note("Morning Run", "5km in the park", LocalDateTime.of(2024, 6, 2, 6, 0)));
        notes.add(new Note("Trip to Paris", "Book flights and hotel", LocalDateTime.of(2024, 6, 10, 11, 0)));
        notes.add(new Note("Visa Application", "Submit documents", LocalDateTime.of(2024, 6, 11, 14, 0)));
        notes.add(new Note("Salary Received", "Check bank statement", LocalDateTime.of(2024, 6, 1, 8, 0)));
        notes.add(new Note("Pay Bills", "Electricity and Internet", LocalDateTime.of(2024, 6, 3, 9, 0)));
        return notes;
    }
}
