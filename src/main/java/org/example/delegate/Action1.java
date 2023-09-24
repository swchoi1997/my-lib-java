package org.example.delegate;

public interface Action1<R> extends Delegate{
    void invork(R r) throws Exception;
}
