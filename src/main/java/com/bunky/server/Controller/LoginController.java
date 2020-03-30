package com.bunky.server.Controller;

import com.bunky.server.Entity.User;
import com.bunky.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.Collection;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * creates new user in DB  -- post users/createUser [[[[ details ]]]]
     *
     * @param mail     s
     * @param username s
     * @return user id
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public int createUser(@RequestBody String mail, @RequestBody String username) {
        return this.userService.createUser(mail, username);
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public int loginUser(String mail) {
        return userService.getUserByMail(mail);
    }

    @RequestMapping(value = "/newApt", method = RequestMethod.POST)
    public int createApartment(@RequestBody int userId, @RequestBody String aptName) {
        return this.userService.createApt(userId, aptName);
    }

    @RequestMapping(value = "/loginApt", method = RequestMethod.PUT)
    public void loginApartment(@RequestBody int aptCode, @RequestBody int userID) {
        this.userService.loginApt(aptCode, userID);
    }


//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public User getUserById(@PathVariable("id") int id){
//        return this.userService.getUserById(id);
//    }


//    @RequestMapping(method = RequestMethod.GET)
//    public Collection<User> getAllUsers() {
//        return this.userService.getAllUsers();
//    }


//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void deleteUserById(@PathVariable("id") int id){
//        this.userService.deleteUserById(id);
//    }

//    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void updateUser(@RequestBody User user) {
//        this.userService.updateUser(user);
//    }


}
