package edu.streams.creature;

import java.util.Random;

public class CreatureFactory {
    private static final int startYear = -1000;
    private static final int endYear = 1000;
    private static final Random random = new Random();

    public static Creature generate() {
        CreatureTypeEnum[] types = CreatureTypeEnum.values();

        CreatureTypeEnum type = types[random.nextInt(types.length)];
        String[] names = CreatureTypeEnum.names.get(type);

        String name = names[random.nextInt(names.length)];

        int firstMention = startYear + (int) Math.round(Math.random() * (endYear - startYear));

        int power = random.nextInt(100);

        return new Creature(name, type, firstMention, power);
    }
}
