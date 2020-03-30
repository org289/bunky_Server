package com.bunky.server.Entity;

import java.util.ArrayList;
import java.util.List;

public class Apartment {
    private final String name;
    private final int id;
    private final List<Integer> users;

    public Apartment(String name, int userAdmin, int id) {
        this.name = name;
        this.users = new ArrayList<>(userAdmin);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void addUser(int userId){
        users.add(userId);
    }
}
