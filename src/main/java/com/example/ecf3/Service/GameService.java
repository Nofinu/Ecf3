package com.example.ecf3.Service;

import com.example.ecf3.Entity.Game;
import com.example.ecf3.Entity.User;
import com.example.ecf3.Exception.FieldEmptyException;
import com.example.ecf3.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ILoginService loginService;

    @Autowired
    private UserService userService;

    public boolean create(int p1Id,int p2Id,String dateStr){
        if(p1Id ==0 ||p2Id == 0 || dateStr == null){
            FieldEmptyException.with("player 1", "player 2", "date");
        }
        User user1 = userService.findUserById(p1Id);
        User user2 = userService.findUserById(p2Id);
        Game game = Game.builder().player1(user1).player2(user2).dateMatch(LocalDate.parse(dateStr)).build();
        game = gameRepository.save(game);
        return game.getId()>0;
    }
}
