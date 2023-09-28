package org.example.property;

public interface IProperty {
    void load(final String path);

    Object value(final String keys);
}
