/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;


import com.mycompany.myapp.entities.Utilisateur;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.Reclamations;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 21699
 */
public class ServiceUtilisateur {
    public boolean resultOK;
    public ArrayList<Utilisateur> users;
    public static ServiceUtilisateur instance=null;
    private ConnectionRequest req;
    public String json ="";

    private ServiceUtilisateur() {
         req = new ConnectionRequest();
    }

    public static ServiceUtilisateur getInstance() {
        if (instance == null) {
            instance = new ServiceUtilisateur();
        }
        return instance;
    }
    public boolean AddUser(Utilisateur u) {
        
        
        
         String url = Statics.BASE_URL + "/addUser/newJSON?username="+u.getUsername()+ "&password=" + u.getPassword()  + "&email=" + u.getEmail()  + "&nom=" + u.getNom()  + "&sexe=" + u.getSexe()  + "&age=" 
                 +u.getAge()  + "&numtel=" + u.getNum(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                json = new String(req.getResponseData());
                req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        // controle username 
        if (json.equals("exist")) 
        {
            System.out.println("exist !!!");
            Dialog.show("Alert","cet utilisateur existe deja  ", new Command("OK"));
            resultOK = false ; 
        }
        
        
        
        return resultOK;
    }
    
    public ArrayList<Utilisateur> parseUsers(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(usersListJson);
            List<Map<String,Object>> list = (List<Map<String,Object>>)usersListJson.get("root");
            System.out.println(list);
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                
                Utilisateur u = new Utilisateur();
                
                u.setId(((int)Float.parseFloat(obj.get("id").toString())));
                 u.setUsername(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                u.setNom(obj.get("nom").toString());
                u.setSexe(obj.get("sexe").toString());
                u.setAge(((int)Float.parseFloat(obj.get("age").toString())));
                u.setNum(((int)Float.parseFloat(obj.get("numtel").toString())));
                u.setAuth(((int)Float.parseFloat(obj.get("auth").toString())));
               
                //Ajouter la tâche extraite de la réponse Json à la liste
                users.add(u);
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
        return users;
    }
    
    public ArrayList<Utilisateur> getAllUsers(){
        String url = Statics.BASE_URL+"/AllUsersJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
    
    
    //
    
    // Session info 
    public Utilisateur loginUser(Utilisateur u){
        String url = Statics.BASE_URL+"/LoginUser?username="+u.getUsername()+ "&password=" + u.getPassword();
        System.out.println(url);
        req = new ConnectionRequest(url,false);
        req.setUrl(url);
        req.addResponseListener( (e) -> {
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData())+"";
            
            try {
               
                   
                    Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    if (user.size()>0) {
                         u.setId(((int)Float.parseFloat(user.get("id").toString())));
                     u.setEmail(user.get("email").toString());
                     u.setNom(user.get("nom").toString());
                     u.setImage(user.get("image").toString());
                     u.setSexe(user.get("sexe").toString());
                     u.setAuth(((int)Float.parseFloat(user.get("auth").toString())));
                     u.setAge(((int)Float.parseFloat(user.get("age").toString())));
                     u.setNum(((int)Float.parseFloat(user.get("numtel").toString())));
                    
                     
                        
                       // new HomeForm(r).show();
                       System.out.println("Bienvenue");
                       
                    
                }
                
            }catch (Exception ex){
                System.out.println(ex);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       return u ;
    }
    
    
    public String NewPw(String email) {
        
        
        
         String url = Statics.BASE_URL + "/recupJSON?email="+email; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener( (e) -> {
            JSONParser j = new JSONParser();
             json = new String(req.getResponseData());
              
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return json;
      
    
    }
     
    
    public boolean UpdateUser(Utilisateur u) {
        
        
        
         String url = Statics.BASE_URL + "/UpdateUserJSON/"+u.getId()+"?email=" + u.getEmail()  + "&nom=" + u.getNom()  + "&sexe=" + u.getSexe()  + "&age=" 
                 +u.getAge()  + "&numtel=" + u.getNum(); //création de l'URL
         
         System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                json = new String(req.getResponseData());
                req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        
        
        
        return resultOK;
    }
     
}
