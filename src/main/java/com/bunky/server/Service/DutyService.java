package com.bunky.server.Service;

import com.bunky.server.Dao.DutyDao;
import com.bunky.server.Entity.Duty;
import com.bunky.server.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DutyService {

    private DutyDao dutyDao;

    @Autowired
    public DutyService(DutyDao dutyDao) {
        this.dutyDao = dutyDao;
    }

    public Duty addDuty(User user, Duty.DutyFrequency frequency, String title, List<User> participants) {
        return dutyDao.addDuty(new Duty(title, participants, frequency));
    }

    // List<Shift> getShiftsByDate(User user, LocalDate untilDate)
    // get User's duties by parameter. needs "shift" entity contains the next shift for every task
    // this function will computes all next shifts for the requester(user) using the current shift

    // TODO: create entity "shiftReplacement" - needs to take into consideration when calculating next shifts
}
