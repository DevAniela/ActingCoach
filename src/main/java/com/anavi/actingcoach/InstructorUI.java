package com.anavi.actingcoach;

import java.util.Scanner;

public class InstructorUI extends ActingCoachUI {

    private Instructor instructor;

    public InstructorUI(AuthenticationSystem authSystem, Instructor instructor) {
        super(authSystem);
        this.instructor = instructor;
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. View Sessions");
        System.out.println("2. Add Feedback");
        System.out.println("3. Add Evaluation");
        System.out.println("4. Cancel Session");
        System.out.println("0. Exit");
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("TO DO: View sessions");
                    break;
                case 2:
                    System.out.println("TO DO: Add feedback");
                    break;
                case 3:
                    System.out.println("TO DO: Add evaluation");
                    break;
                case 4:
                    System.out.println("TO DO: Cancel session");
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Not a valid option");
            }

        }

    }
}
