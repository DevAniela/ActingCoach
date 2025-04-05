/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.anavi.actingcoach;

/**
 * Main class for testing ActingCoach
 *
 * @author Ana Vi
 */
public class ActingCoach {

    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("                                                      ACTING COACH TESTS");
        System.out.println("------------------------------------------------------------------------");

        //Test creating an Actor
        Actor actor = new Actor("Daria Jane", "daria@email.com", "password123", "actor");
        System.out.println("Name: " + actor.getName());
        System.out.println("Email: " + actor.getEmail());
        //Test creating an Instructor
        Instructor instructor = new Instructor("Gus Constantin", "gus@email.com", "password456", "instructor");
        System.out.println("N: " + instructor.getName());
        System.out.println("E: " + instructor.getEmail());
        System.out.println("P: " + instructor.getPassword());
        //Test registering actors/instructors
        AuthenticationSystem authSys = new AuthenticationSystem();
        authSys.register("Neil Gaiman", "neil@gaiman.com", "passman123", "actor");
        authSys.register("Amanda Palmer", "ukulele@feminist.org", "789dresden", "instructor");
        //Test logging in with correct credentials
        authSys.login("ukulele@feminist.org", "789dresden");
        //Test loggin out
        authSys.logout();
        //Test logging in with incorrect credentials
        authSys.login("wrong@feminist.org", "789dresden");
        //Test logging out with no user logged in
        authSys.logout();
        //Check if logged in
        System.out.println("Is logged in: " + authSys.isLoggedIn());
        authSys.login("neil@gaiman.com", "passman123");
        System.out.println("Is logged in: " + authSys.isLoggedIn());
    }
}
