package com.esliceu.PracticaDibuix.Services;

import com.esliceu.PracticaDibuix.DAO.UserDAO;
import com.esliceu.PracticaDibuix.DAO.UserDAOImpl;
import com.esliceu.PracticaDibuix.Exceptions.UserDoesntExist;
import com.esliceu.PracticaDibuix.Exceptions.UserExist;
import com.esliceu.PracticaDibuix.Model.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServices {

    public void register(User user) throws UserExist {
        // Hash de la contrasenya abans d'emmagatzemar-la a la llista d'usuaris
        user.setPassword(DigestUtils.md5Hex(user.getPassword()).toUpperCase());

        // Obtenir el DAO d'usuari i inserir el nou usuari
        UserDAO userDAO = new UserDAOImpl();
        userDAO.saveUser(user);
    }

    public User login(String userName, String password) throws UserDoesntExist {
        // Obtenir el DAO d'usuari i autenticar l'usuari
        UserDAO userDAO = new UserDAOImpl();
        if (password.length() < 5) {
            return null;
        }
        User user = userDAO.getUser(userName, DigestUtils.md5Hex(password).toUpperCase());
        return user;
    }
}
