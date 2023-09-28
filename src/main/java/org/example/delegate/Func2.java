package org.example.delegate;

public interface Func2<T1, T2, R> extends Delegate{
    R invork(T1 t1, T2 t2) throws Exception;
}
