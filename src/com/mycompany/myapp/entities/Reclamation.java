/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author 21699
 */
public class Reclamation {
    int idrec ; 
    int iduser;
    String objet;
    String message;
    String image ; 
    Date DateAjout;

    public Reclamation() {
    }

    public Reclamation(int idrec, int iduser, String objet, String message, String image, Date DateAjout) {
        this.idrec = idrec;
        this.iduser = iduser;
        this.objet = objet;
        this.message = message;
        this.image = image;
        this.DateAjout = DateAjout;
    }
    public Reclamation(int iduser, String objet, String message, String image, Date DateAjout) {
        
        this.iduser = iduser;
        this.objet = objet;
        this.message = message;
        this.image = image;
        this.DateAjout = DateAjout;
    }

    public int getIdrec() {
        return idrec;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateAjout() {
        return DateAjout;
    }

    public void setDateAjout(Date DateAjout) {
        this.DateAjout = DateAjout;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idrec=" + idrec + ", iduser=" + iduser + ", objet=" + objet + ", message=" + message + ", image=" + 
                image + ", DateAjout=" + DateAjout + '}';
    }
    
    
    
}
