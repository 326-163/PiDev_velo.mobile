package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.ListLocationsForm;
import com.mycompany.myapp.gui.ListReservationsForm;
import com.mycompany.myapp.gui.AddLocationForm;
import com.mycompany.myapp.gui.AddReservationForm;
//import com.mycompany.myapp.gui.NotificationsForm;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */

public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
       /*  String image=Vars.current_user.getPhoto();
        Image profilePic = null;
        try {
                    palceHolder = EncodedImage.create("/giphy.gif");
                } catch (IOException ex) {

                }
                if(image!=null){
                profilePic = URLImage.createToStorage(palceHolder, image, "http://127.0.0.1/rent/uploads/user/"+image);

                }*/
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  "+Statics.current_user.getNom()+" "+Statics.current_user.getPrenom(), "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());
            
        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu(" Dashboard ", FontImage.MATERIAL_DASHBOARD,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu(" Profile ", FontImage.MATERIAL_SETTINGS,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu(" Louer ", FontImage.MATERIAL_ADD,  e -> new AddLocationForm(res).show());
        getToolbar().addMaterialCommandToSideMenu(" Liste Locations ", FontImage.MATERIAL_LIST,  e -> {Statics.current_choice = 1;new ListLocationsForm(res).show();});
        getToolbar().addMaterialCommandToSideMenu(" Mes Locations ", FontImage.MATERIAL_LIST,  e -> {Statics.current_choice = 1;new ListLocationsForm(res).show();});
        getToolbar().addMaterialCommandToSideMenu(" Liste Reservation ", FontImage.MATERIAL_LIST, e -> {Statics.current_choice = 1;new ListReservationsForm(res).show();});
                                                                                       
        if(Statics.current_user.getRoles().contains(" ADMIN ")){
        getToolbar().addMaterialCommandToSideMenu(" Toutes les locations ", FontImage.MATERIAL_LIST,  e -> {Statics.current_choice = 8;new ListLocationsForm(res).show();});
        getToolbar().addMaterialCommandToSideMenu(" Toutes les reservations ", FontImage.MATERIAL_LIST,  e -> {Statics.current_choice = 4;new ListReservationsForm(res).show();});   
    }
     getToolbar().addMaterialCommandToSideMenu(" Paramétres ", FontImage.MATERIAL_SETTINGS,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu(" Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
   
    }
    
    protected abstract void showOtherForm(Resources res);
}
