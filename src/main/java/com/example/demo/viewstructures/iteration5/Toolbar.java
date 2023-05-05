package com.example.demo.viewstructures.iteration5;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

class Toolbar extends HorizontalLayout { // Package protected to prevent accidental import into other iterations

    private final HorizontalLayout left;
    private final HorizontalLayout right;

    public Toolbar() {
        left = createSection();
        right = createSection();
        add(left, right);
        setWidthFull();
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        setSpacing(false);
    }

    public Toolbar withHorizontalPadding() {
        addClassName(LumoUtility.Padding.Horizontal.MEDIUM);
        return this;
    }

    public Toolbar withLeftComponents(Component... components) {
        left.add(components);
        return this;
    }

    public Toolbar withRightComponents(Component... components) {
        right.add(components);
        return this;
    }

    private HorizontalLayout createSection() {
        var section = new HorizontalLayout();
        section.setSizeUndefined();
        section.setSpacing(true);
        return section;
    }
}
