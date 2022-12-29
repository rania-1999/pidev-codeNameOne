/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.L10NManager;
import com.codename1.location.Location;
import com.codename1.processing.Result;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author Bechir
 */
public class localisation extends Form {
     public localisation() {
Form hi = new Form("tunis", new TableLayout(16, 2));
L10NManager l10n = L10NManager.getInstance();
hi.add("format(double)").add(l10n.format(11.11)).
    add("format(int)").add(l10n.format(33)).
    
    add("formatDateLongStyle").add(l10n.formatDateLongStyle(new Date())).
    add("formatDateShortStyle").add(l10n.formatDateShortStyle(new Date())).
    add("formatDateTime").add(l10n.formatDateTime(new Date())).
    add("formatDateTimeMedium").add(l10n.formatDateTimeMedium(new Date())).
    add("formatDateTimeShort").add(l10n.formatDateTimeShort(new Date())).
    
    add("parseDouble").add(l10n.format(l10n.parseDouble("34.35"))).
    add("parseInt").add(l10n.format(l10n.parseInt("56"))).
    add("parseLong").add("" + l10n.parseLong("4444444"));
hi.show();
     }
}