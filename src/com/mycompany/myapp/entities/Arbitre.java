/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author rania arafa
 */
public class Arbitre {
    private int id;
    private String nom, prenom, filiere, date, disponibilite, image ;
    //private Date updated_at;

   

    public Arbitre(String nom, String prenom, String filiere, String disponibilite, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        this.disponibilite = disponibilite;
this.image=image;
    }

    public Arbitre(String nom, String prenom, String filiere) {
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
    }

    public Arbitre() {
    }

    @Override
    public String toString() {
        return "Arbitre{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", filiere=" + filiere + ", date=" + date + ", disponibilite=" + disponibilite + ", image=" + image + '}';
    }


    public Arbitre(int id, String nom, String prenom, String filiere, String date, String disponibilite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        this.date = date;
        this.disponibilite = disponibilite;
    }

    public Arbitre(String nom, String prenom, String filiere, String date, String disponibilite, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        this.date = date;
        this.disponibilite = disponibilite;
        this.image = image;
    }

    public Arbitre(int id, String nom, String prenom, String filiere, String date, String disponibilite, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        this.date = date;
        this.disponibilite = disponibilite;
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    

  


  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }



  
   
   
}
