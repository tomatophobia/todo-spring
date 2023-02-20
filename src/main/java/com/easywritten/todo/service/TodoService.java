package com.easywritten.todo.service;

import com.easywritten.todo.domain.Todo;
import com.easywritten.todo.domain.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    public final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAll() {
        return todoRepository.getAll();
    }

    public Todo get(Long id) {
        return todoRepository.get(id);
    }

    public Todo create(Todo todoWithoutId) {
        return todoRepository.create(todoWithoutId);
    }

    public int delete(Long id) {
        return todoRepository.delete(id);
    }

    public int replaceOrCreate(Todo todo) {
        return todoRepository.replaceOrCreate(todo);
    }

}
