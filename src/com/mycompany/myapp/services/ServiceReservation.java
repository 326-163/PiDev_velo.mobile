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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

    public ArrayList<Reservation> getAllReservations(String json) {
        ArrayList<Reservation> listReservations = new ArrayList<>();
        try {

            JSONParser j = new JSONParser();

            Map<String, Object> reservations = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reservations.get("root");
            System.out.println(json);
            for (Map<String, Object> obj : list) {
                Reservation r = new Reservation();

                r.setId((int) Float.parseFloat(obj.get("id").toString()));
                r.setTitre(obj.get("titre").toString());
                String sdateDeb = obj.get("date creation").toString();
                String sdateFin = obj.get("date creation").toString();
                try {
                    Date dateDeb = new SimpleDateFormat("dd/MM/yyyy").parse(sdateDeb);
                    Date dateFin = new SimpleDateFormat("dd/MM/yyyy").parse(sdateFin);
                    r.setDateDeb(dateDeb);
                    r.setDateFin(dateFin);

                } catch (ParseException ex) {
                    System.out.println("ex date" + ex);
                }
                r.setId((int) Float.parseFloat(obj.get("id").toString()));
                listReservations.add(r);
            }

        } catch (IOException ex) {
        }

        return listReservations;

    }

  ArrayList<Reservation> listReservations = new ArrayList<>();
   
    public ArrayList<Reservation> getReservations() {
  req.removeAllArguments();
         req.setPost(false);
        req.removeAllArguments();

 if (Statics.current_choice == 1) {
            req.setUrl(Statics.BASE_URL+"/api/reservations/all");
//            req.addArgument("user", String.valueOf(Statics.current_user.getId()));
        }
//        else  if (Statics.current_choice == 2) {
//                req.setUrl(Statics.BASE_URL+"/api/mobile/reservation/mesreservations/");
//                req.addArgument("user", String.valueOf(Statics.current_user.getId()));
//            } 
        else if (Statics.current_choice == 2) {
                    req.setUrl(Statics.BASE_URL+"/api/reservation/recherche");
                    req.addArgument("recherche", String.valueOf(Statics.current_search));
                } 

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             ServiceReservation ds = new ServiceReservation();
                listReservations = ds.getAllReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }

    /*   String url = Statics.BASE_URL + "http://127.0.0.1:8000/api/reservations/all";
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
        return reservations;*/
    public void addReservation(Reservation r) {
        req.removeAllArguments();
        req.setPost(true);
        req.setUrl(Statics.BASE_URL + "/api/reservation/new");
        req.addArgument("titre", String.valueOf(r.getTitre()));
        req.addArgument("date debut", new SimpleDateFormat("dd-MM-yyyy").format(r.getDateDeb()));
        req.addArgument("date fin", new SimpleDateFormat("dd-MM-yyyy").format(r.getDateFin()));

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("http response: " + req.getResponseCode());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        
        
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("Your reservation has been accepted");
        n.setAlertTitle("Reservation acceptée!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
    }

    /* String url = Statics.BASE_URL + "http://127.0.0.1:8000/api/reservation/new" +
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
                r.setDateDeb(obj.get("dateDeb").toString());
                r.setDateFin(obj.get("date fin").toString());
                reservations.add(r);
            }

        } catch (IOException ex) {

        }
        return reservations;*/
    
    
    public void updateReservation(Reservation r) {
//        ConnectionRequest con = new ConnectionRequest();

        String url = "http://127.0.0.1:8000/api/reservation/update"
                
                + r.getId() 
                + "/" + r.getDateDeb()
                + "/" + r.getDateFin();
        System.err.println(url);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);
            System.out.println("Reservation updated");
        });
        NetworkManager.getInstance().addToQueueAndWait(req); //appel asynchrone
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
