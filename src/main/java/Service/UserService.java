package Service;

import Entity.User;
import Exeption.*;
import Repository.UserRepository;
import Service.impl.LoginService;
import jdk.jshell.spi.ExecutionControl;
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

    public boolean signUp(String firstName, String lastName, String email, String password) throws ExecutionControl.NotImplementedException {
        try {
            userRepository.findByEmail(email);
            throw new UserExistException();
        }
        catch (Exception ex) {
            User user = User.builder().firstName(firstName).lastName(lastName).email(email).password(password).build();
            userRepository.save(user);
            return user.getId() > 0;
        }
    }

    public boolean signIn(String email, String password) throws UserNotExistException,WrongPasswordException {
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

    public List<User> getUsers() throws ExecutionControl.NotImplementedException, NotLoggedException {
        if(loginService.isLogged()) {
                return (List<User>)userRepository.findAll();
        }
        throw new NotLoggedException();
    }

    public boolean updateUser (int id,String firstName, String lastName, String email, String password) throws ExecutionControl.NotImplementedException, NotLoggedException, WrongUserException {
        if(loginService.isLogged()) {
            if(id == loginService.getUserId()){
                User user = userRepository.findById(id).get();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                userRepository.save(user);
                return true;
            }
            throw new WrongUserException();
        }
        throw new NotLoggedException();
    }
}
