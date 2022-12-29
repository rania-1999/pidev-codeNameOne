/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Utilisateur;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 21699
 */
public class ServiceReclamation {
     public boolean resultOK;
    public ArrayList<Reclamation> Recs;
    public static ServiceReclamation instance=null;
    private ConnectionRequest req;

    private ServiceReclamation() {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }
    
     public boolean AddReclamation(Reclamation r) {
        
        
        
         String url = Statics.BASE_URL + "/addRec/newJSON?objet="+r.getObjet()+ "&message=" + r.getMessage()+"&user="+r.getIduser(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
       public ArrayList<Reclamation> parseRecs(String jsonText){
        try {
            Recs=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> RecsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(RecsListJson);
            List<Map<String,Object>> list = (List<Map<String,Object>>)RecsListJson.get("root");
            System.out.println(list);
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                
                Reclamation r = new Reclamation();
                
                r.setIdrec(((int)Float.parseFloat(obj.get("idrec").toString())));

                 r.setObjet(obj.get("objet").toString());
                 r.setMessage(obj.get("message").toString());
               
              Recs.add(r);
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
        return Recs;
    }
    
    public ArrayList<Reclamation> getAllRecs(int id){
        String url = Statics.BASE_URL+"/AllRecJSON/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Recs = parseRecs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Recs;
    }
     
    
    public boolean DeleteRec(int id){
           String url= Statics.BASE_URL+"/DeleteRecJSON/"+id;
            req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                                req.removeResponseListener(this);

                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
           
           
       }
     
    
}
