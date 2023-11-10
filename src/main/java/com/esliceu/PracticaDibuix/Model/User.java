package com.esliceu.PracticaDibuix.Model;

public class User {

    private String userName;
    private String nameAndLastName;
    private String password;



    public String getNameAndLastName() {
        return nameAndLastName;
    }

    public void setNameAndLastName(String nameAndLastName) {
        this.nameAndLastName = nameAndLastName;
    }

    public User(String userName, String nameAndLastName, String password) {
        this.userName = userName;
        this.nameAndLastName = nameAndLastName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
