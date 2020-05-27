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
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nahawnd
 */
public class ServiceReservation {

    public ArrayList<Reservation> reservations;

    public static ServiceReservation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReservation() {
        req = new ConnectionRequest();
    }

    public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }

    public boolean addReservation(Reservation r) {
        String url = Statics.BASE_URL + "http://127.0.0.1:8000/api/reservation/new" +
                r.getDateDeb()  +
                "/" + r.getDateFin();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Reservation> parseReservations(String jsonText) {
        try {
            reservations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> reservationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reservationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reservation r = new Reservation();
                int id = Integer.parseInt(obj.get("id").toString());
                r.setId((int) id);
                r.setDateDeb(obj.get("date creation").toString());
                r.setDateFin(obj.get("date creation").toString());
                reservations.add(r);
            }

        } catch (IOException ex) {

        }
        return reservations;
    }

    public ArrayList<Reservation> getAllReservations() {
        String url = Statics.BASE_URL + "http://127.0.0.1:8000/api/reservations/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }

    public void updateReservation(Reservation r) {
        ConnectionRequest con = new ConnectionRequest();

        String url = "http://127.0.0.1:8000/api/reservation/update"
                + r.getId() + "/" + r.getDateDeb()
                + "/" + r.getDateFin();
        System.err.println(url);
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            System.out.println("Reservation updated");
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //appel asynchrone
    }

    public void DeleteReservation(int id) {
        ConnectionRequest con = new ConnectionRequest();

        String url = "http://127.0.0.1:8000/api/reservation/delete" + id;
        System.err.println(url);
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con); //appel asynchrone

    }

}
