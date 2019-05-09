package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String landingPage(){
        return "home";
    }

    @GetMapping("/home/{name}")
    public String landingPage(@PathVariable String name, Model model){
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping("/roll-dice")
    public String dice(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String dice(@PathVariable int n, Model model){
        double randomNum = (Math.random() * ((6 - 1) + 1)) + 1;
        int compareTo = (int) randomNum;
        model.addAttribute("n", n);
        model.addAttribute("random", compareTo);
        System.out.println(compareTo);
        return "roll-dice";
    }
}
