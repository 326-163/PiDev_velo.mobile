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
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nahawnd
 */
public class ServiceLocation {

    public ArrayList<Location> locations;

    public static ServiceLocation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceLocation() {
        req = new ConnectionRequest();
    }

    public static ServiceLocation getInstance() {
        if (instance == null) {
            instance = new ServiceLocation();
        }
        return instance;
    }

    public boolean addLocation(Location l) {
        String url = Statics.BASE_URL + "/location/new/"
                + l.getTitre()
                + "/" + l.getLieu()
                + "/" + l.getPrix()
                + "/" + l.getPhoto()
                // + "/" + l.getRating()
                + "/" + l.getDateCreation();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//appel asynchrone
        return resultOK;
    }

    public ArrayList<Location> parseLocations(String jsonText) {
        try {
            locations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> locationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) locationsListJson.get("root");

            for (Map<String, Object> obj : list) {
                Location l = new Location();
                 float id = Float.parseFloat(obj.get("id").toString());
               // l.setId((int)id);
       
                // int rating = Integer.parseInt(obj.get("rating").toString());
                l.setId((int) id);
                l.setTitre(obj.get("titre").toString());
                l.setLieu(obj.get("lieu").toString());
                l.setPrix((Float) Float.parseFloat(obj.get("prix").toString()));
                l.setPhoto(obj.get("photo").toString());
                //l.setRating((int) rating);
                l.setDateCreation(obj.get("dateCreation").toString());
                locations.add(l);
            }

        } catch (IOException ex) {

        }
        return locations;
    }

    public ArrayList<Location> getAllLocations() {
        String url = Statics.BASE_URL +"/locations/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                locations = parseLocations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return locations;
    }

    public void updateLocation(Location l) {
        ConnectionRequest con = new ConnectionRequest();

        String url = "location/update"
                + l.getId()
                + "/" + l.getTitre()
                + "/" + l.getLieu()
                + "/" + l.getPrix()
                + "/" + l.getPhoto()
                + "/" + l.getDateCreation();
        System.err.println(url);
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            System.out.println("Location updated");
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //appel asynchrone
    }

    public void DeleteLocation(int id) {
        ConnectionRequest con = new ConnectionRequest();

        String url = "location/delete" + id;
        System.err.println(url);
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con); //appel asynchrone

    }

}
