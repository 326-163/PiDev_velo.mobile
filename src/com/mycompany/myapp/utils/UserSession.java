/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.entities.fos_user;

/**
 *
 * @author Anis
 */
public class UserSession {
     public static UserSession instance;

    private fos_user u;

    public fos_user getU() {
        return u;
    }

   

    @Override
    public String toString() {
        return "UserSession{" +
                "u=" + u +
                '}';
    }

    public UserSession(fos_user u) {
        this.u = u;

    }

    public static UserSession getInstance(fos_user u) {
        if(instance == null) {
            instance = new UserSession(u);
        }
        return instance;
    }

    

    public void cleanUserSession() {
        instance=null;
    }


    }

