package ca.yorku.eecs3311.team09.models;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
import java.util.Objects;

public class UserModel {
    protected String username;
    protected String hashedPassword;

    protected UserModel() {
        this.username = "";
        this.hashedPassword = "";
    }

    public UserModel(String username, String password) {
        this.username = username;
        this.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.hashedPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel)) return false;
        UserModel userModel = (UserModel) o;
        return this.username.equals(userModel.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public static List<UserModel> getAll() {
        return DBContext.getInstance().getUserMapper().getAllUsers();
    }

    public static UserModel get(String username) {
        return DBContext.getInstance().getUserMapper().getUser(username);
    }

    public static void put(UserModel user) {
        DBContext.getInstance().getUserMapper().putUser(user);
    }

    public static void drop(String username) {
        DBContext.getInstance().getUserMapper().dropUser(username);
    }

    public static boolean usernameExists(String username) {
        return DBContext.getInstance().getUserMapper().usernameExists(username);
    }
}
