package org.example.delegate;

public interface Action2<R, T> extends Delegate{
    void invork(R r, T t) throws Exception;
}
