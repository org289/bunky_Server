package com.bunky.server.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "duties")
public class Duty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "duty_generator")
    @SequenceGenerator(name = "duty_generator", sequenceName = "duty_seq")
    private Integer dutyId;

    private String name;
    @ManyToMany
    private List<User> participants;
    private DutyFrequency frequency;

    //TODO: should include apt field?


    public Duty(String name, List<User> participants, DutyFrequency frequency) {
        this.name = name;
        this.participants = participants;
        this.frequency = frequency;
    }

    public Duty() {
    }

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer taskId) {
        this.dutyId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public DutyFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(DutyFrequency frequency) {
        this.frequency = frequency;
    }

    public enum DutyFrequency {
        DAILY(0), WEEKLY(1), MONTHLY(2);

        private int value;

        DutyFrequency(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    public class NextShift{
        private User executor;
        private LocalDate startDate;
        private LocalDate endDate;

        public NextShift(User executor, LocalDate startDate, LocalDate endDate) {
            this.executor = executor;
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }
}
