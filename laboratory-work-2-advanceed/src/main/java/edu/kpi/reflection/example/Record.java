package edu.kpi.reflection.example;

import edu.kpi.reflection.annotations.Column;
import edu.kpi.reflection.annotations.Table;

@Table
public class Record {
    @Column(name = "record_id", unique = true)
    public int id;

    @Column(nullable = false)
    public String name;

    @Column(updatable = false)
    public int sum;
}
