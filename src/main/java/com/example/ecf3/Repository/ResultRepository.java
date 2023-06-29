package com.example.ecf3.Repository;

import com.example.ecf3.Entity.Result;
import com.example.ecf3.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends CrudRepository<Result,Integer> {
    public int countByWinner(User winner);
}
