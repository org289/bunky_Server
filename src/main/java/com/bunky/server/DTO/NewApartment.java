package com.bunky.server.DTO;

import com.bunky.server.Entity.User;

public class NewApartment {
    private Integer userId;
    private String aptName;

    public Integer getUserId() {
        return userId;
    }

    public String getAptName() {
        return aptName;
    }
}
