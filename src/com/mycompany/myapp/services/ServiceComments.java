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
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author essia
 */
public class ServiceComments {
      public ArrayList<Commentaire> Commentaire;
   public boolean resultOK;
    public static ServiceComments instance=null;
    private ConnectionRequest req;
     float id =0;
    

    public ServiceComments() {
         req = new ConnectionRequest();
    }

    public static ServiceComments getInstance() {
        if (instance == null) {
            instance = new ServiceComments();
        }
        return instance;
    }
    //Add
        public boolean addComments(Commentaire c) {
        String url = Statics.BASE_URL + "/addCommentaire/newJSON?contenu="+c.getContenu()+"&news="+c.getIdNews()+"&user="+c.getIdUtilisateur(); //création de l'URL
        System.out.println(url);
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
         public ArrayList<Commentaire> parseComments(String jsonText){
   try {
            Commentaire=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> CommentsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
         
            List<Map<String,Object>> list = (List<Map<String,Object>>)CommentsListJson.get("root");
           
            //Parcourir la liste des tâches Json
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Commentaire c = new Commentaire();

              //  c.setIdNews( (int) Float.parseFloat(obj.get("").toString()));
               
                c.setContenu(obj.get("contenu").toString());
float id = Float.parseFloat(obj.get("idcommentaire").toString());
                c.setId((int)id);               
                
                
            //   float iduser =  Float.parseFloat(obj.get("idUtilisateur").toString());
            
              //  c.setIdUtilisateur((int)iduser);
               
                //Ajouter les commentaires extraits de la réponse Json à la liste
                Commentaire.add(c);
                
            }
            
            
        } catch (IOException ex) {
             System.out.println(ex.getMessage()); 
            
        } 
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Commentaire;
    }
         
         // get user id 
         public int getIDUSER(int idc) {
            
              String url = Statics.BASE_URL+"/UserCommentbyIdJSON/"+idc;
              
        req.setUrl(url);
        System.out.println("URL:"+ url);
              
        req.setPost(false);
      
        req.addResponseListener( (e) -> {
           
            String ids = new String(req.getResponseData());
//                id = Integer.parseInt(ids);
               
//              float ids = Float.parseFloat(req.getResponseData().toString());


                
                
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        System.out.println("ID = "+id);
       
        return (int)id;
             
         }
         //
         //show
       public ArrayList<Commentaire> getAllComments(int idn){
        String url = Statics.BASE_URL+"/CommentbyIdJSON/"+idn;
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Commentaire = parseComments(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
     
        return Commentaire;
    }
       //Delete
       public boolean deletecomment(int id){
           String url= Statics.BASE_URL+"/DeleteCommentaireJSON/"+id;
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
         //Update 
    public boolean modifierCommentaire(Commentaire c) {
        String url = Statics.BASE_URL +"/UpdateCommentaireJSON/"+c.getId()+"?contenu="+c.getContenu();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
        
    }
    
}
