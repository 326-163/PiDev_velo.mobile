/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author nahawnd
 */
public class HomeForm extends SideMenuBaseForm {

    Form home;
    private EncodedImage palceHolder;

    public HomeForm(Resources theme) {
        home = this;
        setTitle(" Accueil ");
        setLayout(BoxLayout.y());

        Container ch = new Container(BoxLayout.y());

        Image logo = theme.getImage("logo.png");

        ImageViewer imagelogo = new ImageViewer(logo);

        Style imagestyle = imagelogo.getAllStyles();

        imagestyle.setMarginTop(100);

        ch.add(imagelogo);

        Label texthome = new Label("Bienvenue dans Vélo.TN");
        int fontsize = Display.getInstance().convertToPixels(50);
        Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        Style styletext = texthome.getUnselectedStyle();
        styletext.setMarginTop(100);
        styletext.setMarginBottom(50);
        styletext.setFont(font);
        styletext.setFgColor(0xE3051A);
        Container centered = BorderLayout.centerAbsolute(texthome);

        /*String s = "Liste Locations";
        Button Locations = new Button(s.toUpperCase());
        Locations.setPreferredSize(new Dimension(500, 130));
        Style pro = Locations.getAllStyles();

        Locations.addActionListener(e -> new ListLocationsForm(theme).show());

        Container containerlocations = BorderLayout.centerAbsolute(Locations);

        String s1 = "Louer";
        Button Louer = new Button(s1.toUpperCase());
        Louer.setPreferredSize(new Dimension(500, 130));
        Container contaddpro = BorderLayout.centerAbsolute(Louer);
        Louer.addActionListener(e -> new AddLocationForm(theme).show());

        String s2 = "Résérver";
        Button Résérver = new Button(s2.toUpperCase());
        Résérver.setPreferredSize(new Dimension(500, 130));
        Container contpanier = BorderLayout.centerAbsolute(Résérver);

        Résérver.addActionListener(e -> new AddReservationForm(theme).show());

        String s3 = "Liste Réservations";
        Button Réservations = new Button(s3.toUpperCase());
        Réservations.setPreferredSize(new Dimension(500, 130));
        Container contorder = BorderLayout.centerAbsolute(Réservations);

        int total = 500;
        Réservations.addActionListener(e -> new ListReservationsForm(theme).show());

        home.add(ch);
        home.add(centered);
        home.add(containerlocations);
        home.add(contaddpro);
        home.add(contpanier);
        home.add(contorder);
        home.show();*/
        Button btnAddLocation = new Button("Louer");
        Button btnListLocations = new Button("Consulter liste locations");
        Button btnChercherLocation = new Button("Chercher location");

        Button btnAddReservation = new Button(" Reserver vélo");
        Button btnListReservations = new Button("Consulter liste Reservations");

        btnAddLocation.addActionListener(e -> new AddLocationForm(theme).show());
        btnListLocations.addActionListener(e -> new ListLocationsForm(theme).show());
//        btnChercherLocation.addActionListener(e-> new RechercheLocationForm(current).show());

        btnAddReservation.addActionListener(e -> new AddReservationForm(theme).show());
        btnListReservations.addActionListener(e -> new ListReservationsForm(theme).show());

        addAll(btnAddLocation, btnListLocations, btnAddReservation, btnListReservations);

        ButtonGroup bg = new ButtonGroup();

        Container radioContainer = new Container();

        Button skip = new Button("Suivant");
        skip.setUIID("SkipButton");
        skip.addActionListener(e -> new ProfileForm(theme).show());

        Container southLayout = BoxLayout.encloseY(
                radioContainer,
                skip
        );
        add(BorderLayout.south(
                southLayout
        ));

//        F1.getToolbar().
//        addCommandToLeftSideMenu("Profil",null, ev->{F2.show();});
//        
//        F1.getToolbar().
//        addCommandToLeftSideMenu("Deconnexion",null, ev->{F4.show();});
//        
//        F1.getToolbar().addCommandToOverflowMenu("Exit",null, ev->{
//        Display.getInstance().exitApplication();
//        });
    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
