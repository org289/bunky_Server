package com.bunky.server.Service;

import com.bunky.server.Dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private LoginDao loginDao;

    @Autowired
    public LoginService(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public Integer createUser(String mail, String username) {
        return loginDao.createUser(mail, username);
    }

    public String getUserByMail(String mail) {
        return loginDao.getUserByMail(mail);
    }

    public String createApt(String userId, String aptName) {
        return loginDao.createApt(userId, aptName);
    }

    public void loginApt(String aptCode, String userID) {
        loginDao.loginApt(aptCode, userID);
    }

    public List<Apartment> getAllApt() {
        return loginDao.getAllApt();
    }

}
