package org.ess.module.user.Event;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class UserEvent {
    public final static PublishSubject<UserEvent.Type> subject = PublishSubject.create();

    public static void subscribe(UserEvent.Type eventType, Consumer<UserEvent.Type> consumer) {
        subject.filter(e -> e.equals(eventType)).subscribe(consumer);
    }

    public static enum Type {
        CREATE,
        UPDATE,
        DELETE
    }
}
