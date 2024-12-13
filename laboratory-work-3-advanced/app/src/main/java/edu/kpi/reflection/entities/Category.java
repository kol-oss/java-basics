package edu.kpi.reflection.entities;

import edu.kpi.annotations.Column;
import edu.kpi.annotations.Table;

@Table(name = "categories")
public class Category {
    @Column(name = "category_id", unique = true)
    public int id;

    @Column(nullable = false)
    public String name;
}
