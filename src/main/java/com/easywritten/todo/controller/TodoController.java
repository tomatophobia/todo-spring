package com.easywritten.todo.controller;

import com.easywritten.todo.domain.Todo;
import com.easywritten.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/todos/{id}")
    @ResponseBody
    public Todo getTodo(@PathVariable Long id) {
        return todoService.getTodo(id);
    }


    @PostMapping("/api/todos")
    @ResponseBody
    public Todo createTodo(@RequestBody Todo todoWithoutId) {
        return todoService.createTodo(todoWithoutId);
    }

    @DeleteMapping("/api/todos/{id}")
    @ResponseBody
    public int deleteTodo(@PathVariable Long id) {
        // Long 타입으로 전달이 될까? => 테스트 해보니까 된다.
        return todoService.deleteTodo(id);
    }

    @GetMapping("/todos")
    public String todoListPage(Model model) {
        model.addAttribute("todos", todoService.getTodos());
        return "content/todo_list";
    }

}
