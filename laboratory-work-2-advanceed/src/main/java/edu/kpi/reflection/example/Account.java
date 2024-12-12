package edu.kpi.reflection.example;

import java.util.List;

public class Account {
    private final int id;
    private final int balance;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return this.id;
    }

    public int getBalance() {
        return this.balance;
    }

    public List<String> getFields() {
        return List.of("id", "balance");
    }
}
