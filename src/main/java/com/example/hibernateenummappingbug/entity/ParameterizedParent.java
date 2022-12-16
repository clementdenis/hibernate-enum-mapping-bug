package com.example.hibernateenummappingbug.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ParameterizedParent<L> {

    @Enumerated(EnumType.STRING)
    private MyEnum myEnum;

    public MyEnum getMyEnum() {
        return myEnum;
    }

    public void setMyEnum(MyEnum myEnum) {
        this.myEnum = myEnum;
    }

}
