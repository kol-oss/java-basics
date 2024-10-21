package edu.streams.creature;

public class Creature {
    private final String name;
    private final CreatureTypeEnum type;
    private final int firstMention;
    private final int power;

    public Creature(String name, CreatureTypeEnum type, int firstMention, int power) {
        this.name = name;
        this.type = type;
        this.firstMention = firstMention;
        this.power = power;
    }

    public String getName() {
        return this.name;
    }

    public CreatureTypeEnum getType() {
        return this.type;
    }

    public int getFirstMention() {
        return this.firstMention;
    }

    public int getPower() {
        return this.power;
    }

    @Override
    public String toString() {
        return "Creature '" + this.name + "' (" + this.type + "). Power: " + this.power + ". First Mention (year): " + this.firstMention;
    }
}
