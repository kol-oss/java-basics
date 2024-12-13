package edu.kpi.reflection.example;

import edu.kpi.reflection.builder.Entity;
import edu.kpi.reflection.builder.EntityBuilder;
import edu.kpi.reflection.entities.Category;
import edu.kpi.reflection.generator.reflection.ReflectionGenerator;

public class CategorySQLRepository {
    private final Entity entity;

    public CategorySQLRepository(Category entityInstance) {
        this.entity = new EntityBuilder(new ReflectionGenerator(entityInstance)).build();
    }

    public String insert() {
        return this.entity.insert();
    }

    public String select() {
        return this.entity.select();
    }

    public String update() {
        return this.entity.update();
    }

    public String delete() {
        return this.entity.delete();
    }
}
