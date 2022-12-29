/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Bechir
 */
public class pdf extends Form{
    public pdf( ) {
    Form hi = new Form("PDF Viewer", BoxLayout.y());
Button devGuide = new Button("participant PDF");
devGuide.addActionListener(e -> {
    FileSystemStorage fs = FileSystemStorage.getInstance();
    String fileName = fs.getAppHomePath() + "pdf-participant.pdf";
    if(!fs.exists(fileName)) {
        Util.downloadUrlToStorage("https://artsetmetiers.fr/sites/site_internet/files/2016-11/pdf.pdf", fileName, true);
    }
    Display.getInstance().execute(fileName);
});
hi.add(devGuide);

hi.show();
     }
}
    

