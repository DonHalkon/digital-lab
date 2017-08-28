package com.naiden.digitallab.js.components;


import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

import java.io.Serializable;
import java.util.ArrayList;

@JavaScript({"JSME/jsme.nocache.js", "jsme-connector.js"})
public class JSME extends AbstractJavaScriptComponent {

    public interface ValueChangeListener extends Serializable {
        void valueChange();
    }

    ArrayList<ValueChangeListener> listeners = new ArrayList<ValueChangeListener>();

    public void addValueChangeListener(ValueChangeListener listener) {
        listeners.add(listener);
    }

    public void setValue(String value) {
        getState().value = value;
    }

    public String getValue() {
        return getState().value;
    }

    @Override
    protected JSMEState getState() {
        return (JSMEState) super.getState();
    }

}
