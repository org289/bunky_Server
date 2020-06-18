package com.bunky.server.Service;

import com.bunky.server.Dao.DutyDao;
import com.bunky.server.Entity.Duty;
import com.bunky.server.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
        if (name == null || name.isEmpty() || participants == null || participants.size() == 0 || frequency == null) {
            // the duty requested is not valid
            return null;
        } // else, insert new duty to DB
        return dutyDao.addDuty(new Duty(name, participants, frequency));
    }

    public List<Duty> getAllAptDuties(Integer aptId) {
        return dutyDao.getAllAptDuties(aptId);
    }

    @Scheduled(cron = "* 0/30 * * * *")
    // this function will update duties' shifts every day at 00:00.
    public void updateShifts() {
        System.out.println("inside scheduled task"); // TODO: delete comment
        List<Duty> allDuties = dutyDao.getAll();
        for (Duty duty : allDuties) {
//            System.out.println(duty.getName()); // TODO: delete comment
            updateNextShift(duty);
        }
    }

    public Duty updateNextShift(Duty dutyFromDb) {
        // check if the next shift in given duty needs to be updated and update
        // first, if NULL, something is not right - return
        if (dutyFromDb == null || dutyFromDb.getShift() == null) {
            return null;
        }

        Duty.Shift newNextShift = dutyFromDb.getShift();
        // advance next shift by one until the ending date is bigger from today (or equal).
        while (newNextShift.getEndDate().compareTo(LocalDate.now()) < 0) {
//            System.out.println("updating shift"); // TODO: delete comment
            newNextShift = calcNextShift(dutyFromDb);
        }
        newNextShift.setExecuted(false);
        dutyFromDb.setShift(newNextShift);
        return dutyDao.updateNextShift(dutyFromDb);
    }

    private Duty.Shift calcNextShift(Duty duty) {
        Duty.Shift next = duty.getShift();
        List<User> participants = duty.getParticipants();
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
        int currentExecutor = participants.indexOf(next.getExecutor());
        int nextExecutor = (currentExecutor + 1) % participants.size();
        next.setExecutor(participants.get(nextExecutor));
        return next;
    }

    public Duty flipIsExecuted(Integer dutyId) {
        Duty dutyFromDb = dutyDao.getDutyById(dutyId);
        if (dutyFromDb != null) {
            dutyFromDb.getShift().flipExecuted();
            dutyFromDb = dutyDao.updateDuty(dutyFromDb);
        }
        return dutyFromDb;
    }

    public List<Duty> getAllByUser(User user) {
        return dutyDao.getAllByUser(user);
    }

    public Integer deleteDutyById(Integer dutyId) {
        Duty duty = dutyDao.getDutyById(dutyId);
        if (duty != null) {
            dutyDao.deleteDuty(duty);
            return duty.getDutyId();
        }
        return null;
    }


// List<Shift> getShiftsByDate(User user, LocalDate untilDate)
// get User's duties by parameter. needs "shift" entity contains the next shift for every task
// this function will computes all next shifts for the requester(user) using the current shift

// TODO: create entity "shiftReplacement" - needs to take into consideration when calculating next shifts
}
