package edu.streams.collector;

import edu.streams.creature.Creature;
import edu.streams.creature.CreatureTypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CreatureCollector implements Collector<Creature, HashMap<CreatureTypeEnum, ArrayList<Creature>>, HashMap<CreatureTypeEnum, ArrayList<Creature>>> {
    @Override
    public Supplier<HashMap<CreatureTypeEnum, ArrayList<Creature>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<HashMap<CreatureTypeEnum, ArrayList<Creature>>, Creature> accumulator() {
        return (accumulator, value) -> {
            ArrayList<Creature> creatures = accumulator.get(value.getType());

            if (creatures == null) {
                creatures = new ArrayList<>();
            }

            creatures.add(value);
            accumulator.put(value.getType(), creatures);
        };
    }

    @Override
    public BinaryOperator<HashMap<CreatureTypeEnum, ArrayList<Creature>>> combiner() {
        return null;
    }

    @Override
    public Function<HashMap<CreatureTypeEnum, ArrayList<Creature>>, HashMap<CreatureTypeEnum, ArrayList<Creature>>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        HashSet<Characteristics> characteristics = new HashSet<>();
        characteristics.add(Characteristics.IDENTITY_FINISH);
        characteristics.add(Characteristics.UNORDERED);

        return characteristics;
    }
}
