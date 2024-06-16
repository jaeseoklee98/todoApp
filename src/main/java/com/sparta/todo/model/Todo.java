package com.sparta.todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Email
    private String name;

    @Column
    @NotBlank
    private String password;

    @Column
    private String description;

    @Column
    private String manager;


    public Todo(String name, String description, String password, String manager) {
        this.name = name;
        this.description = description;
        this.password = password;
        this.manager = manager;
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
