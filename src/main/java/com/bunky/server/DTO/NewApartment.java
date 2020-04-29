package com.bunky.server.DTO;

import com.bunky.server.Entity.User;

public class NewApartment {
    private User user;
    private String aptName;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAptName(String aptName) {
        this.aptName = aptName;
    }

    public String getAptName() {
        return aptName;
    }
}
