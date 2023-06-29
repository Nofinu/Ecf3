package Service.impl;

import Entity.User;
import Service.ILoginService;
import jakarta.servlet.http.HttpSession;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    private HttpSession httpSession;

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public boolean login(User user) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("not implemented");
    }

    @Override
    public boolean isLogged() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("not implemented");
    }

    @Override
    public boolean isAdmin() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("not implemented");
    }

    @Override
    public int getUserId() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("not implemented");
    }

    @Override
    public String getUserFullname() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("not implemented");
    }
}
