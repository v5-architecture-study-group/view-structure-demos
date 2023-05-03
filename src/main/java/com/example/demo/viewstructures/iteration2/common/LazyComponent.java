package com.example.demo.viewstructures.iteration2.common;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.function.SerializableSupplier;

import java.io.Serializable;
import java.util.Objects;

public class LazyComponent<C extends Component> implements Serializable {

    private C component;

    private final SerializableSupplier<C> componentFactory;
    private final SerializableConsumer<C> componentAttacher;

    public LazyComponent(SerializableSupplier<C> componentFactory,
                         SerializableConsumer<C> componentAttacher) {
        this.componentFactory = Objects.requireNonNull(componentFactory, "componentFactory must not be null");
        this.componentAttacher = Objects.requireNonNull(componentAttacher, "compnentAttacher must not be null");
    }

    public C get() {
        if (component == null) {
            component = componentFactory.get();
            componentAttacher.accept(component);
        }
        return component;
    }
}
