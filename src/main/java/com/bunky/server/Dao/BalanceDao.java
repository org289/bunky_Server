package com.bunky.server.Dao;


import com.bunky.server.Entity.Expense;
import com.bunky.server.repository.ExpenseRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BalanceDao {

    private final ExpenseRepo expenseRepo;

    public BalanceDao(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;

    }

    public Expense createExpense(Expense expense){
        return expenseRepo.save(expense);
    }

    public List<Expense> getAllAptExpenses(Integer aptId) {
        return expenseRepo.getAllByApartment(aptId);
    }

    public List<Expense> getAllAptExpensesFromDate(Integer aptId, LocalDate fromDate) {
        return expenseRepo.getAllByApartmentFromDate(aptId, fromDate);
    }
}
