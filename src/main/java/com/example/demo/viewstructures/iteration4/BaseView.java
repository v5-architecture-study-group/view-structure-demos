package com.example.demo.viewstructures.iteration4;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayoutVariant;
import com.vaadin.flow.theme.lumo.LumoUtility;

import static java.util.Objects.requireNonNull;

@JsModule("@vaadin/vaadin-lumo-styles/presets/compact.js")
class BaseView extends Composite<ContainerWithToolbars> { // Package protected to prevent accidental import into other iterations

    private final ContentContainer mainContentContainer = new ContentContainer();
    private final ContentContainer rightContentContainer = new ContentContainer();

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

    private static class ContentContainer extends Div {
        public ContentContainer() {
            addClassName(LumoUtility.BoxShadow.SMALL);
            addClassName(LumoUtility.Margin.Vertical.MEDIUM);
            addClassName(LumoUtility.BorderRadius.MEDIUM);
            addClassName(LumoUtility.Background.BASE);
        }

        public void setContent(Component component) {
            requireNonNull(component, "component must not be null");
            removeAll();
            add(component);
        }
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
        mainContentContainer.setContent(component);
        return this;
    }

    public BaseView withRightContent(Component component) {
        rightContentContainer.setContent(component);
        return this;
    }
}
