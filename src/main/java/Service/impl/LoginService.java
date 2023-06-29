package Service.impl;

import Entity.User;
import Service.ILoginService;
import jdk.jshell.spi.ExecutionControl;

public class LoginService implements ILoginService {
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
