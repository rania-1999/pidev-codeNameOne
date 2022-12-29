package com.mycompany.myapp.gui;


import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.services.ServiceNews;
import java.net.URISyntaxException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author essia
 */
public class AddNewsForm extends Form {
    public AddNewsForm(Form previous){
         setTitle("Ajoutez une nouvelle actualité");
        setLayout(BoxLayout.y());
        
        TextArea tfTitre = new TextArea("Titre");
        TextArea tfContenu= new TextArea( "Contenu");
       ComboBox ts = new ComboBox("Football", "Handball", "Rugby");
        Button btnValider = new Button("Ajouter");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length()==0)||(tfContenu.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        News n = new News();
                        n.setTitre(tfTitre.getText());
                        n.setContenu(tfContenu.getText());
                        n.setTypeSport(ts.getSelectedItem().toString());
                       
                       
                        if( new ServiceNews().addNews(n))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
       
        addAll(tfTitre,tfContenu,ts,btnValider);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
        
        
    }
    
    
}
