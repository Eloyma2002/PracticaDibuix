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

    // Llista que emmagatzema els usuaris registrats com a base de dades
    static List<User> users = new ArrayList<>();

    @Override
    public void saveUser(User user) throws UserExist {
        // Verificar si l'usuari ja existeix abans de desar-lo
        for (User existingUser : users) {
            if (existingUser.getUsername().equalsIgnoreCase(user.getUsername())) {
                throw new UserExist();
            }
        }
        // Afegir el nou usuari a la llista
        users.add(user);
    }

    @Override
    public User getUser(String userName, String password) throws UserDoesntExist {
        // Buscar un usuari per nom d'usuari i contrasenya
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
