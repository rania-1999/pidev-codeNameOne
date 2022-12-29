/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;

import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.utils.UserSession;


/**
 *
 * @author 21699
 */
public class ReclamationForm  extends Form{
    
     public ReclamationForm(Form previous) {
        setTitle("Reclamation");
        setLayout(BoxLayout.y());
        
        TextField tfObjet = new TextField("","Objet");
        TextField tfMessage = new TextField("","Message");
      
        
        Button sendBTN = new Button("Envoyer");
        
        sendBTN.addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent evt) {
            if ((tfObjet.getText().length()==0) || (tfMessage.getText().length()==0)) {
                Dialog.show("Alert","Veuillez remplir les champs ", new Command("OK"));
            }
            else {
            try {
                       Reclamation r = new Reclamation(UserSession.getID(),tfObjet.getText(),tfMessage.getText(),null,null);
            
                        if( ServiceReclamation.getInstance().AddReclamation(r))
                            Dialog.show("Success","Reclamation envoyé",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Verifier les champs", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Verifier les données", new Command("OK"));
                    }
            }
        }
    });
        addAll(tfObjet,tfMessage,sendBTN);
        sidemenu();
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
    }
     public void sidemenu() {
         Resources theme;
         theme = UIManager.initFirstTheme("/theme");
        Toolbar tb = getToolbar();
        Image icon = theme.getImage("logo.png"); 
        Container topBar = BorderLayout.west(new Label(icon));
       // topBar.add(BorderLayout.SOUTH, new Label("Cool App Tagline...", "SidemenuTagline")); 
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Mon profil", FontImage.MATERIAL_SETTINGS, e -> new MyProfile().show() ); 
        tb.addMaterialCommandToSideMenu("News", FontImage.MATERIAL_INFO, e -> new ListNewsForm().show() );
        tb.addMaterialCommandToSideMenu("Reclamation", FontImage.MATERIAL_MESSENGER, e ->  new Reclamations().show());
        tb.addMaterialCommandToSideMenu("Evenement", FontImage.MATERIAL_EVENT, e -> {});
        tb.addMaterialCommandToSideMenu("Match", FontImage.MATERIAL_INFO, e -> {});
        tb.addMaterialCommandToSideMenu("Terrain", FontImage.MATERIAL_GPS_FIXED, e -> {});
        tb.addMaterialCommandToSideMenu("Coachs", FontImage.MATERIAL_GROUPS, e -> {});
        tb.addMaterialCommandToSideMenu("Arbitres", FontImage.MATERIAL_SPORTS, e -> {});
        tb.addMaterialCommandToSideMenu("Deconnexion", FontImage.MATERIAL_LOGOUT, e -> {
         UserSession US =  UserSession.getInstance(UserSession.getID(),UserSession.getUsername(),UserSession.getPassword(),UserSession.getEmail(),UserSession.getNom(),UserSession.getAge(),UserSession.getSexe(),UserSession.getAuth(),UserSession.getURLImg(),UserSession.getNumTel(),UserSession.getNotif());
           
        US.cleanUserSession();
            new Login().show();
        });
    }
    
}
