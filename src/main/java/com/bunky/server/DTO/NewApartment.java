package com.bunky.server.DTO;

import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;

public class NewApartment {
    private User user;
    private String aptName;
    private Apartment.CurrencySymbol currency;

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

    public Apartment.CurrencySymbol getCurrency() {
        return currency;
    }

    public void setCurrency(Apartment.CurrencySymbol currency) {
        this.currency = currency;
    }
}
