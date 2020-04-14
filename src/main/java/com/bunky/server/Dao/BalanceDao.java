package com.bunky.server.Dao;


import com.bunky.server.Entity.Expense;
import com.bunky.server.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceDao {

    private final ExpenseRepo expenseRepo;

    public BalanceDao(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    public void createExpense(Expense expense){
        expenseRepo.save(expense);
    }
}
