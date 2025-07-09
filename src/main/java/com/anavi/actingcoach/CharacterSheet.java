/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anavi.actingcoach;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana Vi
 */
public class CharacterSheet {

    //ATTRIBUTES
    private int id;
    private Actor actor;
    private String characterName;
    private List<String> personalityTraits;
    private List<String> physicalTraits;
    private String background;
    private String motivation;
    private String notes;

    //CONSTRUCTORS
    public CharacterSheet() {
        this.actor = null;
        this.characterName = "";
        this.personalityTraits = new ArrayList<>();
        this.physicalTraits = new ArrayList<>();
        this.background = "";
        this.motivation = "";
        this.notes = "";
    }

    public CharacterSheet(Actor actor, String characterName) {
        this.actor = actor;
        this.characterName = characterName;
        this.personalityTraits = new ArrayList<>();
        this.physicalTraits = new ArrayList<>();
        this.background = "";
        this.motivation = "";
        this.notes = "";
    }

    public CharacterSheet(Actor actor, String characterName, List<String> personalityTraits, List<String> physicalTraits, String background, String motivation, String notes) {
        this.actor = actor;
        this.characterName = characterName;
        this.personalityTraits = personalityTraits;
        this.physicalTraits = physicalTraits;
        this.background = background;
        this.motivation = motivation;
        this.notes = notes;
    }

    //GETTERS/SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacterName() {
        return this.characterName;
    }

    public void setCharacterName(String newCharacterName) {
        this.characterName = newCharacterName;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String newBackground) {
        this.background = newBackground;
    }

    public List<String> getPersonalityTraits() {
        return personalityTraits;
    }

    public void setPersonalityTraits(List<String> newPersonalityTraits) {
        this.personalityTraits = newPersonalityTraits;
    }

    public List<String> getPhysicalTraits() {
        return physicalTraits;
    }

    public void setPhysicalTraits(List<String> newPhysicalTraits) {
        this.physicalTraits = newPhysicalTraits;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String newMotivation) {
        this.motivation = newMotivation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String newNotes) {
        this.notes = newNotes;
    }

    public Actor getActor() {
        return actor;
    }
}
