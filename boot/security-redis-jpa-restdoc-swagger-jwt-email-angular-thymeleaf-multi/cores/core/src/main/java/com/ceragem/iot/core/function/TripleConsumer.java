package com.ceragem.iot.core.function;

@FunctionalInterface
public interface TripleConsumer<T, U, Y> {
        void accept(T t, U u, Y y);
}
