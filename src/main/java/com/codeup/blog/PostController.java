package com.codeup.blog;


import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;

    }

    @GetMapping("/posts")
    public String posts(Model model){
        model.addAttribute("listOfPosts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.findOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/remove/{id}")
    public String deletePostForm(@PathVariable long id){
        postDao.findOne(id);
        return "posts/index";
    }

    @PostMapping("/posts/remove/{id}")
    public String deletePost(@PathVariable long id){
        postDao.delete(id);
        return "posts/index";
    }



    @PostMapping("/posts/{id}/edit")
    public String editpost(@RequestParam String title, @RequestParam String body, @PathVariable Long id){
        Post post = postDao.findOne(id);
        post.setBody(body);
        post.setTitle(title);
        postDao.save(post);
        return "redirect:/posts";

    }

    @GetMapping("/posts/create")
    public String form(Model model){
        model.addAttribute("post", new Post());
        return "posts/form";
    }

    @PostMapping("/posts/create")
    public String addPost(@ModelAttribute Post post){
       User user = userDao.findOne(1L);
       post.setUser(user);
       postDao.save(post);
       return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostForm(@PathVariable long id, Model model){
        Post post = postDao.findOne(id);
        model.addAttribute("post", post);
        return "posts/editpost";
    }

//    public static void main(String[] args) {
//        String example = "I love watching movies";
//
//        String[] copyHere = new String[5];
//        copyHere = example.split(" ");
//
//        String test = "";
//
//        for(int i = copyHere.length -1; i >=0; i--){
//            if(i > 0){
//                test += copyHere[i] + " ";
//            } else if(i == 0)
//            test += copyHere[i];
//        }
//
//        System.out.println(test );
//
//
//    }
}
