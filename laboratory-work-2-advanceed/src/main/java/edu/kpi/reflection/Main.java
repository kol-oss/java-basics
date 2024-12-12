package edu.kpi.reflection;

import edu.kpi.reflection.builder.Entity;
import edu.kpi.reflection.builder.EntityBuilder;
import edu.kpi.reflection.example.Account;
import edu.kpi.reflection.example.Category;
import edu.kpi.reflection.example.Record;
import edu.kpi.reflection.example.User;
import edu.kpi.reflection.generator.NoReflectionGenerator;
import edu.kpi.reflection.generator.ReflectionGenerator;

public class Main {
    public static void main(String[] args) {
        long startTime;

        // Example 1: Usage of User entity
        User user = new User();
        user.id = 1;
        user.name = "Antony";
        user.age = 10;

        startTime = System.currentTimeMillis();

        Entity userEntity = new EntityBuilder(new ReflectionGenerator(user)).build();
        System.out.println(userEntity.insert());
        System.out.println(userEntity.select());
        System.out.println(userEntity.update());
        System.out.println(userEntity.delete());

        System.out.println("User with Reflection: " + (System.currentTimeMillis() - startTime));

        // Example 2: Usage of Category entity
        Category category = new Category();
        category.name = "Sport";

        startTime = System.currentTimeMillis();

        Entity categoryEntity = new EntityBuilder(new ReflectionGenerator(category)).build();
        System.out.println(categoryEntity.insert());
        System.out.println(categoryEntity.select());
        System.out.println(categoryEntity.update());
        System.out.println(categoryEntity.delete());

        System.out.println("Category with Reflection: " + (System.currentTimeMillis() - startTime));

        // Example 3: Usage of Record entity
        Record record = new Record();
        record.id = 99;
        record.name = "Gym rent";
        record.sum = 99;

        startTime = System.currentTimeMillis();

        Entity recordEntity = new EntityBuilder(new ReflectionGenerator(record)).build();
        System.out.println(recordEntity.insert());
        System.out.println(recordEntity.select());
        System.out.println(recordEntity.update());
        System.out.println(recordEntity.delete());

        System.out.println("Record with Reflection: " + (System.currentTimeMillis() - startTime));

        // Example 4: Usage of Account no-reflection entity
        Account account = new Account(1, 10);

        startTime = System.currentTimeMillis();

        Entity accountEntity = new EntityBuilder(new NoReflectionGenerator(account)).build();
        System.out.println(accountEntity.insert());
        System.out.println(accountEntity.select());
        System.out.println(accountEntity.update());
        System.out.println(accountEntity.delete());

        System.out.println("Account without Reflection: " + (System.currentTimeMillis() - startTime));
    }
}
