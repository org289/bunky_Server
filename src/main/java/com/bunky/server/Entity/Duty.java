package com.bunky.server.Entity;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
    @Embedded
    private NextShift nextShift;

    public Duty(String name, List<User> participants, DutyFrequency frequency) {
        this.name = name;
        this.participants = participants;
        this.frequency = frequency;
        LocalDate shiftStartDate = LocalDate.now();
        LocalDate shiftEndDate = LocalDate.now();
        if (this.frequency == DutyFrequency.DAILY) {
            shiftEndDate = shiftStartDate.plusDays(1);
        } else if (this.frequency == DutyFrequency.WEEKLY) {
            shiftEndDate = shiftStartDate.plusWeeks(1);
        } else {
            // MONTHLY
            shiftEndDate = shiftStartDate.plusMonths(1);
        }
        this.nextShift = new NextShift(participants.get(0), shiftStartDate, shiftEndDate);
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

    public NextShift getNextShift() {
        return nextShift;
    }

    public void setNextShift(NextShift nextShift) {
        this.nextShift = nextShift;
    }

    public enum DutyFrequency {
        DAILY(0), WEEKLY(1), MONTHLY(2);

        private final int value;

        DutyFrequency(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @Embeddable
    public static class NextShift {
        @OneToOne
        private User executor;
        private LocalDate startDate;
        private LocalDate endDate;

        public NextShift(User executor, LocalDate startDate, LocalDate endDate) {
            this.executor = executor;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public NextShift() {
        }

        public User getExecutor() {
            return executor;
        }

        public void setExecutor(User executor) {
            this.executor = executor;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }
    }
}
