package com.bunky.server.Dao;

import com.bunky.server.Entity.Duty;
import com.bunky.server.Entity.User;
import com.bunky.server.repository.DutyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DutyDao {
    private DutyRepo dutyRepo;

    @Autowired
    public DutyDao(DutyRepo dutyRepo) {
        this.dutyRepo = dutyRepo;
    }

    public Duty addDuty(Duty duty) {
        return dutyRepo.save(duty);
    }
}
