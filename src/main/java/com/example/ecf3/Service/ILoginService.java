package com.example.ecf3.Service;

import com.example.ecf3.Entity.User;
import jdk.jshell.spi.ExecutionControl;

public interface ILoginService {
    public boolean login(User user) ;
    public boolean logout() ;
    public boolean isLogged() ;

    public boolean isAdmin() ;

    public int getUserId() ;
    public String getUserFullname() ;
}
