/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Arbitre;
import com.mycompany.myapp.gui.AddArbitre;
import com.mycompany.myapp.gui.HomeForm;
import com.mycompany.myapp.gui.MyProfile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author rania arafa
 */
public class ServiceArbitre extends Form {

        public ArrayList<Arbitre> arbitre;

    public static ServiceArbitre instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
        public ArrayList<Arbitre> parseTasks(String jsonText){
            Arbitre t= new Arbitre();
            Container C=new Container();
            C.setLayout(new BorderLayout());

              Form hi = new Form("MultiList", new BorderLayout());

  int mm = Display.getInstance().convertToPixels(3);
  EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
           Image icon2 = URLImage.createToStorage(placeholder, "icon2", "https://png.pngtree.com/png-clipart/20190614/original/pngtree-world-cup-football-athlete-referee-png-image_3803557.jpg");


ArrayList<Map<String, Object>> data = new ArrayList<>();
        try {
            arbitre=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
               // float id = Float.parseFloat(obj.get("id").toString());
               // t.setId((int)id);
                t.setNom(obj.get("nom").toString());
               t.setPrenom(obj.get("prenom").toString());
                              t.setFiliere(obj.get("filiere").toString());
                          t.setImage(obj.get("image").toString());
                        

                              
               t.setDisponibilite(obj.get("disponibilite").toString());
t.setDate(obj.get("updatedAt").toString());



  data.add(createListEntry(t.getNom(),t.getPrenom(),t.getFiliere(),icon2));
           
  
  
 
                       arbitre.add(t);
                            

            }
            
 
  
  
            
        } catch (IOException ex) {
            
        }
  DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
  MultiList ml = new MultiList(model);
  Button retour = new Button("retour");


Button addArbitre=new Button ("vous voulez postuler??");


          hi.add(BorderLayout.CENTER, ml); 
        //addEvenement.addActionListener(e -> new AddEvenement().show());
                retour.addActionListener(x-> new MyProfile().show());
addArbitre.addActionListener (x-> new AddArbitre().show());
    hi.add(BorderLayout.NORTH, retour);
hi.add(BorderLayout.SOUTH, addArbitre);

            hi.show();
    //hi.getContentPane().animateLayout(20000);

        return arbitre;
     

      

    }

    public ArrayList<Arbitre> getAllArbitres(){
        String url = Statics.BASE_URL+"/afficherARjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                arbitre = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return arbitre;
    }
      private ServiceArbitre() {

         req = new ConnectionRequest();
    }

    public static ServiceArbitre getInstance() {
        if (instance == null) {
            instance = new ServiceArbitre();
        }
        return instance;
    }
     
    private Map<String, Object> createListEntry(String name, String prenom, String filiere, Image icon) {
  Map<String, Object> entry = new HashMap<>();
  entry.put("Line1", name);
    entry.put("Line2", prenom);
  entry.put("Line3", filiere);

  entry.put("icon", icon);
  return entry; 
    
}
      
}