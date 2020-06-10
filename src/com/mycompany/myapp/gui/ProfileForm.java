package com.mycompany.myapp.gui;

//import com.codename1.uikit.materialscreens.*;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;



/**
 * Represents a user profile in the app, the first form we open after the walkthru
 *
 * @author Shai Almog
 */
public class ProfileForm extends SideMenuBaseForm {
        private EncodedImage palceHolder;
    public ProfileForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        String image=Statics.current_user.getPhoto();
        Image profilePic = null;
        try {
                    palceHolder = EncodedImage.create("/giphy.gif");
                } catch (IOException ex) {

                }
                if(image!=null){
                profilePic = URLImage.createToStorage(palceHolder, image, "http://127.0.0.1:8000/Rent/uploads/user/"+image);

                }
//        Image profilePic = res.getImage("user-picture.jpg");
                
//                ImageViewer imgv = new ImageViewer();
//        try {
//                    palceHolder = EncodedImage.create("/giphy.gif");
//                } catch (IOException ex) {
//
//                }
//                if(image!=null){
//                URLImage urlImage = URLImage.createToStorage(palceHolder, image, "http://localhost/pi/web/uploads/user/"+image);
//                imgv.setImage(urlImage);
//
//                }
                
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container remainingTasks = BoxLayout.encloseY(
                        new Label("12", "CenterTitle"),
                        new Label("remaining tasks", "CenterSubTitle")
                );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                        new Label("32", "CenterTitle"),
                        new Label("completed tasks", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");
        String role;
        if(Statics.current_user.getRoles().contains("ADMIN")){
            role="Administrateur";
        }else{
            role="Membre";
        }
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(Statics.current_user.getNom()+" "+Statics.current_user.getPrenom(), "Title"),
                                    new Label(role, "SubTitle")
                                )
                            ).add(BorderLayout.WEST, profilePicLabel),
                        GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
                        fab.setVisible(false);
                        remainingTasks.setVisible(false);
                        completedTasks.setVisible(false);
        add(new Label("Today", "TodayTitle"));
//        add(imgv);
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
        addButtonBottom(arrowDown, "Nom : "+Statics.current_user.getNom(), 0xd997f1, true);
        addButtonBottom(arrowDown, "Prénom : "+Statics.current_user.getPrenom(), 0x5ae29d, false);
        addButtonBottom(arrowDown, "Email : "+Statics.current_user.getEmail(), 0x4dc2ff, false);
        addButtonBottom(arrowDown, "Télephone : "+Statics.current_user.getTelephone(), 0xffc06f, false);
        addButtonBottom(arrowDown, "Username : "+Statics.current_user.getUsername(), 0xd997f1, true);
//        addButtonBottom(arrowDown, "Email : "+Vars.current_user.getEmail(), 0x5ae29d, false);
//        addButtonBottom(arrowDown, "Javascript training ", 0x4dc2ff, false);
//        addButtonBottom(arrowDown, "Surprise Party for Matt", 0xffc06f, false);
        setupSideMenu(res);
    }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
