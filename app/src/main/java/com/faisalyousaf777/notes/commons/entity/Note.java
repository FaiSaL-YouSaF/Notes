package com.faisalyousaf777.notes.commons.entity;

import static com.faisalyousaf777.notes.commons.NoteUtils.TAG_ALL;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "notes_table")
public class Note {

    // ====== FIELDS ======
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    private int noteId;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "is_pinned")
    private boolean isPinned;

    @ColumnInfo(name = "is_favorite")
    private boolean isFavorite;

    @ColumnInfo(name = "is_archived")
    private boolean isArchived;

    @ColumnInfo(name = "is_deleted")
    private boolean isDeleted;

    @ColumnInfo(name = "tags")
    private String tags; // Comma-separated tags

    @ColumnInfo(name = "color_code")
    private String colorCode; // Hex color code

    @ColumnInfo(name = "remainder_time")
    private LocalDateTime remainderTime;

    @ColumnInfo(name = "attachment_path")
    private String attachmentPath; // Comma-separated file paths

    @NonNull
    @ColumnInfo(name = "created_at")
    private LocalDateTime createdAt;

    @ColumnInfo(name = "updated_at")
    private LocalDateTime updatedAt;

    @ColumnInfo(name = "last_viewed_at")
    private LocalDateTime lastViewedAt;

    @ColumnInfo(name = "deleted_at")
    private LocalDateTime deletedAt;


    // ====== CONSTRUCTORS ======
    public Note() {
        this.title = "";
        this.content = "";
        this.isPinned = false;
        this.isFavorite = false;
        this.isArchived = false;
        this.isDeleted = false;
        this.tags = TAG_ALL;
        this.colorCode = "#FFFFFF"; // Default white color
        this.remainderTime = null;
        this.attachmentPath = "";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.lastViewedAt = null;
        this.deletedAt = null;
    }

    public Note(@NonNull String title, @NonNull String content) {
        this();
        this.title = title;
        this.content = content;
    }

    public Note(@NonNull String title, @NonNull String content, String tags) {
        this(title, content);
        this.tags = tags;
    }

    public Note(@NonNull String title, @NonNull String content, @NonNull LocalDateTime createdAt) {
        this(title, content);
        this.createdAt = createdAt;
    }

    public Note(@NonNull String title, @NonNull String content, boolean isFavorite) {
        this(title, content);
        this.isFavorite = isFavorite;
    }

    public Note(@NonNull String title, @NonNull String content, @NonNull LocalDateTime createdAt, boolean isFavorite) {
        this(title, content, createdAt);
        this.isFavorite = isFavorite;
    }

    public Note(int noteId, @NonNull String title, @NonNull String content,
                boolean isPinned, boolean isFavorite, boolean isArchived, boolean isDeleted,
                String tags, String colorCode, LocalDateTime remainderTime,
                String attachmentPath, @NonNull LocalDateTime createdAt,
                LocalDateTime updatedAt, LocalDateTime lastViewedAt, LocalDateTime deletedAt) {

        this.noteId = noteId;
        this.title = title;
        this.content = content;
        this.isPinned = isPinned;
        this.isFavorite = isFavorite;
        this.isArchived = isArchived;
        this.isDeleted = isDeleted;
        this.tags = tags;
        this.colorCode = colorCode;
        this.remainderTime = remainderTime;
        this.attachmentPath = attachmentPath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastViewedAt = lastViewedAt;
        this.deletedAt = deletedAt;
    }

    // ====== GETTERS & SETTERS ======
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public LocalDateTime getRemainderTime() {
        return remainderTime;
    }

    public void setRemainderTime(LocalDateTime remainderTime) {
        this.remainderTime = remainderTime;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @NonNull
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NonNull LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getLastViewedAt() {
        return lastViewedAt;
    }

    public void setLastViewedAt(LocalDateTime lastViewedAt) {
        this.lastViewedAt = lastViewedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
