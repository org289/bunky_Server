package com.bunky.server.Service;

import com.bunky.server.Dao.LoginDao;
import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoginService {

    private LoginDao loginDao;

    @Autowired
    public LoginService(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    // USER

    public Integer createUser(String mail, String username) {
        return loginDao.createUser(mail, username);
    }

    public Integer getUserByMail(String mail) {
        return loginDao.getUserByMail(mail);
    }

    public List<User> getAllUsers() {
        return loginDao.getAllUsers();
    }


    // APARTMENT

    public UUID createApt(User user, String aptName) {
        return loginDao.createApt(user, aptName);
    }

    public void loginApt(UUID aptCode, User user) {
        loginDao.loginApt(aptCode, user);
    }

    public List<Apartment> getAllApt() {
        return loginDao.getAllApt();
    }

    public User getUserById(Integer userId) {
        return loginDao.getUserById(userId);
    }
}
