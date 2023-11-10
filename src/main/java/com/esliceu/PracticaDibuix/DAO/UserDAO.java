package com.esliceu.PracticaDibuix.DAO;

import com.esliceu.PracticaDibuix.Exceprions.UserDoesntExist;
import com.esliceu.PracticaDibuix.Exceprions.UserExist;
import com.esliceu.PracticaDibuix.Model.User;

public interface UserDAO {

    void saveUser (User user) throws UserExist;

    User getUser (String userName, String password) throws UserDoesntExist;

    void añadeAdmin();
}
