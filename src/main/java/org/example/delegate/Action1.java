package org.example.delegate;

import java.util.Objects;

@FunctionalInterface
public interface Action1<T> extends Delegate{
    void invoke(T t) throws InterruptedException;

    default Action1<T> andThen(Action1<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { invoke(t); after.invoke(t); };
    }
}
