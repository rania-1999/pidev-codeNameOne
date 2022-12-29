/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


/**
 *
 * @author essia
 */
public class Commentaire {
     private int id;
    private String Contenu;
    private int idNews;
    private int nbreacts;
    private int idUtilisateur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

 



    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public int getNbreacts() {
        return nbreacts;
    }

    public void setNbreacts(int nbreacts) {
        this.nbreacts = nbreacts;
    }
    
   
    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", Contenu=" + Contenu + ", date_dajout=" +  '}';
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
    
}
