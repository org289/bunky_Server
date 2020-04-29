package com.bunky.server.DTO;

import com.bunky.server.Entity.User;

import java.util.UUID;

public class RegisterToApt {
    UUID aptCode;
    User user;

    public UUID getAptCode() {
        return aptCode;
    }

    public void setAptCode(UUID aptCode) {
        this.aptCode = aptCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
