package com.bunky.server.Service;

import com.bunky.server.Dao.BalanceDao;
import com.bunky.server.Entity.Expense;
import com.bunky.server.Entity.ExpenseCategory;
import com.bunky.server.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BalanceService {

    private BalanceDao balanceDao;

    @Autowired
    public BalanceService(BalanceDao balanceDao) {
        this.balanceDao = balanceDao;
    }

    // void CreateExpense(....)
    public void createExpense(User user, ExpenseCategory expenseCategory, LocalDate date, BigDecimal amount) {
        //TODO- is this class gets all those objects or creates them?
        balanceDao.createExpense(new Expense(user, expenseCategory, date, amount));
    }

    // * createRefund(...)

    // List<Expense> aptExpenses(aptId) //TODO: maybe add a period of time to show

    // List<debt> computeBalance(userId)  **computes balance for this user
    // **debt == new class containing the user to pay to, and the amount
    // will call: 1. splitEqually - gets all expenses, split equally and calls to subRefund
    //            2. subtractRefunds - subtract the refunds from the compatible user and returns the userId's debts

    // List<List<debt>>  computeAllBalance(aptId)     --- for each user in apt, computeBalance
}
