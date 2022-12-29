package com.mycompany.myapp.gui;


import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.contacts.Contact;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.services.ServiceNews;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author essia
 */
public class ListNewsForm extends Form {
    Form current;
    
     public ListNewsForm() {
           current = this ; 
        setTitle("List News");
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceNews.getInstance().getAllNews().toString());
//        add(sp);






            ArrayList<News> listNews= ServiceNews.getInstance().getAllNews();
            for ( News n: listNews){
//                String urlImage = "back-logo.jpeg";
//                Image placeHolder =Image.createImage(120,90);
//                EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
//                URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
//                  int height = Display.getInstance().convertToPixels(11.5f);
//        int width = Display.getInstance().convertToPixels(15f);
//        Button Image = new Button(urlim.fill(width,height));
//        Image.setUIID("Label");
//        
//        

                
                int deviceWidth = Display.getInstance().getDisplayWidth(); 
        Image placeholder = Image.createImage(120,90); 
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false); 
        
        String urlImage = "http://127.0.0.1:8000/uploads/images/"+n.getURLImg();
        System.out.println(n.getURLImg());
        URLImage imgUrl = URLImage.createToStorage(encImage, "Large_" + urlImage,urlImage,URLImage.RESIZE_SCALE);
                 ImageViewer img = new ImageViewer(imgUrl);
                  int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(15f);
                 Button Image = new Button(imgUrl.fill(width,height));
        Container cnt=BorderLayout.west(Image);
        String contenu= n.getContenu();
        int length = contenu. length()/3; 
        TextArea ta = new TextArea(n.getTitre()+"\n"+n.getContenu().substring(0, n.getContenu().length() / 20));
            ta.setEditable(false);
  Label Details = new Label();
                Details.setUIID("NewsTOPLINE");
                Style  DetailsStyle = new Style(Details.getUnselectedStyle());
                DetailsStyle.setBgColor(0xf7ad02);
                FontImage nFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_COMMENT,DetailsStyle )  ;
                Details.setIcon(nFontImage);
                Details.setTextPosition(LEFT);
                Details.addPointerPressedListener(l->{
                    System.out.println("DETAAAAILS");
               new NewsById(n,current).show();
                });
//                ScaleImageLabel image = new ScaleImageLabel(urlim);
//                Container containerimg = new Container();
//                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
                
            cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseY(ta),
                    BoxLayout.encloseY(Details)));
            add(cnt);
                
              
                       
            }
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
