package com.example.demo.viewstructures.iteration2;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.function.SerializableSupplier;

import java.io.Serializable;
import java.util.Objects;

class LazyComponent<C extends Component> implements Serializable { // Package protected to prevent accidental import into other iterations

    private C component;

    private final SerializableSupplier<C> componentFactory;
    private final SerializableConsumer<C> componentAttacher;

    public LazyComponent(SerializableSupplier<C> componentFactory,
                         SerializableConsumer<C> componentAttacher) {
        this.componentFactory = Objects.requireNonNull(componentFactory, "componentFactory must not be null");
        this.componentAttacher = Objects.requireNonNull(componentAttacher, "componentAttacher must not be null");
    }

    public C get() {
        if (component == null) {
            component = componentFactory.get();
            componentAttacher.accept(component);
        }
        return component;
    }
}
