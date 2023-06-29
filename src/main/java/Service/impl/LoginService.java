package Service.impl;

import Entity.User;
import Service.ILoginService;
import jakarta.servlet.http.HttpSession;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private HttpSession httpSession;

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public boolean login(User user) throws ExecutionControl.NotImplementedException {
        httpSession.setAttribute("isLogged", true);
        httpSession.setAttribute("fullName", user.getFirstName() + " "+user.getLastName());
        httpSession.setAttribute("isAdmin", user.isAdmin());
        httpSession.setAttribute("userId", user.getId());
        return true;    }

    @Override
    public boolean isLogged() throws ExecutionControl.NotImplementedException {
        return httpSession.getAttribute("isLogged") != null && (boolean)httpSession.getAttribute("isLogged") ;    }

    @Override
    public boolean isAdmin() throws ExecutionControl.NotImplementedException {
        return httpSession.getAttribute("isAdmin") != null && (boolean)httpSession.getAttribute("isAdmin");    }

    @Override
    public int getUserId() throws ExecutionControl.NotImplementedException {
        return (int)httpSession.getAttribute("userId");
    }

    @Override
    public String getUserFullname() throws ExecutionControl.NotImplementedException {
        return (String)httpSession.getAttribute("fullName");
    }
}
