package com.bunky.server.Service;

import com.bunky.server.Dao.DutyDao;
import com.bunky.server.Entity.Duty;
import com.bunky.server.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DutyService {

    private DutyDao dutyDao;

    @Autowired
    public DutyService(DutyDao dutyDao) {
        this.dutyDao = dutyDao;
    }

    public Duty addDuty(Duty.DutyFrequency frequency, String title, List<User> participants) {
        return dutyDao.addDuty(new Duty(title, participants, frequency));
    }

    public List<Duty> getAllAptDuties(Integer aptId) {
        return dutyDao.getAllAptDuties(aptId);
    }

    // check if the next shift in given duty needs to be updated and update
    public Duty updateNextShift(Duty duty) { //TODO: can get only dutyId
        Duty dutyFromDb = dutyDao.getDutyById(duty.getDutyId());
        if (dutyFromDb == null || dutyFromDb.getNextShift() == null) {
            return null;
        }

        Duty.NextShift newNextShift = dutyFromDb.getNextShift();
        // advance next shift by one until the ending date is bigger from today (or equal).
        while (newNextShift.getEndDate().compareTo(LocalDate.now()) < 0) {
            newNextShift = calcNextShift(dutyFromDb);
        }
        dutyFromDb.setNextShift(newNextShift);
        return dutyDao.updateNextShift(dutyFromDb);
    }

    private Duty.NextShift calcNextShift(Duty duty) {
        Duty.NextShift next = duty.getNextShift();
        LocalDate currentStartDate = next.getStartDate();
        LocalDate currentEndDate = next.getEndDate();
        switch (duty.getFrequency()) {
            case DAILY:
                next.setStartDate(currentStartDate.plusDays(1));
                next.setEndDate(currentEndDate.plusDays(1));
                break;
            case WEEKLY:
                next.setStartDate(currentStartDate.plusWeeks(1));
                next.setEndDate(currentEndDate.plusWeeks(1));
                break;
            case MONTHLY:
                next.setStartDate(currentStartDate.plusMonths(1));
                next.setEndDate(currentEndDate.plusMonths(1));
                break;
        }
        int currentExecutor = duty.getParticipants().indexOf(next.getExecutor());
        int nextExecutor = (currentExecutor + 1) % duty.getParticipants().size();
        next.setExecutor(duty.getParticipants().get(nextExecutor));
        return next;
    }


    // List<Shift> getShiftsByDate(User user, LocalDate untilDate)
    // get User's duties by parameter. needs "shift" entity contains the next shift for every task
    // this function will computes all next shifts for the requester(user) using the current shift

    // TODO: create entity "shiftReplacement" - needs to take into consideration when calculating next shifts
}
