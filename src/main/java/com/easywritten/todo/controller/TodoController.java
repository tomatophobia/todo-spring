package com.easywritten.todo.controller;

import com.easywritten.todo.domain.Todo;
import com.easywritten.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/api/todos")
    @ResponseBody
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }


    @PostMapping("/api/todos")
    @ResponseBody
    public Todo createTodo(@RequestBody Todo todoWithoutId) {
        return todoService.createTodo(todoWithoutId);
    }

    @GetMapping("/todos")
    public String todoListPage() {
        return "content/todo_list";
    }

}
