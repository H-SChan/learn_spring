package test.learnspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMembersForm";
    }
}
