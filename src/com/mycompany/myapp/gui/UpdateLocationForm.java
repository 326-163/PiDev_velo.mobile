/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.services.ServiceLocation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author nahawnd
 */
public class UpdateLocationForm extends SideMenuBaseForm {

    public UpdateLocationForm(Resources res, Location l) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("updatee.jpg");
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());

        tb.getUnselectedStyle().setBgImage(tintedImage);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("Retour");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_ARROW_BACK_IOS);
        settingsButton.addActionListener(e -> new AfficherLocation(res, l).show());

        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                        add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("  Modifier ", "WelcomeBlue"),
                                        new Label("Location", "WelcomeWhite")
                                ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);

        //#####begin
        Label lTitre = new Label("Titre");
        lTitre.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tTitre = new TextField(l.getTitre(), "Titre");
        tTitre.getAllStyles().setFgColor(ColorUtil.BLACK);
        tTitre.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));

        Label lLieu = new Label("Lieu");
        lLieu.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        PickerComponent tLieu = PickerComponent.createStrings("Ariana", "Béja", "Ben Arous", "Bizerte", "Gabès", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kébili", "Le Kef", "Mahdia", "La Manouba", "Médenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan").label("Gouvernorat");
        tLieu.getPicker().setSelectedString(l.getLieu());

        Label lPrix = new Label("Prix");
        lPrix.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tPrix = new TextField(String.valueOf((int) l.getPrix()), "Prix");
        tPrix.getAllStyles().setFgColor(ColorUtil.BLACK);
        tPrix.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));

        Label lPhoto = new Label("Photo");
        lPhoto.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));

        Button upload = new Button("upload");
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    //t7ot l url ta3 l fichier php
                    cr.setUrl("http://localhost/Rent/uploads/location/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    try {
                        cr.addData("file", filepath, mime);
                    } catch (java.lang.NullPointerException ex) {
                        System.out.println("no file selected");
                    }

                    System.err.println("path2 =" + fileNameInServer);
                    System.out.println("filename" + fileNameInServer);
                    l.setPhoto(fileNameInServer);
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        Label ldateCreation = new Label("Date creation");
        System.out.println("date md : " + l.getDateCreation());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String datesmd = simpleDateFormat.format(new Date());
        Date datemd = null;
        try {
            datemd = simpleDateFormat.parse(datesmd);
        } catch (ParseException ex) {
            System.out.println("date exception: " + ex);
        }
        System.out.println("date md formated : " + datesmd);
        PickerComponent tdateCreation = PickerComponent.createDate(datemd).label("Date creation");

        Button bModifier = new Button("Modifier location");
        FontImage.setMaterialIcon(bModifier, FontImage.MATERIAL_UPDATE, 5);
        Container cont = new Container(BoxLayout.y());
        cont.addAll(lTitre, tTitre, lPrix, tPrix, lLieu, tLieu, ldateCreation, tdateCreation, lPhoto, upload, bModifier);
        add(BorderLayout.CENTER, cont);

        bModifier.addActionListener(e1 -> {
            Date date5 = new Date();
            date5 = (Date) tdateCreation.getPicker().getValue();
            String dd = simpleDateFormat.format(date5);
            System.out.println(dd);

            l.setTitre(tTitre.getText());
            l.setLieu(tLieu.getPicker().getSelectedString());
            l.setDateCreation(date5);

            System.out.println(l);
            if (verifierChamps(l, tPrix.getText())) {
                l.setPrix((int) Integer.parseInt(tPrix.getText()));
                ServiceLocation.getInstance().addLocation(l);
                System.out.println("Publication modifié avec succès");
                Statics.current_choice = 1;
                ListLocationsForm listLocationsForm = new ListLocationsForm(res);
                listLocationsForm.show();
            }

        });
        //end

        setupSideMenu(res);

    }

    public boolean verifierChamps(Location l, String prix) {
        int p;
        if (l.getTitre().equals("")) {
            Dialog.show("Error", "Veuillez remplir le champ par un titre", "OK", null);
            return false;
        }
        if (prix.equals("")) {
            Dialog.show("Error", "Veuillez écrire un prix", "OK", null);
            return false;
        }
        try {
            p = Integer.parseInt(prix);
        } catch (NumberFormatException ex) {
            Dialog.show("Error", "Le prix doit contenir que des caractères numériques!", "OK", null);
            return false;
        }
        if (p <= 0) {
            Dialog.show("Error", "Veuillez écrire un prix supérieur à 0", "OK", null);
            return false;
        }

        return true;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
