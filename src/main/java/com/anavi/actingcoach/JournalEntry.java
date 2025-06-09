package com.anavi.actingcoach;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JournalEntry {

    //ATTRIBUTES
    private int actorId;
    private LocalDateTime dateTime;
    private String title;
    private String content;

    //CONSTRUCTORS
    public JournalEntry() {
        this.dateTime = LocalDateTime.now();
        this.title = "";
        this.content = "";
    }

    public JournalEntry(int actorId, String title, String content) {
        this.actorId = actorId;
        this.dateTime = LocalDateTime.now();
        this.title = title;
        this.content = content;
    }

    //GETTERS/SETTERS
    public int getActorId() {
        return actorId;
    }
    
    public void setActorId(int actorId) {
        this.actorId = actorId;
    }
    
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
