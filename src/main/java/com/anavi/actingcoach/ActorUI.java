package com.anavi.actingcoach;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActorUI extends ActingCoachUI {

    private Actor actor;
    private Scanner scanner;

    public ActorUI(AuthenticationSystem authSystem, Actor actor) {
        super(authSystem);
        this.actor = actor;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Character Sheets");
        System.out.println("2. Sessions");
        System.out.println("3. Journal");
        System.out.println("4. Improvisation");
        System.out.println("5. Evaluation");
        System.out.println("6. Invoice");
        System.out.println("0. Exit");
    }

    @Override
    public void handleInput() {

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageCharacterSheets();
                    break;
                case 2:
                    manageSessions();
                    break;
                case 3:
                    manageJournal();
                    break;
                case 4:
                    manageImprovisation();
                    break;
                case 5:
                    manageEvaluation();
                    break;
                case 6:
                    manageInvoice();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Not a valid choice.");
                    break;
            }
        }
    }

    public void manageCharacterSheets() {

        boolean submenu = true;

        while (submenu) {
            System.out.println("\n=== Character Sheets Menu ===");
            System.out.println("1. View Character Sheets");
            System.out.println("2. Create New Character Sheet");
            System.out.println("3. Update Character Sheet");
            System.out.println("4. Delete Character Sheet");
            System.out.println("0. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    actor.viewCharacterSheets();
                    break;
                case 2:
                    promptCharacterSheetDetails();
                    break;
                case 3:
                    updateCharacterSheet();
                    break;
                case 4:
                    System.out.println("Enter index of the character sheet you want to delete: ");
                    int indexToDelete = scanner.nextInt();
                    scanner.nextLine();
                    actor.deleteCharacterSheet(indexToDelete - 1);
                    break;
                case 0:
                    submenu = false;
                    break;
                default:
                    System.out.println("Not a valid option");
            }
        }
    }

    public void promptCharacterSheetDetails() {
        System.out.print("Enter character's name: ");
        String name = scanner.nextLine();

        List<String> personality = new ArrayList<>();
        System.out.print("Enter character's personality traits (comma separated): ");
        String[] pTraits = scanner.nextLine().split(",");
        for (String trait : pTraits) {
            personality.add(trait.trim());
        }

        List<String> physical = new ArrayList<>();
        System.out.println("Enter character's physical traits (comma separated): ");
        String[] phTraits = scanner.nextLine().split(",");
        for (String trait : phTraits) {
            physical.add(trait.trim());
        }

        System.out.print("Enter character's background: ");
        String background = scanner.nextLine();

        System.out.print("Enter character's motivations: ");
        String motivations = scanner.nextLine();

        System.out.print("Enter character's notes: ");
        String notes = scanner.nextLine();

        actor.createAndAddCharacterSheet(name, personality, physical, background, motivations, notes);
    }

    public void updateCharacterSheet() {
        actor.viewCharacterSheets();

        System.out.print("Enter the index of the character sheet you want to update: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEnter new details for the character sheet:");
        System.out.print("Enter character's name: ");
        String name = scanner.nextLine();

        List<String> personality = new ArrayList<>();
        System.out.print("Enter character's personality traits (comma separated): ");
        String[] pTraits = scanner.nextLine().split(",");
        for (String trait : pTraits) {
            personality.add(trait.trim());
        }

        List<String> physical = new ArrayList<>();
        System.out.println("Enter character's physical traits (comma separated): ");
        String[] phTraits = scanner.nextLine().split(",");
        for (String trait : phTraits) {
            physical.add(trait.trim());
        }

        System.out.print("Enter character's background: ");
        String background = scanner.nextLine();

        System.out.print("Enter character's motivations: ");
        String motivations = scanner.nextLine();

        System.out.print("Enter character's notes: ");
        String notes = scanner.nextLine();

        CharacterSheet updatedSheet = new CharacterSheet(actor, name, personality, physical, background, motivations, notes);
        actor.updateCharacterSheet(index - 1, updatedSheet);
    }

    public void manageSessions() {

        boolean submenu = true;

        while (submenu) {
            System.out.println("\n=== Sessions Menu ===");
            System.out.println("1. View Sessions");
            System.out.println("2. Book Session");
            System.out.println("3. Modify Session");
            System.out.println("4. Cancel Session");
            System.out.println("0. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    actor.viewSessions();
                    break;
                case 2:
                    promptBookSessionDetails();
                    break;
                case 3:
                    modifySession();
                    break;
                case 4:
                    System.out.println("Enter index of the session you want to cancel: ");
                    int indexToCancel = scanner.nextInt();
                    scanner.nextLine();
                    actor.cancelSession(indexToCancel);
                    break;
                case 0:
                    submenu = false;
                    break;
                default:
                    System.out.println("Not a valid option");
            }
        }
    }

    public void promptBookSessionDetails() {

        System.out.println("Enter instructor's name: ");
        String instructorName = scanner.nextLine();
        Instructor instructor = authSystem.findInstructorByName(instructorName);
        if (instructor == null) {
            System.out.println("Instructor not found.");
            return;
        }

        System.out.println("Enter session date and time (dd.MM.yyyy HH:mm): ");
        String dateTimeInput = scanner.nextLine();
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dateTimeInput, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Not a valid date format.");
            return;
        }

        System.out.println("Is this a group session? (yes/no): ");
        String groupInput = scanner.nextLine();
        boolean isGroupSession = groupInput.equalsIgnoreCase("yes");

        List<String> otherActors = new ArrayList<>();
        if (isGroupSession) {
            System.out.println("Enter other actors (comma separated): ");
            String[] others = scanner.nextLine().split(",");
            for (String name : others) {
                otherActors.add(name.trim());
            }
        }

        actor.bookSession(instructor, dateTime, isGroupSession, otherActors);
    }

    private void modifySession() {
        actor.viewSessions();

        List<Session> sessions = actor.getSessions();
        if (sessions.isEmpty()) {
            return;
        }

        System.out.println("Enter the index of the session you want to update: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > sessions.size()) {
            System.out.println("Not a valid index");
            return;
        }

        Session session = sessions.get(index - 1);

        System.out.print("Enter new date and time (dd.MM.yyyy HH:mm): ");
        String dateInput = scanner.nextLine();
        LocalDateTime dateTime = LocalDateTime.parse(dateInput, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

        System.out.print("Enter new instructor's name: ");
        String instructorName = scanner.nextLine();
        Instructor instructor = authSystem.findInstructorByName(instructorName);
        if (instructor == null) {
            System.out.println("Instructor not found.");
            return;
        }

        System.out.println("Is this a group session? (yes/no): ");
        boolean isGroupSession = scanner.nextLine().equalsIgnoreCase("yes");

        List<String> otherActors = new ArrayList<>();
        if (isGroupSession) {
            System.out.print("Enter names of other actors (comma separated): ");
            String[] actorNames = scanner.nextLine().split(",");
            for (String name : actorNames) {
                otherActors.add(name.trim());
            }
        }
        actor.modifySession(session, dateTime, instructor, isGroupSession, otherActors);
    }

    public void manageJournal() {
        boolean submenu = true;

        while (submenu) {
            System.out.println("\n=== Journal Menu ===");
            System.out.println("1. View Journal Entries");
            System.out.println("2. Add Journal Entry");
            System.out.println("3. Modify Journal Entry");
            System.out.println("4. Delete Journal Entry");
            System.out.println("0. Back to Main Menu");

            System.out.print("Choose an option");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    actor.viewJournal();
                    break;
                case 2:
                    addJournalEntry();
                    break;
                case 3:
                    modifyJournalEntry();
                    break;
                case 4:
                    deleteJournalEntry();
                    break;
                case 0:
                    submenu = false;
                    break;
                default:
                    System.out.println("Not a valid option");
            }
        }
    }

    public void addJournalEntry() {
        System.out.println("Enter title: ");
        String title = scanner.nextLine();

        System.out.println("Enter content: ");
        String content = scanner.nextLine();

        actor.addJournalEntry(title, content);
    }

    public void modifyJournalEntry() {
        actor.viewJournal();
        System.out.println("Enter index of journal entry to modify: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new title: ");
        String title = scanner.nextLine();

        System.out.println("Enter new content: ");
        String content = scanner.nextLine();

        actor.modifyJournalEntry(index - 1, title, content);
    }

    public void deleteJournalEntry() {
        actor.viewJournal();
        System.out.println("Enter index of journal entry to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        actor.deleteJournalEntry(index - 1);
    }

    public void manageImprovisation() {
        boolean submenu = true;

        while (submenu) {
            System.out.println("\n=== Improv Menu ===");
            System.out.println("1. Start Improvisation");
            System.out.println("2. End Improvisation");
            System.out.println("3. View all Improvisations");
            System.out.println("0. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    actor.startImprovisation();
                    break;
                case 2:
                    actor.endImprovisation();
                    break;
                case 3:
                    actor.viewImprovisations();
                    break;
                case 0:
                    submenu = false;
                    break;
                default:
                    System.out.println("Not a valid option");
            }
        }
    }

    public void manageEvaluation() {
        // TODO
    }

    public void manageInvoice() {
        // TODO
    }
}
