package com.bunky.server.Service;

import com.bunky.server.Dao.UserDao;
import com.bunky.server.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Collection<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    public User getUserById(int id){
        return this.userDao.getUserById(id);
    }

    public void deleteUserById(int id) {
        this.userDao.deleteUserById(id);
    }

    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    public void insertUserById(User user) {
        userDao.insertUserById(user);
    }
}
