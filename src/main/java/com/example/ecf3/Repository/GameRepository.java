package com.example.ecf3.Repository;

import com.example.ecf3.Entity.Game;
import com.example.ecf3.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game,Integer> {
    public List<Game> findByPlayer1OrPlayer2(User user1, User user2);

}
