/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.utils.UserSession;
import java.util.ArrayList;

/**
 *
 * @author 21699
 */
public class ListeReclamation extends Form {

    public ListeReclamation(Form previous) {
         setTitle("Mes reclamations");
      ArrayList<Reclamation> ListeRecla = ServiceReclamation.getInstance().getAllRecs(UserSession.getID());
        
      
      for (Reclamation r : ListeRecla) {
          Container c = BorderLayout.west(this);
          
          Label info = new Label(r.getObjet());
          
         
          
          
          // BEGIN SUPPRESSION
           Label Supprimer = new Label(" ");
        Style supprmierStyle = new Style(Supprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        Supprimer.setIcon(suprrimerImage);
        Supprimer.setTextPosition(RIGHT);
        Supprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");

            if(dig.show("Suppression","Voulez vous vraiment annuler votre reclamation?","Annuler","Oui")) {
                dig.dispose();

            }
            else {
                dig.dispose();
                 }
                if(ServiceReclamation.getInstance().DeleteRec(r.getIdrec())) {
                   new Reclamations().show();                    
                }
           
        });
        
        // END SUPPRESSION
        
        c.add(BorderLayout.WEST, BoxLayout.encloseX(info,Supprimer));
        
        add(c); 
        
         System.out.println(getComponentIndex(c));   
         System.out.println(getComponentIndex(info));  
         System.out.println(getComponentIndex(Supprimer));  
         
      }
      
         // BEGIN SEARCH
          
          Toolbar.setGlobalToolbar(true);
            Style s = UIManager.getInstance().getComponentStyle("Title");

            TextField searchField = new TextField("", "Rechercher.."); 
            searchField.getHintLabel().setUIID("Title");
            searchField.setUIID("Title");
            searchField.getAllStyles().setAlignment(Component.LEFT);
            getToolbar().setTitleComponent(searchField);
            FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH,
             s);
            searchField.addDataChangeListener((i1, i2) -> { 
             String t = searchField.getText();
             if(t.length() < 1) {
             for(Component cmp : getContentPane()) {
             cmp.setHidden(false);
             cmp.setVisible(true);
             }
             } else {
             t = t.toLowerCase();
             for(Component cmp : getContentPane()) {
                  System.out.println(((Container) cmp).getComponentCount());
             String val = null;
              val = ((Label) ((Container)((Container) cmp).getComponentAt(0)).getComponentAt(0)).getText();
            System.out.println( val );
             boolean show = val != null && val.toLowerCase().indexOf(t) >
             -1;
             cmp.setHidden(!show); 
             cmp.setVisible(show);
             }
             }
             getContentPane().animateLayout(250);
            });
            getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
             searchField.startEditingAsync(); 
            });
          // END SEARCH
         getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
    
    
}
