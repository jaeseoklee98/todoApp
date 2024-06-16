package com.sparta.todo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private String username;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Todo todo;

    public Comment(String comment, String username, Todo todo) {
        this.comment = comment;
        this.username = username;
        this.todo = todo;
    }

    public void update(String comment) {
        this.comment = comment;
    }
}
