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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserAptDao {

    private final UserRepo userRepo;
    private final AptRepo aptRepo;

    @Autowired
    public UserAptDao(UserRepo userRepo, AptRepo aptRepo) {
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

    public Apartment aptByUser(User user) {
        return aptRepo.aptByUser(user);
    }

    // APARTMENTS

    public Integer createApt(User user, String aptName, Apartment.CurrencySymbol currency) {
        return aptRepo.save(new Apartment(aptName, user, currency)).getId();
    }

    public List<Apartment> getAllApt() {
        return new ArrayList<>(aptRepo.findAll());
    }


    public Apartment loginApt(Integer aptCode, User userID) {
        Apartment apartment = aptRepo.findOne(Example.of(new Apartment(aptCode, null, null))).orElse(null);
        if (apartment != null) {
            apartment.addUser(userID);
            aptRepo.save(apartment);
        }
        return apartment;
    }

    public User renameUser(User user, String newName) {
        if (user == null || newName == null || newName.equals("")) {
            return null;
        }
        User userFromDb = getUserById(user.getUserId());
        if (userFromDb == null) {
            return null;
        }
        userFromDb.setName(newName);
        return userRepo.save(userFromDb);
    }

    public List<String> getCurrencies() {
        return Stream.of(Apartment.CurrencySymbol.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

//    public String updateCurrencies() {
//        List<Apartment> apts = getAllApt();
//        int counter = 0;
//        for (Apartment apt : apts) {
//            apt.setCurrency(Apartment.CurrencySymbol.₪);
//            aptRepo.save(apt);
//            counter++;
//        }
//        return String.valueOf(counter);
//    }
}
