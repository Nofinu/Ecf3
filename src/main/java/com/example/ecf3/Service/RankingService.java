package com.example.ecf3.Service;

import com.example.ecf3.Entity.User;
import com.example.ecf3.Exception.NotLoggedException;
import com.example.ecf3.Service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RankingService {

    @Autowired
    private UserService userService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private LoginService loginService;

    public List<User> getRanking () throws NotLoggedException {
        if(loginService.isLogged()){
           List<User> userList = userService.getUsers();
            for (User u:userList) {
                int wins = resultService.countWin(u);
                u.setTotalScore(wins);
            }
            return userList.stream().sorted(Comparator.comparingInt(User::getTotalScore)).toList();
        }
        throw new NotLoggedException();
    }
}
