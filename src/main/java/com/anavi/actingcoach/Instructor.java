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
public class Instructor extends User {

    //ATTRIBUTES
    private List<Session> sessions;

    //CONSTRUCTORS
    public Instructor() {
        super();
        this.sessions = new ArrayList<>();
    }

    public Instructor(String name, String email, String password) {
        super(name, email, password);
        this.sessions = new ArrayList<>();
    }

    //GETTERS/SETTERS
    public void getSessionDetails(Session session) {
        if (sessions.contains(session)) {
            System.out.println("Session with " + session.getActor().getName() + " on " + session.getDateTime() + "");
        } else {
            System.out.println("Couldn't find session.");
        }
    }

    public void getScheduledSessions() {
        System.out.println("Scheduled sessions:");
        for (Session session : sessions) {
            if (session.getDateTime().isAfter(LocalDateTime.now())) {
                System.out.println(session.getDateTime() + " with " + session.getActor().getName());
            }
        }
    }

    //METHODS
    void addSession(Session newSession) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void evaluateActor(Actor actor, Session session, Evaluation evaluation) {
        if (sessions.contains(session) && session.getActor().equals(actor)) {
            session.setEvaluation(evaluation);
            System.out.println("Evaluation added for " + actor.getName());
        } else {
            System.out.println("Couldn't find session or actor.");
        }
    }

    public void addFeedback(Session session, Actor actor, String feedback) {
        if (sessions.contains(session) && session.getActor().equals(actor)) {
            session.addFeedback(actor, feedback);
            System.out.println("Evaluation added for " + actor.getName());
        } else {
            System.out.println("Couldn't find session or actor.");
        }
    }

    public void addObservations(Session session, String observations) {
        if (sessions.contains(session)) {
            session.addObservations(observations);
            System.out.println("Observations added to session " + session.getDateTime());
        } else {
            System.out.println("Couldn't find session.");
        }
    }

    public void addExercise(String exercise) {
        System.out.println("Exercise added: " + exercise);
        //TO DO: add Improvisation system, and database to save exercises
    }

    public void addDialogueLine(String line) {
        System.out.println("Dialogue line added: " + line);
        //TO DO: add Improvisation system, and database to save lines
    }

    @Override
    public void authenticate() {
        System.out.println("Authenticating instructor: " + getName());
    }
}
