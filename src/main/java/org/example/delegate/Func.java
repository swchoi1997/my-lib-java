package org.example.delegate;

@FunctionalInterface
public interface Func<R> extends Delegate{
    R invoke() throws InterruptedException;
}
