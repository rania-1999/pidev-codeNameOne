/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SliderBridge;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rania arafa
 */
public class Download extends Form {
    public Download(){
      Form hi = new Form("Download Progress", new BorderLayout());
     int mm = Display.getInstance().convertToPixels(4);
     
     
     
     
  EncodedImage placeholder = EncodedImage.createFromImage(com.codename1.ui.Image.createImage(mm * 10, mm * 10, 0), false);
           com.codename1.ui.Image icon2 = URLImage.createToStorage(placeholder, "icon2", "http://www.riadmehdi.net/wp-content/uploads/2018/11/csm_img-event_54745635d1.jpg");
ArrayList<Map<String, Object>> data = new ArrayList<>();

  data.add(createListEntry(icon2));
       
  DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
  MultiList ml = new MultiList(model);
Slider progress = new Slider();
Button download = new Button("Download");
download.addActionListener((e) -> {
    ConnectionRequest cr = new ConnectionRequest("https://phantom-marca.unidadeditorial.es/3ac9762db6b0c864eac4bfd2209d8acb/resize/1320/f/jpg/assets/multimedia/imagenes/2021/05/05/16202488146234.jpg", false);
    SliderBridge.bindProgress(cr, progress);
    NetworkManager.getInstance().addToQueueAndWait(cr);
    if(cr.getResponseCode() == 200) {
        hi.add(BorderLayout.CENTER, new ScaleImageLabel(EncodedImage.create(cr.getResponseData())));
        hi.revalidate();
    }
});
hi.add(BorderLayout.SOUTH, progress).add(BorderLayout.NORTH, download);
          hi.add(BorderLayout.CENTER, ml); 

hi.show();  
    }
    private Map<String, Object> createListEntry(  com.codename1.ui.Image icon) {
  Map<String, Object> entry = new HashMap<>();
 
  entry.put("icon", icon);
  return entry; 
   
}
    
}
