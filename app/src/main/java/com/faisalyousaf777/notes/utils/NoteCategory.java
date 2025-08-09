package com.faisalyousaf777.notes.utils;

import androidx.annotation.NonNull;

public enum NoteCategory {
    ALL("All"),
    PERSONAL("Personal"),
    WORK("Work"),
    STUDY("Study"),
    SHOPPING("Shopping"),
    TRAVEL("Travel"),
    HEALTH("Health"),
    FINANCE("Finance"),
    HOBBY("Hobby"),
    OTHER("Other");

    private final String displayName;

    NoteCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @NonNull
    @Override
    public String toString() {
        return displayName;
    }
}
