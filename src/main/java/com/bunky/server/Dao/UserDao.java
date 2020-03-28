package com.bunky.server.Dao;

import com.bunky.server.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

@Repository
public class UserDao {
    private static Map<Integer, User> users;

    static {
        users = new HashMap<Integer, User>() {
            {
                put(1, new User(1, "Amy Parizada", "amy@gmail.com"));
                put(2, new User(2, "Or Gut", "or@gmail.com"));
                put(3, new User(3, "Yuval Cohen", "yuval@gmail.com"));
                put(4, new User(4, "Miriel Jerbi", "miriel@gmail.com"));
            }
        };
    }

    public Collection<User> getAllUsers() {
        return this.users.values();
    }

    public User getUserById(int id) {
        return this.users.get(id);
    }

    public void deleteUserById(int id) {
        this.users.remove(id);
    }

    public void updateUser(User user) {
        User updatedUser = users.get((user.getId()));
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        users.put((updatedUser.getId()), updatedUser);
    }

    public void insertUserById(User user) {
        this.users.put(user.getId(), user);
    }
}
