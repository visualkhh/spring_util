package com.genome.dx.core.function;

@FunctionalInterface
public interface TripleConsumer<T, U, Y> {
        void accept(T t, U u, Y y);
}
