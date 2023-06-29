package com.example.ecf3.Repository;

import com.example.ecf3.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByEmail(String email);
}
