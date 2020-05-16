package com.bunky.server.Controller;

import com.bunky.server.DTO.NewDuty;
import com.bunky.server.Dao.DutyDao;
import com.bunky.server.Entity.Duty;
import com.bunky.server.Service.DutyService;
import com.bunky.server.repository.DutyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DutyController {
    private final DutyService dutyService;
    private final DutyDao dutyDao;
    private DutyRepo dutyRepo;

    @Autowired
    public DutyController(DutyService dutyService, DutyDao dutyDao,DutyRepo dutyRepo) {
        this.dutyService = dutyService;
        this.dutyDao = dutyDao;
        this.dutyRepo=dutyRepo;

    }

    @RequestMapping(value = "/addDuty", method = RequestMethod.POST)
    public Duty addDuty(@RequestBody NewDuty newDuty) {
        return dutyService.addDuty(newDuty.getUser(), newDuty.getFrequency(), newDuty.getTitle(),newDuty.getParticipants());
    }
}
