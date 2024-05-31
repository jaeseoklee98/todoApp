package com.sparta.todo.dto;

import lombok.Getter;

@Getter
public class TodoResponse {

    private String name;
    private String description;
    private String manager;

    public TodoResponse(String name, String description, String manager) {
        this.name = name;
        this.description = description;
        this.manager = manager;
    }
}
