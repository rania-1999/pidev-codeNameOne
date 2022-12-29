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
import com.mycompany.myapp.entities.ListeAtt;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author rania arafa
 */
public class ServiceListeAtt {
  
    //var
    boolean resultOK;
    ConnectionRequest req;
    static ServiceListeAtt instance;
    ArrayList<ListeAtt> tasks = new ArrayList<>();
    
    
    //constructor
    private ServiceListeAtt() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static ServiceListeAtt getInstance(){
        
        if (instance == null) {
            instance = new ServiceListeAtt();
        }
        
        return instance;
    }
    
    
    
    
    //ADD TASK 
    public boolean addListeAction(ListeAtt t){
        
        String url = Statics.BASE_URL + "/addARLjson?nom=" + t.getNom()+ "&prenom=" + t.getPrenom()+ "&filiere="+t.getFiliere()+"&email="+t.getEmail();//création de l'URL
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
 
  
    
    
      
    
    
}
