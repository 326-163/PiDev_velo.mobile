/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.util.Map;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.UserSession;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author nahawnd
 */
public class UserService {

    public static UserService instance;
    private ConnectionRequest req;
    
      
     public ArrayList<User> users;
    public boolean resultOK;


    public UserService() {
        req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    boolean result;

    public boolean loginAction(String username, String password) {

        // création d'une nouvelle demande de connexion
        String url = Statics.BASE_URL + "/login" + username + "/" + password;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion

        req.addResponseListener((e) -> {
            result = req.getResponseCode() == 200;
            if (result) {
                try {
                    parseListUserJson(new String(req.getResponseData()));
                    String str = new String(req.getResponseData());//Récupération de la réponse du serveur
                    System.out.println(str);//Affichage de la réponse serveur sur la console
                } catch (ParseException ex) {

                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        return result;
    }

    public User parseListUserJson(String json) throws ParseException {

        User u = new User();
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
            u.setId((int) (double) obj.get("id"));
            u.setUsername(obj.get("username").toString());
            u.setEmail(obj.get("email").toString());
            u.setRoles(obj.get("roles").toString());
            if (obj.get("telephoneNumber") != null) {
                u.setTelephone(obj.get("telephoneNumber").toString());
            }
            if (obj.get("profilePic") != null) {
                u.setPhoto(obj.get("profilePic").toString());
            }

            if (obj.get("nom") != null) {
                u.setNom(obj.get("name").toString());
            }
            if (obj.get("prenom") != null) {
                u.setPrenom(obj.get("firstName").toString());
            }

            UserSession z = UserSession.getInstance(u);
            System.out.println(UserSession.instance);

        } catch (IOException ex) {
        }

        return u;
    }

      public void Login(String username, String password) {
       req.setPost(true);
        req.setUrl(Statics.BASE_URL + "/login");
        req.addArgument("username", username);
        req.addArgument("password", password);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(req.getResponseData());
                JSONParser j = new JSONParser();
                if (json.compareTo("Failed") > 0) {
                    Map<String, Object> u;
                    try {
                        u = j.parseJSON(new CharArrayReader(json.toCharArray()));

                        Statics.current_user = new User((int) Float.parseFloat(u.get("id").toString()));
                        Statics.current_user.setUsername(u.get("username").toString());
                        Statics.current_user.setEmail(u.get("email").toString());
//                        Statics.current_user.setPhoto(u.get("photo").toString());
                        Statics.current_user.setNom(u.get("nom").toString());
                        Statics.current_user.setPrenom(u.get("prenom").toString());
                        Statics.current_user.setTelephone(u.get("telephone").toString());
                        Statics.current_user.setRoles(u.get("roles").toString());
                        System.out.println("tel : " + u.get("telephone").toString());
                        System.out.println(Statics.current_user);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void Register(User u) {
//        ConnectionRequest con = new ConnectionRequest();
        req.setPost(true);
        req.setUrl(Statics.BASE_URL + "/register");
        req.addArgument("username", u.getUsername());
        req.addArgument("nom", u.getNom());
        req.addArgument("prenom", u.getPrenom());
        req.addArgument("email", u.getEmail());
        req.addArgument("telephone", u.getTelephone());
        req.addArgument("password", u.getPassword());
        req.addArgument("photo", u.getPhoto());
        req.addArgument("roles", "ROLE_USER");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(req.getResponseData());
                JSONParser j = new JSONParser();
                if (json.compareTo("Failed") > 0) {
                    Map<String, Object> u;
                    try {
                        u = j.parseJSON(new CharArrayReader(json.toCharArray()));

//                        Statics.current_user = user;
                        Statics.current_user.setId((int) Float.parseFloat(u.get("id").toString()));
                        Statics.current_user.setRoles("ROLE_USER");
                        System.out.println(Statics.current_user);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
