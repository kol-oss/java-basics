package edu.kpi.reflection.entities;

import edu.kpi.annotations.Column;
import edu.kpi.annotations.Table;

@Table(name = "users")
public class User {
    @Column(name = "user_id", unique = true)
    public int id;

    @Column(name = "username", nullable = false, updatable = false)
    public String name;

    @Column
    public int age;
}
