package com.sparta.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.todo.model.Comment;

import java.time.LocalDateTime;

public class CommentResponse {
    private Long id;
    private String username;
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private Long todoId;

    public CommentResponse(Long id, String comment, String username, Long todoId, LocalDateTime createdAt) {
        this.id = id;
        this.comment = comment;
        this.username = username;
        this.todoId = todoId;
        this.createdAt = createdAt;
    }

    public static CommentResponse toDto(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getComment(),
                comment.getUsername(),
                comment.getTodo().getId(),
                comment.getCreatedAt()
        );
    }
}
