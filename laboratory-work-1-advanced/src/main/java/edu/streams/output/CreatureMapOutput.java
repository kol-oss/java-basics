package edu.streams.output;

import edu.streams.creature.Creature;
import edu.streams.creature.CreatureTypeEnum;
import edu.streams.handler.CreatureHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class CreatureMapOutput {
    public static String creaturesToString(HashMap<CreatureTypeEnum, ArrayList<Creature>> creatures) {
        StringBuilder builder = new StringBuilder();

        builder.append("Creatures:\n");

        for (CreatureTypeEnum type : creatures.keySet()) {
            ArrayList<Creature> creaturesList = creatures.get(type);

            builder.append("\t")
                    .append(type)
                    .append(" [").append(creaturesList.size()).append("]")
                    .append(":").append("\n");

            creaturesList.forEach(c -> builder.append("\t\t").append(c.toString()).append("\n"));

        }

        return builder.toString();
    }

    public static String statsToString(HashMap<CreatureTypeEnum, CreatureHandler.OutliersStats> stats) {
        StringBuilder builder = new StringBuilder();

        builder.append("Outliers Stats:\n");
        for (CreatureTypeEnum type : stats.keySet()) {
            builder.append("\t").append(type).append(": ");

            builder.append(stats.get(type).toString())
                    .append("\n");
        }

        return builder.toString();
    }
}
