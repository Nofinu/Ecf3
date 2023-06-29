package Service.impl;

import Entity.User;
import Repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

public class LoginServiceTest {
    private LoginService loginService;

    @BeforeEach
    void setup (){
        loginService = new LoginService();
    }

    @Test
    void testIsLoggedWithSessionIsLoggedFalse () throws ExecutionControl.NotImplementedException {
        Assertions.assertFalse(loginService.isLogged());
    }
    @Test
    void testIsLoggedWithSessionIsLoggedtrue () throws ExecutionControl.NotImplementedException {
        User user = new User(1,"toto","tata","toto@tata.com","password",true,new ArrayList<>());
        loginService.login(user);
        Assertions.assertTrue(loginService.isLogged());
    }

    @Test
    void testIsAdminWithSessionIsAdminFalse () throws ExecutionControl.NotImplementedException {
        User user = new User(1,"toto","tata","toto@tata.com","password",false,new ArrayList<>());
        loginService.login(user);
        Assertions.assertFalse(loginService.isAdmin());
    }

    @Test
    void testIsAdminWithSessionIsAdmintrue () throws ExecutionControl.NotImplementedException {
        User user = new User(1,"toto","tata","toto@tata.com","password",true,new ArrayList<>());
        loginService.login(user);
        Assertions.assertFalse(loginService.isAdmin());
    }

    @Test
    void testGetUserIdWithSessionUserIdEqualsNull () throws ExecutionControl.NotImplementedException {
        Assertions.assertNotEquals(1,loginService.getUserId());
    }

    @Test
    void testGetUserIdWithSessionUserIdEqual0 () throws ExecutionControl.NotImplementedException {
        User user = new User(1,"toto","tata","toto@tata.com","password",true,new ArrayList<>());
        loginService.login(user);
        Assertions.assertEquals(1,loginService.getUserId());
    }

    @Test
    void testGetUserFullNameWithSessionUserFullNameEqualNull () throws ExecutionControl.NotImplementedException {
        Assertions.assertNotEquals("toto tata",loginService.getUserFullname());
    }

    @Test
    void testGetUserFullNameWithSessionUserFullNameEqualToto_Tata () throws ExecutionControl.NotImplementedException {
        User user = new User(1,"toto","tata","toto@tata.com","password",true,new ArrayList<>());
        loginService.login(user);
        Assertions.assertEquals("toto tata",loginService.getUserFullname());
    }
}
