package com.bunky.server.DTO;

import com.bunky.server.Entity.User;

public class RenameData {
    private User user;
    private String newName;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
