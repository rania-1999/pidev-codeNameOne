/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.services.ServiceComments;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author essia
 */
public class NewsById extends Form {
    Form current;
    public NewsById(News n,Form previous) {
        Resources theme;
        theme = UIManager.initFirstTheme("/theme");
        
        current=this;
          int deviceWidth = Display.getInstance().getDisplayWidth(); 
        Image placeholder = Image.createImage(120,90); 
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false); 
        
        String urlImage = "http://127.0.0.1:8000/uploads/images/"+n.getURLImg();
        System.out.println(n.getURLImg());
        URLImage imgUrl = URLImage.createToStorage(encImage, "Large_" + urlImage,urlImage,URLImage.RESIZE_SCALE);
                 ImageViewer img = new ImageViewer(imgUrl);
                  int height = Display.getInstance().convertToPixels(50.5f);
        int width = Display.getInstance().convertToPixels(70f);
                 Button Image = new Button(imgUrl.fill(width,height));
        TextArea Titre = new TextArea(n.getTitre()+"\n"+n.getContenu());
                    Titre.setEditable(false);

 //String urlImagelike = "like.png";
        System.out.println(n.getURLImg());
        
      Image likeimg =  theme.getImage("like.png"); 
      
                  int height1 = Display.getInstance().convertToPixels(50.5f);
        int width1 = Display.getInstance().convertToPixels(70f);
                 Button Image2 = new Button(likeimg.fill(width1/10,height1/10));
                    Titre.setEditable(false);
                   

                    TextArea Commentaire = new TextArea();
                Label comments=new Label("Ajoutez un commentaire");
                
                Button Valider = new Button("Valider");
                Button CommentsList = new Button("Voir les commentaires");
                Valider.addPointerPressedListener(l->{
                    SpanLabel comment=new SpanLabel();
                    comment.setText("\n"+Commentaire.getText());
                     Container c = BoxLayout.encloseY(comment);
                      if ((Commentaire.getText().length()==0)||(Commentaire.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                            String str = Commentaire.getText();

                        Commentaire co= new Commentaire();
                        CensorWords sw= new CensorWords();
                                 str = sw.CensoredComment(str);
                                                         co.setContenu(str);


                        co.setIdNews(n.getId());
                        
                        co.setIdUtilisateur(UserSession.getID());
                        
                       
                       
                        if( new ServiceComments().addComments(co))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    
                }
                      Label SupprimerNews = new Label();
                        Style Supp= new Style(SupprimerNews.getUnselectedStyle());

    add(c);
    show();
                    
                });
                       CommentsList.addActionListener(e-> new ListComments(previous,n.getId()).show());


         Container c = BoxLayout.encloseY(Image,Titre,comments,Image2,Commentaire,Valider,CommentsList);

    add(c);
    show();
    
    
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());


}
    
}