package com.bunky.server.Dao;


import com.bunky.server.Entity.Expense;
import com.bunky.server.repository.ExpenseRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BalanceDao {

    private final ExpenseRepo expenseRepo;

    public BalanceDao(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;

    }

    public void createExpense(Expense expense){
        expenseRepo.save(expense);
    }

    public List<Expense> getAllAptExpenses(UUID aptId) {
        return expenseRepo.getAllByApartment(aptId);
    }

    public List<Expense> getAllAptExpensesFromDate(UUID aptId, LocalDate fromDate) {
        return expenseRepo.getAllByApartmentFromDate(aptId, fromDate);
    }
}
