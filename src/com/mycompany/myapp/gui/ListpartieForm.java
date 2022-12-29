/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.ServicePartie;

/**
 *
 * @author Bechir
 */
public class ListpartieForm extends Form {
      public ListpartieForm() {
        setTitle("List parties");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServicePartie.getInstance().getAllPartie().toString());
        this.add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
    }
    
    
    
    
    
    
}
