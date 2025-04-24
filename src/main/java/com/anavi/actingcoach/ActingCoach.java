/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.anavi.actingcoach;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        //Create a group session
        Actor actor1 = new Actor("Benedict Cumberbatch", "cucumber@email.com", "1J2k3", "actor");
        Actor actor2 = new Actor("Martin Freeman", "freedom@email.com", "1&2*3", "actor");
        Actor actor3 = new Actor("Jane Lynch", "sue@email.com", "sylvester", "actor");
        Instructor instructor1 = new Instructor("Lee", "strasberg@gmail.com", "method1", "instructor");
        Instructor instructor2 = new Instructor("Angela Lansbury", "shewrote@gmail.com", "method2", "instructor");

        LocalDateTime sessionTime = LocalDateTime.now().plusDays(2);

        Session groupSession = new Session(sessionTime, instructor1, actor3);
        groupSession.setGroupSession(true);

        List<String> otherActors = new ArrayList<>();
        otherActors.add(actor1.getName());
        otherActors.add(actor2.getName());

        groupSession.setOtherActors(otherActors);

        instructor1.addSession(groupSession);
        actor3.bookSession(instructor1, sessionTime, true, otherActors);
        actor1.bookSession(instructor2, sessionTime, true, Arrays.asList(actor3.getName()));

        instructor1.addGeneralFeedback(groupSession, "Strong group synergy. Practice timing.");
        
        Actor currentActor = (Actor) authSys.getLoggedInUser();
        ActorUI actorUI = new ActorUI(authSys, currentActor);
        actorUI.handleInput();
    }
}
