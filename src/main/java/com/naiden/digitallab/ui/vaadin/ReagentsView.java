package com.naiden.digitallab.ui.vaadin;

import com.naiden.digitallab.model.Reagent;
import com.naiden.digitallab.repository.ReagentRepository;
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
@SpringView(name = ReagentsView.VIEW_NAME)
public class ReagentsView extends Grid<Reagent> implements View{
    public static final String VIEW_NAME = "reagentsView";


    @Autowired
    private ReagentRepository reagentRepository;

    public ReagentsView() {
        super(Reagent.class);
    }

    @PostConstruct
    void init() {
        this.setSizeFull();

        List<Reagent> reagents = new ArrayList<>();
        reagentRepository.findAll().forEach(reagents::add);
        setItems(reagents);
        setColumnOrder("id","compound","location","receiptDate","storageLife","comments");

//        ListDataProvider<Reagent> reagentss = new ListDataProvider<>(reagents);
//        this.setDataProvider(reagentss);
//        this.addColumn(Reagent::getId).setCaption("Id");
//        this.addColumn(Reagent::getCompound).setCaption("Compound");
//        this.addColumn(Reagent::getLocation).setCaption("Location");
//        this.addColumn(Reagent::getReceiptDate).setCaption("receiptDate");
//        this.addColumn(Reagent::getStorageLife).setCaption("storageLife");
//        this.addColumn(Reagent::getComments).setCaption("comments");
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }

}
