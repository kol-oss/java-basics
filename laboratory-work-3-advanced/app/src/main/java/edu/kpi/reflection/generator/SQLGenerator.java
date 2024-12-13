package edu.kpi.reflection.generator;

public abstract class SQLGenerator {
    public final String NULL_LITERAL = "NULL";
    public final String STRING_LITERAL = "'";

    public abstract String createInsert();
    public abstract String createSelect();
    public abstract String createUpdate();
    public abstract String createDelete();

    protected String getSerializedValue(Object object) {
        if (object == null) {
            return NULL_LITERAL;
        }
        if (object instanceof String) {
            return STRING_LITERAL + object + STRING_LITERAL;
        }
        return object.toString();
    }
}
