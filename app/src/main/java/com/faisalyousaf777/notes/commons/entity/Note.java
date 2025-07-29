package com.faisalyousaf777.notes.commons.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;


@Entity(tableName = "notes_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int noteId;
    private String title;
    private String content;

    private boolean isPinned;
    private boolean isFavorite;
    private boolean isArchived;
    private boolean isDeleted;

    private String tags;    // Comma-separated tags for the note
    private String colorCode;    // Hex color code for the note background
    private LocalDateTime remainderTime;    // Optional/Nullable
    private String attachmentPath;    // Comma-separated paths for multiple attachments

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastViewedAt;
    private LocalDateTime deletedAt;


}
