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
    private Session session;
    private double amount;
    private boolean isPaid;

    //CONSTRUCTORS
    public Invoice() {
        this.session = null;
        this.amount = 0.0;
        this.isPaid = false;
    }

    public Invoice(Session session, double amount, boolean isPaid) {
        this.session = session;
        this.amount = amount;
        this.isPaid = isPaid;
    }

    //GETTERS/SETTERS
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
