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
        System.out.println("***ActingCoach test***");

        //Test creating an Actor
        Actor actor = new Actor("Daria Jane", "daria@email.com", "password123");
        System.out.println("Name: " + actor.getName());
        System.out.println("Email: " + actor.getEmail());
    }
}
