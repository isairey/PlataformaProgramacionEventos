package org.ess.module.bookings.event;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class BookingEvent {
    public final static PublishSubject<BookingEvent.Type> subject = PublishSubject.create();

    public static void subscribe(BookingEvent.Type eventType, Consumer<BookingEvent.Type> consumer) {
        subject.filter(e -> e.equals(eventType)).subscribe(consumer);
    }

    public static enum Type {
        CREATE,
        UPDATE,
        DELETE
    }
}
