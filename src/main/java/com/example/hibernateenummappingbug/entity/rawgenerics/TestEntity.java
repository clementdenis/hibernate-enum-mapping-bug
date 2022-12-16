package com.example.hibernateenummappingbug.entity.rawgenerics;

import com.example.hibernateenummappingbug.entity.ParameterizedParent;
import jakarta.persistence.*;

@Entity(name = "Raw")
public class TestEntity extends ParameterizedParent {
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
