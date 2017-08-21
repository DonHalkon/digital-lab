package com.naiden.digitallab.ui.vaadin;

import com.naiden.digitallab.model.Compound;
import com.naiden.digitallab.repository.CompoundRepository;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@UIScope
@SpringView(name = CompoundsView.VIEW_NAME)
public class CompoundsView extends Grid<Compound> implements View {
    public static final String VIEW_NAME = "compoundsView";

    @Autowired
    private CompoundRepository compoundRepository;

    @PostConstruct
    void init() {
        this.setSizeFull();
        List<Compound> compoundList = new ArrayList<>();
        compoundRepository.findAll().forEach(compoundList::add);

        ListDataProvider<Compound> compoundListDataProvider = new ListDataProvider<>(compoundList);
        this.setDataProvider(compoundListDataProvider);
        this.addColumn(Compound::getId).setCaption("Id");
        this.addColumn(Compound::getName).setCaption("Name");
        this.addColumn(Compound::getCas).setCaption("CAS");
        this.addColumn(Compound::getSmiles).setCaption("smiles");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}
