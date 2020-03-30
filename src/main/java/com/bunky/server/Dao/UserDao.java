package com.bunky.server.Dao;

import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static javax.swing.UIManager.put;

@Repository
public class UserDao {
    private static final Map<Integer, User> users;
    private static final List<Apartment> apts;
    public static final int NOT_FOUND = -1;

    static {
        users = new HashMap<Integer, User>() {
            {
                put(1, new User(1, "Amy Parizada", "amy@gmail.com"));
                put(2, new User(2, "Or Gur", "or@gmail.com"));
                put(3, new User(3, "Yuval Cohen", "yuval@gmail.com"));
                put(4, new User(4, "Miriel Jerbi", "miriel@gmail.com"));
            }
        };

        apts = new ArrayList<>();
        apts.add(new Apartment("fun", 1, 1));
    }

    public int createUser(String mail, String username) {
        // add new user to DB with this mail and username
        int id = 5;
        users.put(id, new User(id, username, mail));
        return id;
    }

    public int getUserByMail(String mail) {
        return users.entrySet().stream()
                .filter(x -> mail.equals(x.getValue().getEmail()))
                .map(Entry::getKey).findFirst().orElse(NOT_FOUND);
    }

    public int createApt(int userId, String aptName) {
        int id = 2;
        apts.add(new Apartment(aptName, userId, id));
        return id;
    }

    /**
     * add new user to existing apartment
     * @param aptCode
     * @param userID
     */
    public void loginApt(int aptCode, int userID) {
        for (Apartment apartment : apts) {
            if (apartment.getId() == aptCode){
                apartment.addUser(userID);
                break;
            }
        }
    }

//    public Collection<User> getAllUsers() {
//        return users.values();
//    }

//    public User getUserById(int id) {
//        return users.get(id);
//    }

//    public void deleteUserById(int id) {
//        users.remove(id);
//    }
//
//    public void updateUser(User user) {
//        User updatedUser = users.get((user.getId()));
//        updatedUser.setName(user.getName());
//        updatedUser.setEmail(user.getEmail());
//        users.put((updatedUser.getId()), updatedUser);
//    }
//
//    public void insertUserById(User user) {
//        users.put(user.getId(), user);
//    }


}
