package com.bunky.server.Controller;

import com.bunky.server.DTO.LoginUser;
import com.bunky.server.DTO.NewApartment;
import com.bunky.server.DTO.RegisterToApt;
import com.bunky.server.DTO.RenameData;
import com.bunky.server.Dao.UserAptDao;
import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAptController {

    private final UserAptDao userAptDao;

    @Autowired
    public UserAptController(UserAptDao userAptDao) {
        this.userAptDao = userAptDao;
    }

    // USER

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public User createUser(@RequestBody User newUser) { // TODO: test
        String newUserMail = newUser.getMail();
        if (userAptDao.getUserByMail(newUserMail) != null) {
            // can't create new user because this mail already exists in DB
            return null;
        }
        return userAptDao.createUser(newUserMail, newUser.getName());
    }

    @RequestMapping(value = "/renameUser", method = RequestMethod.PUT)
    public User renameUser(@RequestBody RenameData data) {
        return userAptDao.renameUser(data.getUser(), data.getNewName());
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public LoginUser loginUser(String mail) {
        User user = userAptDao.getUserByMail(mail);
        boolean isAMember = false;
        if (user != null) {
            // check if user is member of an apartment
            if (userAptDao.aptByUser(user) != null) {
                isAMember = true;
            }
            return new LoginUser(user, isAMember);
        }
        return null;
    }

    @RequestMapping(value = "/aptIdByUser", method = RequestMethod.GET)
    public Integer aptIdByUser(User user) {
        Apartment apt = userAptDao.aptByUser(user);
        if (apt != null) {
            return apt.getAptId();
        }
        return null;
    }

    @RequestMapping(value = "/allUsersOfAptByUser", method = RequestMethod.GET)
    public List<User> getAllUsersOfAptByUser(User user) {
        Apartment apartment = userAptDao.aptByUser(user);
        if (apartment != null) {
            return apartment.getUsers();
        } else {
            // this user doesnt have an apartment yet
            return null;
        }
    }

    // TODO: only for tests (delete)
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userAptDao.getAllUsers();
    }


    // APARTMENT
    @RequestMapping(value = "/newApt", method = RequestMethod.POST)
    public Integer createApartment(@RequestBody NewApartment newApartment) {
        return userAptDao.createApt(newApartment.getUser(), newApartment.getAptName());
    }

    @RequestMapping(value = "/loginApt", method = RequestMethod.PUT)
    public Integer loginApartment(@RequestBody RegisterToApt registerToApt) {
        Apartment apt = userAptDao.loginApt(registerToApt.getAptCode(), registerToApt.getUser());
        if (apt != null) {
            return apt.getAptId();
        }
        return null;
    }

    // TODO: only for tests (delete)
    @RequestMapping(value = "/apts", method = RequestMethod.GET)
    public List<Apartment> getAllApt() {
        return userAptDao.getAllApt();
    }

}
