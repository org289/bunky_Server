package com.bunky.server.Controller;

import com.bunky.server.DTO.NewApartment;
import com.bunky.server.DTO.NewUser;
import com.bunky.server.DTO.RegisterToApt;
import com.bunky.server.Dao.LoginDao;
import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class LoginController {

    private final LoginDao loginDao;

    public LoginController(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    // USER


    /**
     * get details of new user and adds to DB
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public Integer createUser(@RequestBody NewUser newUser) {
        return this.loginDao.createUser(newUser.getMail(), newUser.getName());
    }

    @RequestMapping(value = "/loginUser/{mail}", method = RequestMethod.GET)
    public Integer loginUser(@PathVariable String mail) {
        return loginDao.getUserByMail(mail);
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return loginDao.getAllUsers();
    }


    @RequestMapping(value = "/aptByUser", method = RequestMethod.GET)
    public Apartment aptByUser(Integer userId) {
        return loginDao.aptByUser(userId);
    }


    // APARTMENT

    @RequestMapping(value = "/newApt", method = RequestMethod.POST)
    public UUID createApartment(@RequestBody NewApartment newApartment) {
        User user = loginDao.getUserById(newApartment.getUserId());
        return this.loginDao.createApt(user, newApartment.getAptName());
    }

    @RequestMapping(value = "/loginApt", method = RequestMethod.PUT)
    public void loginApartment(@RequestBody RegisterToApt registerToApt) {
        User user = loginDao.getUserById(registerToApt.getUserId());
        this.loginDao.loginApt(registerToApt.getAptCode(), user);
    }

    @RequestMapping(value = "/apts", method = RequestMethod.GET)
    public List<Apartment> getAllApt() {
        return loginDao.getAllApt();
    }
}
