/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.ServiceLocation;

/**
 *
 * @author nahawnd
 */
public class ListLocationsForm extends Form{
      public ListLocationsForm(Form previous) {
        setTitle("List Locations");
        
         SpanLabel sp = new SpanLabel();
        sp.setText(ServiceLocation.getInstance().getAllLocations().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
    }
}
    