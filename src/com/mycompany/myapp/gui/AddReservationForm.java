/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.services.ServiceReservation;
import com.mycompany.myapp.entities.Reservation;
import java.util.Date;

/**
 *
 * @author nahawnd
 */
public class AddReservationForm extends Form {

    public AddReservationForm(Form previous) {
        setTitle("Add a new Reservation");

        setLayout(BoxLayout.y());
        TextField tfDateDeb = new TextField("", "ReservationDateDebut");
        Picker dateDeb = new Picker();
        TextField tfDateFin = new TextField("", "ReservationDateFin");
        Picker dateFin = new Picker();
        Button btnValider = new Button("Valider");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((dateDeb.getText().length() == 0) || (dateFin.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Reservation r;
                        r = new Reservation(
                                dateDeb.getText(),
                                dateFin.getText());

                        if (ServiceReservation.getInstance().addReservation(r)) {
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

        addAll(dateDeb, dateFin, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
