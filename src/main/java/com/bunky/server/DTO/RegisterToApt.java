package com.bunky.server.DTO;

import com.bunky.server.Entity.User;

public class RegisterToApt {
    Integer aptCode;
    User user;

    public Integer getAptCode() {
        return aptCode;
    }

    public void setAptCode(Integer aptCode) {
        this.aptCode = aptCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
