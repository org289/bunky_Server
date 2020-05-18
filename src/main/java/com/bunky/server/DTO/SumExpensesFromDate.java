package com.bunky.server.DTO;

import com.bunky.server.Entity.User;

import java.time.LocalDate;

public class SumExpensesFromDate {
    User user;
    LocalDate fromDate;
    LocalDate toDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }
}
