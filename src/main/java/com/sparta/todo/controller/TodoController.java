package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoCreateRequest;
import com.sparta.todo.dto.TodoResponse;
import com.sparta.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> findById(@PathVariable(name = "id") Long id) {

        TodoResponse response = todoService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> findAll() {

        List<TodoResponse> response = todoService.findAll();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<TodoResponse>> deleteById(@PathVariable Long id) {
        List<TodoResponse> response = todoService.deleteById(id);
        return ResponseEntity.ok(response);
    }

}
