package edu.kpi.reflection.generator;

import edu.kpi.reflection.example.Account;

public class NoReflectionGenerator implements SQLGenerator {
    private final Account object;

    public NoReflectionGenerator(Account object) {
        this.object = object;
    }

    public String createInsert() {
        String columns = String.join(", ", this.object.getFields());
        String values = this.object.getId() + ", " +  this.object.getBalance();

        return "INSERT INTO accounts (" + columns + ") VALUES (" + values + ")";
    }

    public String createSelect() {
        int id =  this.object.getId();

        return "SELECT * FROM accounts WHERE " + this.object.getFields().get(0) + " = " + id;
    }

    public String createUpdate() {
        String setParams = this.object.getFields().get(1) + " = " + this.object.getBalance();

        return "UPDATE accounts SET " + setParams + " WHERE " + this.object.getFields().get(0) + " = " + this.object.getId();
    }

    public String createDelete() {
        return "DELETE FROM accounts WHERE " + this.object.getFields().get(0) + " = " + this.object.getId();
    }
}
