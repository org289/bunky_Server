package com.bunky.server.Dao;

import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static javax.swing.UIManager.put;

@Repository
public class LoginDao {
    private static final Map<String, User> users;
    private static final List<Apartment> apts;

    static {
        users = new HashMap<String, User>() {
            {
                put("u1", new User("u1", "Amy Parizada", "amy@gmail.com"));
                put("u2", new User("u2", "Or Gur", "or@gmail.com"));
                put("u3", new User("u3", "Yuval Cohen", "yuval@gmail.com"));
                put("u4", new User("u4", "Miriel Jerbi", "miriel@gmail.com"));
            }
        };

        apts = new ArrayList<>();
        apts.add(new Apartment("fun", "u1", "a1"));
    }

    public String createUser(String mail, String username) {
        // add new user to DB with this mail and username
        String id = "u5";
        users.put(id, new User(id, username, mail));
        return id;
    }

    public String getUserByMail(String mail) {
        return users.entrySet().stream()
                .filter(x -> mail.equals(x.getValue().getEmail()))
                .map(Entry::getKey).findFirst().orElse(null);
    }

    public String createApt(String userId, String aptName) {
        String id = "a2";
        apts.add(new Apartment(aptName, userId, id));
        return id;
    }

    /**
     * add new user to existing apartment
     * @param aptCode
     * @param userID
     */
    public void loginApt(String aptCode, String userID) {
        for (Apartment apartment : apts) {
            if (apartment.getId().equals(aptCode)){
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
