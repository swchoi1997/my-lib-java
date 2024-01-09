package org.example.delegate;

import java.util.Objects;

@FunctionalInterface
public interface Action2<T1, T2> extends Delegate{
    void invoke(T1 t1, T2 t2) throws InterruptedException;

    default Action2<T1, T2> andThen(Action2<? super T1, ? super T2> after) {
        Objects.requireNonNull(after);
        return (T1 t1, T2 t2) -> { invoke(t1, t2); after.invoke(t1, t2); };
    }
}
