/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anavi.actingcoach;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Ana Vi
 */
public class AuthenticationSystem {

    //ATTRIBUTES
    private Map<String, User> users; //key = email
    private User loggedInUser;

    //CONSTRUCTORS
    public AuthenticationSystem() {
        this.users = new HashMap<>();
        this.loggedInUser = null;
    }

    //METHODS
    public boolean register(String name, String email, String password, String role) {
        if (users.containsKey(email)) {
            System.out.println("Email already registered.");
            return false;
        }

        User newUser;
        if (role.equalsIgnoreCase("actor")) {
            newUser = new Actor(name, email, password, role);
        } else if (role.equalsIgnoreCase("instructor")) {
            newUser = new Instructor(name, email, password, role);
        } else {
            System.out.println("Role not valid.");
            return false;
        }

        users.put(email, newUser);
        System.out.println(role + " registered succesfully.");
        return true;
    }

    public boolean login(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            user.authenticate();
            System.out.println("Welcome, " + user.getName() + "!");
            return true;
        } else {
            System.out.println("Email or password not valid.");
            return false;
        }
    }

    public void logout() {
        if (loggedInUser != null) {
            System.out.println("See you later, " + loggedInUser.getName() + "!");
            loggedInUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public boolean isInstructor() {
        return loggedInUser instanceof Instructor;
    }

    public boolean isActor() {
        return loggedInUser instanceof Actor;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public Instructor findInstructorByName(String instructorName) {
        for (User user : users.values()) {
            if (user instanceof Instructor && user.getName().equalsIgnoreCase(instructorName)) {
                return (Instructor) user;
            }
        }
        return null;
    }
}
