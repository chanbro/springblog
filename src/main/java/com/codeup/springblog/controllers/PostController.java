package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String getPosts(Model model){
        ArrayList<Post> postList = new ArrayList<>();
        postList.add(new Post(2, "Second Post", "askdfhkashdfkjahsdf"));
        postList.add(new Post(3, "Third Post", "some more text..."));

        model.addAttribute("posts", postList);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable int id, Model model){
        Post post1 = new Post(id, "Europa's First Post", "Remote Learning Today!");
        model.addAttribute("title", post1.getTitle());
        model.addAttribute("body", post1.getBody());
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getCreatePostForm(Model model){
        model.addAttribute("post" , new Post());
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
        post.setTitle("Updated Title Worked!");
        postDao.save(post);
        return "Updating post";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }
}