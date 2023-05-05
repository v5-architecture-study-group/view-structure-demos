package com.example.demo.viewstructures.iteration5;

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
    private final ContentContainer leftContentContainer = new ContentContainer();
    private final ContentContainer rightContentContainer = new ContentContainer();

    public BaseView() {
        leftContentContainer.addClassName("left-content");
        rightContentContainer.addClassName("right-content");
        mainContentContainer.addClassName("main-content");

        var secondarySplitLayout = new SplitLayout(mainContentContainer, rightContentContainer);
        secondarySplitLayout.setSizeFull();
        secondarySplitLayout.addThemeVariants(SplitLayoutVariant.LUMO_MINIMAL);
        secondarySplitLayout.setSplitterPosition(80);

        var primarySplitLayout = new SplitLayout(leftContentContainer, secondarySplitLayout);
        primarySplitLayout.setSizeFull();
        primarySplitLayout.addThemeVariants(SplitLayoutVariant.LUMO_MINIMAL);
        primarySplitLayout.setSplitterPosition(20);

        addClassName(LumoUtility.Background.CONTRAST_5);
        addClassName(LumoUtility.Padding.Vertical.MEDIUM);

        getContent().withContent(primarySplitLayout).withToolbarCustomizer(Toolbar::withHorizontalPadding);
    }

    @Override
    protected ContainerWithToolbars initContent() {
        return new ContainerWithToolbars(); // Because of package protection, Flow can't instantiate this class on its own
    }

    private static class ContentContainer extends Div {

        // We want to get the shadows to look right between all three different component container divs, even though
        // they are distributed over two different split layouts. With the same approach as in iteration 4, the
        // shadows in the splitters should look slightly different. In order to make the shadows look the same, we
        // have to do some tweaking of the DOM-tree and some CSS-hacking. We also no longer can rely on only
        // getStyle() and addClassName() - we need to use styles.css as well.
        //
        // Note that the programming API of clients of BaseView has not changed because of this change.

        private final Div inner = new Div();

        public ContentContainer() {
            addClassName("iteration5");
            addClassName("content-container");
            addClassName("outer");

            inner.addClassName("inner");
            add(inner);

            setSizeFull();
        }

        // By using our custom setContent method instead of just adding and removing components to/from the div
        // directly, we have better control of the resulting DOM tree.
        public void setContent(Component component) {
            requireNonNull(component, "component must not be null");
            inner.removeAll();
            inner.add(component);
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

    public BaseView withLeftContent(Component component) {
        leftContentContainer.setContent(component);
        return this;
    }
}
