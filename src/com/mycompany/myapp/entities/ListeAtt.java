/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author rania arafa
 */
public class ListeAtt {
    private int id;
    private String nom, prenom, filiere, image, email;

    public ListeAtt(String nom, String prenom, String filiere, String image, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        this.image = image;
        this.email = email;
    }

    public ListeAtt(String nom, String prenom, String filiere, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ListeAtt{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", filiere=" + filiere + ", image=" + image + ", email=" + email + '}';
    }

    public ListeAtt(int id, String nom, String prenom, String filiere, String image, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        this.image = image;
        this.email = email;
    }

    public ListeAtt() {
    }

    public int getId() {
        return id;
    }


    


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

  

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
}
