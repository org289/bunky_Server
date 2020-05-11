package com.bunky.server.Dao;


import com.bunky.server.Entity.Expense;
import com.bunky.server.Entity.ExpenseCategory;
import com.bunky.server.repository.ExpenseCategoryRepo;
import com.bunky.server.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BalanceDao {

    private final ExpenseRepo expenseRepo;
    private final ExpenseCategoryRepo expenseCategoryRepo;

    @Autowired
    public BalanceDao(ExpenseRepo expenseRepo, ExpenseCategoryRepo expenseCategoryRepo) {
        this.expenseRepo = expenseRepo;
        this.expenseCategoryRepo = expenseCategoryRepo;

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

    public List<ExpenseCategory> getListOfExpenseCategory() {
        return expenseCategoryRepo.findAll();
    }

    public Expense getExpenseById(Integer expenseId) {
        return expenseRepo.findById(expenseId).orElse(null);
    }

    public void deleteExpense(Expense expense) {
        expenseRepo.delete(expense);
    }

    public List<Expense> getExpensesWithLimit(Integer aptID, int limit) {
        return expenseRepo.getAllByApartmentByLimit(aptID, PageRequest.of(0, limit, Sort.by("date").descending()));
    }
}
