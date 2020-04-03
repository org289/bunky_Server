package com.bunky.server.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "apartments")
public class Apartment {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private List<Integer> users;

    public Apartment(String name, Integer userAdmin) {
        this.name = name;
        this.users = new ArrayList<Integer>();
        this.users.add(userAdmin);
    }

    public Apartment() {
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void addUser(Integer userId){
        users.add(userId);
    }
}
