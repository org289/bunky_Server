package com.bunky.server.Service;

import com.bunky.server.Dao.UserAptDao;
import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAptService {

    private UserAptDao userAptDao;

    @Autowired
    public UserAptService(UserAptDao userAptDao) {
        this.userAptDao = userAptDao;
    }

    // USER

    public User createUser(String mail, String username) {
        return userAptDao.createUser(mail, username);
    }

    public User getUserByMail(String mail) {
        return userAptDao.getUserByMail(mail);
    }

    public List<User> getAllUsers() {
        return userAptDao.getAllUsers();
    }


    // APARTMENT

    public Integer createApt(User user, String aptName) {
        return userAptDao.createApt(user, aptName);
    }

    public void loginApt(Integer aptCode, User user) {
        userAptDao.loginApt(aptCode, user);
    }

    public List<Apartment> getAllApt() {
        return userAptDao.getAllApt();
    }

    public User getUserById(Integer userId) {
        return userAptDao.getUserById(userId);
    }
}
