package de.skratzsch.patterns.observer.core;

/**
 * Functional interface for observers.
 * @param <T> The type of event this observer handles.
 */
@FunctionalInterface
public interface Observer<T extends Event> {
    /**
     * Called when an event occurs.
     * @param event The event to handle.
     */
    void onEvent(T event);
}
