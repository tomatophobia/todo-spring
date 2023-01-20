package com.easywritten.todo.domain;

import java.util.List;

public interface TodoRepository {
    List<Todo> getAll();
    Todo get(Long id);
    Todo create(Todo todoWithoutId);

    int delete(Long id);
}
