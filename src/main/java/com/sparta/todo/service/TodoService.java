package com.sparta.todo.service;

import com.sparta.todo.dto.TodoCreateRequest;
import com.sparta.todo.model.Todo;
import com.sparta.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public Long create(TodoCreateRequest request){

        Todo todo = new Todo(request.getTitle(),
                request.getDescription(),
                request.getPassword(),
                request.getManager());

        return repository.save(todo).getId();  // DB에 저장된 ID 값 반환
    }
}
