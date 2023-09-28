package org.example.delegate;

public interface Func1<T1, R> extends Delegate{
    R invork(T1 t1) throws Exception;
}
