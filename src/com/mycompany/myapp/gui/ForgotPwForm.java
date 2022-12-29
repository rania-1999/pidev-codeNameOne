/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceMail;
import com.mycompany.myapp.services.ServiceUtilisateur;
import java.util.Random;

/**
 *
 * @author 21699
 */
public class ForgotPwForm extends Form{

    public ForgotPwForm() {
        setTitle("Mot de passe oublié");
        setLayout(BoxLayout.y());
        
        TextField tfEmail = new TextField("","Votre adresse email",50,TextField.EMAILADDR);
        
      
        
        Button sendBTN = new Button("Envoyer");
        
        sendBTN.addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent evt) {
            if (tfEmail.getText().length()==0)  {
                Dialog.show("Alert","Veuillez saisir votre adresse Email ", new Command("OK"));
            }
            else {
            try {
                      
                    
                    
                    // Random rand = new Random();
                //    int nb = rand.nextInt(999999);
                   // String NewPw = Integer.toString(nb);
                    String NewPw = ServiceUtilisateur.getInstance().NewPw(tfEmail.getText()) ;
                    ServiceMail SM = new ServiceMail();
                    SM.sendmailfunc(tfEmail.getText(),"Votre nouveau mot de passe est : "+NewPw);
                      Dialog.show("Success","Veuillez verifier votre mail",new Command("OK"));
                   
                    
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "ERROR", new Command("OK"));
                    }
            }
        }
    });
        addAll(tfEmail,sendBTN);
     
    
    }
    
    
    
}
