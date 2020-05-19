package com.bunky.server.Controller;

import com.bunky.server.DTO.NewDuty;
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
    public Duty addDuty(@RequestBody NewDuty newDuty) {
        Duty duty = dutyService.addDuty(newDuty.getFrequency(), newDuty.getTitle(), newDuty.getParticipants());
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
    public Duty aptDuties(@RequestBody Duty duty) {
        Duty updatedDuty = dutyService.updateNextShift(duty);
        return updatedDuty;
    }
}
