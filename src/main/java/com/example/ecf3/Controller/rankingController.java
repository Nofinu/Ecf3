package com.example.ecf3.Controller;

import com.example.ecf3.Exception.EmailAlreadyRegisterException;
import com.example.ecf3.Exception.NotLoggedException;
import com.example.ecf3.Service.RankingService;
import com.example.ecf3.Service.ResultService;
import com.example.ecf3.Service.UserService;
import com.example.ecf3.Service.impl.LoginService;
import jakarta.servlet.annotation.HandlesTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ranking")
public class rankingController {

    @Autowired
    private RankingService rankingService;
    @Autowired
    private LoginService loginService;

    @GetMapping("")
    public ModelAndView getRanking() throws NotLoggedException {
        ModelAndView modelAndView = new ModelAndView("ranking");
        modelAndView.addObject("users",rankingService.getRanking());
        modelAndView.addObject("isLogged",loginService.isLogged());
        modelAndView.addObject("userID", loginService.getUserId());
        modelAndView.addObject("isAdmin",loginService.isAdmin());
        return modelAndView;
    }

    @ExceptionHandler(NotLoggedException.class)
    public ModelAndView handleEmailException(NotLoggedException ex) {
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("message", ex.getMessage());
        return mv;
    }
}
