/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
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
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ServiceReservation;
import com.mycompany.myapp.utils.Statics;
import java.util.Date;

/**
 *
 * @author nahawnd
 */
public class UpdateReservationForm extends SideMenuBaseForm {

    public UpdateReservationForm(Resources res, Reservation r) {
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
//        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_ARROW_BACK_IOS);
//        settingsButton.addActionListener(e -> new AfficherReservation(res, r).show());

getToolbar().addMaterialCommandToLeftSideMenu("menuButton", FontImage.MATERIAL_MENU, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });

        getToolbar().addMaterialCommandToLeftSideMenu("Profile", FontImage.MATERIAL_SHOPPING_CART, ev -> new ProfileForm(res).show());
        getToolbar().addMaterialCommandToLeftSideMenu("Louer", FontImage.MATERIAL_ADD_CIRCLE, ev -> new AddLocationForm(res).show());
        getToolbar().addMaterialCommandToLeftSideMenu("Locations", FontImage.MATERIAL_STORE, ev -> new ListLocationsForm(res).show());
        getToolbar().addMaterialCommandToLeftSideMenu("Reserver", FontImage.MATERIAL_STORE, ev -> new AddReservationForm(res).show());

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
                                        new Label("Reservation", "WelcomeWhite")
                                ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);

        //#####begin
        Label lTitre = new Label("Titre");
        lTitre.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        TextField tTitre = new TextField(r.getTitre(), "Titre");
        tTitre.getAllStyles().setFgColor(ColorUtil.BLACK);
        tTitre.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));

        Label ldateDeb = new Label("Date debut");
        System.out.println("date md : " + r.getDateDeb());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String datesmd = simpleDateFormat.format(new Date());
        Date datemd = null;
        try {
            datemd = simpleDateFormat.parse(datesmd);
        } catch (ParseException ex) {
            System.out.println("date exception: " + ex);
        }
        System.out.println("date md formated : " + datesmd);
        PickerComponent tdateDeb = PickerComponent.createDate(datemd).label("Date debut");

        Label ldateFin = new Label("Date fin");
        System.out.println("date md : " + r.getDateFin());
        try {
            datemd = simpleDateFormat.parse(datesmd);
        } catch (ParseException ex) {
            System.out.println("date exception: " + ex);
        }
        System.out.println("date md formated : " + datesmd);
        PickerComponent tdateFin = PickerComponent.createDate(datemd).label("Date fin");

        Button bModifier = new Button("Modifier reservation");
        FontImage.setMaterialIcon(bModifier, FontImage.MATERIAL_ADD, 5);
        Container cont = new Container(BoxLayout.y());
        cont.addAll(lTitre, tTitre, ldateDeb, tdateDeb, ldateFin, tdateFin, bModifier);
        add(BorderLayout.CENTER, cont);

        bModifier.addActionListener(e1 -> {
            Date date5 = new Date();
            date5 = (Date) tdateDeb.getPicker().getValue();
            String dd = simpleDateFormat.format(date5);
            System.out.println(dd);

            r.setTitre(tTitre.getText());
            r.setDateDeb(date5);
            r.setDateFin(date5);

            System.out.println(r);

            ServiceReservation.getInstance().addReservation(r);
            System.out.println("Reservation ajoutée avec succès");
            Statics.current_choice = 1;
            ListReservationsForm listReservationsForm = new ListReservationsForm(res);
            listReservationsForm.show();

        });
        //end

        setupSideMenu(res);
    }

    public boolean verifierChamps(Location l, String prix) {

        if (l.getTitre().equals("")) {
            Dialog.show("Error", "Veuillez remplir le champ par un titre", "OK", null);
            return false;
        }

        return true;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
