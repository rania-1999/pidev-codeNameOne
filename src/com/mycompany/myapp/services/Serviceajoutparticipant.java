/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.participant;
import com.mycompany.myapp.entities.partie;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author Bechir
 */
public class Serviceajoutparticipant {
    
    public ArrayList<participant> partie;
    
    public static Serviceajoutparticipant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    Serviceajoutparticipant() {
         req = new ConnectionRequest();
    }

    public static Serviceajoutparticipant getInstance() {
        if (instance == null) {
            instance = new Serviceajoutparticipant();
        }
        return instance;
    }

    public boolean addparticipant(participant p) {
           String url = Statics.BASE_URL + "/addParticipjson?nom=" + p.getNom()+ "&prenom=" + p.getPrenom();//création de l'URL//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    } 
    
    
    
    
}
