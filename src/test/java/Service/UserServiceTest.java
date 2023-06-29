package Service;

import com.example.ecf3.Entity.User;
import com.example.ecf3.Exeption.*;
import com.example.ecf3.Repository.UserRepository;
import com.example.ecf3.Service.ILoginService;
import com.example.ecf3.Service.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ILoginService loginService;

    @BeforeEach
    void setUp(){
        userService = new UserService();
        userService.setUserRepository(userRepository);
    }

    @Test
    void testSignUpWithEmailAlreadyRegisterExpetedUserExistExeption (){
        Mockito.when(userRepository.findByEmail("toto@tata.com")).thenReturn(User.builder().firstName("t").lastName("t").build());
        Assertions.assertThrowsExactly(UserExistException.class,()->{
           userService.signUp("toto","tata","toto@tata.com","123");
        });
    }

    @Test
    void testSignUpWithNewUserExpectedTrue () throws ExecutionControl.NotImplementedException, UserExistException {
        Mockito.when(userRepository.findByEmail("to@ta;com")).thenThrow(HibernateException.class);
        userService.setLoginService(loginService);
        User user = User.builder().firstName("toto").lastName("tata").email("to@ta.com").password("123").build();
        Mockito.when(loginService.login(user)).thenReturn(true);
        Mockito.when(userRepository.save(user)).thenReturn(User.builder().id(8).build());
       Assertions.assertTrue(userService.signUp("toto","tata","to@ta.com","123"));
    }

    @Test
    void testSignInWithNewUserExpetedUserNotExistExeption () throws ExecutionControl.NotImplementedException {
        Mockito.when(userRepository.findByEmail("test@test.com")).thenReturn(null);
        Assertions.assertThrowsExactly(UserNotExistException.class,()->{
            userService.signIn("test@test.com","123");
        });
    }

    @Test
    void testSignInWithUserExistExpetedTrue () throws ExecutionControl.NotImplementedException, UserNotExistException, WrongPasswordException {
        Mockito.when(userRepository.findByEmail("to@ta.com")).thenReturn(User.builder().email("to@ta.com").password("123").build());
        Assertions.assertTrue(userService.signIn("to@ta.com","123"));
    }

    @Test
    void testGetUserNotLogged () throws ExecutionControl.NotImplementedException {
        userService.setLoginService(loginService);
        Mockito.when(loginService.isLogged()).thenReturn(false);
       Assertions.assertThrowsExactly(NotLoggedException.class,()->{
            userService.getUsers();
        });
    }

    @Test
    void testUpdateUserNotLoggedExpetedNotLoggedException () throws ExecutionControl.NotImplementedException {
        userService.setLoginService(loginService);
        Mockito.when(loginService.isLogged()).thenReturn(false);
        Assertions.assertThrowsExactly(NotLoggedException.class,()->{
            userService.updateUser(1,"toto","tata","toto@tata.com","123");
        });
    }

    @Test
    void testUpdateUserNotRightUserExpeted() throws ExecutionControl.NotImplementedException, UserNotExistException, WrongPasswordException {
        userService.setLoginService(loginService);
        Mockito.when(loginService.isLogged()).thenReturn(true);
        Mockito.when(loginService.getUserId()).thenReturn(1);
        Assertions.assertThrowsExactly(WrongUserException.class,()->{
            userService.updateUser(2,"toto","tata","toto@tata.com","123");
        });
    }

    @Test
    void testUpdateExpetedTrue() throws ExecutionControl.NotImplementedException, UserNotExistException, WrongPasswordException, NotLoggedException, WrongUserException {
        userService.setLoginService(loginService);
        Mockito.when(loginService.isLogged()).thenReturn(true);
        Mockito.when(loginService.getUserId()).thenReturn(1);
        Assertions.assertTrue(userService.updateUser(1,"toto","tata","toto@tata.com","123"));
    }
}
