package com.codeup.blog;


import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
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


    @PostMapping("posts/update/{id}")
        public String updatePost(@PathVariable long id){
            Post post = postDao.findOne(id);
            post.setBody("test");
            postDao.save(post);
            return "posts/index";
        }


    @GetMapping("/posts/create")
    @ResponseBody
    public String form(){
        return "Here is a form to add a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String addPost(){
        return "Post has been created";
    }
}
