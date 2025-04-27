package com.anavi.actingcoach;

import java.util.Scanner;

public class InstructorUI extends ActingCoachUI {

    private Instructor instructor;
    private Scanner scanner;

    public InstructorUI(AuthenticationSystem authSystem, Instructor instructor) {
        super(authSystem);
        this.instructor = instructor;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. View Scheduled Sessions");
        System.out.println("2. View Session Details");
        System.out.println("3. Add Session Feedback");
        System.out.println("4. Evaluate Actor");
        System.out.println("5. Cancel Session");
        System.out.println("0. Exit");
    }

    public void manageSessionDetails() {
        System.out.println("Enter index of the session you want to view: ");
        int sessionIndex = scanner.nextInt();
        scanner.nextLine();
        if (sessionIndex > 0 && sessionIndex <= instructor.getSessions().size()) {
            System.out.println(instructor.getSessionDetails(instructor.getSessions().get(sessionIndex - 1)));
        } else {
            System.out.println("Not a valid index.");
        }
    }

    public void manageSessionFeedback() {
        System.out.print("Enter session index: ");
        int i = scanner.nextInt();
        Session session = instructor.getSessions().get(i - 1);
        System.out.print("Enter general feedback for session " + i + ": ");
        String generalFeedback = scanner.nextLine();
        instructor.addGeneralFeedback(session, generalFeedback);
    }

    public void manageActorEvaluation() {
        System.out.println("Enter session index: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > instructor.getSessions().size()) {
            System.out.println("Not a valid session index.");
            return;
        }

        Session session = instructor.getSessions().get(index - 1);

        if (session.isCanceled()) {
            System.out.println("This session is canceled.");
            return;
        }

        System.out.println("\n=== Evaluating " + session.getActor().getName() + " ===");
        
        int expressiveness = getValidRating("expressiveness");
        int diction = getValidRating("diction");
        int emotion = getValidRating("emotion");
        System.out.print("Enter any observations or advice you might have for this actor: ");
        String notes = scanner.nextLine();

        Evaluation evaluation = new Evaluation(session, expressiveness, diction, emotion, notes);

        instructor.evaluateActor(session, evaluation);
    }

    public int getValidRating(String type) {
        int rating = -1;
        while (rating < 1 || rating > 10) {
            System.out.print("Enter rating for " + type + " (1-10): ");
            rating = scanner.nextInt();
            if (rating < 1 || rating > 10) {
                System.out.println("Please enter a valid rating between 1 and 10.");
            }
        }
        return rating;
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
                    instructor.viewScheduledSessions();
                    break;
                case 2:
                    manageSessionDetails();
                    break;
                case 3:
                    manageSessionFeedback();
                    break;
                case 4:
                    manageActorEvaluation();
                    break;
                case 5:
                    System.out.println("Enter index of the session you want to cancel: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    instructor.cancelSession(index);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Not a valid option.");
            }
        }
    }
}
