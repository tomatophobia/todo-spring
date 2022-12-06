package com.easywritten.todo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TodoController {

    @GetMapping("/home")
    public String goHome() {
        return "content/home";
    }

    @GetMapping("/todos")
    public String todoListPage() {
        return "content/todo_list";
    }

}
