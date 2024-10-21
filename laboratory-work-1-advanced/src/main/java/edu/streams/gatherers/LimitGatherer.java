package edu.streams.gatherers;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

/* GENERICS:
* T - type of the element
* AtomicInteger - state (counter of successful)
*/

public class LimitGatherer<T> implements Gatherer<T, AtomicInteger, T> {
    private final int limit;

    public LimitGatherer(int limit) {
        this.limit = limit;
    }

    @Override
    public Supplier<AtomicInteger> initializer() {
        return AtomicInteger::new;
    }

    @Override
    public Integrator<AtomicInteger, T, T> integrator() {
        return Integrator.of((state, item, downstream) -> {
            if (state.get() < limit) {
                state.incrementAndGet();
                downstream.push(item);
                return true;
            }

            return false;
        });
    }
}
