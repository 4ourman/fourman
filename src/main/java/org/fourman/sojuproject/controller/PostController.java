package org.fourman.sojuproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @GetMapping("/main")
    public String mainpage(){
        return "/comment/main";
    }

    @GetMapping("/joinmember")
    public String joinpage(){
        return "/comment/joinmember";
    }

    @GetMapping("/createpost")
    public String createpost(){
        return "/comment/createpost";
    }

}
