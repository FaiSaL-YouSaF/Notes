package com.faisalyousaf777.notes.commons.entity;

import java.time.LocalDateTime;

public class Note {

    private int id;
    private String title;
    private String content;
    private boolean isFavorite;
    private String category;
    private String colorCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Note(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.content = builder.content;
        this.isFavorite = builder.isFavorite;
        this.category = builder.category;
        this.colorCode = builder.colorCode;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public String getCategory() {
        return category;
    }

    public String getColorCode() {
        return colorCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static class Builder {
        private int id;
        private String title;
        private String content;
        private boolean isFavorite;
        private String category;
        private String colorCode;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setIsFavorite(boolean isFavorite) {
            this.isFavorite = isFavorite;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setColorCode(String colorCode) {
            this.colorCode = colorCode;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Note build() {
            return new Note(this);
        }
    }

}
