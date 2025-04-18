package com.anavi.actingcoach;

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
        // TODO: display actor menu
    }

    @Override
    public void handleInput() {
        // TODO: handle actor input
    }

    public void manageCharacterSheets() {
        // TODO
    }

    public void promptCharacterSheetDetails() {
        // TODO
    }

    public void updateCharacterSheet() {
        // TODO
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