package pl.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String username;
    private String email;
    private String password;
    private int groupId;

    public User(){};

    public User(String username, String email, String password, int groupId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String toString() {
        return String.format("User Id: %d,\tusername: %s,\temail: %s,\tuser group id: %d",
                this.id, this.username, this.email, this.groupId);
    }

}