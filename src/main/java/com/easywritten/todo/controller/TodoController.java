package com.easywritten.todo.controller;

import com.easywritten.todo.domain.Todo;
import com.easywritten.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/api/todos")
    @ResponseBody
    public List<Todo> getAll() {
        return todoService.getAll();
    }

    @GetMapping("/api/todos/{id}")
    @ResponseBody
    public Todo get(@PathVariable Long id) {
        return todoService.get(id);
    }


    @PostMapping("/api/todos")
    @ResponseBody
    public Todo create(@RequestBody Todo todoWithoutId) {
        return todoService.create(todoWithoutId);
    }

    @DeleteMapping("/api/todos/{id}")
    @ResponseBody
    public int delete(@PathVariable Long id) {
        // Long 타입으로 전달이 될까? => 테스트 해보니까 된다.
        return todoService.delete(id);
    }

    // RFC 7231 4.3.4 - The PUT method requests that the state of the target resource be created or replaced with the state
    @PutMapping("/api/todos/{id}")
    @ResponseBody
    public int replaceOrCreate(@PathVariable Long id, @RequestBody Todo todoWithoutId) {
        todoWithoutId.setId(id);
        return todoService.replaceOrCreate(todoWithoutId);
    }

    @GetMapping("/todos")
    public String todoListPage(Model model) {
        model.addAttribute("todos", todoService.getAll());
        return "content/todo_list";
    }

}
