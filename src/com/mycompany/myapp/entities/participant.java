/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author user
 */
public class participant {
    
    
  private int id;
private String nom,prenom;

    public participant(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

   

    public participant() {
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

    @Override
    public String toString() {
        return  "id:" + id + 
                " nom:" + nom + 
                " prenom:" + prenom  ;
    }

    public participant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    
    
    
    
    
    
}
