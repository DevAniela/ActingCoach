package com.anavi.actingcoach;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
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

        System.out.print("Enter character's motivation: ");
        String motivation = scanner.nextLine();

        System.out.print("Enter character's notes: ");
        String notes = scanner.nextLine();

        actor.createAndAddCharacterSheet(name, personality, physical, background, motivation, notes);
        System.out.println("Character sheet created for " + name + ".");
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

        System.out.print("Enter character's motivation: ");
        String motivation = scanner.nextLine();

        System.out.print("Enter character's notes: ");
        String notes = scanner.nextLine();

        CharacterSheet updatedSheet = new CharacterSheet(actor, name, personality, physical, background, motivation, notes);
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

        List<Session> sessions = actor.getSessions();
        if (sessions.isEmpty()) {
            System.out.println("You have no sessions booked.");
            return;
        }

        actor.viewSessions();

        try {
            System.out.println("Enter the index of the session you want to update: ");
            int index = Integer.parseInt(scanner.nextLine());

            if (index < 1 || index > sessions.size()) {
                System.out.println("Not a valid session index.");
                return;
            }

            Session session = sessions.get(index - 1);

            if (session.isCanceled()) {
                System.out.println("Session has been canceled and cannot be modified.");
                return;
            }

            boolean submenu = true;
            while (submenu) {
                System.out.println("\n=== Modify Session ===");
                System.out.println("1. Change Date/Time");
                System.out.println("2. Add Actors");
                System.out.println("3. Remove Some Actors");
                System.out.println("4. Remove All Other Actors");
                System.out.println("0. Cancel");

                System.out.println("Choose an option: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Enter new date and time (dd.MM.yyyy HH:mm): ");
                        String dateInput = scanner.nextLine();
                        try {
                            LocalDateTime newDateTime = LocalDateTime.parse(dateInput, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                            session.modifySessionByActor(newDateTime);
                            System.out.println("Session date/time updated successfully.");
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please use dd.MM.yyyy HH:mm");
                        }
                        break;

                    case "2": // Add actors
                        System.out.println("Enter names of actors to add (comma separated): ");
                        String[] newActors = scanner.nextLine().split(",");

                        for (String actorName : newActors) {
                            session.getOtherActors().add(actorName.trim());
                        }
                        session.setGroupSession(true);
                        session.calculateFee();
                        System.out.println("Actors added successfully.");
                        break;

                    case "3": // Remove some actors
                        List<String> currentActors = session.getOtherActors();
                        if (currentActors.isEmpty()) {
                            System.out.println("There are no other actors to remove.");
                            break;
                        }

                        System.out.println("Current list of actors: " + String.join(", ", currentActors));
                        System.out.print("Enter names of actors to remove (comma separated): ");

                        String[] removeActors = scanner.nextLine().split(",");

                        for (String removeName : removeActors) {
                            Iterator<String> iterator = currentActors.iterator();
                            boolean found = false;
                            while (iterator.hasNext()) {
                                String actorName = iterator.next();
                                if (actorName.equalsIgnoreCase(removeName.trim())) {
                                    iterator.remove();
                                    found = true;
                                }
                            }
                            if (!found) {
                                System.out.println("Actor " + removeName.trim() + " not found in the session.");
                            }
                        }

                        if (currentActors.isEmpty()) {
                            session.setGroupSession(false);
                            System.out.println("All other actors removed. Session is now a solo session.");
                        } else {
                            session.setGroupSession(true);
                            System.out.println("Actors removed successfully.");
                        }
                        session.calculateFee();
                        break;

                    case "4": // Remove all other actors
                        session.getOtherActors().clear();
                        session.calculateFee();
                        session.setGroupSession(false);
                        System.out.println("All other actors removed. Session is now a solo session.");
                        break;

                    case "0":
                        submenu = false;
                        System.out.println("No changes made.");
                        break;

                    default:
                        System.out.println("Not a valid option.");
                        break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Not a valid input. Please enter a valid number.");
        }
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

        boolean submenu = true;

        while (submenu) {
            System.out.println("\n=== Evaluations Menu ===");
            System.out.println("1. View Evaluation by Session");
            System.out.println("2. View All Evaluations");
            System.out.println("0. Back to Main Menu");

            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter the index of the session for which you want to see the evaluation: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();

                    List<Session> sessions = actor.getSessions();
                    if (index < 1 || index > sessions.size()) {
                        System.out.println("Not a valid session index.");
                    } else {
                        sessions.get(index - 1).viewEvaluation();
                    }
                    break;
                case 2:
                    System.out.println("\n=== All Evaluations ===");
                    List<Session> allSessions = actor.getSessions();
                    boolean found = false;

                    for (int i = 0; i < allSessions.size(); i++) {
                        Session session = allSessions.get(i);
                        if (session.getEvaluation() != null) {
                            System.out.println("\nSession " + (i + 1) + ":");
                            session.viewEvaluation();
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No evaluations available yet.");
                    }
                    break;
                case 0:
                    submenu = false;
                    break;
                default:
                    System.out.println("Not a valid option");
            }
        }
    }

    public void manageInvoice() {

        boolean submenu = true;

        while (submenu) {
            System.out.println("\n=== Invoice Menu ===");
            System.out.println("1. View Invoice by Session");
            System.out.println("2. View All Invoices");
            System.out.println("0. Back to Main Menu");

            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter the index of the session for which you want to see the invoice: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();

                    List<Session> sessions = actor.getSessions();
                    if (index < 1 || index > sessions.size()) {
                        System.out.println("Not a valid session index.");
                    } else {
                        Session session = sessions.get(index - 1);
                        if (session.getFee() > 0) {
                            System.out.println("Invoice for session on " + session.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + ": ");
                            System.out.println("Fee: €" + session.getFee());
                        } else {
                            System.out.println("No invoice available for this session.");
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n=== All Invoices ===");
                    List<Session> allSessions = actor.getSessions();
                    boolean foundInvoice = false;

                    for (Session session : allSessions) {
                        if (session.getFee() > 0) {
                            System.out.println("Session on " + session.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + ": ");
                            System.out.println("Fee: €" + session.getFee());
                            foundInvoice = true;
                        }
                    }

                    if (!foundInvoice) {
                        System.out.println("No invoices available yet.");
                    }
                    break;

                case 0:
                    submenu = false;
                    break;

                default:
                    System.out.println("Not a valid option.");
            }
        }
    }
}
