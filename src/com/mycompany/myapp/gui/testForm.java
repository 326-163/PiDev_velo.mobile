/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author nahawnd
 */
public class testForm extends Form{
    
       Form current;
    private Resources theme;
 
 public testForm() {
       current = this;
        setTitle("Accueil");
        setLayout(BoxLayout.y());
        
    Form F1 = new Form("Hi World", new FlowLayout(CENTER, CENTER));
        Container cnt=new Container(BoxLayout.y());
        TextField tlogin=new TextField("","login");
        TextField tpassw=new TextField("", "password");
        tpassw.setConstraint(TextField.PASSWORD);
        Button btnva=new Button("valider");
        cnt.add(tlogin);
        cnt.add(tpassw);
        cnt.add(btnva);
        F1.add(cnt);
        F1.show();
        
        Form F2=new Form("",new FlowLayout(CENTER, CENTER));
        Label lbnom=new Label();
        F2.add(lbnom);
        btnva.addActionListener((e)->{
        if(tlogin.getText().equals("admin")
                && tpassw.getText().equals("admin"))
        {
            lbnom.setText("Welcom :"+tlogin.getText());
        F2.show();
        }
        else{
        Dialog.show("error","login ou pwd invalid","ok","cancel");
        }
        });
        
        Form F3=new Form("About",BoxLayout.y());
//        ImageViewer img=new ImageViewer(theme.getImage("nader.jpg"));
        SpanLabel sp=new SpanLabel("description nader ttttt tttt tttt tttt"
                + "ttttt bla balba belab bla bla bela ");
     //   F3.add(img);
        F3.add(sp);
        
        
        getToolbar().
        addCommandToLeftSideMenu("Home",null, ev->{F1.show();});
        
        F2.getToolbar().
        addCommandToLeftSideMenu("About",null, ev->{F3.show();});
        
        F3.getToolbar().addCommandToOverflowMenu("Exit",null, ev->{
        Display.getInstance().exitApplication();
        });
         
}

}