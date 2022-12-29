/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.utils.UserSession;


/**
 *
 * @author 21699
 */
public class Reclamations extends Form{
    Form current;
    public Reclamations() {
        current = this ; 
    setTitle("Accueil");
    setLayout(BoxLayout.y());
    
    System.out.println("Bienuvenue"+UserSession.getUsername());
    
   
   
    
    Button btnReclamer = new Button("Reclamer");
    Button btnListRecs = new Button("Mes reclamations");
    
    
    btnReclamer.addActionListener(e-> new ReclamationForm(current).show());
    btnListRecs.addActionListener(e-> new ListeReclamation(current).show());
    
    // Sidemenu 
    
    sidemenu();
    //
    addAll(btnReclamer,btnListRecs);
    
    
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
