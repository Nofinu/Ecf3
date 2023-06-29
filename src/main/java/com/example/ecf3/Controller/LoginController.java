package com.example.ecf3.Controller;

import com.example.ecf3.Exeption.UserExistException;
import com.example.ecf3.Exeption.UserNotExistException;
import com.example.ecf3.Exeption.WrongPasswordException;
import com.example.ecf3.Service.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    private UserService userService;


    @GetMapping("signin")
    public ModelAndView signIn() {
        ModelAndView mv = new ModelAndView("signin");
        return mv;
    }

    @PostMapping("signin")
    public String signUp(@RequestParam String email, @RequestParam String password) throws UserNotExistException, IOException, WrongPasswordException {
        if(userService.signIn(email, password)) {
            return "redirect:/";
        }
        return null;
    }

    @ExceptionHandler(UserNotExistException.class)
    public ModelAndView handleUserNotExist(UserNotExistException ex) {
        ModelAndView mv = new ModelAndView("signin");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ModelAndView handleWrongPassword(WrongPasswordException ex) {
        ModelAndView mv = new ModelAndView("signin");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    @GetMapping("signup")
    public ModelAndView postSignIn() {
        ModelAndView mv = new ModelAndView("signup");
        return mv;
    }

    @PostMapping("signup")
    public String postSignUp(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) throws UserExistException, IOException, ExecutionControl.NotImplementedException {
        if(userService.signUp(firstName, lastName, email, password)) {
            return "redirect:/user/signin";
        }
        return null;
    }

    @ExceptionHandler(UserExistException.class)
    public ModelAndView handleUserExist(UserExistException ex) {
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("message", ex.getMessage());
        return mv;
    }
}
