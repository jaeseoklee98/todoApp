package com.sparta.todo.service;

import com.sparta.todo.dto.TodoCreateRequest;
import com.sparta.todo.dto.TodoResponse;
import com.sparta.todo.model.Todo;
import com.sparta.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public Long create(TodoCreateRequest request) {

        Todo todo = new Todo(request.getTitle(),
                request.getDescription(),
                request.getPassword(),
                request.getManager());

        return repository.save(todo).getId();  // DB에 저장된 ID 값 반환
    }

    public TodoResponse findById(Long id) {
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("없습니다.")); // Optional 이라 이런 식으로 호출 --> 따로 공부 // 없으면 Exception

        return new TodoResponse(todo.getName(),
                todo.getDescription(),
                todo.getManager());
    }

    public List<TodoResponse> findAll() {

        List<Todo> todos = repository.findAll();

        List<TodoResponse> responses = new ArrayList<>();
        for (Todo todo : todos) {
            responses.add(new TodoResponse(todo.getName(),
                    todo.getDescription(),
                    todo.getManager()));
        }
        return responses;
    }

    public List<TodoResponse> deleteById(Long id) {

        repository.deleteById(id);

        List<Todo> todos = repository.findAll();
        List<TodoResponse> responses = new ArrayList<>();
        for (Todo todo : todos) {
            responses.add(new TodoResponse(todo.getName(),
                    todo.getDescription(),
                    todo.getManager()));
        }

        return responses;
    }
}
