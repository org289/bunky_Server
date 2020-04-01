package com.bunky.server.Service;

import com.bunky.server.Dao.LoginDao;
import com.bunky.server.Entity.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    public String createUser(String mail, String username) {
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


//    public Collection<User> getAllUsers() {
//        return this.userDao.getAllUsers();
//    }

//    public User getUserById(int id){
//        return this.userDao.getUserById(id);
//    }

//    public void deleteUserById(int id) {
//        this.userDao.deleteUserById(id);
//    }

//    public void updateUser(User user) {
//        this.userDao.updateUser(user);
//    }

//    public void insertUserById(User user) {
//        userDao.insertUserById(user);
//    }


}
