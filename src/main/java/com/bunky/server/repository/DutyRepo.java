package com.bunky.server.repository;

import com.bunky.server.Entity.Duty;
import com.bunky.server.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DutyRepo extends JpaRepository<Duty, Integer> {

    // TODO: figure out how to compare one user from d.participants
//    @Query(value = "From Duty d join d.participants u, Apartment a where u Member of a.users and a.aptId=:aptId")
//    List<Duty> getAllByApartment(@Param("aptId") final Integer aptId);

}
