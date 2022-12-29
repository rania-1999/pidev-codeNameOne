/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author essia
 */
public class CensorWords {
      public String CensoredComment(String comment) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\21699\\Desktop\\badwords.txt"));
            String s;
            List<String> words = new ArrayList<String>();
            while ((s = br.readLine()) != null) {
                words.add(s);
            }

            for (String word : words) {
                Pattern rx = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE);
                comment = rx.matcher(comment).replaceAll(new String(new char[word.length()]).replace('\0', '*'));
            }
            return comment;

        } catch (Exception ex) {

            System.out.println("failed to read txt");
            System.out.println(ex);
        }
        return comment;
    }
    
}
