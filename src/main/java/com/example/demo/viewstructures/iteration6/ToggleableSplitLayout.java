package com.example.demo.viewstructures.iteration6;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayoutVariant;

import static java.util.Objects.requireNonNull;

class ToggleableSplitLayout extends FlexLayout {

    private final Component primaryComponent;
    private final Component secondaryComponent;
    private final SplitLayout splitLayout;
    private ToggleMode toggleMode = ToggleMode.BOTH_VISIBLE;

    public ToggleableSplitLayout(Component primaryComponent, Component secondaryComponent) {
        this.primaryComponent = requireNonNull(primaryComponent, "primaryComponent must not be null");
        this.secondaryComponent = requireNonNull(secondaryComponent, "secondaryComponent must not be null");
        splitLayout = new SplitLayout();
        splitLayout.addThemeVariants(SplitLayoutVariant.LUMO_MINIMAL);
        setFlexGrow(1, splitLayout);
        updateLayout();
    }

    public ToggleMode toggleMode() {
        return toggleMode;
    }

    public void setToggleMode(ToggleMode toggleMode) {
        requireNonNull(toggleMode, "toggleMode must not be null");
        if (!this.toggleMode.equals(toggleMode)) {
            this.toggleMode = toggleMode;
            updateLayout();
        }
    }

    public void setOrientation(SplitLayout.Orientation orientation) {
        splitLayout.setOrientation(orientation);
    }

    public void setSplitterPosition(double position) {
        splitLayout.setSplitterPosition(position);
    }

    public void addThemeVariants(SplitLayoutVariant... variants) {
        splitLayout.addThemeVariants(variants);
    }

    private void updateLayout() {
        removeAll();
        splitLayout.removeAll();
        switch (toggleMode) {
            case PRIMARY_VISIBLE_ONLY -> add(primaryComponent);
            case SECONDARY_VISIBLE_ONlY -> add(secondaryComponent);
            case BOTH_VISIBLE -> {
                splitLayout.addToPrimary(primaryComponent);
                splitLayout.addToSecondary(secondaryComponent);
                add(splitLayout);
            }
        }
    }

    public enum ToggleMode {
        PRIMARY_VISIBLE_ONLY,
        SECONDARY_VISIBLE_ONlY,
        BOTH_VISIBLE
    }
}
