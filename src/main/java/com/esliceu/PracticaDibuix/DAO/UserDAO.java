package com.esliceu.PracticaDibuix.DAO;

import com.esliceu.PracticaDibuix.Exceptions.UserDoesntExist;
import com.esliceu.PracticaDibuix.Exceptions.UserExist;
import com.esliceu.PracticaDibuix.Model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserDAO {

    void saveUser(User user) throws UserExist;

    User getUser(String userName, String password) throws UserDoesntExist;
}
