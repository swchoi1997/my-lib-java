package org.example.delegate;

@FunctionalInterface
public interface Func<R> extends Delegate{
    R invork() throws Exception;
}
