/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anavi.actingcoach;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ana Vi
 */
public class Evaluation {

    //ATTRIBUTES
    private int expressiveness;
    private int diction;
    private int emotion;
    private String notes;
    private Map<Actor, String> observations;

    //CONSTRUCTORS
    public Evaluation() {
        this.expressiveness = 0;
        this.diction = 0;
        this.emotion = 0;
        this.notes = "";
        this.observations = new HashMap<>();
    }

    public Evaluation(int expressiveness, int diction, int emotion) {
        this.expressiveness = expressiveness;
        this.diction = diction;
        this.emotion = emotion;
        this.notes = "";
        this.observations = new HashMap<>();
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

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getObservations(Actor actor) {
        return observations.getOrDefault(actor, "No observation found.");
    }

    //METHODS
    public void addObservation(Actor actor, String observation) {
        if (actor != null && observation != null && !observation.isBlank()) {
            this.observations.put(actor, observation);
            System.out.println("Observations added for " + actor.getName());
        } else {
            System.out.println("Observation not valid.");
        }
    }

    public double calculateFinalScore() {
        return (this.expressiveness + this.diction + this.emotion) / 3.0;
    }
}
