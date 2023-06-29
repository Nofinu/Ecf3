package com.example.ecf3.Service.impl;

import com.example.ecf3.Entity.User;
import com.example.ecf3.Service.ILoginService;
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
    public boolean login(User user)  {
        httpSession.setAttribute("isLogged", true);
        httpSession.setAttribute("fullName", user.getFirstName() + " "+user.getLastName());
        httpSession.setAttribute("isAdmin", user.isAdmin());
        httpSession.setAttribute("userId", user.getId());
        return true;    }
    @Override
    public boolean logout()  {
        httpSession.setAttribute("isLogged", false);
        httpSession.setAttribute("fullName", "");
        httpSession.setAttribute("isAdmin", false);
        httpSession.setAttribute("userId", null);
        return true;    }

    @Override
    public boolean isLogged()  {
        return httpSession.getAttribute("isLogged") != null && (boolean)httpSession.getAttribute("isLogged") ;    }

    @Override
    public boolean isAdmin()  {
        return httpSession.getAttribute("isAdmin") != null && (boolean)httpSession.getAttribute("isAdmin");    }

    @Override
    public int getUserId()  {
        return httpSession.getAttribute ("userId")!=null? (int)httpSession.getAttribute("userId") : 0;
    }

    @Override
    public String getUserFullname()  {
        return (String)httpSession.getAttribute("fullName");
    }
}
