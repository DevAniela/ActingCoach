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
        //Test creating an Instructor
        Instructor instructor = new Instructor("Gus Constantin", "gus@email.com", "password456");
        System.out.println("N: " + instructor.getName());
        System.out.println("E: " + instructor.getEmail());
        System.out.println("P: " + instructor.getPassword());
    }
}
