package edu.kpi.reflection.builder;

import edu.kpi.reflection.generator.SQLGenerator;

public class EntityBuilder {
    private final SQLGenerator generator;

    public EntityBuilder(SQLGenerator generator) {
        this.generator = generator;
    }

    public Entity build() {
        return new Entity() {
            @Override
            public String insert() {
                return generator.createInsert();
            }

            @Override
            public String select() {
                return generator.createSelect();
            }

            @Override
            public String update() {
                return generator.createUpdate();
            }

            @Override
            public String delete() {
                return generator.createDelete();
            }
        };
    }
}
