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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Location;
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

    public ArrayList<Location> getAllLocations(String json) {

        ArrayList<Location> listLocations = new ArrayList<>();
        try {

            JSONParser j = new JSONParser();

            Map<String, Object> locations = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) locations.get("root");
            System.out.println(json);
            for (Map<String, Object> obj : list) {
                Location l = new Location();

                l.setId((int) Float.parseFloat(obj.get("id").toString()));
                l.setTitre(obj.get("titre").toString());
                l.setLieu(obj.get("lieu").toString());
                l.setPrix(Float.parseFloat(obj.get("prix").toString()));
                l.setPhoto(obj.get("photo").toString());
                String sdateCreation = obj.get("date creation").toString();
                try {
                    Date dateCreation = new SimpleDateFormat("dd/MM/yyyy").parse(sdateCreation);
                    l.setDateCreation(dateCreation);

                } catch (ParseException ex) {
                    System.out.println("ex date" + ex);
                }

                l.setId((int) Float.parseFloat(obj.get("id").toString()));
                l.setTitre(obj.get("titre").toString());
                l.setLieu(obj.get("lieu").toString());
                l.setPrix(Float.parseFloat(obj.get("prix").toString()));
                // l.setIdU((int) Float.parseFloat(obj.get("idU").toString()));
                listLocations.add(l);
            }

        } catch (IOException ex) {
        }

        return listLocations;

    }

    /* ArrayList<Location> listLocations = new ArrayList<>();

    public ArrayList<Location> getLocations() {
        req.removeAllArguments();
        req.setPost(false);
        req.removeAllArguments();

        if (Statics.current_choice == 1) {
            req.setUrl(Statics.BASE_URL + "/locations/all");
//            con.addArgument("user", String.valueOf(Vars.current_user.getId()));

        } else if (Statics.current_choice == 2) {
            req.setUrl(Statics.BASE_URL + "/locations/all");
            req.addArgument("user", String.valueOf(Statics.current_user.getId()));
            
        } else if (Statics.current_choice == 3) {
            req.setUrl(Statics.BASE_URL + "/annonce/recherche/");
            req.addArgument("recherche", String.valueOf(Statics.current_search));
            
        } else if (Statics.current_choice == 4) {
            req.setUrl(Statics.BASE_URL + "/locations/all");
            req.addArgument("choix", Statics.current_type);


        } else if (Statics.current_choice == 5) {
            req.setUrl(Statics.BASE_URL + "/locations/all");

            
        } else if (Statics.current_choice == 6) {
            req.setUrl(Statics.BASE_URL + "/locations/all");
//                       

            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    ServiceLocation ds = new ServiceLocation();
                    listLocations = ds.getAllLocations(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return listLocations;
        }
    }
     */
    public ArrayList<Location> parseLocations(String jsonText) {
        try {
            locations = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
            c'est la clé définissant le tableau de tâches.*/
            Map<String, Object> locationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
             */
            List<Map<String, Object>> list = (List<Map<String, Object>>) locationsListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                Location l = new Location();   //Création des tâches et récupération de leurs données
                float id = Float.parseFloat(obj.get("id").toString());
                l.setId((int) id);
                l.setTitre(obj.get("titre").toString());
                l.setLieu(obj.get("lieu").toString());
                l.setPrix((Float) Float.parseFloat(obj.get("prix").toString()));
                l.setPhoto(obj.get("photo").toString());
                req.addArgument("date creation", new SimpleDateFormat("dd-MM-yyyy").format(l.getDateCreation()));

                locations.add(l);     //Ajouter la tâche extraite de la réponse Json à la liste
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return locations;
    }

    public ArrayList<Location> getAllLocations() {
        String url = Statics.BASE_URL + "/api/locations/all";
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

    public boolean addLocation(Location l) {
        String url = Statics.BASE_URL + "/api/location/new/"
                + l.getId()
                + l.getTitre()
                + "/" + l.getLieu()
                + "/" + l.getPrix()
                //                + "/" + l.getPhoto()
                + "/" + l.getDateCreation();//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public void updateLocation(Location l, Resources res) {
        req.removeAllArguments();
        req.setPost(true);
        req.setUrl(Statics.BASE_URL + " /api/location/update/");
//        con.addArgument("ens", String.valueOf(x));
        req.addArgument("id", String.valueOf(l.getId()));
        req.addArgument("titre", String.valueOf(l.getTitre()));
        req.addArgument("lieu", String.valueOf(l.getLieu()));
        req.addArgument("prix", String.valueOf(l.getPrix()));
//        req.addArgument("photo", l.getPhoto());
        req.addArgument("date creation", new SimpleDateFormat("dd-MM-yyyy").format(l.getDateCreation()));

        req.addArgument("id", String.valueOf(l.getId()));
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("http response: " + req.getResponseCode());
                Dialog.show("Succés", "Publication modifié", "ok", null);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        /*   ConnectionRequest con = new ConnectionRequest();

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
        NetworkManager.getInstance().addToQueueAndWait(con); //appel asynchrone*/
    }

    public void DeleteLocation(int id) {
        String url = "/api/location/delete?id=" + id;
        System.err.println(url);
        req.setUrl(url);

        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            Dialog.show("Succés", "Publication Supprimer", "ok", null);
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req); //appel asynchrone

    }

    public ArrayList<Location> findLocation(int id) {
        String url = Statics.BASE_URL + "/api/location/recherche" + id;
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

    public ArrayList<Location> getLocationsByTitre(String tittre) {
        String url = Statics.BASE_URL + "/api/location/recherche/";
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

    public void EnvoiNotification(int id) {

    }
}
