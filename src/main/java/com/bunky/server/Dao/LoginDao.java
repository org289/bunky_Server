package com.bunky.server.Dao;

import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import com.bunky.server.repository.AptRepo;
import com.bunky.server.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LoginDao {

    private UserRepo userRepo;
    private AptRepo aptRepo;

    @Autowired
    public LoginDao(UserRepo userRepo, AptRepo aptRepo) {
        this.userRepo = userRepo;
        this.aptRepo = aptRepo;
    }

    // USERS

    public Integer createUser(String mail, String username) {
        return userRepo.save(new User(username, mail)).getId();
    }

    public Integer getUserByMail(String mail) {
        // check how to get by mail, not id (primary-key).
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getMail().equals(mail)) {
                return user.getId();
            }
        }
        // no such user with that mail
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        // iterate all users, and add each user to list.
        userRepo.findAll().forEach(users::add);
        return users;
    }

    public User getUserById(Integer userId) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    // APARTMENTS

    public UUID createApt(User user, String aptName) {
        return aptRepo.save(new Apartment(aptName, user)).getId();
    }

    public List<Apartment> getAllApt() {
        List<Apartment> apartments = new ArrayList<>();
        // iterate all apts, and add each apt to list.
        aptRepo.findAll().forEach(apartments::add);
        return apartments;
    }

    /**
     * add new user to existing apartment
     */
    public void loginApt(UUID aptCode, User userID) {
        List<Apartment> apts = getAllApt();
        for (Apartment apartment : apts) {
            if (apartment.getId().equals(aptCode)) {
                apartment.addUser(userID);
                aptRepo.save(apartment);
                break;
            }
        }
    }


}
