package edu.kpi.reflection;

import edu.kpi.reflection.builder.Entity;
import edu.kpi.reflection.builder.EntityBuilder;
import edu.kpi.reflection.entities.Account;
import edu.kpi.reflection.entities.Category;
import edu.kpi.reflection.entities.Record;
import edu.kpi.reflection.entities.User;
import edu.kpi.reflection.example.UserSQLRepository;
import edu.kpi.reflection.generator.SQLGenerator;
import edu.kpi.reflection.generator.inheritance.InheritanceGenerator;
import edu.kpi.reflection.generator.reflection.ReflectionGenerator;

public class Main {
    private static long benchmark(SQLGenerator generator) {
        long startTime = System.currentTimeMillis();

        Entity entity = new EntityBuilder(generator).build();
        System.out.println(entity.insert());
        System.out.println(entity.select());
        System.out.println(entity.update());
        System.out.println(entity.delete());

        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        // Example 1: Usage of User entity
        User user = new User();
        user.id = 1;
        user.name = "Antony";
        user.age = 10;

        long userExecTime = benchmark(new ReflectionGenerator(user));
        System.out.println("User (with Reflection): " + userExecTime + " ms");

        // Example 2: Usage of Category entity
        Category category = new Category();
        category.name = "Sport";

        long categoryExecTime = benchmark(new ReflectionGenerator(category));
        System.out.println("Category (with Reflection): " + categoryExecTime + " ms");

        // Example 3: Usage of Record entity
        Record record = new Record();
        record.id = 99;
        record.name = "Gym rent";
        record.sum = 99;

        long recordExecTime = benchmark(new ReflectionGenerator(record));
        System.out.println("Record (with Reflection): " + recordExecTime + " ms");

        // Example 4: Usage of Account no-reflection entity
        Account account = new Account(1, 10);

        long accountExecTime = benchmark(new InheritanceGenerator(account));
        System.out.println("Account (with Inheritance): " + accountExecTime + " ms");

        // Example 5: Usage of generated repositories
        UserSQLRepository userRepository = new UserSQLRepository(user);

        System.out.println("Generated User repository:");
        System.out.println(userRepository.insert());
        System.out.println(userRepository.select());
        System.out.println(userRepository.update());
        System.out.println(userRepository.delete());
    }
}
