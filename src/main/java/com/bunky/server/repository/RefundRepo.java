package com.bunky.server.repository;

import com.bunky.server.Entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundRepo extends JpaRepository<Refund, Integer> {

    @Query(value = "From Refund r join r.giver u, Apartment a where u Member of a.users and a.aptId=:aptId")
    // giver and receiver are both from the same apt, so we only check the giver
    List<Refund> getAllByApartment(@Param("aptId") final Integer id);
}
