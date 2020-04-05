package com.bunky.server.DTO;

import java.util.UUID;

public class RegisterToApt {
    UUID aptCode;
    Integer userId;

    public UUID getAptCode() {
        return aptCode;
    }

    public Integer getUserId() {
        return userId;
    }
}
