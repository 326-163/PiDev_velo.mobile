package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;


public class login extends Form  {
    public login(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Label gui_Label = new com.codename1.ui.Label();
    protected com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    protected com.codename1.ui.TextField gui_Text_Field = new com.codename1.ui.TextField();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(false);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("login");
        setName("login");
                gui_Label.setInlineStylesTheme(resourceObjectInstance);
        gui_Label.setName("Label");
        gui_Label.setIcon(resourceObjectInstance.getImage("h.png"));
        gui_Label_1.setText("Label");
                gui_Label_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_1.setName("Label_1");
        gui_Text_Field.setText("TextField");
                gui_Text_Field.setInlineStylesTheme(resourceObjectInstance);
        gui_Text_Field.setName("Text_Field");
        addComponent(gui_Label);
        addComponent(gui_Label_1);
        addComponent(gui_Text_Field);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label.getParent().getLayout()).setInsets(gui_Label, "11.937378% auto auto auto").setReferenceComponents(gui_Label, "-1 -1 -1 -1").setReferencePositions(gui_Label, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label_1.getParent().getLayout()).setInsets(gui_Label_1, "15.443038% auto auto 12.219452%").setReferenceComponents(gui_Label_1, "0 -1 -1 -1").setReferencePositions(gui_Label_1, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Text_Field.getParent().getLayout()).setInsets(gui_Text_Field, "6.9536424% 37.905235% auto 12.219451%").setReferenceComponents(gui_Text_Field, "1 -1 -1 -1").setReferencePositions(gui_Text_Field, "1.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
