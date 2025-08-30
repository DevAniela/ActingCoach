/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anavi.actingcoach;

/**
 *
 * @author Ana Vi
 */
public class Evaluation {

    //ATTRIBUTES
    private int id;
    private Session session;
    private int expressiveness;
    private int diction;
    private int emotion;
    private String notes;

    //CONSTRUCTORS
    public Evaluation() {
        this.id = -1;
        this.expressiveness = 0;
        this.diction = 0;
        this.emotion = 0;
        this.notes = "";
    }

    public Evaluation(int id, Session session, int expressiveness, int diction, int emotion, String notes) {
        this.id = id;
        this.session = session;
        this.expressiveness = expressiveness;
        this.diction = diction;
        this.emotion = emotion;
        this.notes = notes;
    }

    public Evaluation(Session session, int expressiveness, int diction, int emotion, String notes) {
        this(-1, session, expressiveness, diction, emotion, notes);
    }

    //GETTERS/SETTERS
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScores(int expressiveness, int diction, int emotion) {
        this.expressiveness = expressiveness;
        this.diction = diction;
        this.emotion = emotion;
    }

    public int getExpressiveness() {
        return this.expressiveness;
    }

    public int getDiction() {
        return this.diction;
    }

    public int getEmotion() {
        return this.emotion;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return this.session;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    //METHODS
    public double calculateFinalScore() {
        return (this.expressiveness + this.diction + this.emotion) / 3.0;
    }
}
