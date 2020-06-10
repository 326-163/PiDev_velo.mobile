/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author nahawnd
 */
public class Notifications {
    private int id;
    private String AlertTitle;
    private String AlertBody;
    private String receiver;

 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlertTitle() {
        return AlertTitle;
    }

    public void setAlertTitle(String AlertTitle) {
        this.AlertTitle = AlertTitle;
    }

    public String getAlertBody() {
        return AlertBody;
    }

    public void setAlertBody(String AlertBody) {
        this.AlertBody = AlertBody;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Notifications{" + "id=" + id + ", AlertTitle=" + AlertTitle + ", AlertBody=" + AlertBody + ", receiver=" + receiver + '}';
    }

 


    
}
