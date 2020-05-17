package com.bunky.server.Controller;

import com.bunky.server.DTO.NewDuty;
import com.bunky.server.Dao.DutyDao;
import com.bunky.server.Dao.UserAptDao;
import com.bunky.server.Entity.Duty;
import com.bunky.server.Entity.Expense;
import com.bunky.server.Entity.User;
import com.bunky.server.Service.DutyService;
import com.bunky.server.repository.DutyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DutyController {
    private final DutyService dutyService;
    private final DutyDao dutyDao;
    private DutyRepo dutyRepo;
    private final UserAptDao userAptDao;


    @Autowired
    public DutyController(DutyService dutyService, DutyDao dutyDao, DutyRepo dutyRepo, UserAptDao userAptDao) {
        this.dutyService = dutyService;
        this.dutyDao = dutyDao;
        this.dutyRepo = dutyRepo;
        this.userAptDao = userAptDao;


    }

    @RequestMapping(value = "/addDuty", method = RequestMethod.POST)
    public Duty addDuty(@RequestBody NewDuty newDuty) {
        return dutyService.addDuty(newDuty.getFrequency(), newDuty.getTitle(), newDuty.getParticipants());
    }

    @RequestMapping(value = "/getAllAptDuties", method = RequestMethod.GET)
    public List<Duty> aptDuties(User user) {
        Integer aptId = userAptDao.aptByUser(user).getId();
        return dutyService.getAllAptDuties(aptId);
    }
}
