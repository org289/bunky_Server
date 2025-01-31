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

    public List<Duty> getAllAptDuties(Integer aptId) {
        return dutyRepo.getAllByApartment(aptId);
    }

    public Duty updateNextShift(Duty duty) {
        return dutyRepo.save(duty);
    }

    public Duty getDutyById(Integer dutyId) {
        return dutyRepo.findById(dutyId).orElse(null);
    }

    public Duty updateDuty(Duty dutyFromDb) {
        return dutyRepo.save(dutyFromDb);
    }

    public List<Duty> getAllByUser(User user) {
        return dutyRepo.getAllByUser(user);
    }

    public List<Duty> getAll(){
        return dutyRepo.findAll();
    }

    public void deleteDuty(Duty duty) {
        dutyRepo.delete(duty);
    }
}
