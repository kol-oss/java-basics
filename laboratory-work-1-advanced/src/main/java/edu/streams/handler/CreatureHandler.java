package edu.streams.handler;

import edu.streams.creature.Creature;
import edu.streams.creature.CreatureTypeEnum;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class CreatureHandler {
    private final HashMap<CreatureTypeEnum, ArrayList<Creature>> creatures;

    public CreatureHandler(HashMap<CreatureTypeEnum, ArrayList<Creature>> creatures) {
        this.creatures = creatures;
    }

    private Stream<Creature> getCreaturesStream() {
        return this.creatures.values()
                .stream()
                .flatMap(Collection::stream);
    }

    public Creature getMaxPowerCreature() {
        Optional<Creature> result = this.getCreaturesStream()
                .max(Comparator.comparingInt(Creature::getPower));

        return result.orElse(null);
    }

    public Creature getMinPowerCreature() {
        Optional<Creature> result = this.getCreaturesStream()
                .min(Comparator.comparingInt(Creature::getPower));

        return result.orElse(null);
    }

    public double getAveragePower() {
        OptionalDouble average = this.getCreaturesStream()
                .mapToInt(Creature::getPower)
                .average();

        return average.orElse(Double.MIN_VALUE);
    }

    public long getCreaturesListLength() {
        return this.creatures.values()
                .stream()
                .mapToLong(Collection::size)
                .sum();
    }

    /*
    S = sqrt((x1 - xa)^2 + (x2 - xa)^2 + ... + (xn - xa)^2) / N - 1)
     */
    public double getStandardDeviation() {
        double average = this.getAveragePower();
        long length = this.getCreaturesListLength();

        double sum = this.getCreaturesStream()
                .mapToDouble((Creature creature) -> creature.getPower() - average)
                .map((value) -> Math.pow(value, 2))
                .sum();

        return Math.sqrt(sum / (length - 1));
    }

    private double findMedian(DoubleStream stream, long length) {
        OptionalDouble median = length % 2 == 0 ?
                stream.skip(length/2 - 1).limit(2).average() :
                stream.skip(length/2).findFirst();

        return median.orElse(Double.MIN_VALUE);
    }

    private DoubleStream getDoubleStream() {
        return this.getCreaturesStream()
                .sorted(Comparator.comparingInt(Creature::getPower))
                .mapToDouble(Creature::getPower);
    }

    private double getQuartile(boolean isFirst) {
        DoubleStream sorted = this.getDoubleStream();
        long length = this.getCreaturesListLength();
        double median = this.findMedian(sorted, length);

        return this.findMedian(
                this.getDoubleStream().filter(
                        (value) -> isFirst ? value < median : value > median),
                length/2
        );
    }

    // 1. Find median
    // 2. Find median in values before median
    // 3. Find median in value after median
    // 4. Remove before-median from after-median
    public double getInterquartileRange() {
        return this.getQuartile(false) - this.getQuartile(true);
    }

    public boolean isOutlier(Creature creature) {
        double firstQuartile = this.getQuartile(true);
        double thirdQuartile = this.getQuartile(false);

        double minValue = firstQuartile - 1.5 * (thirdQuartile - firstQuartile);
        double maxValue = thirdQuartile + 1.5 * (thirdQuartile - firstQuartile);

        return creature.getPower() < minValue || creature.getPower() > maxValue;
    }

    public static class OutliersStats {
        public int data = 0;
        public int outliers = 0;

        @Override
        public String toString() {
            return STR."{ data: \{data}, outliers: \{outliers} }";
        }
    }

    public HashMap<CreatureTypeEnum, OutliersStats> getOutliers() {
        HashMap<CreatureTypeEnum, OutliersStats> stats = new HashMap<>();

        this.getCreaturesStream().forEach(creature ->
                stats.computeIfAbsent(creature.getType(), _ -> new OutliersStats()).data++
        );

        this.getCreaturesStream()
                .filter(this::isOutlier)
                .forEach(creature -> stats.get(creature.getType()).outliers++);

        return stats;
    }
}
