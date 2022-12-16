package com.example.hibernateenummappingbug.entity.forcecolumndefinition;

import jakarta.persistence.*;

@Entity(name = "ForceColumnDefinition")
public class TestEntity extends ParameterizedParent<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
