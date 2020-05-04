package com.bunky.server.Controller;

import com.bunky.server.DTO.NewApartment;
import com.bunky.server.DTO.NewUser;
import com.bunky.server.DTO.RegisterToApt;
import com.bunky.server.DTO.twoUsers;
import com.bunky.server.Dao.LoginDao;
import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    private final LoginDao loginDao;

    @Autowired
    public LoginController(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    // USER

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public User createUser(@RequestBody NewUser newUser) {
        String newUserMail = newUser.getMail();
        if (loginDao.getUserByMail(newUserMail) == null){
            // can't create new user because this mail already exists in DB
            // TODO: check what to throw
            return null;
        }
        return loginDao.createUser(newUserMail, newUser.getName());
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public User loginUser(String mail) {
        return loginDao.getUserByMail(mail);
    }

    @RequestMapping(value = "/aptByUser", method = RequestMethod.GET)
    public Apartment aptByUser(User user) {
        return loginDao.aptByUser(user);
    }

    // TODO: only for tests (delete)
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return loginDao.getAllUsers();
    }

    // TODO: only for tests (delete)
    @RequestMapping(value = "/testSendingUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody twoUsers newUser) {
        System.out.println("success!");
        System.out.println("user1: " + newUser.getUser1());
        System.out.println("user2: " + newUser.getUser2());
    }


    // APARTMENT

    @RequestMapping(value = "/newApt", method = RequestMethod.POST)
    public Integer createApartment(@RequestBody NewApartment newApartment) {
        return loginDao.createApt(newApartment.getUser(), newApartment.getAptName());
    }

    @RequestMapping(value = "/loginApt", method = RequestMethod.PUT)
    public void loginApartment(@RequestBody RegisterToApt registerToApt) {
        loginDao.loginApt(registerToApt.getAptCode(), registerToApt.getUser());
    }

    // TODO: only for tests (delete)
    @RequestMapping(value = "/apts", method = RequestMethod.GET)
    public List<Apartment> getAllApt() {
        return loginDao.getAllApt();
    }
}
