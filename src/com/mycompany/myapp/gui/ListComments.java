/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.services.ServiceComments;
import com.mycompany.myapp.utils.UserSession;
import java.util.ArrayList;

/**
 *
 * @author essia
 */
public class ListComments extends Form {
     public ListComments(Form previous ,int id) {
        setTitle("List Comments");
        

                
              
        
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceNews.getInstance().getAllNews().toString());
//        add(sp);
            ArrayList<Commentaire> listComments= ServiceComments.getInstance().getAllComments(id);
            for ( Commentaire c: listComments){
             
                   c.setIdUtilisateur(ServiceComments.getInstance().getIDUSER(c.getId())); 

                
                  
                String urlImage = "back-logo.jpeg";
                Image placeHolder =Image.createImage(120,90);
                EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
                URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
                  int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(15f);
        Button Image = new Button(urlim.fill(width,height));
        Image.setUIID("Label");
        
        
        Container cnt=BorderLayout.west(Image);
            TextArea ta = new TextArea(c.getContenu());

            ta.setEditable(false);
  
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                Container containerimg = new Container();
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
                 
        Label Supprimer = new Label(" ");
        Supprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(Supprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0x000000);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        Supprimer.setIcon(suprrimerImage);
        Supprimer.setTextPosition(RIGHT);
        Supprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");

            if(dig.show("Suppression","Vous voulez supprimer ce commentaire ?","Annuler","Oui")) {
                dig.dispose();

            }
            else {
                dig.dispose();
                 }
                if(ServiceComments.getInstance().deletecomment(c.getId())) {
                   new ListComments(previous,id).show();                    
                }
           
        });
        Label Modifier = new Label(" ");
        Modifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(Modifier.getUnselectedStyle());
        modifierStyle.setFgColor(0x000000);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        Modifier.setIcon(mFontImage);
        Modifier.setTextPosition(LEFT);
        
        
        Modifier.addPointerPressedListener(l -> {
              
            new ModifierCommentaireForm(previous,c,id).show();
        });
        
        
        
                
cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseY(ta),
         BoxLayout.encloseX(Supprimer),
         BoxLayout.encloseX(Modifier)
         ));
add(cnt); 
            }
     
            
                  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

     

    
     }}
