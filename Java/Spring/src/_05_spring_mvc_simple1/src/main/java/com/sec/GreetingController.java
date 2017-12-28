package com.sec;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        List<User> list = new ArrayList<>();

        User u1 = new User();
        u1.username = "guoqingyun";
        u1.realname = "gqy";
        u1.age = 28;
        list.add(u1);

        User u2 = new User();
        u2.username = "xiaolulu";
        u2.realname = "xll";
        u2.age = 27;
        list.add(u2);
        
        Userform userform = new Userform();
        userform.setList(list);
        model.addAttribute("userform",userform);
        model.addAttribute("name", name);
        return "greetings";
    }


    @RequestMapping(value="/submit",method=RequestMethod.POST)
    public String submit(@RequestParam String []username, Model model) {
        for(String name : username ){
            System.out.println(name);
        }
        // return "redirect:/greetings";
        return "forward:/greeting";
    }
}