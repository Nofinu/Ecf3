package Service;

import Entity.User;
import jdk.jshell.spi.ExecutionControl;

public interface ILoginService {
    public boolean login(User user) throws ExecutionControl.NotImplementedException;
    public boolean isLogged() throws ExecutionControl.NotImplementedException;

    public boolean isAdmin() throws ExecutionControl.NotImplementedException;

    public int getUserId() throws ExecutionControl.NotImplementedException;
    public String getUserFullname() throws ExecutionControl.NotImplementedException;
}
