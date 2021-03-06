package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.services.EmailService;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    @Autowired
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
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

//    @GetMapping("/posts/create")
//    public String getCreatePostForm(){
////        model.addAttribute("posts" , new Post());
//        return "posts/create";
//    }

//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String savePost() {
//        Post newPost = new Post();
//        newPost.setTitle("New Post");
//        newPost.setBody("This is a newly saved post!");
//        newPost.setUser(userDao.getOne(1L));
//        postDao.save(newPost);
//        String emailSubject = "This is the email subject";
//        String emailBody = "Email Body Test";
//        emailService.prepareAndSend(newPost, emailSubject, emailBody);
//        return "Saving post";
//    }

    @GetMapping("/posts/create")
    public String createPostForm(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(loggedInUser.getUsername());
        model.addAttribute("post", new Post());
        return "posts/create";
    }

//    @PostMapping("/posts/create")
//    public String submitPost(@RequestParam String title, @RequestParam String body){
//        Post post = new Post(title, body);
//        User user = userDao.getOne(1L);
//        post.setUser(user);
//        postDao.save(post);
//        return "redirect:/posts/" + post.getId();
//    }

//    @PostMapping("/posts/create")
//    public String submitPost(@ModelAttribute Post post){
//        User user = userDao.getOne(1L);
//        post.setUser(user);
//        postDao.save(post);
//        return "redirect:/posts/" + post.getId();
//    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam String title, @RequestParam String body ){
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setBody(body);
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newPost.setUser(loggedIn);
        postDao.save(newPost);
        return "redirect:/posts";
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
    public String editForm(@PathVariable long id, Model model) {
        Post postToEdit = postDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
        Post p = postDao.getOne(id);
        p.setTitle(post.getTitle());
        p.setBody(post.getBody());
        postDao.save(p);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    @ResponseBody
    public String deletePost(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }
}