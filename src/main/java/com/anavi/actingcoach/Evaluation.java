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
    private Session session;
    private int expressiveness;
    private int diction;
    private int emotion;
    private String notes;

    //CONSTRUCTORS
    public Evaluation() {
        this.session = new Session();
        this.expressiveness = 0;
        this.diction = 0;
        this.emotion = 0;
        this.notes = "";
    }

    public Evaluation(Session session, int expressiveness, int diction, int emotion, String notes) {
        this.session = new Session();
        this.expressiveness = expressiveness;
        this.diction = diction;
        this.emotion = emotion;
        this.notes = notes;
    }

    //GETTERS/SETTERS
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
