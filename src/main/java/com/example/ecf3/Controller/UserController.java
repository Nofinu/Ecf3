package com.example.ecf3.Controller;

import com.example.ecf3.Entity.Game;
import com.example.ecf3.Entity.User;
import com.example.ecf3.Exception.*;
import com.example.ecf3.Service.GameService;
import com.example.ecf3.Service.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;


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
            return "redirect:/";
        }
        return null;
    }

    @GetMapping("logout")
    public ModelAndView getSignOut (){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        userService.logOut();
        return modelAndView;
    }

    @ExceptionHandler(UserExistException.class)
    public ModelAndView handleUserExist(UserExistException ex) {
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    @GetMapping("profil/{id}")
    public ModelAndView getPorfil (@PathVariable("id")Integer id){
        ModelAndView modelAndView = new ModelAndView("profil");
        User user = userService.findUserById(id);
        modelAndView.addObject("user",user);
        List<Game> gamesList = gameService.findAllgame(user);
        modelAndView.addObject("gameList",gamesList.stream().filter(g -> g.getDateMatch().isAfter(LocalDate.now())).toList());
        return modelAndView;
    }
    @PostMapping("profil/{id}")
    public ModelAndView postPorfil (@PathVariable("id")Integer id,@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) throws EmailAlreadyRegisterException, NotLoggedException, WrongUserException {
        ModelAndView modelAndView = new ModelAndView();
        if(userService.updateUser(id,firstName,lastName,email,password)){
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @ExceptionHandler(EmailAlreadyRegisterException.class)
    public ModelAndView handleEmailException(EmailAlreadyRegisterException ex) {
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    @ExceptionHandler(WrongUserException.class)
    public ModelAndView handleWrongUser(WrongUserException ex) {
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("message", ex.getMessage());
        return mv;
    }
}
