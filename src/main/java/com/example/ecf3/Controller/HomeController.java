package com.example.ecf3.Controller;

import com.example.ecf3.Service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private LoginService loginService;

    @GetMapping("")
    public ModelAndView getHome (){
        ModelAndView modelAndView = new ModelAndView("homePage");
        modelAndView.addObject("isLogged",loginService.isLogged());
        modelAndView.addObject("userID", loginService.getUserId());
        return modelAndView;
    }
}
