package com.easywritten.todo.domain;

public interface TodoRepository {
    Todo create(Todo todoWithoutId);

    Todo get(long id);
}
