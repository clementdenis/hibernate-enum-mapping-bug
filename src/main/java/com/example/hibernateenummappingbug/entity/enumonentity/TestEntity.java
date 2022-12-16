package com.example.hibernateenummappingbug.entity.enumonentity;

import com.example.hibernateenummappingbug.entity.MyEnum;
import com.example.hibernateenummappingbug.entity.ParameterizedParent;
import jakarta.persistence.*;

@Entity(name = "EnumOnEntity")
public class TestEntity extends Parent<Long> {
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

    @Enumerated(EnumType.STRING)
    private MyEnum myEnum;

    public MyEnum getMyEnum() {
        return myEnum;
    }

    public void setMyEnum(MyEnum myEnum) {
        this.myEnum = myEnum;
    }

}
