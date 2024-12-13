package edu.kpi.reflection.generator.inheritance;

import java.util.Map;

public abstract class SQLSerializable {
    public abstract String getTableName();
    public abstract Map<String, Object> getUniqueField();

    public abstract Map<String, Object> getFieldsAndValues();
}
