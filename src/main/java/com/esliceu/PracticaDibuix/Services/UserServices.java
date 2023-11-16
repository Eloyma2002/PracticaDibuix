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
        // Hash de la contraseña antes de almacenarla en la lista de usuarios
        user.setPassword(DigestUtils.md5Hex(user.getPassword()).toUpperCase());

        // Obtener el DAO de usuario e insertar el nuevo usuario
        UserDAO userDAO = new UserDAOImpl();
        userDAO.saveUser(user);
    }

    public User login(String userName, String password) throws UserDoesntExist {
        // Obtener el DAO de usuario y autenticar al usuario
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUser(userName, DigestUtils.md5Hex(password).toUpperCase());
        return user;
    }

    public void securitySession(HttpServletRequest req, HttpServletResponse resp) {
        // Obtener el DAO de usuario y configurar la cookie de sesión
        UserDAO userDAO = new UserDAOImpl();
        userDAO.sessionCookie(req, resp);
    }
}
