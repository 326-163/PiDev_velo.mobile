/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.myapp.services.UserService;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.entities.fos_user;
import java.io.IOException;
//import static org.apache.commons.text.CharacterPredicates.DIGITS;
//import org.apache.commons.text.RandomStringGenerator;


/**
 * The Login form
 *
 * @author Shai Almog
 */

/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */




/**
 * The Login form
 *
 * @author Shai Almog
 */
public class RegisterForm extends Form {
            fos_user u=new fos_user();
            String fileNameInServer = "";
            boolean upim=true;
            
    public RegisterForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Créer un compte ", "WelcomeWhite"),
                new Label("GRATUIT", "WelcomeBlue")
        );
        
        getTitleArea().setUIID("Container");
        
        Image profilePic = theme.getImage("user.jpg");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());
        
        TextField tLogin = new TextField("", "Username", 20, TextField.USERNAME) ;
        TextField tNom = new TextField("", "Nom", 20, TextField.USERNAME) ;
        TextField tPrenom = new TextField("", "Prénom", 20, TextField.USERNAME) ;
        TextField tEmail = new TextField("", "Email", 20, TextField.EMAILADDR) ;
        TextField tPhone = new TextField("", "Téléphone", 20, TextField.PHONENUMBER) ;
        TextField tPassword = new TextField("", "Mot de passe", 20, TextField.PASSWORD) ;
        tLogin.getAllStyles().setMargin(LEFT, 0);
        tPassword.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        Label nomIcon = new Label("", "TextField");
        Label prenomIcon = new Label("", "TextField");
        Label emailIcon = new Label("", "TextField");
        Label telephoneIcon = new Label("", "TextField");
        Label photoIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        nomIcon.getAllStyles().setMargin(RIGHT, 0);
        prenomIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(nomIcon, FontImage.MATERIAL_PEOPLE_ALT, 3);
        FontImage.setMaterialIcon(prenomIcon, FontImage.MATERIAL_PERSON_PIN, 3);
        FontImage.setMaterialIcon(emailIcon, FontImage.MATERIAL_EMAIL, 3);
        FontImage.setMaterialIcon(telephoneIcon, FontImage.MATERIAL_PHONE, 3);
        FontImage.setMaterialIcon(photoIcon, FontImage.MATERIAL_ADD_A_PHOTO, 3);
        
        Button registerButton = new Button("S'inscrire");
        registerButton.setUIID("LoginButton");
