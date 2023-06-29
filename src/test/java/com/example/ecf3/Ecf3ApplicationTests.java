package com.example.ecf3;

import com.example.ecf3.Entity.User;
import com.example.ecf3.Exeption.*;
import com.example.ecf3.Repository.UserRepository;
import com.example.ecf3.Service.ILoginService;
import com.example.ecf3.Service.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Ecf3ApplicationTests {

	@Test
	void contextLoads() {
	}

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
		Mockito.when(userRepository.findByEmail("toto@tata.com")).thenReturn(new User());
		Assertions.assertThrowsExactly(UserExistException.class,()->{
			userService.signUp("toto","tata","toto@tata.com","123");
		});
	}

	@Test
	void testSignUpWithNewUserExpectedTrue () throws ExecutionControl.NotImplementedException, UserExistException {
		User user = User.builder().firstName("toto").lastName("tata").email("to@ta.com").password("123").build();
		Mockito.when(userRepository.save(user)).thenReturn(new User());
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
		Mockito.when(userRepository.findByEmail("test@test.com")).thenReturn(User.builder().email("to@ta.com").password("123").build());
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
		Assertions.assertThrowsExactly(NotLoggedException.class,()->{
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
