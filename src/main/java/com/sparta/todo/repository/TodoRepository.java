package com.sparta.todo.repository;

import com.sparta.todo.dto.TodoCreateRequest;
import com.sparta.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    //void delete(TodoCreateRequest request);
}
