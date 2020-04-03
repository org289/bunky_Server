package com.bunky.server.Controller;

import com.bunky.server.DTO.NewApartment;
import com.bunky.server.DTO.NewUser;
import com.bunky.server.DTO.RegisterToApt;
import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import com.bunky.server.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    // USER

    /**
     * get details of new user and adds to DB
     *
     * @param newUser new
     * @return userId
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public Integer createUser(@RequestBody NewUser newUser) {
        return this.loginService.createUser(newUser.getMail(), newUser.getName());
    }

    // TODO: maybe loginUser should get user by id, and add a different method getting use by mail.
    @RequestMapping(value = "/loginUser/{mail}", method = RequestMethod.GET)
    public Integer loginUser(@PathVariable String mail) {
        return loginService.getUserByMail(mail);
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return loginService.getAllUsers();
    }


    // APARTMENT

    @RequestMapping(value = "/newApt", method = RequestMethod.POST)
    public Integer createApartment(@RequestBody NewApartment newApartment) {
        return this.loginService.createApt(newApartment.getUserId(), newApartment.getAptName());
    }

    @RequestMapping(value = "/loginApt", method = RequestMethod.PUT)
    public void loginApartment(@RequestBody RegisterToApt registerToApt) {
        this.loginService.loginApt(registerToApt.getAptCode(), registerToApt.getUserId());
    }

    @RequestMapping(value = "/apts", method = RequestMethod.GET)
    public List<Apartment> getAllApt() {
        return loginService.getAllApt();
    }
}
