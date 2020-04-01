package com.bunky.server.Controller;

import com.bunky.server.DTO.NewApartment;
import com.bunky.server.DTO.NewUser;
import com.bunky.server.DTO.RegisterToApt;
import com.bunky.server.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService userService;

    /**
     * get details of new user and adds to DB
     *
     * @param newUser new
     * @return userId
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@RequestBody NewUser newUser) {
        return this.userService.createUser(newUser.getEmail(), newUser.getName());
    }

    @RequestMapping(value = "/loginUser/{mail}", method = RequestMethod.GET)
    public String loginUser(@PathVariable String mail) {
        return userService.getUserByMail(mail);
    }

    @RequestMapping(value = "/newApt", method = RequestMethod.POST)
    public String createApartment(@RequestBody NewApartment newApartment) {
        return this.userService.createApt(newApartment.getUserId(), newApartment.getAptName());
    }

    @RequestMapping(value = "/loginApt", method = RequestMethod.PUT)
    public void loginApartment(@RequestBody RegisterToApt registerToApt) {
        this.userService.loginApt(registerToApt.getAptCode(), registerToApt.getUserID());
    }
}
