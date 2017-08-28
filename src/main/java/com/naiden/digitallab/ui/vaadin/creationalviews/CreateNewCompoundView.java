package com.naiden.digitallab.ui.vaadin.creationalviews;

import com.naiden.digitallab.js.components.JSME;
import com.naiden.digitallab.js.components.JsLabel;
import com.naiden.digitallab.model.Compound;
import com.naiden.digitallab.repository.CompoundRepository;
import com.naiden.digitallab.ui.vaadin.CompoundsView;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import elemental.json.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@UIScope
@SpringView(name = CreateNewCompoundView.VIEW_NAME)
public class CreateNewCompoundView extends HorizontalLayout implements View {
    public static final String VIEW_NAME = "createNewCompoundView";


    @Autowired
    private CompoundRepository compoundRepository;

    @PostConstruct
    void init() {
        Binder<Compound> binder = new Binder<>(Compound.class);

        VerticalLayout leftPanel = new VerticalLayout();
        addComponent(leftPanel);

        TextField nameField = new TextField("Name:");
        TextField casField = new TextField("CAS:");
        TextField smilesField = new TextField("SMILES:");
        binder.bind(nameField, Compound::getName, Compound::setName);
        binder.bind(casField, Compound::getCas, Compound::setCas);
        binder.bind(smilesField, Compound::getSmiles, Compound::setSmiles);

        Button saveButton = new Button("Save", event -> {
            Compound compound = new Compound();
            try {
                binder.writeBean(compound);
                compoundRepository.save(compound);
                getUI().getNavigator().navigateTo(CompoundsView.VIEW_NAME);
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        });

        leftPanel.addComponent(nameField);
        leftPanel.addComponent(casField);
        leftPanel.addComponent(smilesField);
        leftPanel.addComponent(saveButton);

//        JSME jsme = new JSME();
        JsLabel jsLabel = new JsLabel("haha");


//        JavaScript.getCurrent().addFunction("jsme.jsme.nocache.jsme",
//                new JavaScriptFunction() {
//                    @Override
//                    public void call(JsonArray arguments) {
//                        Notification.show("Received call");
//                    }
//                });

//        Link link = new Link("Send Message", new ExternalResource(
//                "javascript:jsme.jsme.nocache.jsme()"));
        VerticalLayout rightPanel = new VerticalLayout();
        rightPanel.addComponent(jsLabel);
        addComponent(rightPanel);


    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }

}
