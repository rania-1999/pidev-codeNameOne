/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceUtilisateur;
import com.mycompany.myapp.utils.UserSession;
import com.mycompany.myapp.gui.localisation;
import java.util.ArrayList;

/**
 *
 * @author 21699
 */
public class MyProfile extends Form{
    public MyProfile() {
        
         setTitle("Mon profil");
        setLayout(BoxLayout.y());
        
        //
         int deviceWidth = Display.getInstance().getDisplayWidth(); 
        Image placeholder = Image.createImage(deviceWidth / 2, deviceWidth / 2); // 
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false); 
      
        URLImage imgUrl = URLImage.createToStorage(encImage, "Large_" + "http://127.0.0.1:8000/uploads/images/"+UserSession.getURLImg(), "http://127.0.0.1:8000/uploads/images/"+UserSession.getURLImg(), URLImage.RESIZE_SCALE);

         ImageViewer img = new ImageViewer(imgUrl);
        
        //
        ArrayList<Utilisateur> UsersList = ServiceUtilisateur.getInstance().getAllUsers();
         TextField tfUsername = new TextField("","username");
         tfUsername.setEditable(false);
         TextField tfEmail = new TextField("","Email");
         TextField tfNom = new TextField("","Nom");
         TextField tfNum = new TextField("","Numero de téléphone");
         TextField tfAge = new TextField("","Age");
         ComboBox<String> Sexe = new ComboBox();
         Sexe.addItem("Homme");
         Sexe.addItem("Femme");
        
        for (Utilisateur u : UsersList)  {
        if (u.getId() == UserSession.getID()) {
           tfUsername.setText(u.getUsername());
           tfEmail.setText(u.getEmail());
           tfNom.setText(u.getNom());
           tfNum.setText(Integer.toString(u.getNum()));
           tfAge.setText(Integer.toString(u.getAge()));
           
        }
    }
        
        
        
        
         Button SaveBTN = new Button("Enregistrer");
         Button BackBTN = new Button("Annuler");
        // begin validator 
        Validator val = new Validator();
        val.addConstraint(tfUsername, new LengthConstraint(3)).
         
         addConstraint(tfNom, new LengthConstraint(3)).
         addConstraint(tfNum, new NumericConstraint(false)).
         addConstraint(tfEmail, RegexConstraint.validEmail());
        val.addSubmitButtons(SaveBTN);
        // end validator
        
        SaveBTN.addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent evt) {
             if(tfNum.getText().length() != 8) {
                 Dialog.show("Alert","Veuillez entrer un numero à 8 chiffres ", new Command("OK"));
            }
            else {
            try {
                         Utilisateur u = new Utilisateur(UserSession.getID(),UserSession.getUsername(),UserSession.getPassword(),tfEmail.getText(),tfNom.getText(),Sexe.getSelectedItem(),Integer.parseInt(tfAge.getText()),Integer.parseInt(tfNum.getText()),UserSession.getURLImg(),null);
            
                        if( ServiceUtilisateur.getInstance().UpdateUser(u)) {
                            Dialog.show("Success","Modification enregistré",new Command("OK"));
                         //   Sms s=new Sms();
                           // s.send(tfNum.getText());
                            new ListNewsForm().show();
                        }
                       
                           
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "ERROR", new Command("OK"));
                    }
            }
        }
    });
        
        BackBTN.addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent evt) {
             new ListNewsForm().show();
        }
    });
        
         Container ModifForm = BoxLayout.encloseY(
                 
                tfUsername,
                createLineSeparator(),
                tfEmail,
                createLineSeparator(),
                 tfNom,
                createLineSeparator(),
                 tfAge,
                createLineSeparator(),
                 tfNum,
                createLineSeparator(),
                 Sexe,
                createLineSeparator(),
                SaveBTN,
                BackBTN
        );
         ModifForm.setScrollableY(true);
         add(img);
         add(ModifForm);
        
       
        
    
        
        sidemenu();
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
        tb.addMaterialCommandToSideMenu("Evenement", FontImage.MATERIAL_EVENT, e ->  new ListeEvenement(){});
        tb.addMaterialCommandToSideMenu("Match", FontImage.MATERIAL_INFO, e -> new ListpartieForm() {});
     tb.addMaterialCommandToSideMenu("Participant", FontImage.MATERIAL_INFO, e -> new ListparticipantForm(){});

        tb.addMaterialCommandToSideMenu("Terrain", FontImage.MATERIAL_GPS_FIXED, e -> {});
        tb.addMaterialCommandToSideMenu("Coachs", FontImage.MATERIAL_GROUPS, e -> {});
        tb.addMaterialCommandToSideMenu("Arbitres", FontImage.MATERIAL_SPORTS, e ->  new ListeArbitre(){});
     tb.addMaterialCommandToSideMenu("recorder", FontImage.MATERIAL_INFO, e ->new Capturee()  {});
     tb.addMaterialCommandToSideMenu("localisation", FontImage.MATERIAL_INFO, e ->new localisation() {});
     tb.addMaterialCommandToSideMenu("pdf", FontImage.MATERIAL_INFO, e ->new pdf() {});

        tb.addMaterialCommandToSideMenu("Deconnexion", FontImage.MATERIAL_LOGOUT, e -> {
         UserSession US =  UserSession.getInstance(UserSession.getID(),UserSession.getUsername(),UserSession.getPassword(),UserSession.getEmail(),UserSession.getNom(),UserSession.getAge(),UserSession.getSexe(),UserSession.getAuth(),UserSession.getURLImg(),UserSession.getNumTel(),UserSession.getNotif());
           
        US.cleanUserSession();
            new Login().show();
        });
    }
    
     public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
}
