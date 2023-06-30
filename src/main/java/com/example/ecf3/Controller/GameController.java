package com.example.ecf3.Controller;

import com.example.ecf3.Entity.Game;
import com.example.ecf3.Entity.Result;
import com.example.ecf3.Entity.User;
import com.example.ecf3.Exception.NotLoggedException;
import com.example.ecf3.Service.ILoginService;
import com.example.ecf3.Service.GameService;
import com.example.ecf3.Service.ResultService;
import com.example.ecf3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/match")
public class GameController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @Autowired
    private ResultService resultService;

    @GetMapping("/form")
    public ModelAndView getFormMatch () throws NotLoggedException {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        if(loginService.isAdmin()){
            modelAndView.setViewName("MatchForm");
            modelAndView.addObject("users",userService.getUsers());
            modelAndView.addObject("isLogged",loginService.isLogged());
            modelAndView.addObject("userID",loginService.getUserId());
            modelAndView.addObject("isAdmin",loginService.isAdmin());
        }
        return modelAndView;
    }

    @PostMapping("/form")
    public ModelAndView postForm(@RequestParam("p1") Integer p1,@RequestParam("p2") Integer p2,@RequestParam("date") String dateStr){
        gameService.create(p1,p2,dateStr);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("")
    public ModelAndView getMatch(){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        if(loginService.isLogged()){
            User user = userService.findUserById(loginService.getUserId());
            List<Game> games = gameService.findAllgame(user);
            modelAndView.addObject("games",games);
            modelAndView.addObject("isLogged",loginService.isLogged());
            modelAndView.addObject("userID", loginService.getUserId());
            modelAndView.addObject("isAdmin",loginService.isAdmin());
            modelAndView.setViewName("GamePage");
        }
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView postResult(@RequestParam("id") Integer id, @RequestParam("winner") Integer idWinner){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        if(loginService.isLogged()){
            resultService.create(id,idWinner);
            modelAndView.setViewName("redirect:/match");
        }
        return modelAndView;
    }

}
