package edu.streams;

/* VARIANT:
* Number: 27
* C4 = 27 % 4 = 3
*/

/* TASKS (VARIANT 3):
* Domain area: Хтонічні істоти (нечисть).
* Обов’язкова інформація: ім’я, вид істоти, дата першої згадки в (можливо окультній) літературі, сила атаки
* Field A: вид
* Field B: кількість років з моменту першої згадки в літературі
* Field C: вид істоти
* Field D: сила атаки
*/

import edu.streams.collector.CreatureCollector;
import edu.streams.creature.Creature;
import edu.streams.creature.CreatureFactory;
import edu.streams.creature.CreatureTypeEnum;
import edu.streams.gatherers.LimitGatherer;
import edu.streams.gatherers.SkipGatherer;
import edu.streams.handler.CreatureHandler;
import edu.streams.output.CreatureMapOutput;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    private static final int SKIP = 15;
    private static final int LIMIT = 500;

    public static void main(String[] args) {
        // Skip predicate: if creature is demon
        Predicate<Creature> skipSelector =
                creature -> creature.getType() == CreatureTypeEnum.DEMON;

        // Filter predicate: if creature first mention was from 0 to current year
        LocalDate date = LocalDate.now();
        Predicate<Creature> filterSelector =
                creature -> {
                    int difference = date.getYear() - creature.getFirstMention();
                    return difference >= 0 && difference < date.getYear();
                };

        HashMap<CreatureTypeEnum, ArrayList<Creature>> grouped = (HashMap<CreatureTypeEnum, ArrayList<Creature>>) Stream.generate(CreatureFactory::generate)
                .filter(filterSelector)
                .gather(new SkipGatherer<>(SKIP, skipSelector))
                .gather(new LimitGatherer(LIMIT))
                .collect(new CreatureCollector());

        System.out.println(CreatureMapOutput.creaturesToString(grouped));

        CreatureHandler handler = new CreatureHandler(grouped);

        System.out.println(STR."Total creatures: \{LIMIT}");

        Creature maxPowerCreature = handler.getMaxPowerCreature();
        System.out.println(STR."Max power is: \{maxPowerCreature.getPower()} [\{maxPowerCreature.getType()}]");

        Creature minPowerCreature = handler.getMinPowerCreature();
        System.out.println(STR."Min power is: \{minPowerCreature.getPower()} [\{minPowerCreature.getType()}]");

        System.out.println(STR."Average power: \{handler.getAveragePower()}");
        System.out.println(STR."Standart deviation: \{handler.getStandardDeviation()}");

        System.out.println(STR."Interquartile range: \{handler.getInterquartileRange()}");

        System.out.println(CreatureMapOutput.statsToString(handler.getOutliers()));
    }
}
