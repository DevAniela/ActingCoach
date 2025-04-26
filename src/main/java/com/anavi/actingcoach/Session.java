/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anavi.actingcoach;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana Vi
 */
public class Session {

    //ATTRIBUTES
    private LocalDateTime dateTime;
    private Instructor instructor;
    private Actor actor;
    private List<String> otherActors;
    private String generalFeedback;
    private Evaluation evaluation;
    private boolean isGroupSession;
    private boolean canceled;

    //CONSTRUCTORS
    public Session() {
        this.dateTime = LocalDateTime.now();
        this.instructor = new Instructor();
        this.actor = new Actor();
        this.otherActors = new ArrayList<>();
        this.generalFeedback = "";
        this.evaluation = new Evaluation();
        this.isGroupSession = false;
        this.canceled = false;
    }

    public Session(LocalDateTime dateTime, Instructor instructor, Actor actor) {
        this.dateTime = dateTime;
        this.instructor = instructor;
        this.actor = actor;
        this.otherActors = new ArrayList<>();
        this.generalFeedback = "";
        this.evaluation = new Evaluation();
        this.isGroupSession = false;
        this.canceled = false;
    }

    public Session(Actor actor, Instructor instructor, LocalDateTime dateTime) {
        this.actor = actor;
        this.instructor = instructor;
        this.dateTime = dateTime;
    }

    //GETTERS/SETTERS
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime newDateTime) {
        if (newDateTime.isAfter(LocalDateTime.now())) {
            this.dateTime = newDateTime;
        } else {
            System.out.println("Not a valid date. Please look to the future and let go of the past.");
        }
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<String> getOtherActors() {
        return this.otherActors;
    }

    public void setOtherActors(List<String> otherActors) {
        this.otherActors = otherActors;
    }

    public Evaluation getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public String getGeneralFeedback() {
        return this.generalFeedback;
    }

    public void setGeneralFeedback(String generalFeedback) {
        this.generalFeedback = generalFeedback;
    }

    public void setGroupSession(boolean b) {
        this.isGroupSession = b;
    }

    public void setCancelled(boolean b) {
        this.canceled = b;
    }

    //METHODS
    public void modifySessionByActor(Instructor newInstructor, List<String> newOtherActors, LocalDateTime newDateTime) {
        if (!canceled && newDateTime.isAfter(LocalDateTime.now())) {
            this.dateTime = newDateTime;
        } else {
            System.out.println("Not a valid date. Please look to the future and let go of the past.");
        }
        if (newInstructor != null) {
            this.instructor = newInstructor;
        }
        if (newOtherActors != null && !newOtherActors.isEmpty()) {
            this.otherActors = newOtherActors;
        }
        System.out.println("Session updated successfully.");
    }

    public void modifySessionByInstructor(LocalDateTime newDateTime, String newGeneralFeedback, Evaluation newEvaluation) {
        if (!canceled && newDateTime.isAfter(LocalDateTime.now())) {
            this.dateTime = newDateTime;
        } else {
            System.out.println("Not a valid date. Please look to the future and let go of the past.");
        }
        if (newGeneralFeedback != null && !newGeneralFeedback.isBlank()) {
            this.generalFeedback = newGeneralFeedback;
        }

        if (newEvaluation != null) {
            this.evaluation = newEvaluation;
        }
    }

    public boolean isGroupSession() {
        return otherActors != null && !otherActors.isEmpty();
    }

    public void cancelSession() {
        if (!canceled) {
            this.canceled = true;
            System.out.println("Session canceled.");
        } else {
            System.out.println("Session is already canceled.");
        }
    }

    public boolean isCanceled() {
        return canceled;
    }
}
