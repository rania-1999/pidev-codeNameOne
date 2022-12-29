/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URISyntaxException;


/**
 *
 * @author essia
 */
public class Sms {
    public static final String ACCOUNT_SID = "AC56a2eca45982bc1195f8a19a2d7836e6";
    public static final String AUTH_TOKEN = "eca00626ba9f1d78c18b25d1db4815ad";

    public  void send(String num)  {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message;
        message = Message
                .creator(new PhoneNumber("+216"+num), // to
                        new PhoneNumber("+12565024685"), // from
                        "Inscription effectuée avec succès  ")
                .create();
        System.out.println(message.getSid());
        System.out.println("notif");
    }
    
}
