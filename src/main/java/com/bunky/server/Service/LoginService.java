package com.bunky.server.Service;

import com.bunky.server.Dao.LoginDao;
import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Integer createApt(Integer userId, String aptName) {
        return loginDao.createApt(userId, aptName);
    }

    public void loginApt(Integer aptCode, Integer userID) {
        loginDao.loginApt(aptCode, userID);
    }

    public List<Apartment> getAllApt() {
        return loginDao.getAllApt();
    }

}
