package com.bunky.server.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "apartments")
public class Apartment {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apt_generator")
    @SequenceGenerator(name = "apt_generator", sequenceName = "apt_seq", initialValue = 10000)
    private Integer aptId;

    @OneToMany
    @JoinColumn(name = "apt_id")
    private List<User> users;

    private CurrencySymbol currency;

    public Apartment(String name, User userAdmin) {
        this.name = name;
        this.users = new ArrayList<>();
        this.users.add(userAdmin);
    }

    public Apartment(Integer aptId, String name, User userAdmin) {
        this(name, userAdmin);
        this.aptId = aptId;
    }

    public Apartment(String name, User userAdmin, CurrencySymbol currency) {
        this(name, userAdmin);
        this.currency = currency;
    }

    public Apartment() {
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return aptId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Integer getAptId() {
        return aptId;
    }

    public void setAptId(Integer aptId) {
        this.aptId = aptId;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public CurrencySymbol getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencySymbol currency) {
        this.currency = currency;
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

    public enum CurrencySymbol {
        ₪(0), $(1), €(2), £(3), ¥(4);

        private final int value;

        CurrencySymbol(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
