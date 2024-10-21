package edu.streams.creature;

import java.util.HashMap;

public enum CreatureTypeEnum {
    FIRE,
    WATER,
    AIR,
    EARTH,
    SPIRIT,
    SHIFTER,
    DRAGON,
    HYBRID,
    FAE,
    ANCIENT,
    TITAN,
    CELESTIAL,
    DEMON,
    NATURE,
    LEGENDARY,
    DESERT,
    ICE;

    public static final HashMap<CreatureTypeEnum, String[]> names = new HashMap<>();

    static {
        names.put(FIRE, new String[] {"Phoenix", "Ifrit", "Salamander", "Fire Drake", "Hellhound"});
        names.put(WATER, new String[] {"Kraken", "Leviathan", "Mermaid", "Kelpie", "Selkie"});
        names.put(AIR, new String[] {"Griffin", "Sphinx", "Thunderbird", "Roc", "Sylph"});
        names.put(EARTH, new String[] {"Golem", "Troll", "Cyclops", "Nymph", "Ent"});
        names.put(SPIRIT, new String[] {"Banshee", "Ghost", "Wraith", "Lich", "Poltergeist"});

        names.put(SHIFTER, new String[] {"Werewolf", "Vampire", "Doppelganger", "Changeling", "Kitsune"});
        names.put(DRAGON, new String[] {"Dragon", "Wyvern", "Hydra", "Fafnir", "Tiamat"});
        names.put(HYBRID, new String[] {"Chimera", "Minotaur", "Centaur", "Manticore", "Hippogriff"});
        names.put(FAE, new String[] {"Fairy", "Pixie", "Brownie", "Spriggan", "Leprechaun"});
        names.put(ANCIENT, new String[] {"Basilisk", "Medusa", "Cerberus", "Scylla", "Charybdis"});

        names.put(TITAN, new String[] {"Yeti", "Bigfoot", "Frost Giant", "Cyclops", "Titan"});
        names.put(CELESTIAL, new String[] {"Angel", "Seraphim", "Cherub", "Deva", "Archangel"});
        names.put(DEMON, new String[] {"Demon", "Succubus", "Incubus", "Balrog", "Azazel"});
        names.put(NATURE, new String[] {"Dryad", "Satyr", "Faun", "Sylvan", "Naiad"});
        names.put(LEGENDARY, new String[] {"Unicorn", "Pegasus", "Hippocampus", "Ceryneian Hind", "Qilin"});

        names.put(DESERT, new String[] {"Sphinx", "Djinn", "Anubis", "Ammit", "Griffin"});
        names.put(ICE, new String[] {"Wendigo", "Frost Drake", "Ice Golem", "Frost Elf", "Glacier Serpent"});
    }
}
