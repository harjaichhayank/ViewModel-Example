package com.example.architectureExample;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int priority;
    private String title;
    private String description;

    Note(int priority, String title, String description) {
        setPriority(priority);
        setTitle(title);
        setDescription(description);
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    int getPriority() {
        return priority;
    }

    private void setPriority(int priority) {
        this.priority = priority;
    }

    String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }
}
