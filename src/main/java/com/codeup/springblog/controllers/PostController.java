package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String getPosts(){
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getPost(@PathVariable int id){
        return "view an individual post, id="+id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getCreatePostForm(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "create a new post";
    }

    @RequestMapping(path="/posts", method=RequestMethod.DELETE)
    @ResponseBody
    public String delete(){
        return "DELETE!!";
    }
}