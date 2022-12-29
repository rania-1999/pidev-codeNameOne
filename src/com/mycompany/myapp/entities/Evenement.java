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
public class Evenement {
    private int idEvenement, nbrePL;
    private String nomEvenement, descripEvenement,  lieu, date;

    public Evenement(int nbrePL, String nomEvenement, String descripEvenement, String lieu, String date) {
        this.nbrePL = nbrePL;
        this.nomEvenement = nomEvenement;
        this.descripEvenement = descripEvenement;
        this.lieu = lieu;
        this.date = date;
    }

    public Evenement(int idEvenement, int nbrePL, String nomEvenement, String descripEvenement, String lieu, String date) {
        this.idEvenement = idEvenement;
        this.nbrePL = nbrePL;
        this.nomEvenement = nomEvenement;
        this.descripEvenement = descripEvenement;
        this.lieu = lieu;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

  
    

  

  


 
    
    
   


  

  

    public Evenement(int nbrePL, String nomEvenement, String descripEvenement, String lieu) {
        this.nbrePL = nbrePL;
        this.nomEvenement = nomEvenement;
        this.descripEvenement = descripEvenement;
        this.lieu = lieu;
    }

   

   

    public Evenement(int idEvenement, int nbrePL, String nomEvenement, String descripEvenement, String lieu) {
        this.idEvenement = idEvenement;
        this.nbrePL = nbrePL;
        this.nomEvenement = nomEvenement;
        this.descripEvenement = descripEvenement;
        this.lieu = lieu;
       
    }

    

    

    public Evenement() {
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public int getNbrePL() {
        return nbrePL;
    }

    public void setNbrePL(int nbrePL) {
        this.nbrePL = nbrePL;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public String getDescripEvenement() {
        return descripEvenement;
    }

    public Evenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public void setDescripEvenement(String descripEvenement) {
        this.descripEvenement = descripEvenement;
    }

  

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Evenement{" + "nbrePL=" + nbrePL + ", nomEvenement=" + nomEvenement + ", descripEvenement=" + descripEvenement + ", lieu=" + lieu + ", date=" + date + '}';
    }

 
}
