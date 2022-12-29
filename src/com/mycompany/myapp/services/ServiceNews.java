/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;



import com.codename1.components.MultiButton;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.mycompany.myapp.entities.News;

import java.util.Date;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;




/**
 *
 * @author essia
 */
public class ServiceNews {
     public ArrayList<News> News;
   public boolean resultOK;
    public static ServiceNews instance=null;
    private ConnectionRequest req;

    public ServiceNews() {
         req = new ConnectionRequest();
    }

    public static ServiceNews getInstance() {
        if (instance == null) {
            instance = new ServiceNews();
        }
        return instance;
    }
        public boolean addNews(News n) {
        String url = Statics.BASE_URL + "/addNewS/newJSON?titre=" + n.getTitre() + "&contenu=" + n.getContenu()+ "&typesport=" + n.getTypeSport(); //création de l'URL
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
         public ArrayList<News> parseNews(String jsonText){
   try {
            News=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> NewsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
         
            List<Map<String,Object>> list = (List<Map<String,Object>>)NewsListJson.get("root");
            
            //Parcourir la liste des tâches Json
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                News t = new News();

                
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setContenu(obj.get("contenu").toString());
                t.setTitre(obj.get("titre").toString());
               t.setTypeSport(obj.get("typesport").toString());
               t.setURLImg(obj.get("imagenews").toString());
               
            
               
                //Ajouter la tâche extraite de la réponse Json à la liste
                News.add(t);
                
            }
            
            
        } catch (IOException ex) {
             System.out.println(ex.getMessage()); 
            
        } 
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return News;
    }
       public ArrayList<News> getAllNews(){
        String url = Statics.BASE_URL+"/listeNewsJSON2";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                News = parseNews(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return News;
    }
    
       public  boolean NewsById( News n){
           String url = Statics.BASE_URL+"/NewsbyIdJSON/id="+n.getId();

           req.setUrl(url);
           req.addResponseListener(new ActionListener<NetworkEvent>() {
                           JSONParser j = new JSONParser();

            @Override
            public void actionPerformed(NetworkEvent evt) {
                           resultOK = req.getResponseCode()==200;

                req.removeResponseListener(this);
            }
        });
         
                   NetworkManager.getInstance().addToQueueAndWait(req);
return resultOK;
           
           
       }
       
    
}
