/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import static com.codename1.io.Log.p;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.mycompany.myapp.entities.participant;
import com.mycompany.myapp.entities.partie;
import com.mycompany.myapp.gui.HomeForm;
import com.mycompany.myapp.gui.MyProfile;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Bechir
 */
public class ServicePartie extends Form {
    
    public ArrayList<partie> partie;
    
    public static ServicePartie instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
 public ArrayList<partie> parseTasks(String jsonText){
            
               partie t= new partie();
            Container C=new Container();
            C.setLayout(new BorderLayout());
            
            
            
              Form hi = new Form("partilist", new BorderLayout());

  int mm = Display.getInstance().convertToPixels(3);
  EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
           Image icon2 = URLImage.createToStorage(placeholder, "icon2", "https://png.pngtree.com/png-clipart/20190614/original/pngtree-world-cup-football-athlete-referee-png-image_3803557.jpg");


ArrayList<Map<String, Object>> data = new ArrayList<>();
            
            
        try {
           
            partie=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
          
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
              
                
               
                
                t.setEquipea(obj.get("equipea").toString());
               t.setEquipeb(obj.get("equipeb").toString());
                t.setTour(obj.get("tour").toString());
                    
                 data.add(createListEntry(t.getEquipea(),t.getEquipeb(),t.getTour(),icon2));             
             
                partie.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
  MultiList ml = new MultiList(model);
  Button retour = new Button("retour");





          hi.add(BorderLayout.CENTER, ml); 
        //addEvenement.addActionListener(e -> new AddEvenement().show());
                retour.addActionListener(e-> new MyProfile().show());

    hi.add(BorderLayout.NORTH, retour);


            hi.show();
    hi.getContentPane().animateLayout(200);


        return partie;
        
    }
    
    public ArrayList<partie> getAllPartie(){
        String url = Statics.BASE_URL+"/afficherPartiejson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                partie = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return partie;
    }
      private ServicePartie() {
         req = new ConnectionRequest();
    }

    public static ServicePartie getInstance() {
        if (instance == null) {
            instance = new ServicePartie();
        }
        return instance;
}

    
    
    private Map<String, Object> createListEntry(String equipea, String equipeb,String tour, Image icon) {
  Map<String, Object> entry = new HashMap<>();
  entry.put("Line1", equipea);
    
  entry.put("Line2", equipeb);
  entry.put("Line3", tour);
 // entry.put("Line4", date);
  

  entry.put("icon", icon);
  return entry; 
    
}
    
    
    
}
    
    
    
    
    
    


    

