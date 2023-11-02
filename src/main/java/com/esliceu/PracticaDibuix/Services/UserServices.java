package com.esliceu.PracticaDibuix.Services;

import com.esliceu.PracticaDibuix.DAO.UserDAO;
import com.esliceu.PracticaDibuix.DAO.UserDAOImpl;
import com.esliceu.PracticaDibuix.Model.User;

public class UserServices {

    public void register(User usuari) {

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.saveUser(usuari);
    }
}
