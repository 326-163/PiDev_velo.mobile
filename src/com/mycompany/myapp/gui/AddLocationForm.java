/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.OnOffSwitch;
import com.codename1.io.MultipartRequest;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
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
import com.mycompany.myapp.gui.SideMenuBaseForm;
import java.util.Date;

/**
 *
 * @author nahawnd
 */
public class AddLocationForm extends SideMenuBaseForm {

    Location l = new Location();
    String fileNameInServer = "";
    boolean upim = true;

    public AddLocationForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("ajout.jpg");
//        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
//        Graphics g = tintedImage.getGraphics();
//        g.drawImage(profilePic, 0, 0);
//        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());

//        tb.getUnselectedStyle().setBgImage(tintedImage);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("Retour");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_ARROW_BACK_IOS);
        settingsButton.addActionListener(e -> new ListLocationsForm(res).show());

        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                        add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("  Ajouter une ", "WelcomeBlue"),
                                        new Label("Location", "WelcomeWhite")
                                ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);

        //begin
        Label lTitre = new Label("Titre");
        lTitre.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tTitre = new TextField("", "Titre");
        tTitre.getAllStyles().setFgColor(ColorUtil.BLACK);
        tTitre.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));

        Label lLieu = new Label("Lieu");
        lLieu.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        PickerComponent tLieu = PickerComponent.createStrings("Ariana", "Béja", "Ben Arous", "Bizerte", "Gabès", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kébili", "Le Kef", "Mahdia", "La Manouba", "Médenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan").label("Lieu");

        Label lPrix = new Label("Prix");
        lPrix.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tPrix = new TextField("", "Prix");
        tPrix.getAllStyles().setFgColor(ColorUtil.BLACK);
        tPrix.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));

        Label lPhoto = new Label("Photo");
        lPhoto.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        Button upload = new Button("upload");
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                upim = true;
                try {

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
                        upim = false;
                    }

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

        Label lActive = new Label("Active");
        OnOffSwitch tActive = new OnOffSwitch();

        Button Valider = new Button("Publier location");
        FontImage.setMaterialIcon(Valider, FontImage.MATERIAL_ADD, 5);
        Container cont = new Container(BoxLayout.y());

        cont.addAll(lTitre, tTitre, lPrix, tPrix, lLieu, tLieu, ldateCreation, tdateCreation, lPhoto, upload, lActive, tActive, Valider);
        add(BorderLayout.CENTER, cont);
        Valider.addActionListener(e1 -> {

//          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date5 = new Date();
            date5 = (Date) tdateCreation.getPicker().getValue();
            String dd = simpleDateFormat.format(date5);
            System.out.println(dd);

            l.setTitre(tTitre.getText());
            l.setLieu(tLieu.getPicker().getSelectedString());
            l.setDateCreation(date5);

//            l.setId(Statics.current_user.getId());
//            if (tActive.isValue()) {
//                l.setActive(true);
//            } else {
//                l.setActive(false);
//            }

            System.out.println(l);
            if (verifierChamps(l, tPrix.getText())) {
                l.setPrix((int) Integer.parseInt(tPrix.getText()));
                ServiceLocation.getInstance().addLocation(l);
                System.out.println("Publication ajoutée avec succès");
                Statics.current_choice = 1;
                ListLocationsForm listLocationsForm = new ListLocationsForm(res);
//                 Form listeAnnoncesForm = listeAnnonces.getListeAnnoncesForm();
                listLocationsForm.show();
            }

        });
        //end

//        setupSideMenu(res);
    }

    @Override

    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

    public boolean verifierChamps(Location l, String prix) {
        int p;
        if (l.getTitre().equals("")) {
            Dialog.show("Error", "Veuillez remplir le champ par un titre", "OK", null);
            return false;
        }
        if (l.getLieu().equals("")) {
            Dialog.show("Error", "Veuillez Saisir votre lieu", "OK", null);
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

//        if (fileNameInServer.equals("")) {
//            Dialog.show("Error", "Veuillez choisir une photo", "OK", null);
//            return false;
//        }

        return true;
    }

    /*     setTitle("Add a new Location");
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
        });*/
}
