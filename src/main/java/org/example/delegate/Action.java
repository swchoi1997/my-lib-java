package org.example.delegate;


import java.util.Objects;

@FunctionalInterface
public interface Action extends Delegate{
    void invork();

    default Action andThen(Action after) {
        Objects.requireNonNull(after);
        return () -> { invork(); after.invork(); };
    }
}
