package com.example.ecf3.Service;

import com.example.ecf3.Entity.User;
import com.example.ecf3.Exception.*;
import com.example.ecf3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ILoginService loginService;

    public void setUserRepository(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public boolean signUp(String firstName, String lastName, String email, String password) throws UserExistException {
            User user = userRepository.findByEmail(email);
           if(user == null){
               User userCreate = User.builder().firstName(firstName).lastName(lastName).email(email).password(password).build();
               userCreate = userRepository.save(userCreate);
               loginService.login(userCreate);
               return userCreate.getId() > 0;
           }
            throw new UserExistException();
    }

    public boolean signIn(String email, String password) throws UserNotExistException, WrongPasswordException {
        try {
            User user = userRepository.findByEmail(email);
            if(user.getPassword().equals(password)) {
                return loginService.login(user);
            }
            throw new WrongPasswordException();
        }catch (Exception ex) {
            throw new UserNotExistException();
        }

    }

    public List<User> getUsers() throws NotLoggedException {
        if(loginService.isLogged()) {
                return (List<User>)userRepository.findAll();
        }
        throw new NotLoggedException();
    }

    public boolean updateUser (int id,String firstName, String lastName, String email, String password) throws NotLoggedException, WrongUserException, EmailAlreadyRegisterException {
        if(loginService.isLogged()) {
            if(id == loginService.getUserId()){
                User user = userRepository.findById(id).get();
                try{
                    if(!email.equals(user.getEmail())){
                        userRepository.findByEmail(email);
                    }else{
                        user.setFirstName(firstName);
                        user.setLastName(lastName);
                        user.setEmail(email);
                        user.setPassword(password);
                        userRepository.save(user);
                        return true;
                    }
                }catch (Exception ex){
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setPassword(password);
                    userRepository.save(user);
                    return true;
                }
                throw new EmailAlreadyRegisterException();
            }
            throw new WrongUserException();
        }
        throw new NotLoggedException();
    }


    public boolean logOut (){
        loginService.logout();
        return true;
    }

    public User findUserById(int id){
        return userRepository.findById(id).get();
    }
}
