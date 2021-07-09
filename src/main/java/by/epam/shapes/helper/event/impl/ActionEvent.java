package by.epam.shapes.helper.event.impl;

import by.epam.shapes.helper.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ActionEvent<T> implements Event<Consumer<T>, T> {

    private final List<Consumer<T>> consumerList = new ArrayList<>();

    @Override
    public void subscribe(Consumer<T> listener) {
        consumerList.add(listener);
    }

    @Override
    public void unsubscribe(Consumer<T> listener) {
        consumerList.remove(listener);
    }

    @Override
    public void invoke(T t) {
        for (Consumer<T> consumer : consumerList) {
            consumer.accept(t);
        }
    }
}
