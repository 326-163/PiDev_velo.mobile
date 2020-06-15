/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.entities.Reservation;

/**
 *
 * @author nahawnd
 */
public class Statics {

    
   // public static final String BASE_URL="http://127.0.0.1:8000/PiDev_velo.tn/api";
   public static final String BASE_URL="http://localhost/PiDev_velo.tn/web/app_dev.php";
   
     public static int current_admin = 0;
    public static int current_choice = 0;
//  public static FosUser current_user;
    public static Location current_location;
    public static Reservation current_reservation;
    public static fos_user current_user;
    public static String current_type;
    public static String current_search;
    public static int unlock_oldMarker = 0;
    public static int unlock_newMarker = 0;
    public static String comment_user_name;
}
