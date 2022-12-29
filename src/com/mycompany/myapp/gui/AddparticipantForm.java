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
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.participant;
import com.mycompany.myapp.services.Serviceajoutparticipant;

/**
 *
 * @author bhk
 */
public class AddparticipantForm extends Form{

    public AddparticipantForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        
        setTitle("Ajouter participant");
        setLayout(BoxLayout.y());
        
        TextField tfnom = new TextField("","participantNom");
        TextField tfprenom= new TextField("", "participantPrenom");
       
        
        Button btnValider = new Button("Add Participant");
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)||(tfprenom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        participant p = new participant (tfprenom.getText(), tfnom.getText());
                       if( Serviceajoutparticipant.getInstance().addparticipant(p))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                       else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfnom,tfprenom,btnValider);
        getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
