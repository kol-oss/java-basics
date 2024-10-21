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

import edu.streams.creature.Creature;
import edu.streams.creature.CreatureFactory;
import edu.streams.creature.CreatureTypeEnum;
import edu.streams.gatherers.LimitGatherer;
import edu.streams.gatherers.SkipGatherer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final int SKIP = 10;
    private static final int LIMIT = 100;

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

        HashMap<CreatureTypeEnum, Creature> grouped = (HashMap<CreatureTypeEnum, Creature>) Stream.generate(CreatureFactory::generate)
                .gather(new SkipGatherer<>(SKIP, skipSelector))
                .gather(new LimitGatherer(LIMIT))
                .filter(filterSelector)
                .collect(Collectors.groupingBy(Creature::getType));

        System.out.println(grouped);
    }
}
