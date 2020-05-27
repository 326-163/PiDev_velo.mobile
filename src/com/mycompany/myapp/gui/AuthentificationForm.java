/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author nahawnd
 */


public class AuthentificationForm extends Form {  
    Form current;
    private Resources theme;
 
    public AuthentificationForm(Form previous) {
        current=this;
        setTitle("Login");
        setLayout(BoxLayout.y());
       
       Form F1 = new Form("Hi World", new FlowLayout(CENTER, CENTER));
        Container cnt=new Container(BoxLayout.y());
        TextField tlogin=new TextField("","login");
        TextField tpass=new TextField("", "password");
        tpass.setConstraint(TextField.PASSWORD);
        Button btnva=new Button("valider");
        cnt.add(tlogin);
        cnt.add(tpass);
        cnt.add(btnva);
        F1.add(cnt);
        F1.show();
        
        Form F2=new Form("",new FlowLayout(CENTER, CENTER));
        Label lbnom=new Label();
        F2.add(lbnom);
        btnva.addActionListener((e)->{
        if(tlogin.getText().equals("nahawand")
                && tpass.getText().equals("admin"))
        {
            lbnom.setText("Bienvenue :"+tlogin.getText());
        F2.show();
        }
        else{
        Dialog.show("error","login ou pwd invalid","ok","cancel");
        }
        });
        
        Form F3=new Form("Home",BoxLayout.y());
        ImageViewer img=new ImageViewer(theme.getImage("nader.jpg"));
        SpanLabel sp=new SpanLabel("Bienvenue dans notre app velo.tn");
        F3.add(img);
        F3.add(sp);
        
        
          
        F3.getToolbar().addCommandToOverflowMenu("Exit",null, ev->{
        Display.getInstance().exitApplication();
        });
        
           F1.getToolbar().addCommandToLeftBar("Back", null, ev -> {
            F1.show();
        });
    }
}
