package com.bunky.server.DTO;

import com.bunky.server.Entity.User;

public class LoginUser {
    private User user;
    private boolean isMemberOfApt;

    public LoginUser(User user, boolean isMemberOfApt) {
        this.user = user;
        this.isMemberOfApt = isMemberOfApt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isMemberOfApt() {
        return isMemberOfApt;
    }

    public void setMemberOfApt(boolean memberOfApt) {
        isMemberOfApt = memberOfApt;
    }
}
