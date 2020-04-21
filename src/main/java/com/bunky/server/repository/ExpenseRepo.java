package com.bunky.server.repository;

import com.bunky.server.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Integer> {

//    @Query(value = "FROM Expense ex, Apartment apt JOIN ex.user exu JOIN apt.users au WHERE apt.aptId =:aptId and exu.userId=au.userId")
//    List<Expense> getAllByApartment(@Param("aptId") final UUID aptId);

    @Query(value = "From Expense ex join ex.user u, Apartment a where u Member of a.users and a.aptId=:aptId")
    List<Expense> getAllByApartment(@Param("aptId") final UUID aptId);

}
