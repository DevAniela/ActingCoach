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
            instructor.getSessionDetails(instructor.getSessions().get(sessionIndex - 1));
        } else {
            System.out.println("Not a valid index.");
        }
    }

    public void manageSessionFeedback() {
        System.out.print("Enter session index: ");
        int i = scanner.nextInt();
        Session session = instructor.getSessions().get(i - 1);
        System.out.print("Enter general feedback for session " + i + ": ");
        String generalFeedback = scanner.next();
        instructor.addGeneralFeedback(session, generalFeedback);
    }

    public void manageActorEvaluation() {
        System.out.println("Enter session index: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        Session session = instructor.getSessions().get(index - 1);

        if (session.isCanceled()) {
            System.out.println("This session is cancelled.");
            return;
        }

        System.out.println("\n=== Evaluating " + session.getActor().getName() + " ===");
        System.out.println("Enter rating for expressiveness (1-10): ");
        int expressiveness = scanner.nextInt();
        System.out.print("Enter rating for diction (1-10): ");
        int diction = scanner.nextInt();
        System.out.print("Enter rating for emotion (1-10): ");
        int emotion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter any observations or advice you might have for this actor: ");
        String notes = scanner.nextLine();

        Evaluation evaluation = new Evaluation(session, expressiveness, diction, emotion, notes);

        instructor.evaluateActor(session, evaluation);
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
                    System.out.println("TO DO: Cancel Session");
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
