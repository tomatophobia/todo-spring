package com.easywritten.todo.service;

import com.easywritten.todo.domain.Todo;
import com.easywritten.todo.domain.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    public final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todoWithoutId) {
        return todoRepository.create(todoWithoutId);
    }

}
