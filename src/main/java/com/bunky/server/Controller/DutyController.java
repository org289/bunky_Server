package com.bunky.server.Controller;

import com.bunky.server.Dao.UserAptDao;
import com.bunky.server.Entity.Duty;
import com.bunky.server.Entity.User;
import com.bunky.server.Service.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DutyController {
    private final DutyService dutyService;
    private final UserAptDao userAptDao;


    @Autowired
    public DutyController(DutyService dutyService, UserAptDao userAptDao) {
        this.dutyService = dutyService;
        this.userAptDao = userAptDao;
    }

    @RequestMapping(value = "/addDuty", method = RequestMethod.POST)
    public Duty addDuty(@RequestBody Duty newDuty) {
        Duty duty = dutyService.addDuty(newDuty.getFrequency(), newDuty.getName(), newDuty.getParticipants());
        return duty;
    }

    @RequestMapping(value = "/getAllAptDuties", method = RequestMethod.GET)
    public List<Duty> aptDuties(User user) {
        Integer aptId = userAptDao.aptByUser(user).getId();
        List<Duty> allAptDuties = dutyService.getAllAptDuties(aptId);
        return allAptDuties;
    }

    // TODO: only for tests. delete
    @RequestMapping(value = "/changeNextExecutor", method = RequestMethod.PUT)
    public Duty changeNextExecutor(@RequestBody Duty duty) {
        Duty updatedDuty = dutyService.updateNextShift(duty);
        return updatedDuty;
    }

    // TODO: only for tests. delete
    @RequestMapping(value = "/updateDuties", method = RequestMethod.PUT)
    public void update() {
        dutyService.updateShifts();
    }

    @RequestMapping(value = "/flipIsExecuted", method = RequestMethod.PUT)
    public Duty flipIsExecuted(@RequestBody Duty duty) {
        Duty updatedDuty = dutyService.flipIsExecuted(duty.getDutyId());
        return updatedDuty;
    }

    @RequestMapping(value = "/getMyDuties", method = RequestMethod.GET)
    public List<Duty> getAllByUser(User user){
        return dutyService.getAllByUser(user);
    }

    @RequestMapping(value = "/removeDuty", method = RequestMethod.PUT)
    public Integer removeDuty(@RequestBody Integer dutyId) {
        return dutyService.deleteDutyById(dutyId);
    }

}
