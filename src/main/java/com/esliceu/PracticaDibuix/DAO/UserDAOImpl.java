package com.esliceu.PracticaDibuix.DAO;

import com.esliceu.PracticaDibuix.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    static List<User> usuaris = new ArrayList<>();

    @Override
    public void saveUser(User usuari) {

        for (User user : usuaris) {
            if (user.getNomUsuari().equalsIgnoreCase(usuari.getNomUsuari())) {
                return;
            }
        }
        usuaris.add(usuari);
        System.out.println("Lista: " + usuaris.size());
    }
}
