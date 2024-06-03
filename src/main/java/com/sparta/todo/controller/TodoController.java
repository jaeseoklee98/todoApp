package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoCreateRequest;
import com.sparta.todo.dto.TodoDeleteRequest;
import com.sparta.todo.dto.TodoResponse;
import com.sparta.todo.model.Todo;
import com.sparta.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
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
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id,
        @RequestBody TodoDeleteRequest request) {

        todoService.delete(id, request);

        return ResponseEntity.ok("Success");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable(name = "id") Long id,
         @RequestBody TodoCreateRequest request) {
        todoService.update(id, request);

        return ResponseEntity.ok("Success");
    }

}
