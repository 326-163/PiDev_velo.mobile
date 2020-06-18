/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.Format;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
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

/**
 *
 * @author nahawnd
 */
public class AfficherLocation extends SideMenuBaseForm {

    private EncodedImage palceHolder;

    public AfficherLocation(Resources res, Location l) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = null;

        profilePic = res.getImage("bike.jpg");

        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());

        tb.getUnselectedStyle().setBgImage(tintedImage);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
//        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> getToolbar().openSideMenu());

getToolbar().addMaterialCommandToLeftSideMenu("menuButton", FontImage.MATERIAL_MENU, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });

        getToolbar().addMaterialCommandToLeftSideMenu("Profile", FontImage.MATERIAL_SHOPPING_CART, ev -> new ProfileForm(res).show());
        getToolbar().addMaterialCommandToLeftSideMenu("Louer", FontImage.MATERIAL_ADD_CIRCLE, ev -> new AddLocationForm(res).show());
        getToolbar().addMaterialCommandToLeftSideMenu("Locations", FontImage.MATERIAL_STORE, ev -> new ListLocationsForm(res).show());
        getToolbar().addMaterialCommandToLeftSideMenu("Reserver", FontImage.MATERIAL_STORE, ev -> new AddReservationForm(res).show());
        
        Button settingsButton = new Button("Retour");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_ARROW_BACK_IOS);
//        settingsButton.addActionListener(e -> new ListLocationsForm(res).show());

        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                        add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("  Détails de ", "WelcomeBlue"),
                                        new Label("location", "WelcomeWhite")
                                ));

        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);

        //#####begin
        String image = l.getPhoto();
        ImageViewer imgv = new ImageViewer();
        try {
            palceHolder = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {

        }
        if (image != null) {
            URLImage urlImage = URLImage.createToStorage(palceHolder, image, "http://localhost/rent/uploads/location/" + image);
            imgv.setImage(urlImage);

        }

        Label lTitre = new Label("Titre: " + l.getTitre());
        Label lLieu = new Label("Lieu: " + l.getLieu());
        Label lPrix = new Label("Prix: " + l.getPrix());
        Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String ress = dateFormat.format(l.getDateCreation());
        Button bModif = new Button("Modifier");
        Button bSupp = new Button("Supprimer");
        Container cb = new Container(BoxLayout.x());
        cb.addAll(bModif, bSupp);
        cb.setVisible(false);

        if (Statics.current_user.getRoles().contains("ADMIN") || Statics.current_user.getId() == l.getId()) {
            cb.setVisible(true);
        }
        bModif.addActionListener(e -> {
            UpdateLocationForm updateLocationForm = new UpdateLocationForm(res,l);

            updateLocationForm.show();

        });
        bSupp.addActionListener(e1 -> {
            if (Dialog.show("voulez vous supprimer cette location", "", "Supprimer", "Annuler")) {
                ServiceLocation.getInstance().DeleteLocation(l.getId());
                System.out.println("Publication supprimée avec succès !");
//                ListLocationsForm listLocationsForm = new ListLocationsForm(res);

//                listLocationsForm.show();
            }

        });
        PickerComponent signallPicker = PickerComponent.createStrings("Contenu indésirable", "Harcèlement", "Discours haineux", "Nudité", "Violence", "Autre").label("Cause");

        setupSideMenu(res);

    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

}
