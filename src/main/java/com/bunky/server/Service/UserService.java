package com.bunky.server.Service;

import com.bunky.server.Dao.UserDao;
import com.bunky.server.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public int createUser(String mail, String username){
        return userDao.createUser(mail, username);
    }

    public int getUserByMail(String mail) {
        return userDao.getUserByMail(mail);
    }

    public int createApt(int userId, String aptName) {
        return userDao.createApt(userId, aptName);
    }

    public void loginApt(int aptCode, int userID){
        userDao.loginApt( aptCode,  userID);
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
