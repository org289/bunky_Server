package com.bunky.server.DTO;

import com.bunky.server.Entity.Duty;
import com.bunky.server.Entity.User;

import java.util.List;

public class NewDuty {
    private String title;
    private List<User> participants;
    private Duty.DutyFrequency frequency;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public Duty.DutyFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Duty.DutyFrequency frequency) {
        this.frequency = frequency;
    }
}
