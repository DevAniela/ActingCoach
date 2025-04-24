/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anavi.actingcoach;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public Instructor(String name, String email, String password, String role) {
        super(name, email, password, role);
        this.sessions = new ArrayList<>();
    }

    //GETTERS/SETTERS
    public String getSessionDetails(Session session) {
        if (sessions.contains(session)) {
            if (session.isCanceled()) {
                return "Session with " + session.getActor().getName() + " on " + session.getDateTime() + " is canceled.";
            }
            return "Session with " + session.getActor().getName() + " is on " + session.getDateTime();
        } else {
            return "Couldn't find session.";
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
    public void addSession(Session newSession) {
        sessions.add(newSession);
    }

    public void evaluateActor(Actor actor, Session session, Evaluation evaluation) {
        if (sessions.contains(session) && session.getActor().equals(actor)) {
            session.addEvaluation(actor, evaluation);
            System.out.println("Evaluation added for " + actor.getName());
        } else {
            System.out.println("Couldn't find session or actor.");
        }
    }

    public void addGeneralFeedback(Session session, String generalFeedback) {
        if (sessions.contains(session)) {
            session.setGeneralFeedback(generalFeedback);

            StringBuilder feedbackMessage = new StringBuilder();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            feedbackMessage.append("Feedback added for the session on: ").append(session.getDateTime().format(formatter)).append(" with ").append(session.getActor().getName());

            if (session.isGroupSession()) {
                List<String> others = session.getOtherActors();
                if (others != null && !others.isEmpty()) {
                    feedbackMessage.append(", and: ");
                    feedbackMessage.append(String.join(", ", others));
                    feedbackMessage.append(".");
                }
            }
            System.out.println(feedbackMessage);
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
        System.out.println("Authenticating instructor: " + getName() + " with email: " + getEmail());
    }
}
