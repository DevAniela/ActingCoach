/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anavi.actingcoach;

import java.time.LocalDateTime;

/**
 *
 * @author Ana Vi
 */
public class Improvisation {

    //ATTRIBUTES
    private int actorId;
    private String promptStart;
    private String promptEnd;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    //CONSTRUCTORS
    public Improvisation(String promptStart, String promptEnd) {
        this.promptStart = promptStart;
        this.promptEnd = promptEnd;
        this.startDateTime = LocalDateTime.now();
        this.actorId = -1; // placeholder
    }
    
        public Improvisation(String promptStart, String promptEnd, int actorId) {
        this.promptStart = promptStart;
        this.promptEnd = promptEnd;
        this.startDateTime = LocalDateTime.now();
        this.actorId = actorId;
    }

    //GETTERS/SETTERS
    public int getActorId() {
        return this.actorId;
    }

    public String getPromptStart() {
        return promptStart;
    }

    public void setPromptStart(String promptStart) {
        this.promptStart = promptStart;
    }

    public String getPromptEnd() {
        return promptEnd;
    }

    public void setPromptEnd(String promptEnd) {
        this.promptEnd = promptEnd;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    //METHODS
    public String getDurationFormatted() {
        if (startDateTime != null && endDateTime != null) {
            long seconds = java.time.Duration.between(startDateTime, endDateTime).getSeconds();
            long minutes = seconds / 60;
            long remainingSeconds = seconds % 60;
            return String.format("%d min %d sec", minutes, remainingSeconds);
        }
        return "Improv not completed yet.";
    }
}
