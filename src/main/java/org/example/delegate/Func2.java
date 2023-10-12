package org.example.delegate;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface Func2<T1, T2, R> extends Delegate{
    R invork(T1 t1, T2 t2) throws Exception;

    default <V1, V2> Func2<V1, V2, R> compose(Func2<? super V1, ? super V2, ? extends T1> before1,
                                              Func2<? super V1, ? super V2, ? extends T2> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (V1 v1, V2 v2) -> invork(before1.invork(v1, v2), before2.invork(v1, v2));
    }

    default <V> Func2<T1, T2, V> andThen(Func2<? super R, ? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T1 t1, T2 t2) -> after.invork(invork(t1, t2), invork(t1, t2));
    }

}
