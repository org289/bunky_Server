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
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "apartments")
public class Apartment {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID aptId;

    @OneToMany
    @JoinColumn(name = "apt_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(name, apartment.name) &&
                Objects.equals(aptId, apartment.aptId) &&
                Objects.equals(users, apartment.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, aptId, users);
    }
}
