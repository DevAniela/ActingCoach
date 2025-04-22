package com.anavi.actingcoach;

import java.time.LocalDateTime;

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
        return "[" + dateTime + "] " + title + ": \n" + content;
    }    
}
