package org.ess.module.event.event;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class EventEvent {
    public final static PublishSubject<EventEvent.Type> subject = PublishSubject.create();

    public static void subscribe(EventEvent.Type eventType, Consumer<EventEvent.Type> consumer) {
        subject.filter(e -> e.equals(eventType)).subscribe(consumer);
    }

    public enum Type {
        CREATE,
        UPDATE,
        DELETE
    }
}
