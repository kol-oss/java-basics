package edu.streams.gatherers;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

/* GENERICS:
 * T - type of the element
 * AtomicInteger - state (counter of successful)
 */

public class SkipGatherer<T> implements Gatherer<T, AtomicInteger, T> {
    private final int skip;
    private final Predicate<T> selector;

    public SkipGatherer(int skip, final Predicate<T> selector) {
        this.skip = skip;
        this.selector = selector;
    }

    @Override
    public Supplier<AtomicInteger> initializer() {
        return AtomicInteger::new;
    }

    @Override
    public Integrator<AtomicInteger, T, T> integrator() {
        return Integrator.ofGreedy((state, item, downstream) -> {
            if (state.get() <= skip && selector.test(item)) {
                state.incrementAndGet();
                return true;
            }

            downstream.push(item);
            return true;
        });
    }
}
