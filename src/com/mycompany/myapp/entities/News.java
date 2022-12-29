/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;
import java.util.Date;

/**
 *
 * @author essia
 */
public class News {
       private int id;
    private String Titre,Contenu,TypeSport;
    private Date date;
    private String URLImg;
    private int nbreacts;
    public News() {
    }

    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

   

    public String getTitre() {
        return Titre;
    }
    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }
     public String getContenu() {
        return Contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTypeSport() {
        return TypeSport;
    }

    public void setTypeSport(String TypeSport) {
        this.TypeSport = TypeSport;
    }

    public String getURLImg() {
        return URLImg;
    }

    public void setURLImg(String URLImg) {
        this.URLImg = URLImg;
    }

    public int getNbreacts() {
        return nbreacts;
    }

    public void setNbreacts(int nbreacts) {
        this.nbreacts = nbreacts;
    }

    @Override
    public String toString() {
        return  "id=" + id + ", Titre=" + Titre + ", Contenu=" + Contenu + ", TypeSport=" + TypeSport + ", date=" + date;
    }
  
    
 
     
    

    
    
    
    
}
