package org.example.delegate;

import java.util.Objects;

@FunctionalInterface
public interface Action2<T1, T2> extends Delegate{
    void invork(T1 t1, T2 t2) throws Exception;

    default Action2<T1, T2> andThen(Action2<? super T1, ? super T2> after) {
        Objects.requireNonNull(after);
        return (T1 t1, T2 t2) -> { invork(t1, t2); after.invork(t1, t2); };
    }
}
