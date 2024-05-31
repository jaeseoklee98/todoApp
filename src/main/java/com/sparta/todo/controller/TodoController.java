package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoCreateRequest;
import com.sparta.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody TodoCreateRequest request) {

        Long id = todoService.create(request);
        return ResponseEntity.ok("todo created");
    }
}
