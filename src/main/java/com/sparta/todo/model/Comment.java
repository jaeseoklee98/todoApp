package com.sparta.todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "댓글을 입력해주세요.")
    private String comment;

    @Column
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
