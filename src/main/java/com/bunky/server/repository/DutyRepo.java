package com.bunky.server.repository;

import com.bunky.server.Entity.Duty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DutyRepo extends JpaRepository<Duty, Integer> {
}
