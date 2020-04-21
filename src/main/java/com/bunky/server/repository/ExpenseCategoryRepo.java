package com.bunky.server.repository;

import com.bunky.server.Entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseCategoryRepo extends JpaRepository<ExpenseCategory, Integer> {

}
