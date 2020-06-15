/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author nahawnd
 */
public class ListReservationsForm extends SideMenuBaseForm {

    private EncodedImage palceHolder;

    public ListReservationsForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("velo.jpg");
//        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
//        Graphics g = tintedImage.getGraphics();
//        g.drawImage(profilePic, 0, 0);
//        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
//
//        tb.getUnselectedStyle().setBgImage(tintedImage);

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

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);

        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                        add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("  Liste des ", "WelcomeBlue"),
                                        new Label("Reservations", "WelcomeWhite")
                                ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        Container con4 = new Container(BoxLayout.x());
        Container con5 = new Container(new BorderLayout());

        PickerComponent signallPicker = PickerComponent.createStrings("Vélo", "Plus récent").label("Critère");
        InteractionDialog dlg1 = new InteractionDialog("Filter par:");
        Dimension pre = dlg1.getContentPane().getPreferredSize();
        dlg1.setLayout(new BorderLayout());
        dlg1.add(BorderLayout.CENTER, signallPicker);
        Button ok = new Button("Ok");
        Button close = new Button("Annuler");
        close.addActionListener((ee) -> dlg1.dispose());
        Container cont4 = new Container(BoxLayout.x());
        Container cont5 = new Container(BoxLayout.y());
//        cont5.add(BoxLayout.encloseXCenter(cont4));
        cont4.addAll(close, ok);
        cont5.add(BoxLayout.encloseXCenter(cont4));
        dlg1.addComponent(BorderLayout.SOUTH, cont5);
        ok.addActionListener(e -> {
            System.out.println("Critère: " + signallPicker.getPicker().getSelectedString());

            if (signallPicker.getPicker().getSelectedString().equals("Vélo")) {
                Statics.current_type = signallPicker.getPicker().getSelectedString();
                Statics.current_choice = 5;
                new ListReservationsForm(res).show();
            }
            if (signallPicker.getPicker().getSelectedString().equals("Date croissant") || signallPicker.getPicker().getSelectedString().equals("Date décroissant")) {
                if (signallPicker.getPicker().getSelectedString().equals("Date croissant")) {
                    Statics.current_type = "asc";
                } else {
                    Statics.current_type = "desc";
                }
                Statics.current_choice = 6;
                new ListReservationsForm(res).show();
            }
            if (signallPicker.getPicker().getSelectedString().equals("Plus récent")) {
                Statics.current_choice = 7;
                new ListReservationsForm(res).show();
            }
        });

        Button bTri = new Button("Trier par");
        bTri.getAllStyles().setBgColor(ColorUtil.BLACK);
        bTri.getAllStyles().setFgColor(ColorUtil.BLACK);
        FontImage.setMaterialIcon(bTri, FontImage.MATERIAL_SORT, 5);
        bTri.addActionListener(e -> {
            dlg1.show(200, 200, 100, 100);
        });
        con5.add(BorderLayout.EAST, bTri);
        con5.add(BorderLayout.WEST, con4);
        TextField tRech = new TextField("", "Recherche");
        tRech.getAllStyles().setFgColor(ColorUtil.BLACK);
        tRech.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        Button rechButton = new Button("");
        rechButton.getAllStyles().setBgColor(ColorUtil.BLACK);
        rechButton.getAllStyles().setFgColor(ColorUtil.BLACK);
        int color = 0xffffff;
        Font materialFontBack = FontImage.getMaterialDesignFont();
        FontImage imageBack = FontImage.createFixed("\uE8B6", materialFontBack, ColorUtil.BLACK, 30, 30);

        rechButton.setUIID("Title");
//        FontImage.setMaterialIcon(rechButton, FontImage.MATERIAL_SEARCH);
        rechButton.setIcon(imageBack);
        rechButton.addActionListener(e -> {
            if (!tRech.getText().equals("")) {
                System.out.println("recherche: " + tRech.getText());
                Statics.current_search = tRech.getText();
                Statics.current_choice = 3;
                new ListReservationsForm(res).show();
            }
        });
        con4.add(BoxLayout.encloseXRight(tRech, rechButton));

        add(BorderLayout.NORTH, con5);

        //begin
//        if (!ServiceReservation.getInstance().getReservations().isEmpty()) {
//            Container con3 = new Container(BoxLayout.y());
//
//            for (Reservation r : ServiceReservation.getInstance().getReservations()) {
//
//                int id = r.getId();
//                String titre = r.getTitre();
//                String dateDeb = r.getDateDeb().toString();
//                String dateFin = r.getDateFin().toString();
//
//                Label lTitre = new Label("Titre: " + titre);
//                Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                String ress = dateFormat.format(r.getDateDeb());
//                String ress1 = dateFormat.format(r.getDateFin());
//                Label ldateDeb = new Label("Date debut : " + ress);
//                Label ldateFin = new Label("Date fin : " + ress);
//
//                Button bAfficher = new Button("Afficher");
//                bAfficher.addActionListener(e -> {
////                    Annonce a1=new Annonce();
//                    Statics.current_reservation = r;
//                    System.out.println("testtt");
//                    AfficherReservation afficherReservation = new AfficherReservation(res, r);;
//                    afficherReservation.show();
//
//                });
//
//                System.out.println("Reservation= id: " + id + " Titre: " + titre + "Date debut: " + dateDeb + " Date fin : " + dateFin);
//                Container con = new Container(BoxLayout.x());
//                Container con1 = new Container(BoxLayout.y());
//                ImageViewer imgv = new ImageViewer();
//
//                try {
//                    palceHolder = EncodedImage.create("/giphy.gif");
//                } catch (IOException ex) {
//
//                }
//
//                con1.addAll(lTitre, ldateDeb, ldateFin);
//                con.addAll(imgv, con1);
//
//                con3.addAll(con, bAfficher);
//            }
//            con3.setScrollableY(true);
//            System.out.println("sroll : " + con3.isScrollableY());
//            add(BorderLayout.CENTER, con3);
//
//        } else {
//            Container cont = new Container(BoxLayout.y());
//            cont.add(BoxLayout.encloseXCenter(new Label("Aucune Reservation à afficher")));
//            add(BorderLayout.CENTER, cont);
//
//        }
//end
//        setupSideMenu(res);
    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

}
