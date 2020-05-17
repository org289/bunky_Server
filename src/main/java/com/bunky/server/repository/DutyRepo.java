package com.bunky.server.repository;

import com.bunky.server.Entity.Duty;
import com.bunky.server.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DutyRepo extends JpaRepository<Duty, Integer> {

    @Query(value = "From Duty d where exists (select a from Apartment a where a.aptId=:aptId)")
    List<Duty> getAllByApartment(@Param("aptId") final Integer aptId);
}
