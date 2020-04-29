package com.bunky.server.DTO;

import com.bunky.server.Entity.User;

import java.time.LocalDate;

public class SumExpensesFromDate {
    User user;
    LocalDate date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
