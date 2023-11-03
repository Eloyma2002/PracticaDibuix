package com.esliceu.PracticaDibuix.Services;

import com.esliceu.PracticaDibuix.DAO.UserDAO;
import com.esliceu.PracticaDibuix.DAO.UserDAOImpl;
import com.esliceu.PracticaDibuix.Exceprions.UserDoesntExist;
import com.esliceu.PracticaDibuix.Exceprions.UserExist;
import com.esliceu.PracticaDibuix.Model.User;
import org.apache.commons.codec.digest.DigestUtils;

public class UserServices {

    public void register(User user) throws UserExist {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()).toUpperCase());
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.saveUser(user);
    }

    public User login(String userName, String password) throws UserDoesntExist {
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.getUser(userName, DigestUtils.md5Hex(password).toUpperCase());
        return user;
    }

}
