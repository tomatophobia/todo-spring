package com.easywritten.todo.service;

import com.easywritten.todo.domain.Todo;
import com.easywritten.todo.domain.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    public final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodos() {
        return todoRepository.getAll();
    }

    public Todo createTodo(Todo todoWithoutId) {
        return todoRepository.create(todoWithoutId);
    }

    public int deleteTodo(Long id) {
        return todoRepository.delete(id);
    }

}
