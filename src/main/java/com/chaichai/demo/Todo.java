package com.chaichai.demo;

import lombok.Data;

@Data
public class Todo {
    private long id;
    private  String name;

    public Todo(){}

    public Todo(long id, String name){
        super();
        this.id = id;
        this.name = name;
    }


}
