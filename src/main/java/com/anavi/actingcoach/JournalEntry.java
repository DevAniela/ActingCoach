package com.anavi.actingcoach;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JournalEntry {

    //ATTRIBUTES
    private LocalDateTime dateTime;
    private String title;
    private String content;

    //CONSTRUCTORS
    public JournalEntry() {
        this.dateTime = LocalDateTime.now();
        this.title = "";
        this.content = "";
    }

    public JournalEntry(String title, String content) {
        this.dateTime = LocalDateTime.now();
        this.title = title;
        this.content = content;
    }

    //GETTERS/SETTERS
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //METHODS
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "[" + dateTime.format(formatter) + "] " + title + ": \n" + content;
    }
}
