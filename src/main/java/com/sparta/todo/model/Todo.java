package com.sparta.todo.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String password;

    @Column
    private String manager;

    @Column
    private String createdAt;

    public Todo(String name, String description, String password, String manager) {
        this.name = name;
        this.description = description;
        this.password = password;
        this.manager = manager;
        this.createdAt = LocalDateTime.now().toString();
    }
}
