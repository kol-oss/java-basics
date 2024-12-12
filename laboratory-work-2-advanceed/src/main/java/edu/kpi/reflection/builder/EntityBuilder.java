package edu.kpi.reflection.builder;

public class EntityBuilder {
    public static Entity build(Object object) {
        SQLGenerator generator = new SQLGenerator(object);

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
