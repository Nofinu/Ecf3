package Service;

import Exeption.NotLoggedException;
import Exeption.UserExistException;
import Exeption.UserNotExistException;
import Repository.UserRepository;
import Service.impl.LoginService;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp(){
        userService = new UserService();
    }

    @Test
    void testSignUpWithEmailAlreadyRegisterExpetedUserExistExeption (){
        Assertions.assertThrowsExactly(UserExistException.class,()->{
           userService.signUp("toto","tata","toto@tata.com","123");
        });
    }

    @Test
    void testSignUpWithNewUserExpectedTrue () throws ExecutionControl.NotImplementedException {
       Assertions.assertTrue(userService.signUp("toto","tata","to@ta.com","123"));
    }

    @Test
    void testSignInWithNewUserExpetedUserNotExistExeption () throws ExecutionControl.NotImplementedException {
        Assertions.assertThrowsExactly(UserNotExistException.class,()->{
            userService.signIn("test@test.com","123");
        });
    }

    @Test
    void testSignInWithUserExistExpetedTrue () throws ExecutionControl.NotImplementedException {
        Assertions.assertTrue(userService.signIn("to@ta.com","123"));
    }

    @Test
    void testGetUserNotLogged () throws ExecutionControl.NotImplementedException {
        Assertions.assertThrowsExactly(NotLoggedException.class,()->{
            userService.getUsers();
        });
    }

    @Test
    void testUpdateUserNotLoggedExpetedNotLoggedException (){
        Assertions.assertThrowsExactly(NotLoggedException.class,()->{
            userService.updateUser(1,"toto","tata","toto@tata.com","123");
        });
    }

    @Test
    void testUpdateUserNotRightUserExpeted() throws ExecutionControl.NotImplementedException {
        userService.signIn("to@ta.com","123");
        Assertions.assertThrowsExactly(NotLoggedException.class,()->{
            userService.updateUser(2,"toto","tata","toto@tata.com","123");
        });
    }

    @Test
    void testUpdateExpetedTrue() throws ExecutionControl.NotImplementedException {
        userService.signIn("to@ta.com","123");
        Assertions.assertTrue(userService.updateUser(1,"toto","tata","toto@tata.com","123"));
    }
}
