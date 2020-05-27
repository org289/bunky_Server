package com.bunky.server.repository;

import com.bunky.server.Entity.Duty;
import com.bunky.server.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DutyRepo extends JpaRepository<Duty, Integer> {

    @Query(value = "select DISTINCT d From Duty d, User u, Apartment a where u member of a.users and u member of d.participants and a.aptId=:aptId")
    List<Duty> getAllByApartment(@Param("aptId") final Integer aptId);

    // get user memberOf participants
    @Query(value = "From Duty d where d.shift.executor = :user")
    List<Duty> getAllByUser(@Param("user") final User user);

}
