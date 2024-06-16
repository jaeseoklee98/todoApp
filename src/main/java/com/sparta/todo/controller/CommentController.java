package com.sparta.todo.controller;

import com.sparta.todo.dto.CommentCreateRequest;
import com.sparta.todo.dto.CommentResponse;
import com.sparta.todo.dto.CommentUpdateRequest;
import com.sparta.todo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("todo/{id}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping
    public ResponseEntity<CommentResponse> create(
            @PathVariable(name = "id") long todoId,
            @RequestBody CommentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(todoId,request)
        );
    }

    @PutMapping("{commentId}")
    public ResponseEntity<CommentResponse> update(
            @PathVariable(name = "id") long id,
            @PathVariable(name = "commentId") long commentId,
            @RequestBody CommentUpdateRequest request) {
        return ResponseEntity.ok().body(service.update(id, commentId, request));
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") long id, @PathVariable(name = "commentId") long commentId, @RequestBody String username) {
        service.delete(id, commentId, username);
        return ResponseEntity.ok().body("댓글 삭제 완료");
    }
}
