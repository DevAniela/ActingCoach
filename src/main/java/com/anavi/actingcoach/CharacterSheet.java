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
    private Actor actor;
    private String characterName;
    private List<String> personalityTraits;
    private List<String> physicalTraits;
    private String background;
    private String motivations;
    private String notes;

    //CONSTRUCTORS
    public CharacterSheet() {
        this.actor = null;
        this.characterName = "";
        this.personalityTraits = new ArrayList<>();
        this.physicalTraits = new ArrayList<>();
        this.background = "";
        this.motivations = "";
        this.notes = "";
    }

    public CharacterSheet(Actor actor, String characterName) {
        this.actor = actor;
        this.characterName = characterName;
        this.personalityTraits = new ArrayList<>();
        this.physicalTraits = new ArrayList<>();
        this.background = "";
        this.motivations = "";
        this.notes = "";
    }

    public CharacterSheet(Actor actor, String characterName, List<String> personalityTraits, List<String> physicalTraits, String background, String motivations, String notes) {
        this.actor = actor;
        this.characterName = characterName;
        this.personalityTraits = personalityTraits;
        this.physicalTraits = physicalTraits;
        this.background = background;
        this.motivations = motivations;
        this.notes = notes;
    }

    //GETTERS/SETTERS
    public String getCharacterName() {
        return this.characterName;
    }

    public void setCharacterName(String newCharacterName) {
        this.characterName = newCharacterName;
    }

    public void setBackground(String newBackground) {
        this.background = newBackground;
    }

    public void setPersonalityTraits(List<String> newPersonalityTraits) {
        this.personalityTraits = newPersonalityTraits;
    }

    public void setPhysicalTraits(List<String> newPhysicalTraits) {
        this.physicalTraits = newPhysicalTraits;
    }

    public void setMotivations(String newMotivations) {
        this.motivations = newMotivations;
    }

    public void setNotes(String newNotes) {
        this.notes = newNotes;
    }
}
