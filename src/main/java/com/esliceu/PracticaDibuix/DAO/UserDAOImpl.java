package com.esliceu.PracticaDibuix.DAO;

import com.esliceu.PracticaDibuix.Exceprions.UserDoesntExist;
import com.esliceu.PracticaDibuix.Exceprions.UserExist;
import com.esliceu.PracticaDibuix.Model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    static List<User> users = new ArrayList<>();

    @Override
    public void saveUser(User user1) throws UserExist {

        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(user1.getUserName())) {
                throw new UserExist();
            }
        }
        users.add(user1);
        System.out.println(users.get(users.size()-1).getUserName());
    }

    @Override
    public User getUser(String userName, String password) throws UserDoesntExist {

        for (User user: users) {
            if (user.getUserName().equalsIgnoreCase(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void añadeAdmin() {
        User usere = new User("admin", "", DigestUtils.md5Hex("123").toUpperCase());
        users.add(usere);
    }

}
