/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceUtilisateur;


/**
 *
 * @author 21699
 */
public class Inscription extends Form{

    public Inscription(Form previous) {
        setTitle("Inscription");
        setLayout(BoxLayout.y());
        
        TextField tfUsername = new TextField("","Username");
        Label lbUsername = new Label("");
        TextField tfPassword = new TextField("","Password",50,TextField.PASSWORD);
      //  PasswordField pfPassword = new PasswordField();
        TextField tfEmail = new TextField("","Email",50,TextField.EMAILADDR);
        TextField tfNom = new TextField("","Nom");
        TextField tfAge = new TextField("","Age");
      
         ComboBox<String> Sexe = new ComboBox();
         Sexe.addItem("Homme");
         Sexe.addItem("Femme");
         
        TextField tfNum = new TextField("","Numero de telephone",8,TextField.PHONENUMBER);
        
        Button InscriBTN = new Button("S'inscrire");
        
        // begin validator 
        Validator val = new Validator();
        val.addConstraint(tfUsername, new LengthConstraint(3)).
         addConstraint(tfPassword, new LengthConstraint(3)).
         addConstraint(tfNom, new LengthConstraint(3)).
         addConstraint(tfNum, new NumericConstraint(false)).
         addConstraint(tfEmail, RegexConstraint.validEmail());
        val.addSubmitButtons(InscriBTN);
        // end validator 
        
        InscriBTN.addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent evt) {
             if(tfNum.getText().length() != 8) {
                 Dialog.show("Alert","Veuillez entrer un numero à 8 chiffres ", new Command("OK"));
            }
            else {
            try {
                       Utilisateur u = new Utilisateur(tfUsername.getText(),tfPassword.getText(),tfEmail.getText(),tfNom.getText(),Sexe.getSelectedItem(),Integer.parseInt(tfAge.getText()),Integer.parseInt(tfNum.getText()),"",null);
            
                        if( ServiceUtilisateur.getInstance().AddUser(u)) {
                            Dialog.show("Success","Votre compte a été crée",new Command("OK"));
                         //   Sms s=new Sms();
                           // s.send(tfNum.getText());
                            new Login().show();
                        }
                       
                           
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "ERROR", new Command("OK"));
                    }
            }
        }
    });
        addAll(tfUsername,lbUsername,tfPassword,tfEmail,tfNom,tfAge,Sexe,tfNum,InscriBTN);
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
    }
    
    
}
