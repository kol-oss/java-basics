package edu.kpi.reflection.entities;

import edu.kpi.reflection.generator.inheritance.SQLSerializable;

import java.util.HashMap;
import java.util.Map;

public class Account extends SQLSerializable {
    private final int id;
    private final int balance;
    private final String bankName = "MONO";

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    @Override
    public String getTableName() {
        return "accounts";
    }

    @Override
    public Map<String, Object> getUniqueField() {
        HashMap<String, Object> keyFields = new HashMap<>();
        keyFields.put("id", this.id);

        return keyFields;
    }

    @Override
    public Map<String, Object> getFieldsAndValues() {
        HashMap<String, Object> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("balance", this.balance);
        fieldsAndValues.put("bankName", this.bankName);

        return fieldsAndValues;
    }
}
