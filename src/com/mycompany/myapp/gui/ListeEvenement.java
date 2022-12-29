/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ServiceEvenement;

/**
 *
 * @author rania arafa
 */
public class ListeEvenement extends Form{
    public ListeEvenement(){
     this.setTitle("Evenement List");
        this.setLayout(BoxLayout.y());
        
        SpanLabel tasksListSP = new SpanLabel();
        tasksListSP.setText(ServiceEvenement.getInstance().getAllEvenements().toString());
        
        this.add(tasksListSP);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
}
}
