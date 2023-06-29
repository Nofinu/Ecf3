package Service.impl;

import Entity.User;
import Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

public class LoginServiceTest {
    private LoginService loginService;

    @Mock
    private HttpSession httpSession;

    @BeforeEach
    void setUp (){
        loginService = new LoginService();
    }

    @Test
    void testIsLoggedWithSessionIsLoggedFalse () throws ExecutionControl.NotImplementedException {
        loginService.setHttpSession(httpSession);
        Mockito.when(httpSession.getAttribute("")).thenReturn(null);
        Assertions.assertFalse(loginService.isLogged());
    }
    @Test
    void testIsLoggedWithSessionIsLoggedtrue () throws ExecutionControl.NotImplementedException {

        Mockito.when(httpSession.getAttribute("isLogged")).thenReturn(true);
        loginService.setHttpSession(httpSession);
        Assertions.assertTrue(loginService.isLogged());
    }

    @Test
    void testIsAdminWithSessionIsAdminFalse () throws ExecutionControl.NotImplementedException {
        Mockito.when(httpSession.getAttribute("isAdmin")).thenReturn(false);
        loginService.setHttpSession(httpSession);
        Assertions.assertFalse(loginService.isAdmin());
    }

    @Test
    void testIsAdminWithSessionIsAdmintrue () throws ExecutionControl.NotImplementedException {
        Mockito.when(httpSession.getAttribute("isLogged")).thenReturn(true);
        loginService.setHttpSession(httpSession);
        Assertions.assertFalse(loginService.isAdmin());
    }

    @Test
    void testGetUserIdWithSessionUserIdEqualsNull () throws ExecutionControl.NotImplementedException {
        Mockito.when(httpSession.getAttribute("userId")).thenReturn(null);
        loginService.setHttpSession(httpSession);
        Assertions.assertNotEquals(1,loginService.getUserId());
    }

    @Test
    void testGetUserIdWithSessionUserIdEqual0 () throws ExecutionControl.NotImplementedException {
        Mockito.when(httpSession.getAttribute("userId")).thenReturn(1);
        loginService.setHttpSession(httpSession);
        Assertions.assertEquals(1,loginService.getUserId());
    }

    @Test
    void testGetUserFullNameWithSessionUserFullNameEqualNull () throws ExecutionControl.NotImplementedException {
        Mockito.when(httpSession.getAttribute("fullname")).thenReturn(null);
        loginService.setHttpSession(httpSession);
        Assertions.assertNotEquals("toto tata",loginService.getUserFullname());
    }

    @Test
    void testGetUserFullNameWithSessionUserFullNameEqualToto_Tata () throws ExecutionControl.NotImplementedException {
        Mockito.when(httpSession.getAttribute("fullname")).thenReturn("toto tata");
        loginService.setHttpSession(httpSession);
        Assertions.assertEquals("toto tata",loginService.getUserFullname());
    }
}
