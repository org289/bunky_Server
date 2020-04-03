package com.bunky.server.Dao;

import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import com.bunky.server.repository.AptRepo;
import com.bunky.server.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginDao {

    private UserRepo userRepo;
    private AptRepo aptRepo;

    @Autowired
    public LoginDao(UserRepo userRepo, AptRepo aptRepo) {
        this.userRepo = userRepo;
        this.aptRepo = aptRepo;
    }

    public Integer createUser(String mail, String username) {
        return userRepo.save(new User(username, mail)).getId();
    }


    public String getUserByMail(String mail) {

    }
//        return users.entrySet().stream()
//                .filter(x -> mail.equals(x.getValue().getEmail()))
//                .map(Entry::getKey).findFirst().orElse(null);
//    }

    public Integer createApt(Integer userId, String aptName) {
        return aptRepo.save(new Apartment(aptName, userId)).getId();
    }

    public List<Apartment> getAllApt() {
        aptRepo.findAll();
    }


    /**
     * add new user to existing apartment
     *
     * @param aptCode
     * @param userID
     */
    public void loginApt(String aptCode, String userID) {
        for (Apartment apartment : apts) {
            if (apartment.getId().equals(aptCode)) {
                apartment.addUser(userID);
                break;
            }
        }
    }
