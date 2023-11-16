package com.esliceu.PracticaDibuix.Model;

public class User {

    private String username;
    private String nameAndLastName;
    private String password;

    public String getNameAndLastName() {
        return nameAndLastName;
    }

    public void setNameAndLastName(String nameAndLastName) {
        this.nameAndLastName = nameAndLastName;
    }

    public User(String username, String nameAndLastName, String password) {
        this.username = username;
        this.nameAndLastName = nameAndLastName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
