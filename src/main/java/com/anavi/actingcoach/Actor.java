/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anavi.actingcoach;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Ana Vi
 */
public class Actor extends User {

    //ATTRIBUTES
    private List<CharacterSheet> characterSheets;
    private List<Session> sessions;
    private List<JournalEntry> journalEntries;
    private List<Invoice> invoiceHistory;
    private int pointsEarned;

    //CONSTRUCTORS
    public Actor() {
        super();
        this.characterSheets = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.journalEntries = new ArrayList<>();
        this.invoiceHistory = new ArrayList<>();
        this.pointsEarned = 0;
    }
    public Actor(String name, String email, String password) {
        super(name, email, password);
        this.characterSheets = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.journalEntries = new ArrayList<>();
        this.invoiceHistory = new ArrayList<>();
        this.pointsEarned = 0;
    }

    //GETTERS/SETTERS
    public List<CharacterSheet> getCharacterSheets() {
        return characterSheets;
    }
    public void setCharacterSheets(List<CharacterSheet> characterSheets) {
        this.characterSheets = characterSheets;
    }
    public List<Session> getSessions() {
        return sessions;
    }
    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
    public List<JournalEntry> getJournalEntries() {
        return journalEntries;
    }
    public void setJournalEntries(List<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }
    public List<Invoice> getInvoiceHistory() {
        return invoiceHistory;
    }
    public void setInvoiceHistory(List<Invoice> invoiceHistory) {
        this.invoiceHistory = invoiceHistory;
    }
    public int getPointsEarned() {
        return pointsEarned;
    }
    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    //METHODS
    //for character sheets
    public void addCharacterSheet(CharacterSheet sheet) {
        characterSheets.add(sheet);
        System.out.println("Character sheet added: " + sheet.getName());
    }
    public void viewCharacterSheets() {
        if (characterSheets.isEmpty()) {
            System.out.println("No character sheets available.");
        } else {
            System.out.println("Character sheets: ");
            for (int i = 0; i < characterSheets.size(); i++) {
                System.out.println((i + 1) + ". " + characterSheets.get(i).getName());
            }
        }
    }
    public void updateCharacterSheet(int index, String newDetails) {
        if(index>=0 && index<characterSheets.size()) {
            characterSheets.get(index).setDetails(newDetails);
            System.out.println("Character sheet updated.");
        } else {
            System.out.println("Not a valid character sheet index.");
        }
    }
    public void deleteCharacterSheet(int index) {
        if(index>=0 && index<characterSheets.size()) {
            characterSheets.remove(index);
        } else {
            System.out.println("Not a valid character sheet index.");
        }
    }
    
    //for sessions
    public void scheduleSession(Instructor instructor, LocalDateTime dateTime) {
        Session newSession = new Session(instructor, this, dateTime);
        sessions.add(newSession);
        System.out.println("Session scheduled with " + instructor.getName() + " on " + dateTime);
    }
    public void modifySession(Session session, LocalDateTime newDate) {
        if(sessions.contains(session)) {
            session.setDate(newDate);
            System.out.println("Session updated to " + newDate);
        } else {
            System.out.println("Couldn't find session.");
        }
    }
    public void cancelSession(Session session) {
        if(sessions.contains(session)) {
            session.setCancelled(true);
            System.out.println("Session cancelled.");
        } else {
            System.out.println("Couldn't find session.");
        }
    }
    
    @Override
    public void authenticate() {
        System.out.println("Authenticating actor: " + getName());
    }
}
