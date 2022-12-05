package com.easywritten.todo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TodoController {
    @GetMapping("/testTodo")
    @ResponseBody
    public String testTodo() {
        return "Todo";
    }

    @GetMapping("/home")
    public String goHome() {
        return "content/home";
    }

}
