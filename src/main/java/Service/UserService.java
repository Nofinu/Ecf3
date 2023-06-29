package Service;

import Entity.User;
import Repository.UserRepository;
import Service.impl.LoginService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository appUserRepository;

    @Autowired
    private LoginService loginService;

    public boolean signUp(String firstName, String lastName, String email, String password) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("notImplemented");

    }

    public boolean signIn(String email, String password) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("notImplemented");

    }

    public List<User> getUsers() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("notImplemented");
    }

    public boolean updateUser (int id,String firstName, String lastName, String email, String password) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("notImplemented");
    }
}
