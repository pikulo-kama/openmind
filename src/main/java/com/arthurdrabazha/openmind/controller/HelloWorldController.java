package com.arthurdrabazha.openmind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld() {
//        model.addAttribute("name", "World");

        return "hello";
    }

    @GetMapping("/hello/{name}")
    public String helloSomeone(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name );

        return "hello";
    }
}
