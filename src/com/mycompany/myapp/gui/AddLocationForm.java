/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ServiceLocation;
import com.mycompany.myapp.entities.Location;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

/**
 *
 * @author nahawnd
 */
public class AddLocationForm extends Form{

    private Resources theme;
    private Form current;

    public AddLocationForm(Form previous) {
        setTitle("Add a new Location");
        setLayout(BoxLayout.y());

        Form F = new Form("Ajout", BoxLayout.y());
        Container cnt = new Container(BoxLayout.y());
        TextField tfTitre = new TextField("", "LocationTitre");
        TextField tfLieu = new TextField("", "LocationLieu");
        TextField tfPrix = new TextField("", "LocationPrix");
        Button btnPhoto = new Button("Upload photo");
        //ImageViewer img = new ImageViewer(theme.getImage("acceuil.png"));
        //add(img);
        // Label lbimg = new Label(theme.getImage(l.getPhoto()));
        // cnt.add(cnt);
        // cnt.add(lbimg);
        //TextField tfRating = new TextField("", "LocationRating");
        TextField tfDateCreation = new TextField("", "LocationDateCreation");
        Picker dateCreation = new Picker();
        Button btnValider = new Button("Add Location");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length() == 0)
                        || (tfLieu.getText().length() == 0)
                        || (tfPrix.getText().length() == 0)
                        || (btnPhoto.getText().length() == 0)
                        // || (tfRating.getText().length() == 0)){
                        || (dateCreation.getText().length() == 0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));

                } else {
                    try {
                        
                        Location l = new Location(
                                tfTitre.getText(),
                                tfLieu.getText(),
                                Float.parseFloat(tfPrix.getText()),
                                 btnPhoto.getText(),
                                //Integer.parseInt( tfRating.getText()));                              
                                tfDateCreation.getText());

                        if (ServiceLocation.getInstance().addLocation(l)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Prix must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfTitre, tfLieu, tfPrix, btnPhoto, dateCreation, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        F.getToolbar().addCommandToLeftBar("Back", null, ev -> {
            F.show();
        });
    }

}
