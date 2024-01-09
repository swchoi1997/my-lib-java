package org.example.delegate;

import java.util.Objects;

@FunctionalInterface
public interface Func1<T1, R> extends Delegate {
    R invoke(T1 t1) throws InterruptedException;

    default <V> Func1<V, R> compose(Func1<? super V, ? extends T1> before) {
        Objects.requireNonNull(before);
        return (V v) -> invoke(before.invoke(v));
    }

    default <V> Func1<T1, V> andThen(Func1<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T1 t1) -> after.invoke(invoke(t1));
    }
}
