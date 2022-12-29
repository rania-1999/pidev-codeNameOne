/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
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
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceUtilisateur;
import com.mycompany.myapp.utils.UserSession;

/**
 *
 * @author 21699
 */
public class Login extends Form{
    Form current ; 
     public Login() {
         current = this ;
        setTitle("Connexion");
        setLayout(BoxLayout.y());
        
        int deviceWidth = Display.getInstance().getDisplayWidth(); // yekho f taille mtaa l'appareil 
        Image placeholder = Image.createImage(deviceWidth / 2, deviceWidth / 2); // 
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false); // placeholder li aamalneha f ligne li kbal 
      
        URLImage imgUrl = URLImage.createToStorage(encImage, "Large_" + "http://127.0.0.1:8000/uploads/images/0ad57d6eb727c43fbc5ac037cfda8556.jpeg", "http://127.0.0.1:8000/uploads/images/0ad57d6eb727c43fbc5ac037cfda8556.jpeg", URLImage.RESIZE_SCALE);

         ImageViewer img = new ImageViewer(imgUrl);
        
        
        TextField tfUsername = new TextField("","Username");
        TextField tfPassword = new TextField("","Password",50,TextField.PASSWORD);
        
        
        Button CnxBTN = new Button("Connexion");
         Button btnInscription = new Button("Creer un compte");
         Button btnForgotPw = new Button("Mot de passe oublié ? ");
        
        CnxBTN.addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent evt) {
            if ((tfUsername.getText().length()==0) || (tfPassword.getText().length()==0) ) {
                Dialog.show("Alert","Veuillez saisir vos informations ", new Command("OK"));
            }
            else {
            try {
                       Utilisateur user = new Utilisateur();
                       user.setUsername(tfUsername.getText());
                       user.setPassword(tfPassword.getText());
            
                     Utilisateur u =  ServiceUtilisateur.getInstance().loginUser(user);
                     if (u.getId()== 0 ) {
                          Dialog.show("ERROR", "Veuillez verifier les informations", new Command("OK"));
                         new Login().show();
                       
                     }
                     else {
                      UserSession US = UserSession.getInstance(u.getId(),u.getUsername(),u.getPassword(),u.getEmail(),u.getNom(),u.getAge(),u.getSexe(),u.getAuth(),u.getImage(),u.getNum(),u.getNotif());
                
                      System.out.println(US);
                      new ListNewsForm().show() ;
                     }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Veuillez verifier les informations", new Command("OK"));
                    }
            }
        }
    });
        btnInscription.addActionListener(e-> new Inscription(this).show());
        btnForgotPw.addActionListener(e->new ForgotPwForm().show());
         Container LogForm = BoxLayout.encloseY(
                 
                tfUsername,
                createLineSeparator(),
                tfPassword,
                createLineSeparator(),
                CnxBTN,
                btnForgotPw,
                createLineSeparator(),
                btnInscription
        );
         LogForm.setScrollableY(true);
         add(img);
         add(LogForm);
       
     
    }
     
       public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
}
