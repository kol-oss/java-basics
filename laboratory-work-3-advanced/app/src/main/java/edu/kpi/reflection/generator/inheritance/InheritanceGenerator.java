package edu.kpi.reflection.generator.inheritance;

import edu.kpi.reflection.generator.SQLGenerator;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InheritanceGenerator extends SQLGenerator {
    private final SQLSerializable object;

    public InheritanceGenerator(SQLSerializable object) {
        this.object = object;
    }

    private List<String> getSerializedValues() {
        Map<String, Object> fieldsAndValues = this.object.getFieldsAndValues();

        Set<String> columns = fieldsAndValues.keySet();
        List<Object> values = fieldsAndValues.values().stream().toList();

        if (columns.size() != values.size()) {
            throw new InvalidParameterException("The number of columns and values do not match");
        }

        return values.stream()
                .map(this::getSerializedValue)
                .toList();
    }

    private Map.Entry<String, Object> getUniqueEntry() {
        Map<String, Object> uniqueField =  this.object.getUniqueField();
        return uniqueField.entrySet().iterator().next();
    }

    public String createInsert() {
        Map.Entry<String, Object> uniqueField = this.getUniqueEntry();
        Map<String, Object> fieldsAndValues = this.object.getFieldsAndValues();

        String columns = String.join(", ", fieldsAndValues.keySet());
        String values = String.join(", ", this.getSerializedValues());

        return "INSERT INTO " + this.object.getTableName() + " (" + uniqueField.getKey() + ", " + columns + ") VALUES (" + uniqueField.getValue() + ", " + values + ")";
    }

    public String createSelect() {
        Map.Entry<String, Object> entry = this.getUniqueEntry();

        String key = entry.getKey();
        Object value = entry.getValue();

        return "SELECT * FROM " + this.object.getTableName() + " WHERE " + key + " = " + this.getSerializedValue(value);
    }

    public String createUpdate() {
        Map.Entry<String, Object> entry = this.getUniqueEntry();

        String key = entry.getKey();
        Object value = entry.getValue();

        Map<String, Object> fieldsAndValues = this.object.getFieldsAndValues();

        StringBuilder setParams = new StringBuilder();
        int counter = 0;

        for (Map.Entry<String, Object> fieldAndValue : fieldsAndValues.entrySet()) {
            setParams.append(fieldAndValue.getKey())
                    .append(" = ")
                    .append(this.getSerializedValue(fieldAndValue.getValue()));

            if (counter != fieldsAndValues.size() - 1) {
                setParams.append(", ");
                counter++;
            }
        }

        return "UPDATE " + this.object.getTableName() + " SET " + setParams + " WHERE " + key + " = " + this.getSerializedValue(value);
    }

    public String createDelete() {
        Map.Entry<String, Object> entry = this.getUniqueEntry();

        String key = entry.getKey();
        Object value = entry.getValue();

        return "DELETE FROM " + this.object.getTableName() + " WHERE " + key + " = " + value;
    }
}
