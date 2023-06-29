package com.example.ecf3.Service;

import com.example.ecf3.Entity.Game;
import com.example.ecf3.Entity.Result;
import com.example.ecf3.Entity.User;
import com.example.ecf3.Exception.FieldEmptyException;
import com.example.ecf3.Repository.GameRepository;
import com.example.ecf3.Repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private GameRepository gameRepository;

    public Boolean create (int idGame,int idWinner){
        if(idGame ==0 || idWinner ==0){
            FieldEmptyException.with("idGame","idWinner");
        }

        Game game = gameService.findById(idGame);
        User user =userService.findUserById(idWinner);
        Result result =Result.builder().game(game).winner(user).build();
        game.setResult(result);
        result = resultRepository.save(result);
        gameRepository.save(game);

        return result.getId()>0;
    }
}
