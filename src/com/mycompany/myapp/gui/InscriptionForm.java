/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author nahawnd
 */
public class InscriptionForm {

    private Resources theme;
    private Form current;

    public InscriptionForm(Form previous) {
        Form F = new Form("Inscription", BoxLayout.y());
        TextField tnom = new TextField("", "saisir nom");
        TextField temail = new TextField();
        temail.setHint("Email");
        TextField tpass = new TextField("", "saisir password");
        tpass.setConstraint(TextField.PASSWORD);

        Button btn = new Button("valider");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tnom.getText().length() == 0)
                        || (temail.getText().length() == 0)
                        || (tpass.getText().length() == 0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
                }

            }

        });

        F.addAll(tnom, temail, tpass, btn);
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        F.getToolbar().addCommandToOverflowMenu("Exit", null, ev -> {
            Display.getInstance().exitApplication();
            F.show();

        });

    }
}
