/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author khaledguedria
 */
public class HomeForm extends Form{
    
    
    public HomeForm(){
        
        this.setTitle("Home Form");
        this.setLayout(BoxLayout.y());
        
        Button listeEvenement = new Button("Liste Evenement");
        Button ListArbitre = new Button("Liste Arbitre");
        Button m=new Button ("media");
        Button map= new Button ("map");
        map.addActionListener(x-> new CameraDemo());
        listeEvenement.addActionListener(e -> new ListeEvenement());
        ListArbitre.addActionListener(x-> new ListeArbitre());
        m.addActionListener(x->new Capturee());
        this.addAll(listeEvenement, ListArbitre,m,map);
        
    }
    
}
