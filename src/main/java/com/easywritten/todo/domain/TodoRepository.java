package com.easywritten.todo.domain;

import java.util.List;

public interface TodoRepository {
    Todo get(long id);
    List<Todo> getAll();
    Todo create(Todo todoWithoutId);
}
