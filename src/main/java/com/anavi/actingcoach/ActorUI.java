package com.anavi.actingcoach;

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
        // TODO
    }

    public void manageJournal() {
        // TODO
    }

    public void manageImprovisation() {
        // TODO
    }

    public void manageEvaluation() {
        // TODO
    }

    public void manageInvoice() {
        // TODO
    }
}
