package com.bunky.server.DTO;

import com.bunky.server.Entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NewExpense {
    private User user;
    private Integer categoryId;
    private String name;
    private LocalDate date;
    private BigDecimal amount;


    public Integer getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
