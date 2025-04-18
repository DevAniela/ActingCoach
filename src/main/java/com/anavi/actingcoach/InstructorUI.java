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
        // TODO: display menu options
    }

    @Override
    public void handleInput() {
        // TODO: handle user input
    }
}