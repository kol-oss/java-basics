package edu.kpi.reflection.example;

import edu.kpi.reflection.annotations.Column;
import edu.kpi.reflection.annotations.Table;

@Table(name = "users")
public class User {
    @Column(name = "user_id", unique = true)
    public int id;

    @Column(name = "username", nullable = false, updatable = false)
    public String name;

    @Column
    public int age;
}
