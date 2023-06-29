package com.example.ecf3.Controller;

import com.example.ecf3.Exception.NotLoggedException;
import com.example.ecf3.Service.ILoginService;
import com.example.ecf3.Service.GameService;
import com.example.ecf3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private GameService matchService;

    @GetMapping("/form")
    public ModelAndView getFormMatch () throws NotLoggedException {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        if(loginService.isAdmin()){
            modelAndView.setViewName("MatchForm");
            modelAndView.addObject("users",userService.getUsers());
            modelAndView.addObject("isLogged",loginService.isLogged());
            modelAndView.addObject("userID",loginService.getUserId());
        }
        return modelAndView;
    }

    @PostMapping("/form")
    public ModelAndView postForm(@RequestParam("p1") Integer p1,@RequestParam("p2") Integer p2,@RequestParam("date") String dateStr){
        matchService.create(p1,p2,dateStr);
        return new ModelAndView("redirect:/");
    }

}
