package com.naiden.digitallab.ui.vaadin;

import com.naiden.digitallab.ui.vaadin.creationalviews.CreateNewCompoundView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme("valo")
@SpringUI
@SpringViewDisplay
public class MainView extends UI implements ViewDisplay {

    private Panel springViewDisplay;

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.WINDOW_TOP_TOOLBAR);
        navigationBar.addComponent(createNavigationButton("Show Reagents", ReagentsView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Show Compounds", CompoundsView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Show Locations", LocationsView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Add new reagent", ReagentsView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Add new compound", CreateNewCompoundView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Add new location", LocationsView.VIEW_NAME));
        root.addComponent(navigationBar);

        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        root.addComponent(springViewDisplay);
        root.setExpandRatio(springViewDisplay, 1.0f);

    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }

    @Override
    public void showView(View view) {
        springViewDisplay.setContent((Component) view);
    }
}
