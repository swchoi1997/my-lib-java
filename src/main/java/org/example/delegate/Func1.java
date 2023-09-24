package org.example.delegate;

public interface Func1<F, R> extends Delegate{
    F invork(R r) throws Exception;
}
