package edu.kpi.reflection.generator;

import edu.kpi.reflection.annotations.Column;
import edu.kpi.reflection.annotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionGenerator implements SQLGenerator {
    private final Object object;
    private final String tableName;

    public ReflectionGenerator(Object object) {
        Class<?> reflected = object.getClass();

        if (!object.getClass().isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Object is not annotated with @Table");
        }

        Table table = reflected.getAnnotation(Table.class);
        String tableName = table.name();

        if (tableName.isEmpty()) {
            tableName = object.getClass().getSimpleName().toLowerCase() + "s";
        }

        this.object = object;
        this.tableName = tableName;
    }

    private String getColumnName(Field field) {
        Column column = field.getAnnotation(Column.class);
        String name = column.name();

        return name.isEmpty() ? field.getName().toLowerCase() : name;
    }

    private String convertToString(Object object) {
        if (object == null) {
            return NULL_LITERAL;
        }
        if (object instanceof String) {
            return STRING_LITERAL + object.toString() + STRING_LITERAL;
        }
        return object.toString();
    }

    private void checkNullable(Field field, Object value) {
        Column column = field.getAnnotation(Column.class);

        if (!column.nullable() && value == null) {
            throw new IllegalArgumentException("Field '" + this.getColumnName(field) + "' cannot be null.");
        }
    }

    public String createInsert() {
        Class<?> reflected = this.object.getClass();

        Map<String, String> values = new HashMap<>();

        for (Field field : reflected.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Column.class)) continue;

            field.setAccessible(true);
            String columnName = this.getColumnName(field);

            try {
                Object fieldValue = field.get(object);
                this.checkNullable(field, fieldValue);

                values.put(columnName, fieldValue == null ? NULL_LITERAL : this.convertToString(fieldValue));
            } catch (IllegalAccessException exception) {
                throw new RuntimeException(exception);
            }
        }

        String columnsString = String.join(", ", values.keySet());
        String valuesString = String.join(", ", values.values());

        return String.format("INSERT INTO %s (%s) VALUES (%s);", this.tableName, columnsString, valuesString);
    }

    public String createSelect() {
        Class<?> reflected = this.object.getClass();

        List<String> columns = new ArrayList<>();

        String uniqueColumnName = null;
        Object uniqueColumnValue = null;

        for (Field field : reflected.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Column.class)) continue;

            Column column = field.getAnnotation(Column.class);
            if (column.unique()) {
                uniqueColumnName = this.getColumnName(field);

                try {
                    uniqueColumnValue = field.get(this.object);
                } catch (IllegalAccessException exception) {
                    throw new RuntimeException(exception);
                }
            }

            columns.add(this.getColumnName(field));
        }

        String selectQuery = String.format("SELECT %s FROM %s", String.join(", ", columns), this.tableName);
        if (uniqueColumnName != null && uniqueColumnValue != null) {
            selectQuery += String.format(" WHERE %s = %s", uniqueColumnName, this.convertToString(uniqueColumnValue));
        }

        return selectQuery;
    }

    public String createUpdate() {
        Class<?> reflected = this.object.getClass();
        Map<String, String> setValues = new HashMap<>();

        String uniqueColumnName = null;
        String uniqueColumnValue = null;

        for (Field field : reflected.getDeclaredFields()) {
            field.setAccessible(true);

            if (!field.isAnnotationPresent(Column.class)) continue;

            Column column = field.getAnnotation(Column.class);
            String columnName = this.getColumnName(field);

            try {
                Object fieldValue = field.get(object);

                if (!column.updatable()) {
                    continue;
                }

                this.checkNullable(field, fieldValue);

                if (column.nullable() && fieldValue == null) {
                    setValues.put(columnName, NULL_LITERAL);
                } else {
                    setValues.put(columnName, this.convertToString(fieldValue));
                }

                if (column.unique()) {
                    if (uniqueColumnName == null) {
                        uniqueColumnName = columnName;
                    }
                    uniqueColumnValue = convertToString(fieldValue);
                }

            } catch (IllegalAccessException exception) {
                throw new RuntimeException(exception);
            }
        }

        List<String> setParams = setValues.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue())
                .toList();

        if (uniqueColumnName == null || uniqueColumnValue == null) {
            throw new IllegalArgumentException("At least one unique field is missing or incorrectly annotated");
        }

        return String.format("UPDATE %s SET %s WHERE %s = %s;", this.tableName, String.join(", ", setParams), uniqueColumnName, uniqueColumnValue);
    }

    public String createDelete() {
        Class<?> reflected = this.object.getClass();

        String uniqueColumnName = null;
        Object uniqueColumnValue = null;

        for (Field field : reflected.getDeclaredFields()) {
            field.setAccessible(true);

            if (!field.isAnnotationPresent(Column.class)) continue;

            Column column = field.getAnnotation(Column.class);
            if (column.unique()) {
                uniqueColumnName = this.getColumnName(field);

                try {
                    uniqueColumnValue = field.get(this.object);
                } catch (IllegalAccessException exception) {
                    throw new RuntimeException(exception);
                }

                break;
            }
        }

        if (uniqueColumnName == null || uniqueColumnValue == null) {
            throw new IllegalArgumentException("At least one unique field is missing or incorrectly annotated");
        }

        return String.format("DELETE FROM %s WHERE %s = %s;", this.tableName, uniqueColumnName, this.convertToString(uniqueColumnValue));
    }
}