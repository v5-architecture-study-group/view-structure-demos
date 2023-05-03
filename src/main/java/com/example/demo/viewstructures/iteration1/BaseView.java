package com.example.demo.viewstructures.iteration1;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@JsModule("@vaadin/vaadin-lumo-styles/presets/compact.js")
class BaseView extends VerticalLayout { // Package protected to prevent accidental import into other iterations

    private HorizontalLayout top;
    private HorizontalLayout topLeftSection;
    private HorizontalLayout topRightSection;
    private HorizontalLayout bottom;
    private HorizontalLayout bottomLeftSection;
    private HorizontalLayout bottomRightSection;

    public BaseView() {
        setSizeFull();
        setMargin(false);
        setPadding(true);
        setSpacing(true);
    }

    private HorizontalLayout topToolbar() {
        if (top == null) {
            top = createToolbar();
            addComponentAsFirst(top);
        }
        return top;
    }

    private HorizontalLayout topLeftToolbarSection() {
        if (topLeftSection == null) {
            topLeftSection = createToolbarSection();
            topToolbar().addComponentAsFirst(topLeftSection);

        }
        return topLeftSection;
    }

    private HorizontalLayout topRightToolbarSection() {
        if (topRightSection == null) {
            topRightSection = createToolbarSection();
            topToolbar().add(topRightSection);
        }
        return topRightSection;
    }

    private HorizontalLayout bottomToolbar() {
        if (bottom == null) {
            bottom = createToolbar();
            add(bottom);
        }
        return bottom;
    }

    private HorizontalLayout bottomLeftToolbarSection() {
        if (bottomLeftSection == null) {
            bottomLeftSection = createToolbarSection();
            bottomToolbar().addComponentAsFirst(bottomLeftSection);
        }
        return bottomLeftSection;
    }

    private HorizontalLayout bottomRightToolbarSection() {
        if (bottomRightSection == null) {
            bottomRightSection = createToolbarSection();
            bottomToolbar().add(bottomRightSection);
        }
        return bottomRightSection;
    }

    private HorizontalLayout createToolbar() {
        var toolbar = new HorizontalLayout();
        toolbar.setWidthFull();
        toolbar.setJustifyContentMode(JustifyContentMode.BETWEEN);
        toolbar.setSpacing(false);
        return toolbar;
    }

    private HorizontalLayout createToolbarSection() {
        var section = new HorizontalLayout();
        section.setSizeUndefined();
        section.setSpacing(true);
        return section;
    }

    public BaseView withTopLeftComponents(Component... components) {
        topLeftToolbarSection().add(components);
        return this;
    }

    public BaseView withTopRightComponents(Component... components) {
        topRightToolbarSection().add(components);
        return this;
    }

    public BaseView withBottomLeftComponents(Component... components) {
        bottomLeftToolbarSection().add(components);
        return this;
    }

    public BaseView withBottomRightComponents(Component... components) {
        bottomRightToolbarSection().add(components);
        return this;
    }

    public BaseView withMainContent(Component component) {
        if (top == null) {
            addComponentAsFirst(component);
        } else {
            addComponentAtIndex(1, component);
        }
        return this;
    }
}
