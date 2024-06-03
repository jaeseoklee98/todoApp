package com.sparta.todo.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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

    // 1번 방법
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    // 2번 방법
    public void validatePassword2(String password) {
        if (!this.password.equals(password)) {
            throw new IllegalArgumentException("잘못된 입력");
        }
    }

    public void update(String name, String description, String manager) {
        this.name = name;
        this.description = description;
        this.manager = manager;
    }
}
