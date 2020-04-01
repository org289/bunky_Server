package com.bunky.server.Entity;

import java.util.ArrayList;
import java.util.List;

public class Apartment {
    private final String name;
    private final String id;
    private final List<String> users;

    public Apartment(String name, String userAdmin, String id) {
        this.name = name;
        this.users = new ArrayList<String>();
        this.users.add(userAdmin);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<String> getUsers() {
        return users;
    }

    public void addUser(String userId){
        users.add(userId);
    }
}
