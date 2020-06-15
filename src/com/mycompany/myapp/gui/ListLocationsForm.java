/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.services.ServiceLocation;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author nahawnd
 */
public class ListLocationsForm extends SideMenuBaseForm {

    private EncodedImage palceHolder;

    int id;

    public static Form details;

    ServiceLocation l = new ServiceLocation();

    ArrayList<Location> locations = l.getAllLocations();

    private static ListLocationsForm instance;

    public static ListLocationsForm getInstance() {
        return instance;
    }

    public ListLocationsForm() {
        instance = this;
    }

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
//        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> getToolbar().openSideMenu());
//        
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

        Button backButton = new Button("Retour");
        backButton.setUIID("Title");
        FontImage.setMaterialIcon(backButton, FontImage.MATERIAL_ARROW_BACK_IOS);
        backButton.addActionListener(e -> new HomeForm(res).show());

        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton)
                                .add(BorderLayout.EAST, settingsButton)
                                .add(BorderLayout.CENTER, backButton)
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

        PickerComponent signallPicker = PickerComponent.createStrings( "Prix haut", "Prix bas", "Plus récent").label("Critère");
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

          
            if (signallPicker.getPicker().getSelectedString().equals("Prix croissant") || signallPicker.getPicker().getSelectedString().equals("Prix décroissant")) {
                if (signallPicker.getPicker().getSelectedString().equals("Prix croissant")) {
                    Statics.current_type = "asc";
                } else {
                    Statics.current_type = "desc";
                }
                Statics.current_choice = 4;
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
        /*  setLayout(BoxLayout.y());
        for (Location l : locations) {
            add(AddItems(l, res));
        }

      
    

    

    public Container AddItems(Location pro, Resources theme) {
        Container item = new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));

        EncodedImage enco = EncodedImage.createFromImage(theme.getImage("load.png"), false);
        String url = "http://127.0.0.1:8000/api/uploads/" + pro.getPhoto();
        Image im = URLImage.createToStorage(enco, pro.getPhoto(), url);
        ImageViewer imv = new ImageViewer(im);
        item.add(imv);

        Container data = new Container(BoxLayout.y());

        Label ltitre= new Label("Titre :");
        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        ltitre.getUnselectedStyle().setFont(fnt);
        Label tftitre = new Label(pro.getTitre());

        Container nom = new Container(BoxLayout.x());
        nom.add( ltitre);
        nom.add(tftitre);

        data.add(nom);

        Label lreference = new Label("Lieu:");
        Font fnt2 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        lreference.getUnselectedStyle().setFont(fnt2);
        SpanLabel tfreference = new SpanLabel(pro.getLieu());

        Container reference = new Container(BoxLayout.x());
        reference.add(lreference);
        reference.add(tfreference);

        data.add(reference);

       

        Label lprix = new Label("Price :");
        Font fnt4 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        lprix.getUnselectedStyle().setFont(fnt4);
        Label tfprix = new Label(Float.toString(pro.getPrix()) + "DT");

        Container prix = new Container(BoxLayout.x());
        prix.add(lprix);
        prix.add(tfprix);

        data.add(prix);
        
        
//        Container sliQut = new Container(BoxLayout.y());
//        Label l = new Label("Choose :");
//        Slider sliderquatity = new Slider();
//        Style styleslider = sliderquatity.getAllStyles();
//       
//        Button checkout = new Button("Add to cart");
//        sliderquatity.setEditable(true);
//        //sliderquatity.setMinValue(0);
//        sliderquatity.setMinValue(0);
//        sliderquatity.setMaxValue(pro.getQuantity());
//        sliQut.add(l);
//        sliQut.add(sliderquatity);
//        sliQut.add(checkout);
//        sliderquatity.addActionListener(new ActionListener() {
//               @Override
//               public void actionPerformed(ActionEvent evt) {
//                  
//                   l.setText("Choose :"+sliderquatity.getProgress());
//               }
//           });
//        data.add(sliQut);
//        
//        checkout.addActionListener(new ActionListener()
//        {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//               
//                PanierService ps = new PanierService();
//                if (ps.AddToPanier(pro.getId(),sliderquatity.getProgress()))
//                {
//                    Dialog.show("Sucess", "Product Added !", "OK",null);
//                    new PanierForm(theme).show();
//                }
//                else
//                {
//                    Dialog.show("échec","There is a problem,verify ! ","OK",null);
//                }
//                 
//            }
//            
//        });
               
   
        item.add(data);
        return item;
    }
    
    public  int get_id_loc()
    {
        return id;
    }
    
         */
        //end
    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
