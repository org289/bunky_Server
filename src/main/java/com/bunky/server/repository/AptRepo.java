package com.bunky.server.repository;

import com.bunky.server.Entity.Apartment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AptRepo extends CrudRepository<Apartment, UUID> {
//    @Query(value= "SELECT apt_id FROM apartments", nativeQuery = true)

}
