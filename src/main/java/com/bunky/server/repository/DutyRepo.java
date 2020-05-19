package com.bunky.server.repository;

import com.bunky.server.Entity.Duty;
import com.bunky.server.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DutyRepo extends JpaRepository<Duty, Integer> {

//    @Query(value = "From Duty d join d.participants, User us, Apartment a where exists (select d.participants from Apartment a, Duty d where a.aptId=:aptId)")
//    List<Duty> getAllByApartment(@Param("aptId") final Integer aptId);

//    @Query(value = "From Duty d join d.participants u, Apartment a where u member of a.users and a.aptId=:aptId")
//    List<Duty> getAllByApartment(@Param("aptId") final Integer aptId);

    @Query(value = "select DISTINCT d From Duty d, User u, Apartment a where u member of a.users and u member of d.participants and a.aptId=:aptId")
    List<Duty> getAllByApartment(@Param("aptId") final Integer aptId);
}
