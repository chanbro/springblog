package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String getPosts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model){
        model.addAttribute("posts", postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getCreatePostForm(Model model){
        model.addAttribute("posts" , new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "create a new post";
    }


    @GetMapping("/posts/update")
    @ResponseBody
    public String updatePost(){
        Post post = postDao.getOne(1L);
        post.setTitle("Great Horned Owl");
        postDao.save(post);
        return "Updating post";
    }

    @GetMapping("/posts/{id}/edit")
    @ResponseBody
    public String editPost(@PathVariable long id, @RequestParam String title, @RequestParam String body, Model model){
        Post post = postDao.getOne(id);
        post.setTitle(title);
        post.setTitle(body);
        postDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    @ResponseBody
    public String deletePost(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }
}