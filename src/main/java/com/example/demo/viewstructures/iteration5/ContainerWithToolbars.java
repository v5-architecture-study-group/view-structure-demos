package com.example.demo.viewstructures.iteration5;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.function.SerializableConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

class ContainerWithToolbars extends VerticalLayout { // Package protected to prevent accidental import into other iterations

    private final LazyComponent<Toolbar> top = new LazyComponent<>(this::createToolbar, this::addComponentAsFirst);
    private final LazyComponent<Toolbar> bottom = new LazyComponent<>(this::createToolbar, this::add);
    private Component content;
    private List<SerializableConsumer<Toolbar>> toolbarCustomizers;

    public ContainerWithToolbars() {
        setMargin(false);
        setPadding(false);
        setSpacing(false);
        setAlignItems(Alignment.STRETCH);
        setSizeFull();
    }

    private Toolbar createToolbar() {
        var tb = new Toolbar();
        if (toolbarCustomizers != null) {
            toolbarCustomizers.forEach(tbc -> tbc.accept(tb));
        }
        return tb;
    }

    public ContainerWithToolbars withToolbarCustomizer(SerializableConsumer<Toolbar> toolbarCustomizer) {
        requireNonNull(toolbarCustomizer, "toolbarCustomizer must not be null");
        if (top.isInitialized() || bottom.isInitialized()) {
            throw new IllegalStateException("Toolbar has already been initialized");
        }
        if (toolbarCustomizers == null) {
            toolbarCustomizers = new LinkedList<>();
        }
        toolbarCustomizers.add(toolbarCustomizer);
        return this;
    }

    public ContainerWithToolbars withTopLeftComponents(Component... components) {
        top.get().withLeftComponents(components);
        return this;
    }

    public ContainerWithToolbars withTopRightComponents(Component... components) {
        top.get().withRightComponents(components);
        return this;
    }

    public ContainerWithToolbars withBottomLeftComponents(Component... components) {
        bottom.get().withLeftComponents(components);
        return this;
    }

    public ContainerWithToolbars withBottomRightComponents(Component... components) {
        bottom.get().withRightComponents(components);
        return this;
    }

    public ContainerWithToolbars withContent(Component content) {
        Objects.requireNonNull(content, "content must not be null");
        if (this.content != null) {
            remove(this.content);
        }
        this.content = content;
        if (top.isInitialized()) {
            addComponentAtIndex(1, content);
        } else {
            addComponentAsFirst(content);
        }
        return this;
    }

    public ContainerWithToolbars withPadding() {
        setPadding(true);
        return this;
    }

    public ContainerWithToolbars withSpacing() {
        setSpacing(true);
        return this;
    }
}
