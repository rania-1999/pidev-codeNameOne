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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import static com.codename1.ui.events.ActionEvent.Type.Calendar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.gui.HomeForm;
import com.mycompany.myapp.gui.MyProfile;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.gui.Download;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rania arafa
 */
public class ServiceEvenement extends Form {

        public ArrayList<Evenement> evenement;

    public static ServiceEvenement instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
        public ArrayList<Evenement> parseTasks(String jsonText){
            Evenement t= new Evenement();
            Container C=new Container();
            C.setLayout(new BorderLayout());

              Form hi = new Form("Evenement", new BorderLayout());

  int mm = Display.getInstance().convertToPixels(4);
  EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
           Image icon2 = URLImage.createToStorage(placeholder, "icon2", "http://www.riadmehdi.net/wp-content/uploads/2018/11/csm_img-event_54745635d1.jpg");


ArrayList<Map<String, Object>> data = new ArrayList<>();
        try {
            evenement=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
               float id = Float.parseFloat(obj.get("nbrePL").toString());
               t.setNbrePL((int)id);
                t.setNomEvenement(obj.get("nomevenement").toString());
               t.setLieu(obj.get("lieu").toString());
                          t.setDescripEvenement(obj.get("descripevenement").toString());
                        
t.setDate(obj.get("dateevenement").toString());
                     



  data.add(createListEntry(t.getNomEvenement(),t.getDescripEvenement(),t.getLieu(),t.getDate(),t.getNbrePL(),icon2));
           
  
  
 
                       evenement.add(t);
                            

            }
            
 
  
  
            
        } catch (IOException ex) {
            
        }
  DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
  MultiList ml = new MultiList(model);
  Button retour = new Button("retour");

Button a = new Button ("ligue des champions");
a.addActionListener(x-> new Download());
hi.add(BorderLayout.SOUTH,a);
          hi.add(BorderLayout.CENTER, ml); 
        //addEvenement.addActionListener(e -> new AddEvenement().show());
                retour.addActionListener(x->new MyProfile().show());

    hi.add(BorderLayout.NORTH, retour);


            hi.show();
    //hi.getContentPane().animateLayout(20000);

        return evenement;
     

      

    }

    public ArrayList<Evenement> getAllEvenements(){
        String url = Statics.BASE_URL+"/afficherEVjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                evenement = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return evenement;
    }
      private ServiceEvenement() {

         req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }
     
    private Map<String, Object> createListEntry(String name, String descrip, String lieu, String date, int nbre, Image icon) {
  Map<String, Object> entry = new HashMap<>();
  entry.put("Line1", name);
    entry.put("Line2", descrip);
  entry.put("Line3", lieu);
entry.put("line4",date);
entry.put("line5", nbre);
  entry.put("icon", icon);
  return entry; 
   
}
}