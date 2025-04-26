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
    public List<Session> getSessions() {
        return this.sessions;
    }

    public String getSessionDetails(Session session) {
        if (sessions.contains(session)) {

            StringBuilder sessionInfo = new StringBuilder();

            sessionInfo.append("Session on ").append(session.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))).append(", booked by ").append(session.getActor().getName());

            if (session.isGroupSession()) {

                List<String> otherActors = session.getOtherActors();

                if (otherActors != null && !otherActors.isEmpty()) {
                    sessionInfo.append(", with ").append(String.join(", ", otherActors));
                }
            }

            if (session.isCanceled()) {
                sessionInfo.append(" (CANCELLED)");
            }
            return sessionInfo.toString();
        } else {
            return "Couldn't find session.";
        }
    }

    //METHODS
    public void viewScheduledSessions() {
        if (sessions.isEmpty()) {
            System.out.println("You have no scheduled sessions.");
            return;
        }

        System.out.println("\n=== Your Scheduled Sessions ===");
        for (int i = 0; i < sessions.size(); i++) {

            Session s = sessions.get(i);

            if (s.getDateTime().isAfter(LocalDateTime.now()) && !s.isCanceled()) {

                StringBuilder sessionInfo = new StringBuilder();

                sessionInfo.append((i + 1)).append(". ").append(s.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))).append(", with ").append(s.getActor().getName()).append(".");

                if (s.isGroupSession() && s.getOtherActors() != null && !s.getOtherActors().isEmpty()) {
                    sessionInfo.append(", and ").append(String.join(", ", s.getOtherActors()));
                }
                sessionInfo.append(".");
                System.out.println(sessionInfo);
            }
        }
    }

    public void addSession(Session newSession) {
        sessions.add(newSession);
    }

    public void evaluateActor(Session session, Evaluation evaluation) {
        if (sessions.contains(session)) {
            session.setEvaluation(evaluation);
            System.out.println("Evaluation added for " + session.getActor().getName());
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
