/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anavi.actingcoach;

/**
 *
 * @author Ana Vi
 */
public abstract class User {

    //ATRIBUTE
    protected int id;
    protected String name, email, password, role;
    
    public static final String ROLE_ACTOR = "Actor";
    public static final String ROLE_INSTRUCTOR = "Instructor";

    //CONSTRUCTOR
    public User() {
        this.id = -1;
        this.name = "";
        this.email = "";
        this.password = "";
        this.role = "";
    }

    public User(String name, String email, String password, String role) {
        this.id = -1;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //GETTERI SI SETTERI
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    //METODE ABSTRACTE
    public abstract void authenticate(); //implementare in subclase
}
