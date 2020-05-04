package com.bunky.server.Dao;

import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import com.bunky.server.repository.AptRepo;
import com.bunky.server.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginDao {

    private final UserRepo userRepo;
    private final AptRepo aptRepo;

    @Autowired
    public LoginDao(UserRepo userRepo, AptRepo aptRepo) {
        this.userRepo = userRepo;
        this.aptRepo = aptRepo;
    }

    // USERS

    public User createUser(String mail, String username) {
        return userRepo.save(new User(username, mail));
    }

    public User getUserByMail(String mail) {
        return userRepo.findOne(Example.of(new User(null, mail))).orElse(null);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userRepo.findAll());
    }

    public User getUserById(Integer userId) {
        return userRepo.findById(userId).orElse(null);
    }

    public Apartment aptByUser(User user){
        return aptRepo.aptByUser(user);
    }

    // APARTMENTS

    public Integer createApt(User user, String aptName) {
        return aptRepo.save(new Apartment(aptName, user)).getId();
    }

    public List<Apartment> getAllApt() {
        return new ArrayList<>(aptRepo.findAll());
    }

    /**
     * add new user to existing apartment
     */
    public void loginApt(Integer aptCode, User userID) {
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
