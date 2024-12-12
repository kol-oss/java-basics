package edu.kpi.reflection.generator;

public interface SQLGenerator {
    String NULL_LITERAL = "NULL";
    String STRING_LITERAL = "'";

    String createInsert();
    String createSelect();
    String createUpdate();
    String createDelete();
}
