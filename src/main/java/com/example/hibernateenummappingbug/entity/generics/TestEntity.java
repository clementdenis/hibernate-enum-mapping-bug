package com.example.hibernateenummappingbug.entity.generics;

import com.example.hibernateenummappingbug.entity.ParameterizedParent;
import jakarta.persistence.*;

@Entity(name = "WithGenerics")
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
