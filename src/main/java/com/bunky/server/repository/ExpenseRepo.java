package com.bunky.server.repository;

import com.bunky.server.Entity.Expense;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Integer> {

    @Query(value = "From Expense ex join ex.user u, Apartment a where u Member of a.users and a.aptId=:aptId")
    List<Expense> getAllByApartment(@Param("aptId") final Integer aptId);

    @Query(value = "From Expense ex join ex.user u, Apartment a where u Member of a.users and a.aptId= :aptId and ex.date >= :fromDate")
    List<Expense> getAllByApartmentFromDate(@Param("aptId") final Integer aptId, @Param("fromDate") final LocalDate fromDate);

    @Query(value = "From Expense ex join ex.user u, Apartment a where u Member of a.users and a.aptId=:aptId")
    List<Expense> getAllByApartmentByLimit(@Param("aptId") final Integer aptId, @Param("limit") Pageable pageable);


}
