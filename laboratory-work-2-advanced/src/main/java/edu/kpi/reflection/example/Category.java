package edu.kpi.reflection.example;

import edu.kpi.reflection.annotations.Column;
import edu.kpi.reflection.annotations.Table;

@Table(name = "categories")
public class Category {
    @Column(name = "category_id", unique = true)
    public int id;

    @Column(nullable = false)
    public String name;
}
