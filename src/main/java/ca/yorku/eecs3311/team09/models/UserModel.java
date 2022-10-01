package ca.yorku.eecs3311.team09.models;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Objects;

public class UserModel {
    protected String username;
    protected String hashedPassword;

    public UserModel(String username) {
        this.username = username;
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

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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
}
