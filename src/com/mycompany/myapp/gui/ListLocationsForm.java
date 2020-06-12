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
import com.codename1.ui.util.Resources;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author nahawnd
 */
public class ListLocationsForm extends SideMenuBaseForm {

    private EncodedImage palceHolder;

    public ListLocationsForm(Resources res) {
       super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("velo.jpg");
//        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
//        Graphics g = tintedImage.getGraphics();
//        g.drawImage(profilePic, 0, 0);
//        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());

//        tb.getUnselectedStyle().setBgImage(tintedImage);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

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
                                        new Label("Locations", "WelcomeWhite")
                                ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        Container con4 = new Container(BoxLayout.x());
        Container con5 = new Container(new BorderLayout());

        PickerComponent signallPicker = PickerComponent.createStrings("Vélo", "Prix croissant", "Prix décroissant", "Plus récent").label("Critère");
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
                new ListLocationsForm(res).show();
            }
            if (signallPicker.getPicker().getSelectedString().equals("Prix croissant") || signallPicker.getPicker().getSelectedString().equals("Prix décroissant")) {
                if (signallPicker.getPicker().getSelectedString().equals("Prix croissant")) {
                    Statics.current_type = "asc";
                } else {
                    Statics.current_type = "desc";
                }
                Statics.current_choice = 6;
                new ListLocationsForm(res).show();
            }
            if (signallPicker.getPicker().getSelectedString().equals("Plus récent")) {
                Statics.current_choice = 7;
                new ListLocationsForm(res).show();
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
                new ListLocationsForm(res).show();
            }
        });
        con4.add(BoxLayout.encloseXRight(tRech, rechButton));

        add(BorderLayout.NORTH, con5);
        
        
         //begin
       

    }
    //end
    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
/*  SpanLabel sp = new SpanLabel();
        sp.setText(ServiceLocation.getInstance().getAllLocations().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
    }*/
