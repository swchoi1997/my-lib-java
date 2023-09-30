package org.example.delegate;

import java.util.Objects;

@FunctionalInterface
public interface Func1<T1, R> extends Delegate {
    R invork(T1 t1) throws Exception;

    default <V> Func1<V, R> compose(Func1<? super V, ? extends T1> before) {
        Objects.requireNonNull(before);
        return (V v) -> invork(before.invork(v));
    }

    default <V> Func1<T1, V> andThen(Func1<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T1 t1) -> after.invork(invork(t1));
    }
}
