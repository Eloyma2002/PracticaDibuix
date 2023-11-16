package com.esliceu.PracticaDibuix.DAO;

import com.esliceu.PracticaDibuix.Exceptions.UserDoesntExist;
import com.esliceu.PracticaDibuix.Exceptions.UserExist;
import com.esliceu.PracticaDibuix.Model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    // Lista que almacena los usuarios registrados a modo de base de datos
    static List<User> users = new ArrayList<>();

    @Override
    public void saveUser(User user) throws UserExist {
        // Verificar si el usuario ya existe antes de guardarlo
        for (User existingUser : users) {
            if (existingUser.getUsername().equalsIgnoreCase(user.getUsername())) {
                throw new UserExist();
            }
        }
        // Agregar el nuevo usuario a la lista
        users.add(user);
    }

    @Override
    public User getUser(String userName, String password) throws UserDoesntExist {
        // Buscar un usuario por nombre de usuario y contraseña

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void sessionCookie(HttpServletRequest req, HttpServletResponse resp) {
        // Obtener la sesión actual de la solicitud
        HttpSession session = req.getSession(false);

        // Verificar si la sesión existe antes de configurar la cookie de sesión
        if (session != null) {
            // Configurar la cookie de sesión con características de seguridad
            Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
            sessionCookie.setSecure(true);
            sessionCookie.setHttpOnly(true);
            resp.addCookie(sessionCookie);
        }
    }
}
