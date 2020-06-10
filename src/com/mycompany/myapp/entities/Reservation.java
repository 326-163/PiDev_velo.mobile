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

    private String titre;
    private int id;
    private Date dateDeb, dateFin;

    public Reservation() {
    }

    public Reservation(int id, String titre, Date dateDeb, Date dateFin) {
        this.id = id;
        this.titre = titre;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }

    public Reservation(Date dateDeb, Date dateFin) {
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Reservation{" + "titre=" + titre + ", id=" + id + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + '}';
    }

}
