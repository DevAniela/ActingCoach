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
//    public void getSessionDetails(Session session) {
//        if (sessions.contains(session)) {
//            System.out.println("Session with " + session.getActor().getName() + " on " + session.getDateTime() + "");
//        } else {
//            System.out.println("Couldn't find session.");
//        }
//    }
//
//    public void getScheduledSessions() {
//        System.out.println("Scheduled sessions:");
//        for (Session session : sessions) {
//            if (session.getDateTime().isAfter(LocalDateTime.now())) {
//                System.out.println(session.getDateTime() + " with " + session.getActor().getName());
//            }
//        }
//    }
//
//    //METHODS
//    public void evaluateActor(Actor actor, Session session, Evaluation evaluation) {
//
//    }
//
//    public void addFeedback(Session session, Actor actor, String feedback) {
//
//    }
//
//    public void addObservations(Session session, String observations) {
//
//    }
//
//    public void addExercises(String exercise) {
//
//    }
//
//    public void addDialogueLine(String line) {
//
//    }

    @Override
    public void authenticate() {
        System.out.println("Authenticating instructor: " + getName());
    }
}
