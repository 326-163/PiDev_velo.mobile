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
public class Location {

    private int id;
    private String titre;
    private String lieu;
    private float prix;
    private String photo;
    private int rating;
    private Date dateCreation;
    private boolean active;

    public Location() {
    }

    public Location(int id, String titre, String lieu, float prix, String photo, int rating, Date dateCreation, boolean active) {
        this.id = id;
        this.titre = titre;
        this.lieu = lieu;
        this.prix = prix;
        this.photo = photo;
        this.rating = rating;
        this.dateCreation = dateCreation;
        this.active = active;
    }

    public Location(String titre, String lieu, float prix, String photo, Date dateCreation, boolean active) {
        this.titre = titre;
        this.lieu = lieu;
        this.prix = prix;
        this.photo = photo;
        this.dateCreation = dateCreation;
        this.active = active;
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", titre=" + titre + ", lieu=" + lieu + ", prix=" + prix + ", photo=" + photo + ", rating=" + rating + ", dateCreation=" + dateCreation + ", active=" + active + '}';
    }

}
