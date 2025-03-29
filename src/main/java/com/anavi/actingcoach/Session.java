/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anavi.actingcoach;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<Actor, String> feedback;
    private List<String> observations;
    private Evaluation evaluation;
    private boolean isGroupSession;
    private boolean canceled;

    //CONSTRUCTORS
    public Session() {
        this.dateTime = dateTime;
        this.instructor = instructor;
        this.actor = actor;
        this.otherActors = new ArrayList<>();
        this.feedback = new HashMap<>();
        this.observations = new ArrayList<>();
        this.evaluation = new Evaluation();
        this.isGroupSession = false;
        this.canceled = false;
    }

    public Session(LocalDateTime dateTime, Instructor instructor, Actor actor, List<String> otherActors, Map<Actor, String> feedback, List<String> observations, Evaluation evaluation) {
        this.dateTime = dateTime;
        this.instructor = instructor;
        this.actor = actor;
        this.otherActors = otherActors;
        this.feedback = feedback;
        this.observations = observations;
        this.evaluation = evaluation;
        this.isGroupSession = !otherActors.isEmpty();
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

    public Actor setActor(Actor actor) {
        return actor;
    }

    void setGroupSession(boolean b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void setOtherActors(List<String> otherActors) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void setDate(LocalDateTime newDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void setCancelled(boolean b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void setEvaluation(Evaluation evaluation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Actor getFeedback(Actor actor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAllFeedback() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //METHODS
    public void modifySessionByActor(Instructor newInstructor, List<String> newOtherActors, LocalDateTime newDateTime) {
        if (newDateTime.isAfter(LocalDateTime.now())) {
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

    public void modifySessionByInstructor(LocalDateTime newDateTime, Map<Actor, String> newFeedback, String newObservations, Evaluation newEvaluation) {
        if (newDateTime.isAfter(LocalDateTime.now())) {
            this.dateTime = newDateTime;
        } else {
            System.out.println("Not a valid date. Please look to the future and let go of the past.");
        }
        if (newFeedback != null) {
            this.feedback = newFeedback;
        }
        if (newObservations != null) {
            this.observations.add(newObservations);
        }
        if (newEvaluation != null) {
            this.evaluation = newEvaluation;
        }
    }

    public boolean isGroupSession() {
        return otherActors != null && !otherActors.isEmpty();
    }

    public void cancelSession() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean canceled() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addFeedback(Actor actor, String feedback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void modifyFeedback() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void addObservations(String observations) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
