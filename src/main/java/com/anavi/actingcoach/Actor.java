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
    private List<Improvisation> improvisations;
    private List<Invoice> invoiceHistory;
    private int pointsEarned;

    //CONSTRUCTORS
    public Actor() {
        super();
        this.characterSheets = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.journalEntries = new ArrayList<>();
        this.improvisations = new ArrayList<>();
        this.invoiceHistory = new ArrayList<>();
        this.pointsEarned = 0;
    }

    public Actor(String name, String email, String password, String role) {
        super(name, email, password, role);
        this.characterSheets = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.journalEntries = new ArrayList<>();
        this.improvisations = new ArrayList<>();
        this.invoiceHistory = new ArrayList<>();
        this.pointsEarned = 0;
    }

    public Actor(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
        this.characterSheets = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.journalEntries = new ArrayList<>();
        this.improvisations = new ArrayList<>();
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

    public List<Improvisation> getImprovisations() {
        return improvisations;
    }

    public void setImprovisations(List<Improvisation> improvisations) {
        this.improvisations = improvisations;
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

    public void createAndAddCharacterSheet(String characterName, List<String> personalityTraits, List<String> physicalTraits, String background, String motivation, String notes) {
        CharacterSheet sheet = new CharacterSheet(this, characterName);
        sheet.setCharacterName(characterName);
        sheet.setPersonalityTraits(personalityTraits);
        sheet.setPhysicalTraits(physicalTraits);
        sheet.setBackground(background);
        sheet.setMotivation(motivation);
        sheet.setNotes(notes);
        characterSheets.add(sheet);
    }

    public void viewCharacterSheets() {
        if (characterSheets.isEmpty()) {
            System.out.println("No character sheets available.");
        } else {
            System.out.println("\n=== Your character sheets ===");
            for (int i = 0; i < characterSheets.size(); i++) {
                System.out.println((i + 1) + ". " + characterSheets.get(i).getCharacterName());
            }
        }
    }

    // TODO: Currently unused. Keep for potential future GUI-based character editing
    public void updateCharacterSheet(int index, String newCharacterName, List<String> newPersonalityTraits, List<String> newPhysicalTraits, String newBackground, String newMotivation, String newNotes) {
        if (index >= 0 && index < characterSheets.size()) {
            CharacterSheet sheet = characterSheets.get(index);
            sheet.setCharacterName(newCharacterName);
            sheet.setPersonalityTraits(newPersonalityTraits);
            sheet.setPhysicalTraits(newPhysicalTraits);
            sheet.setBackground(newBackground);
            sheet.setMotivation(newMotivation);
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

    public void updateCharacterPersonality(int index, List<String> newPersonalityTraits) {
        if (index >= 0 && index < characterSheets.size()) {
            characterSheets.get(index).setPersonalityTraits(newPersonalityTraits);
            System.out.println("Character personality traits updated.");
        } else {
            System.out.println("Not a valid character sheet index.");
        }
    }

    public void updateCharacterPhysical(int index, List<String> newPhysicalTraits) {
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

    public void updateCharacterMotivation(int index, String newMotivation) {
        if (index >= 0 && index < characterSheets.size()) {
            characterSheets.get(index).setMotivation(newMotivation);
            System.out.println("Character motivation updated.");
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
            System.out.println("Character sheet " + index + " removed.");
        } else {
            System.out.println("Not a valid character sheet index.");
        }
    }

    public void updateCharacterSheet(int index, CharacterSheet updatedSheet) {
        if (index >= 0 && index < characterSheets.size()) {
            characterSheets.set(index, updatedSheet);
            System.out.println("Character sheet for " + updatedSheet.getCharacterName() + " updated.");
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
            System.out.println((i + 1) + ". " + s.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + " with " + s.getInstructor().getName() + (s.isCanceled() ? " [CANCELED]" : ""));
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
        Session newSession = new Session(dateTime, instructor, this);
        newSession.setGroupSession(isGroupSession);
        if (isGroupSession && otherActors != null && !otherActors.isEmpty()) {
            newSession.setOtherActors(otherActors);
            System.out.println("Group session booked with instructor " + instructor.getName() + ", on " + dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + ", with: " + String.join(", ", otherActors) + ".");
        } else {
            System.out.println("Solo session booked with instructor " + instructor.getName() + ", on " + dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + ".");
        }
        sessions.add(newSession);
        instructor.addSession(newSession);
    }

    public void cancelSession(Session session) {
        if (sessions.contains(session)) {
            session.setCanceled(true);
            System.out.println("Session on " + session.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + " canceled.");
        } else {
            System.out.println("Couldn't find session.");
        }
    }

    public void cancelSession(int index) {
        if (index < 1 || index > sessions.size()) {
            System.out.println("Not a valid index.");
            return;
        }

        Session session = sessions.get(index - 1);
        if (!session.isCanceled()) {
            session.cancelSession();
            System.out.println("Session " + index + " canceled.");
        }
    }

    //for journal
    public void viewJournal() {
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
        journalEntries.add(new JournalEntry(getId(), title, content));
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

    //for improvisation
    public void startImprovisation() {
        String promptStart = PromptGenerator.getRandomStartPrompt();
        String promptEnd = PromptGenerator.getRandomEndPrompt();

        Improvisation improv = new Improvisation(promptStart, promptEnd);

        improvisations.add(improv);
        System.out.println("\n=== Improvisation started ===");
        System.out.println("Start with: \"" + promptStart + "\"");
        System.out.println("End with: \"" + promptEnd + "\"");
    }

    public void endImprovisation(int index) {
        if (index < 0 || index >= improvisations.size()) {
            System.out.println("Not a valid index.");
            return;
        }

        Improvisation improv = improvisations.get(index);
        improv.setEndDateTime(LocalDateTime.now());
        System.out.println("Improvisation completed.");
    }

    public void endImprovisation() {
        if (!improvisations.isEmpty()) {
            improvisations.get(improvisations.size() - 1).setEndDateTime(LocalDateTime.now());
            System.out.println("Improv completed.");
        }
    }

    public void viewImprovisations() {
        if (improvisations.isEmpty()) {
            System.out.println("No improv exercises found.");
            return;
        }

        System.out.println("\n=== Improvisation Exercises ===");
        for (int i = 0; i < improvisations.size(); i++) {
            Improvisation improv = improvisations.get(i);
            System.out.println((i + 1) + ". Start: " + improv.getStartDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + " | Line 1: \"" + improv.getPromptStart() + "\"" + " | Line 2: \"" + improv.getPromptEnd() + "\"" + improv.getDurationFormatted());
        }
    }

    @Override
    public void authenticate() {
        System.out.println("Authenticating actor: " + getName() + " with email: " + getEmail());
    }
}
