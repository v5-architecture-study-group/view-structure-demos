package com.example.demo.viewstructures.iteration3;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayoutVariant;
import com.vaadin.flow.theme.lumo.LumoUtility;

@JsModule("@vaadin/vaadin-lumo-styles/presets/compact.js")
class BaseView extends VerticalLayout { // Package protected to prevent accidental import into other iterations

    private final LazyComponent<Toolbar> top = new LazyComponent<>(Toolbar::new, this::addComponentAsFirst);
    private final LazyComponent<Toolbar> bottom = new LazyComponent<>(Toolbar::new, this::add);
    private final Div mainContentContainer = createContentContainer();
    private final Div rightContentContainer = createContentContainer();
    private final SplitLayout splitLayout = new SplitLayout(mainContentContainer, rightContentContainer);

    public BaseView() {
        mainContentContainer.addClassName(LumoUtility.Margin.Left.MEDIUM);
        mainContentContainer.addClassName(LumoUtility.Margin.Right.SMALL);
        rightContentContainer.addClassName(LumoUtility.Margin.Left.SMALL);
        rightContentContainer.addClassName(LumoUtility.Margin.Right.MEDIUM);

        splitLayout.setSizeFull();
        splitLayout.addThemeVariants(SplitLayoutVariant.LUMO_MINIMAL);
        splitLayout.setSplitterPosition(80);
        add(splitLayout);
        addClassName(LumoUtility.Background.CONTRAST_5);
        addClassName(LumoUtility.Padding.Vertical.MEDIUM);
        setAlignItems(Alignment.STRETCH);
        setSizeFull();
        setMargin(false);
        setPadding(false);
        setSpacing(false);
    }

    private Div createContentContainer() {
        var div = new Div();
        div.addClassName(LumoUtility.BoxShadow.SMALL);
        div.addClassName(LumoUtility.Margin.Vertical.MEDIUM);
        div.addClassName(LumoUtility.BorderRadius.MEDIUM);
        return div;
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

    public BaseView withRightContent(Component component) {
        rightContentContainer.removeAll();
        rightContentContainer.add(component);
        return this;
    }
}
