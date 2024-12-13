package edu.kpi.reflection.entities;

import edu.kpi.annotations.Column;
import edu.kpi.annotations.Table;

@Table
public class Record {
    @Column(name = "record_id", unique = true)
    public int id;

    @Column(nullable = false)
    public String name;

    @Column(updatable = false)
    public int sum;
}
