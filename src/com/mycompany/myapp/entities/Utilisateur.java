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
public class Utilisateur {
    private int id;
    private String username;
     private String password;
     private String email; 
     private String nom ; 
     private String sexe; 
    private int age; 
    private int num;
    private String image;
    private Date DateCreation;
    
    private int code; 
    private int auth; 
    private int blocked; 
    private int notif;

    public Utilisateur() {
    }

    
    public Utilisateur(int id, String username, String password, String email, String nom, String sexe, int age, int num, String image, Date DateCreation, int code, int auth, int blocked, int notif) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.num = num;
        this.image = image;
        this.DateCreation = DateCreation;
        this.code = code;
        this.auth = auth;
        this.blocked = blocked;
        this.notif = notif;
    }
    
    public Utilisateur(String username, String password, String email, String nom, String sexe, int age, int num, String image, Date DateCreation) {
     
        this.username = username;
        this.password = password;
        this.email = email;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.num = num;
        this.image = image;
        this.DateCreation = DateCreation;
       
    }
   
     public Utilisateur(int id ,String username, String password, String email, String nom, String sexe, int age, int num, String image, Date DateCreation) {
         this.id = id ;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.num = num;
        this.image = image;
        this.DateCreation = DateCreation;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public int getNotif() {
        return notif;
    }

    public void setNotif(int notif) {
        this.notif = notif;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Date DateCreation) {
        this.DateCreation = DateCreation;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", age=" + age + ", num=" + num + ", code=" + code + ", auth=" + auth + ", blocked=" + blocked + ", notif=" 
                + notif + ", username=" + username + ", password=" + password + ", nom=" + nom + ", email=" + email + ", sexe=" + sexe + ", image=" 
                + image + ", DateCreation=" + DateCreation + '}';
    }
    
    
    
    
}
