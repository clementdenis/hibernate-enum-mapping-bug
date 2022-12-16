package com.example.hibernateenummappingbug.entity.forcecolumndefinition;

import com.example.hibernateenummappingbug.entity.MyEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ParameterizedParent<L> {

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private MyEnum myEnum;

    public MyEnum getMyEnum() {
        return myEnum;
    }

    public void setMyEnum(MyEnum myEnum) {
        this.myEnum = myEnum;
    }

}
