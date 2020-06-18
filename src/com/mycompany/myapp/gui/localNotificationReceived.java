/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.util.Resources;

/**
 *
 * @author nahawnd
 */
public class localNotificationReceived extends SideMenuBaseForm {

    localNotificationReceived(Resources res) {
     
    }
     public void localNotificationReceived(String notificationId) {
                System.out.println("Received local notification "+notificationId);
            }
     
       @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
