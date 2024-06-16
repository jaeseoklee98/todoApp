package com.sparta.todo.service;

import com.sparta.todo.dto.CommentCreateRequest;
import com.sparta.todo.dto.CommentResponse;
import com.sparta.todo.dto.CommentUpdateRequest;
import com.sparta.todo.exception.DataNotFoundException;
import com.sparta.todo.model.Comment;
import com.sparta.todo.model.Todo;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final TodoService todoService;
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public CommentResponse save(long todoId, CommentCreateRequest request) {

        Todo todo = todoService.findTodoById(todoId);
        Comment comment = commentRepository.save(new Comment(request.getComment(), request.getUsername(), todo));
        return CommentResponse.toDto(comment);
    }

    @Transactional
    public CommentResponse update(Long id, Long commentId, CommentUpdateRequest request) {
        if (commentId == null || id == null) {
            throw new IllegalArgumentException("선택한 일정이나 댓글 ID가 입력되지 않았습니다.");
        }

        todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id에 맞는 일정 데이터가 없습니다. 아이디 : " + id));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new DataNotFoundException("해당 댓글이 DB에 존재하지 않습니다."));

        if (!Objects.equals(comment.getUsername(), request.getUsername())) {
            throw new IllegalArgumentException("사용자가 일치하지 않습니다.");
        }

        comment.update(request.getComment());
        return CommentResponse.toDto(comment);
    }

    public void delete(Long id, Long commentId, String username) {
        if (id == null || commentId == null) {
            throw new IllegalArgumentException("선택한 일정이나 댓글 ID가 입력되지 않았습니다.");
        }

        todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id에 맞는 일정 데이터가 없습니다. 아이디 : " + id));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new DataNotFoundException("해당 댓글이 DB에 존재하지 않습니다."));

        if(!Objects.equals(comment.getUsername(), username)) {
            throw new IllegalArgumentException("사용자가 일치하지 않습니다.");
        }

        commentRepository.delete(comment);
    }
}
