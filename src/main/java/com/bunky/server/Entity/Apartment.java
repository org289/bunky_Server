package com.bunky.server.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "apartments")
public class Apartment {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID aptId;

    @OneToMany
    @JoinColumn(name = "aptId")
    private List<User> users;

    public Apartment(String name, User userAdmin) {
        this.name = name;
        this.users = new ArrayList<>();
        this.users.add(userAdmin);
    }

    public Apartment() {
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return aptId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }
}
