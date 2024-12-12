package edu.kpi.reflection;

import edu.kpi.reflection.builder.Entity;
import edu.kpi.reflection.builder.EntityBuilder;
import edu.kpi.reflection.example.Category;
import edu.kpi.reflection.example.Record;
import edu.kpi.reflection.example.User;

public class Main {
    public static void main(String[] args) {
        // Example 1: Usage of User entity
        User user = new User();
        user.id = 1;
        user.name = "Antony";
        user.age = 10;

        Entity userEntity = EntityBuilder.build(user);
        System.out.println(userEntity.insert());
        System.out.println(userEntity.select());
        System.out.println(userEntity.update());
        System.out.println(userEntity.delete());

        // Example 2: Usage of Category entity
        Category category = new Category();
        category.name = "Sport";

        Entity categoryEntity = EntityBuilder.build(category);
        System.out.println(categoryEntity.insert());
        System.out.println(categoryEntity.select());
        System.out.println(categoryEntity.update());
        System.out.println(categoryEntity.delete());

        // Example 3: Usage of Record entity
        Record record = new Record();
        record.id = 99;
        record.name = "Gym rent";
        record.sum = 99;

        Entity recordEntity = EntityBuilder.build(record);
        System.out.println(recordEntity.insert());
        System.out.println(recordEntity.select());
        System.out.println(recordEntity.update());
        System.out.println(recordEntity.delete());
    }
}
