package org.example.delegate;


import java.util.Objects;

@FunctionalInterface
public interface Action extends Delegate{
    void invoke() throws InterruptedException;

    default Action andThen(Action after) {
        Objects.requireNonNull(after);
        return () -> { invoke(); after.invoke(); };
    }
}
