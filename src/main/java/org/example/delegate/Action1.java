package org.example.delegate;

import java.util.Objects;
import java.util.function.Consumer;

@FunctionalInterface
public interface Action1<T> extends Delegate{
    void invork(T t);

    default Action1<T> andThen(Action1<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { invork(t); after.invork(t); };
    }
}
