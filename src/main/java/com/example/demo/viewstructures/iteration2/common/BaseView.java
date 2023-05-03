package com.example.demo.viewstructures.iteration2.common;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

@JsModule("@vaadin/vaadin-lumo-styles/presets/compact.js")
public class BaseView extends VerticalLayout {

    private final LazyComponent<Toolbar> top = new LazyComponent<>(Toolbar::new, this::addComponentAsFirst);
    private final LazyComponent<Toolbar> bottom = new LazyComponent<>(Toolbar::new, this::add);
    private final Div mainContentContainer;

    public BaseView() {
        mainContentContainer = new Div();
        mainContentContainer.addClassName(LumoUtility.BoxShadow.SMALL);
        mainContentContainer.addClassName(LumoUtility.Margin.MEDIUM);
        addAndExpand(mainContentContainer);
        addClassName(LumoUtility.Background.CONTRAST_5);
        addClassName(LumoUtility.Padding.Vertical.MEDIUM);
        setAlignItems(Alignment.STRETCH);
        setSizeFull();
        setMargin(false);
        setPadding(false);
        setSpacing(false);
    }

    public BaseView withTopLeftComponents(Component... components) {
        top.get().withLeftComponents(components);
        return this;
    }

    public BaseView withTopRightComponents(Component... components) {
        top.get().withRightComponents(components);
        return this;
    }

    public BaseView withBottomLeftComponents(Component... components) {
        bottom.get().withLeftComponents(components);
        return this;
    }

    public BaseView withBottomRightComponents(Component... components) {
        bottom.get().withRightComponents(components);
        return this;
    }

    public BaseView withMainContent(Component component) {
        mainContentContainer.removeAll();
        mainContentContainer.add(component);
        return this;
    }
}
