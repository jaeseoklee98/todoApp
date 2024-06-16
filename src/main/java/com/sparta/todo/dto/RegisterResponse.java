package com.sparta.todo.dto;

import lombok.Getter;

@Getter
public class RegisterResponse {
    private String message;

    public RegisterResponse(String message) {
        this.message = message;
    }
}
