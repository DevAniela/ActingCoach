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
        this.dateTime = LocalDateTime.now();
        this.instructor = new Instructor();
        this.actor = new Actor();
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

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    void setOtherActors(List<String> otherActors) {
        this.otherActors = otherActors;
    }

    void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public String getFeedback(Actor actor) {
        return feedback.get(actor);
    }

    public String getAllFeedback() {
        StringBuilder allFeedback = new StringBuilder();
        for (Map.Entry<Actor, String> entry : feedback.entrySet()) {
            allFeedback.append(entry.getKey().getName())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return allFeedback.toString();
    }

    void setCancelled(boolean b) {
        this.canceled = b;
    }

    void setGroupSession(boolean b) {
        this.isGroupSession = b;
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

    public void modifySessionByInstructor(LocalDateTime newDateTime, Map<Actor, String> newFeedback, String newObservations, Evaluation newEvaluation) {
        if (!canceled && newDateTime.isAfter(LocalDateTime.now())) {
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

    public void addFeedback(Actor actor, String feedback) {
        if (actor != null && feedback != null && !feedback.isBlank()) {
            this.feedback.put(actor, feedback);
            System.out.println("Feedback added for " + actor.getName());
        } else {
            System.out.println("Feedback not valid.");
        }
    }

    public void modifyFeedback(Actor actor, String newFeedback) {
        if (this.feedback.containsKey(actor) && newFeedback != null && !newFeedback.isBlank()) {
            this.feedback.put(actor, newFeedback);
            System.out.println("Feedback modified for " + actor.getName());
        } else {
            System.out.println("No feedback to modify.");
        }
    }

    public void addObservations(String observations) {
        if (observations != null && !observations.isBlank()) {
            this.observations.add(observations);
            System.out.println("Observations added.");
        } else {
            System.out.println("Observations not valid.");
        }
    }
}
