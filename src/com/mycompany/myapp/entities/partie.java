/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Bechir
 */
public class partie {
    
    private int id;
    private String equipea,equipeb;
    private String tour;
    private String date;

    public partie( String equipea, String equipeb, String tour, String date) {
       
        this.equipea = equipea;
        this.equipeb = equipeb;
        this.tour = tour;
        this.date = date;
    }

    public partie(String equipea, String equipeb, String tour) {
        this.equipea = equipea;
        this.equipeb = equipeb;
        this.tour = tour;
    }

    public partie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipea() {
        return equipea;
    }

    public void setEquipea(String equipea) {
        this.equipea = equipea;
    }

    public String getEquipeb() {
        return equipeb;
    }

    public void setEquipeb(String equipeb) {
        this.equipeb = equipeb;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "partie{" + "equipea=" + equipea + ", equipeb=" + equipeb + ", tour=" + tour + '}';
    }

    
    
    
    
    
    
    
    
    
}
