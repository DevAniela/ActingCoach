package com.anavi.actingcoach;

public abstract class ActingCoachUI {

    protected AuthenticationSystem authSystem;

    public ActingCoachUI(AuthenticationSystem authSystem) {
        this.authSystem = authSystem;
    }

    public abstract void displayMenu();

    public abstract void handleInput();
}