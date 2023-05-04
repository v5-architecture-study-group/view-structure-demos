package com.example.demo.viewstructures.iteration4;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayoutVariant;
import com.vaadin.flow.theme.lumo.LumoUtility;

@JsModule("@vaadin/vaadin-lumo-styles/presets/compact.js")
class BaseView extends Composite<ContainerWithToolbars> { // Package protected to prevent accidental import into other iterations

    private final Div mainContentContainer = createContentContainer();
    private final Div rightContentContainer = createContentContainer();

    public BaseView() {
        mainContentContainer.addClassName(LumoUtility.Margin.Left.MEDIUM);
        mainContentContainer.addClassName(LumoUtility.Margin.Right.SMALL);
        rightContentContainer.addClassName(LumoUtility.Margin.Left.SMALL);
        rightContentContainer.addClassName(LumoUtility.Margin.Right.MEDIUM);

        var splitLayout = new SplitLayout(mainContentContainer, rightContentContainer);
        splitLayout.setSizeFull();
        splitLayout.addThemeVariants(SplitLayoutVariant.LUMO_MINIMAL);
        splitLayout.setSplitterPosition(80);

        addClassName(LumoUtility.Background.CONTRAST_5);
        addClassName(LumoUtility.Padding.Vertical.MEDIUM);

        getContent().withContent(splitLayout).withToolbarCustomizer(Toolbar::withHorizontalPadding);
    }

    @Override
    protected ContainerWithToolbars initContent() {
        return new ContainerWithToolbars(); // Because of package protection, Flow can't instantiate this class on its own
    }

    private Div createContentContainer() {
        var div = new Div();
        div.addClassName(LumoUtility.BoxShadow.SMALL);
        div.addClassName(LumoUtility.Margin.Vertical.MEDIUM);
        div.addClassName(LumoUtility.BorderRadius.MEDIUM);
        div.addClassName(LumoUtility.Background.BASE);
        return div;
    }

    public BaseView withTopLeftComponents(Component... components) {
        getContent().withTopLeftComponents(components);
        return this;
    }

    public BaseView withTopRightComponents(Component... components) {
        getContent().withTopRightComponents(components);
        return this;
    }

    public BaseView withBottomLeftComponents(Component... components) {
        getContent().withBottomLeftComponents(components);
        return this;
    }

    public BaseView withBottomRightComponents(Component... components) {
        getContent().withBottomRightComponents(components);
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
