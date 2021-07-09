package by.epam.shapes.helper.event;

public interface Event<T, R> {
    void subscribe(T listener);
    void unsubscribe(T listener);
    void invoke(R r);
}
