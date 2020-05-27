/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author nahawnd
 */
public class Reservation {

    private int id;
    private String dateDeb, dateFin;

    public Reservation() {
    }

    public Reservation(int id, String dateDeb, String dateFin) {
        this.id = id;

        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }

    public Reservation(String dateDeb, String dateFin) {
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id+ ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + '}';
    }


}
