package com.sparta.todo.dto;

import lombok.Getter;

@Getter
public class TodoCreateRequest {

    private String title;
    private String description;
    private String manager;
    private String password;

}
