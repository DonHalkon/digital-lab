package com.naiden.digitallab.js.components;


import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

@JavaScript({"jsme/jsme.nocache.js", "mycomponent-connector.js"})
public class JSME extends AbstractJavaScriptComponent {

    @Override
    protected JSMEState getState() {
        return (JSMEState) super.getState();
    }

}
