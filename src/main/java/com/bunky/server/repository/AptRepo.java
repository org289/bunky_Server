package com.bunky.server.repository;

import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AptRepo extends JpaRepository<Apartment, UUID> {

    @Query(value= "FROM Apartment apt JOIN apt.users u WHERE u = :user")
    Apartment aptByUser(@Param("user") final User user);

}
