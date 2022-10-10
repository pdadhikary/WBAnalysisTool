package ca.yorku.eecs3311.team09.models;

import java.util.List;

public interface UserMapper {
    public List<UserModel> getAllUsers();

    public UserModel getUser(String username);

    public void putUser(UserModel user);

    public void dropUser(String username);

    public boolean usernameExists(String username);
}
