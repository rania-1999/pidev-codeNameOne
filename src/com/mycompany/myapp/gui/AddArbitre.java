/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.ListeAtt;
import com.mycompany.myapp.services.ServiceListeAtt;

/**
 *
 * @author rania arafa
 */
public class AddArbitre extends Form {
    public AddArbitre(){
   this.setTitle("demande");
        this.setLayout(BoxLayout.y());
        
        TextField tfname = new TextField("", "Inserer votre nom");
        TextField tfprenom = new TextField("", "Inserer votre  prenom");
                TextField tfFiliere = new TextField("", "Inserer votre  filiere");
                        TextField tfEmail = new TextField("", "Inserer votre  email");


        Button submitTaskBtn = new Button("postuler");
        submitTaskBtn.addActionListener((evt) -> {
            
            if (tfname.getText().length() ==0 || tfEmail.getText().length()==0) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                try {
                    
                ListeAtt task = new ListeAtt(tfprenom.getText(),tfFiliere.getText(),tfEmail.getText(), tfname.getText());
                    if (ServiceListeAtt.getInstance().addListeAction(task)) {
                        Dialog.show("Success", "Task added successfully.",null, "OK");
                    }
                    
                } catch (NumberFormatException e) {
                    Dialog.show("Alert", "Task's status must be a number.",null, "OK");
                }
                
                
                
            }
            
            
            
        });
        
        this.addAll(tfname, tfprenom,tfFiliere,tfEmail, submitTaskBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
        
        
    } 
}
