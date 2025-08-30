/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anavi.actingcoach;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ana Vi
 */
public class Invoice {

    //ATTRIBUTES
    private int id;
    private Session session;
    private double amount;
    private boolean isPaid;
    private int actorId;

    //CONSTRUCTORS
    public Invoice() {
        this.id = -1;
        this.session = null;
        this.amount = 0.0;
        this.isPaid = false;
        this.actorId = -1;
    }

    public Invoice(Session session, double amount, boolean isPaid, int actorId) {
        this.session = session;
        this.amount = amount;
        this.isPaid = isPaid;
        this.actorId = actorId;
    }

    //GETTERS/SETTERS
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Session getSession() {
        return this.session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return this.isPaid;
    }

    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    //METHODS
    public void markAsPaid() {
        this.isPaid = true;
    }

    public void displayInvoice() {
        if (session != null) {
            System.out.println("Invoice for session on " + session.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
            System.out.println("Actor: " + session.getActor().getName());
            System.out.println("Amount due: EUR" + String.format("%.2f", amount));
            System.out.println("Payment status: " + (isPaid ? "paid" : "unpaid"));
        } else {
            System.out.println("No session linked to this invoice.");
        }
    }
}
