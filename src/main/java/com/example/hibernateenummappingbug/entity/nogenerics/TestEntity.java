package com.example.hibernateenummappingbug.entity.nogenerics;

import com.example.hibernateenummappingbug.entity.NoParamParent;
import jakarta.persistence.*;

@Entity(name = "NoGenerics")
public class TestEntity extends NoParamParent {
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