//        loginButton.addActionListener(e -> {
//            Toolbar.setGlobalToolbar(false);
//            new WalkthruForm(theme).show();
//            Toolbar.setGlobalToolbar(true);
//        });
        registerButton.addActionListener((e) -> {
            System.out.println("connection buuton clicked");
//            UserService us = new UserService();
            u.setUsername(tLogin.getText());
            u.setUsernameCanonical(tLogin.getText());
            u.setNom(tNom.getText());
            u.setPrenom(tPrenom.getText());
            u.setEmail(tEmail.getText());
            u.setEmailCanonical(tEmail.getText());
            u.setTelephone(tPhone.getText());
            u.setPassword(tPassword.getText());
            u.setPhoto(fileNameInServer);
            System.out.println(u.toString());
//            System.out.println("before current user "+Vars.current_user.toString());
                            UserService.getInstance().Register(u);
//                            System.out.println(Vars.unlock_newMarker);
//            try {
//                //                                        System.out.println("after current user "+Vars.current_user.toString());
//                Thread.sleep(5000);
//            } catch (InterruptedException ex) {
//                System.out.println("ex "+ex);
//            }
//                                        System.out.println(Vars.unlock_newMarker);
//

                            if(verifierChamps(u)){
                            if (Statics.current_user == null) {
                                if (Dialog.show("Error", "Username ou mail existant déja", "ok", null)) {
                                    showBack();
                                }
                            } else {
                                 Toolbar.setGlobalToolbar(false);
                                 new WalkthruForm(theme).show();
                                 Toolbar.setGlobalToolbar(true);
                            }
                            }
//            if (Vars.current_user == null) {
//                if (Dialog.show("Error", "Username ou mot de passe incorrect", "ok", null)) {
//
//                    showBack();
//                }
//            } else {
//                
//                Toolbar.setGlobalToolbar(false);
//                new WalkthruForm(theme).show();
//                Toolbar.setGlobalToolbar(true);
//
//            }

        });
        Button connect = new Button("Vous disposez déjà d'un compte ? Se connecter »");
        connect.setUIID("CreateNewAccountButton");
        connect.addActionListener((e) -> {new LoginForm(theme).show();});
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        Button upload = new Button("upload photo");
//        upload.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
//        upload.setUIID("Title");
//        FontImage.setMaterialIcon(upload, FontImage.MATERIAL_ADD_A_PHOTO,5);
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                upim=true;
                try {
                    
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    //t7ot l url ta3 l fichier php
                    cr.setUrl("http://localhost/Rent/uploads/user/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    try{
                    cr.addData("file", filepath, mime);
                    }catch(java.lang.NullPointerException ex){
                        upim=false;
                        System.out.println("no file selected");
                    }
//                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
               /*   if(upim){
                    RandomStringGenerator randomStringGenerator;
                        randomStringGenerator = new RandomStringGenerator.Builder()
                                .withinRange('0', '9')
                                .filteredBy(DIGITS)
                                .build();
            String out=randomStringGenerator.generate(16).toLowerCase();
                    cr.setFilename("file", out + ".jpeg");//any unique name you want
//filenameinserver héyal variable l jdida ta3 l l'img
                    fileNameInServer += out + ".jpeg";
                    System.err.println("path2 =" + fileNameInServer);
                    //ta3mel l set mta3 l 'image
//                    Vars.current_deal = new Deal(1);
//                    Image im = new Image(1, out, "png");
                    System.out.println("filename"+fileNameInServer);
                    u.setPhoto(fileNameInServer);
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                }*/
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        Container by = BoxLayout.encloseY(welcome,
                profilePicLabel,
                spaceLabel,
                BorderLayout.center(tLogin).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(tNom).
                        add(BorderLayout.WEST, nomIcon),
                BorderLayout.center(tPrenom).
                        add(BorderLayout.WEST, prenomIcon),
                BorderLayout.center(tEmail).
                        add(BorderLayout.WEST, emailIcon),
                BorderLayout.center(tPhone).
                        add(BorderLayout.WEST, telephoneIcon),
                BorderLayout.center(tPassword).
                        add(BorderLayout.WEST, passwordIcon),
                BorderLayout.center(upload).
                        add(BorderLayout.WEST, photoIcon),
                registerButton,
                connect
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
    
    public boolean verifierChamps(fos_user u){
        if(u.getUsername().equals("")){
                Dialog.show("Error", "Veuillez remplir le champ par votre Username","OK",null);
                return false;
            }
        if(u.getNom().equals("")){
                Dialog.show("Error", "Veuillez remplir le champ par votre nom","OK",null);
                return false;
            }
        if(u.getPrenom().equals("")){
                Dialog.show("Error", "Veuillez remplir le champ par votre prénom","OK",null);
                return false;
            }
        if(u.getEmail().equals("")){
                Dialog.show("Error", "Veuillez remplir le champ par votre email","OK",null);
                return false;
            }
        String get = u.getEmail();
                    char a = '@';

                    int count = 0;
                    int atposition = 0, dotposition = 0, flag = 0;

                    for (int i = 0; i < get.length(); i++) {
                        if (get.charAt(i) == a) {
                            count++;
                            atposition = i;
                            if (count >= 2) {
                                flag = 1;
                                break;
                            }
                        }

                        if (get.charAt(i) == '.') {
                            dotposition = i;
                        }
                    }
                    if (atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= get.length() || flag == 1) {
                         Dialog.show("Error", "E-Mail invalide", "ok", null);
                                 return false;}
         if(u.getTelephone().length()!=8){
                Dialog.show("Error", "Le numéro de téléphone doit avoir 8 chiffres","OK",null);
                return false;
            }
            try
        {
          Integer.parseInt(u.getTelephone());
        }
        catch(NumberFormatException ex)
        {
            Dialog.show("Error", "Le numéro de téléphone doit contenir que des caractères numériques!","OK",null);
            return false;
        }
        if(u.getPassword().equals("")){
                Dialog.show("Error", "Veuillez écrire votre mot de passe","OK",null);
                return false;
            }
        if(u.getPhoto().equals("")){
                Dialog.show("Error", "Veuillez choisir une photo","OK",null);
                return false;
            }
        return true;
    }
}
