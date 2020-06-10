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
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Map;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nahawnd
 */
public class UserService {
      public ArrayList<fos_user> users;
    public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private UserService() {
         req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public boolean addUser(fos_user t)
    {
        
     String url = Statics.BASE_URL + "/users/" +t.getUsername()+"/"+ t.getUsernameCanonical()+ "/" + t.getEmail()+"/"+t.getEmailCanonical()+"/"+ t.getEnabled()+"/"+t.getPassword()+"/"+t.getRoles()+"/"+"/"+t.getTelephone()+"/"+t.getPhoto();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() 
        {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;   
    }
    
    
    
    public ArrayList<fos_user> parseUsers(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                fos_user t = new fos_user();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setEmail(obj.get("email").toString());
                t.setUsername(obj.get("username").toString());
                 t.setPassword(obj.get("password").toString());
            users.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return users;
    }
    
    
     public ArrayList<fos_user> getAllUsers(){
        String url = Statics.BASE_URL+"/users/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
    
    public void Login(String username, String password) {
//        ConnectionRequest con = new ConnectionRequest();
        req.setPost(true);
        req.setUrl(Statics.BASE_URL+"/login");
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

                        Statics.current_user = new fos_user((int) Float.parseFloat(u.get("id").toString()));
                        Statics.current_user.setUsername(u.get("username").toString());
                        Statics.current_user.setEmail(u.get("email").toString());
                        Statics.current_user.setPhoto(u.get("photo").toString());
                        Statics.current_user.setNom(u.get("nom").toString());
                        Statics.current_user.setPrenom(u.get("prenom").toString());
                        Statics.current_user.setTelephone(u.get("telephone").toString());
                        Statics.current_user.setRoles(u.get("roles").toString());
                        //Statics.out.println("tel : "+u.get("telephone").toString());
                        System.out.println(Statics.current_user);//kenet statics.out
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void Register(fos_user user) {
//        ConnectionRequest con = new ConnectionRequest();
        req.setPost(true);
        req.setUrl(Statics.BASE_URL+"/register");
        req.addArgument("username", user.getUsername());
        req.addArgument("nom", user.getNom());
        req.addArgument("prenom", user.getPrenom());
        req.addArgument("email", user.getEmail());
        req.addArgument("telephone", user.getTelephone());
        req.addArgument("password", user.getPassword());
        req.addArgument("photo", user.getPhoto());
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

                        Statics.current_user=user;
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
