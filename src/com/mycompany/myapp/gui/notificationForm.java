/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.contacts.Contact;
import com.codename1.contacts.ContactsManager;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Bechir
 */
public class notificationForm extends Form{

    public notificationForm() {
      
      Message m = new Message("Body of message");
        String textAttachmentUri = null;
m.getAttachments().put(textAttachmentUri, "text/plain");
        String imageAttachmentUri = null;
m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {"bechir.mlaouah@esprit.tn"}, "hello", m);
      
    

m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
boolean success = m.sendMessageViaCloudSync("Codename One", "bechir.mlaouah@esprit.tn", "bechir", "Message Subject",
                            "Check out Codename One at https://www.codenameone.com/");
    
}
}
    

