package com.sparta.todo.service;

import com.sparta.todo.dto.TodoCreateRequest;
import com.sparta.todo.dto.TodoDeleteRequest;
import com.sparta.todo.dto.TodoResponse;
import com.sparta.todo.model.Todo;
import com.sparta.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
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
        Todo todo = findTodoById(id);

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

    @Transactional
    public void delete(Long id, TodoDeleteRequest request) {
        Todo todo = findTodoById(id);

        // 1번 방법
        if (!todo.validatePassword(request.getPassword())) {
            throw new IllegalArgumentException(" 잘못된 입력");
        }

        // 2번 방법
        todo.validatePassword2(request.getPassword());

        repository.delete(todo);
    }

    private Todo findTodoById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("없습니다."));
    }

    @Transactional
    public void update(Long id, TodoCreateRequest request) {
        Todo todo = findTodoById(id);

        todo.validatePassword2(request.getPassword());

        todo.update(request.getTitle(), request.getDescription(), request.getManager());

        repository.save(todo);
        }
    }


