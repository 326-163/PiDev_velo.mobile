/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author nahawnd
 */
public class HomeForm extends Form {

    Form current;

    public HomeForm() {
        current = this;
        setTitle("Accueil");
        setLayout(BoxLayout.y());
        Form F1 = new Form("Home", new FlowLayout(CENTER, CENTER));
        add(new Label("Bienvenue dans Vélo.tn "));
        
       Button btnInscription = new Button("Inscrire");
       Button btnAuthentification = new Button("Login");

        Button btnAddLocation = new Button("Louer");
        Button btnListLocations = new Button("Consulter liste locations");
        Button btnChercherLocation = new Button("Chercher location");

        Button btnAddReservation = new Button(" Reserver vélo");
        Button btnListReservations = new Button("Consulter liste Reservations");

        btnAddLocation.addActionListener(e -> new AddLocationForm(current).show());
        btnListLocations.addActionListener(e -> new ListLocationsForm(current).show());
        btnChercherLocation.addActionListener(e-> new RechercheLocationForm(current).show());

        btnAddReservation.addActionListener(e -> new AddReservationForm(current).show());
        btnListLocations.addActionListener(e -> new ListReservationsForm(current).show());
         
      // btnInscription.addActionListener(e -> new InscriptionForm (current).show()); 
       //btnAuthentification.addActionListener(e -> new LoginForm(current).show());

        addAll(btnAddLocation, btnListLocations, btnAddReservation, btnListReservations);

        
       Form F2 = new Form("Login", new FlowLayout(CENTER, CENTER));
        Container cnt = new Container(BoxLayout.y());
        TextField tlogin = new TextField("", "login");
        TextField tpass = new TextField("", "password");
        tpass.setConstraint(TextField.PASSWORD);
        Button btnva = new Button("valider");
        cnt.add(tlogin);
        cnt.add(tpass);
        cnt.add(btnva);
        F2.add(cnt);
        F2.show();

        Form F3 = new Form("", new FlowLayout(CENTER, CENTER));
        btnva.addActionListener((e) -> {
            if (tlogin.getText().equals("nahawand")
                    && tpass.getText().equals("admin")) {
                F1.show();
            } else {
                Dialog.show("error", "login ou pwd invalid", "ok", "cancel");
            }
        });

        Form F4 = new Form("Inscription", BoxLayout.y());
        TextField tnom = new TextField("", "saisir nom");
        TextField temail = new TextField();
        temail.setHint("Email");
        TextField tpassw = new TextField("", "saisir password");
        tpassw.setConstraint(TextField.PASSWORD);

        Button btn = new Button("valider");
        F4.addAll(tnom, temail, tpassw, btn);
        F4.getToolbar().addCommandToLeftBar("Back", null, ev -> {
        F4.show();
        });

       
        
        F3.getToolbar().
        addCommandToLeftSideMenu("Login",null, ev->{F2.show();});
        
        F3.getToolbar().
        addCommandToLeftSideMenu("Inscrire",null, ev->{F4.show();});
        
        F3.getToolbar().addCommandToOverflowMenu("Exit",null, ev->{
        Display.getInstance().exitApplication();
        });
    }

}
