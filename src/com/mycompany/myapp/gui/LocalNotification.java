/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author nahawnd
 */

    public class LocalNotification  extends SideMenuBaseForm{

      public LocalNotification(Resources res) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("notif.jpg");        
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
      //  settingsButton.addActionListener(e ->{ Statics.current_choice=1;new AfficherListeAnnonces(res).show();});
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label(" Notifications des ", "WelcomeBlue"),
                                new Label("Reservations ", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        
          
          
       //#####begin
          
      /*      LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("Quelqu'un veut réserver votre vélo");
        n.setAlertTitle("nouvelle reservation!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
      }
      }
        /*     try {
            theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch(IOException e){
            e.printStackTrace();
        }
              int badgeNumber = 0;
             
             Form hi = new Form("Hi World");
        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        hi.addComponent(new Label("ID"));
        final TextField id = new TextField();
        hi.addComponent(id);
        
        hi.addComponent(new Label("Title"));
        final TextField title = new TextField();
        hi.addComponent(title);
        
        hi.addComponent(new Label("Body"));
        final TextField body = new TextField();
        hi.addComponent(body);
        
        
        hi.addComponent(new Label("Interval"));
        final ComboBox interval = new ComboBox(new Object[]{ "None", "Minute", "Hour", "Day", "Week"});
        hi.addComponent(interval);
        
        
        Button b = new Button("Send Notification");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalNotification n = new LocalNotification();
                n.setAlertBody(body.getText());
                n.setAlertTitle(title.getText());
                n.setId(id.getText());
              n.setBadgeNumber(badgeNumber++);

                int repeatType = LocalNotification.REPEAT_NONE;
                String selRepeat = (String)interval.getModel().getItemAt(interval.getModel().getSelectedIndex());
                if ("Minute".equals(selRepeat)) {
                    repeatType = LocalNotification.REPEAT_MINUTE;
                } else if ("Hour".equals(selRepeat)) {
                    repeatType = LocalNotification.REPEAT_HOUR;
                } else if ("Day".equals(selRepeat)) {
                    repeatType = LocalNotification.REPEAT_DAY;
                } else if( "Week".equals(selRepeat)) {
                    repeatType = LocalNotification.REPEAT_WEEK;
                }

                Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis() + 10 * 1000, repeatType);
            }
        });
        hi.addComponent(b);
        
        Button cancel = new Button("Cancel Notification");
            
        cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().cancelLocalNotification(id.getText());
            }
            
        });
        
        
        hi.addComponent(cancel);
        
        Button clearBadgeNumber = new Button("Clear Badge");
        clearBadgeNumber.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().setBadgeNumber(0);
            }
        });
        hi.addComponent(clearBadgeNumber);
        hi.show();
        
          
           private LocalNotification() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setAlertBody(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setAlertTitle(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setId(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setBadgeNumber(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        */
        
      }
   
 @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
   
   
}
