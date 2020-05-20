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

    public Duty addDuty(Duty.DutyFrequency frequency, String name, List<User> participants) {
        if (name == null || name.isEmpty() || participants == null || participants.size() == 0 || frequency == null){
            // the duty requested is not valid
            return null;
        } // else, insert new duty to DB
        return dutyDao.addDuty(new Duty(name, participants, frequency));
    }

    public List<Duty> getAllAptDuties(Integer aptId) {
        return dutyDao.getAllAptDuties(aptId);
    }

    // check if the next shift in given duty needs to be updated and update
    public Duty updateNextShift(Duty duty) { //TODO: can get only dutyId
        Duty dutyFromDb = dutyDao.getDutyById(duty.getDutyId());
        if (dutyFromDb == null || dutyFromDb.getShift() == null) {
            return null;
        }

        Duty.Shift newNextShift = dutyFromDb.getShift();
        // advance next shift by one until the ending date is bigger from today (or equal).
        while (newNextShift.getEndDate().compareTo(LocalDate.now()) < 0) {
            newNextShift = calcNextShift(dutyFromDb);
        }
        dutyFromDb.setShift(newNextShift);
        return dutyDao.updateNextShift(dutyFromDb);
    }

    private Duty.Shift calcNextShift(Duty duty) {
        Duty.Shift next = duty.getShift();
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

    public Duty setDutyAsExecuted(Integer dutyId) {
        Duty dutyFromDb = dutyDao.getDutyById(dutyId);
        if (dutyFromDb != null) {
            dutyFromDb.getShift().setExecuted(true);
        }
        return dutyFromDb;
    }


    // List<Shift> getShiftsByDate(User user, LocalDate untilDate)
    // get User's duties by parameter. needs "shift" entity contains the next shift for every task
    // this function will computes all next shifts for the requester(user) using the current shift

    // TODO: create entity "shiftReplacement" - needs to take into consideration when calculating next shifts
}
