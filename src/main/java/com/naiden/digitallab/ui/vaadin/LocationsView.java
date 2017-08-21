package com.naiden.digitallab.ui.vaadin;

import com.naiden.digitallab.model.Location;
import com.naiden.digitallab.model.Location;
import com.naiden.digitallab.repository.LocationRepository;
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
@SpringView(name = LocationsView.VIEW_NAME)
public class LocationsView extends Grid<Location> implements View {
    public static final String VIEW_NAME = "locationsView";

    @Autowired
    private LocationRepository locationRepository;

    @PostConstruct
    void init() {
        this.setSizeFull();
        List<Location> LocationList = new ArrayList<>();
        locationRepository.findAll().forEach(LocationList::add);

        ListDataProvider<Location> LocationListDataProvider = new ListDataProvider<>(LocationList);
        this.setDataProvider(LocationListDataProvider);
        this.addColumn(Location::getId).setCaption("Id").setMaximumWidth(100);
        this.addColumn(Location::getDescr).setCaption("Description");
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}
