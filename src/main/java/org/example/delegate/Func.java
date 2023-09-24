package org.example.delegate;

public interface Func<F> extends Delegate{
    F invork() throws Exception;
}
