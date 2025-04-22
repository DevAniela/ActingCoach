/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anavi.actingcoach;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public Actor(String name, String email, String password, String role) {
        super(name, email, password, role);
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
        if (sheet != null) {
            characterSheets.add(sheet);
            System.out.println("Character sheet added for " + sheet.getCharacterName());
        } else {
            System.out.println("Not a valid character sheet.");
        }
    }

    public void createAndAddCharacterSheet(String characterName, List<String> personalityTraits, List<String> physicalTraits, String background, String motivations, String notes) {
        CharacterSheet sheet = new CharacterSheet(this, characterName);
        sheet.setCharacterName(characterName);
        sheet.setPersonalityTraits(personalityTraits);
        sheet.setPhysicalTraits(physicalTraits);
        sheet.setBackground(background);
        sheet.setMotivations(motivations);
        sheet.setNotes(notes);
        characterSheets.add(sheet);
    }

    public void viewCharacterSheets() {
        if (characterSheets.isEmpty()) {
            System.out.println("No character sheets available.");
        } else {
            System.out.println("Character sheets: ");
            for (int i = 0; i < characterSheets.size(); i++) {
                System.out.println((i + 1) + ". " + characterSheets.get(i).getCharacterName());
            }
        }
    }

    public void updateCharacterSheet(int index, String newCharacterName, List<String> newPersonalityTraits, List<String> newPhysicalTraits, String newBackground, String newMotivations, String newNotes) {
        if (index > 0 && index < characterSheets.size()) {
            CharacterSheet sheet = characterSheets.get(index);
            sheet.setCharacterName(newCharacterName);
            sheet.setPersonalityTraits(newPersonalityTraits);
            sheet.setPhysicalTraits(newPhysicalTraits);
            sheet.setBackground(newBackground);
            sheet.setMotivations(newMotivations);
            sheet.setNotes(newNotes);
        } else {
            System.out.println("Not a valid character sheet index");
        }
    }

    public void updateCharacterName(int index, String newCharacterName) {
        if (index >= 0 && index < characterSheets.size()) {
            characterSheets.get(index).setCharacterName(newCharacterName);
            System.out.println("Character name updated.");
        } else {
            System.out.println("Not a valid character sheet index.");
        }
    }

    public void updateCharacterPersonality(int index, List newPersonalityTraits) {
        if (index >= 0 && index < characterSheets.size()) {
            characterSheets.get(index).setPersonalityTraits(newPersonalityTraits);
            System.out.println("Character personality traits updated.");
        } else {
            System.out.println("Not a valid character sheet index.");
        }
    }

    public void updateCharacterPhysical(int index, List newPhysicalTraits) {
        if (index >= 0 && index < characterSheets.size()) {
            characterSheets.get(index).setPhysicalTraits(newPhysicalTraits);
            System.out.println("Character physical traits updated.");
        } else {
            System.out.println("Not a valid character sheet index.");
        }
    }

    public void updateCharacterBackground(int index, String newBackground) {
        if (index >= 0 && index < characterSheets.size()) {
            characterSheets.get(index).setBackground(newBackground);
            System.out.println("Character background updated.");
        } else {
            System.out.println("Not a valid character sheet index.");
        }
    }

    public void updateCharacterMotivations(int index, String newMotivations) {
        if (index >= 0 && index < characterSheets.size()) {
            characterSheets.get(index).setMotivations(newMotivations);
            System.out.println("Character motivations updated.");
        } else {
            System.out.println("Not a valid character sheet index.");
        }
    }

    public void updateCharacterNotes(int index, String newNotes) {
        if (index >= 0 && index < characterSheets.size()) {
            characterSheets.get(index).setNotes(newNotes);
            System.out.println("Character notes updated.");
        } else {
            System.out.println("Not a valid character sheet index.");
        }
    }

    public void deleteCharacterSheet(int index) {
        if (index >= 0 && index < characterSheets.size()) {
            characterSheets.remove(index);
        } else {
            System.out.println("Not a valid character sheet index.");
        }
    }

    public void updateCharacterSheet(int index, CharacterSheet updatedSheet) {
        if (index >= 0 && index < characterSheets.size()) {
            characterSheets.set(index, updatedSheet);
            System.out.println("Character sheet updated.");
        } else {
            System.out.println("Not a valid character sheet index.");
        }
    }

    //for sessions
    public void viewSessions() {
        if (sessions.isEmpty()) {
            System.out.println("You have no sessions booked.");
            return;
        }

        System.out.println("\n=== Your Sessions ===");
        for (int i = 0; i < sessions.size(); i++) {
            Session s = sessions.get(i);
            System.out.println((i + 1) + ". " + s.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + " with " + s.getInstructor().getName() + (s.isCanceled() ? " (Cancelled)" : ""));
        }
    }

    public void bookSession(Instructor instructor, LocalDateTime dateTime, boolean isGroupSession, List<String> otherActors) {
        for (Session session : sessions) {
            if (!session.isCanceled()
                    && !session.getDateTime().plusHours(1).isBefore(dateTime)
                    && !session.getDateTime().minusHours(1).isAfter(dateTime)) {
                System.out.println("You already have a session overlapping at this time.");
                return;
            }
        }
        Session newSession = new Session(this, instructor, dateTime);
        newSession.setGroupSession(isGroupSession);
        if (isGroupSession && otherActors != null && !otherActors.isEmpty()) {
            newSession.setOtherActors(otherActors);
            System.out.println("Group session booked with " + instructor.getName() + " on " + dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + " with: " + String.join(", ", otherActors));
        } else {
            System.out.println("Solo session booked with " + instructor.getName() + " on " + dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        }
        sessions.add(newSession);
        instructor.addSession(newSession);
    }

    public void modifySession(Session session, LocalDateTime newDateTime) {
        if (sessions.contains(session)) {
            session.setDateTime(newDateTime);
            System.out.println("Session updated to " + newDateTime);
        } else {
            System.out.println("Couldn't find session.");
        }
    }

    public void modifySession(Session session, LocalDateTime newDateTime, Instructor newInstructor, boolean isGroupSession, List<String> otherActors) {
        if (sessions.contains(session)) {
            session.setDateTime(newDateTime);
            session.setInstructor(newInstructor);
            session.setGroupSession(isGroupSession);
            session.setOtherActors(otherActors);
            System.out.println("Session updated successfully.");
        } else {
            System.out.println("Session not found.");
        }
    }

    public void cancelSession(Session session) {
        if (sessions.contains(session)) {
            session.setCancelled(true);
            System.out.println("Session cancelled.");
        } else {
            System.out.println("Couldn't find session.");
        }
    }

    void cancelSession(int index) {
        if (index < 1 || index > sessions.size()) {
            System.out.println("Not a valid index.");
            return;
        }

        Session session = sessions.get(index - 1);
        if (!session.isCanceled()) {
            session.cancelSession();
        }
    }

    void viewJournal() {
        if (journalEntries.isEmpty()) {
            System.out.println("No journal entries yet.");
            return;
        }

        System.out.println("\n=== Journal Entries ===");
        for (int i = 0; i < journalEntries.size(); i++) {
            System.out.println(i + 1 + ". " + journalEntries.get(i).getTitle() + " - " + journalEntries.get(i).getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        }
    }

    public void addJournalEntry(String title, String content) {
        journalEntries.add(new JournalEntry(title, content));
        System.out.println("Journal entry added.");
    }

    public void modifyJournalEntry(int index, String newTitle, String newContent) {
        if (index < 0 || index >= journalEntries.size()) {
            System.out.println("Not a valid index.");
            return;
        }

        JournalEntry entry = journalEntries.get(index);
        entry.setTitle(newTitle);
        entry.setContent(newContent);
        System.out.println("Journal entry updated.");
    }

    public void deleteJournalEntry(int index) {
        if (index < 0 || index >= journalEntries.size()) {
            System.out.println("Not a valid index.");
            return;
        }

        journalEntries.remove(index);
        System.out.println("Journal entry deleted.");
    }

    @Override
    public void authenticate() {
        System.out.println("Authenticating actor: " + getName() + " with email: " + getEmail());
    }
}
