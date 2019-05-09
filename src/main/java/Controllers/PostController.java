package Controllers;


import Repos.PostRepository;
import Repos.UserRepository;
import com.codeup.blog.Services.EmailService;
import com.codeup.blog.Models.Post;
import com.codeup.blog.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;

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
    public String editpost(@ModelAttribute Post editedPost){
        editedPost.setUser(userDao.findOne(1l));
        postDao.save(editedPost);
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
       Post savedPost = postDao.save(post);
       emailService.prepareAndSend(savedPost, "test", "test");
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
