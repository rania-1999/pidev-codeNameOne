/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.location.Geofence;
import com.codename1.location.GeofenceListener;
import com.codename1.location.LocationManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.mycompany.myapp.entities.participant;
import com.mycompany.myapp.services.ServiceParticipant;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ListparticipantForm extends Form{

    public ListparticipantForm() {

    super("list participant", BoxLayout.y());
  

        this.setTitle("List participant");
        this.setLayout(BoxLayout.y());
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceParticipant.getInstance().getAllParticipants().toString());
        this.add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
        
        
     
  
}
}
