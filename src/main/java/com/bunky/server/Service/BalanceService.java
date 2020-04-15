package com.bunky.server.Service;

import com.bunky.server.DTO.Debt;
import com.bunky.server.Dao.BalanceDao;
import com.bunky.server.Entity.Expense;
import com.bunky.server.Entity.ExpenseCategory;
import com.bunky.server.Entity.User;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BalanceService {

    private BalanceDao balanceDao;

    @Autowired
    public BalanceService(BalanceDao balanceDao) {
        this.balanceDao = balanceDao;
    }

    public BalanceService() {
    }

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

    public List<Debt> splitEqually(List<Expense> aptExpenses, List<User> users) {
        List<Debt> debts = new ArrayList<>();
        // first, we calculate for each user it's credit / debt
        HashMap<User, BigDecimal> userCreditDebt = calcCreditDebt(aptExpenses, users);
        debts = getDebts(debts, userCreditDebt);
        return debts;
    }

    private List<Debt> getDebts(List<Debt> debts, HashMap<User, BigDecimal> userCreditDebt) {
        Pair<User, BigDecimal> maxCredit = getMax(userCreditDebt);
        Pair<User, BigDecimal> maxDebt = getMin(userCreditDebt);
        if (maxCredit.getValue().equals(BigDecimal.ZERO) && maxDebt.getValue().equals(BigDecimal.ZERO)) {
            // all balanced
            return debts;
        }
        BigDecimal min = minOfTwo(maxCredit.getValue().abs(), maxDebt.getValue().abs());
        debts.add(new Debt(maxDebt.getKey(), maxCredit.getKey(), min));
        userCreditDebt.replace(maxCredit.getKey(), maxCredit.getValue().subtract(min));
        userCreditDebt.replace(maxDebt.getKey(), maxDebt.getValue().add(min));
        return getDebts(debts, userCreditDebt);
    }

    private BigDecimal minOfTwo(BigDecimal val1, BigDecimal val2) {
        return (val2.compareTo(val1) < 0) ? val2 : val1;
    }

    private Pair<User, BigDecimal> getMin(HashMap<User, BigDecimal> userCreditDebt) {
        Pair<User, BigDecimal> minValue = new Pair<>(new User(), BigDecimal.ZERO);

        for (Map.Entry<User, BigDecimal> entry : userCreditDebt.entrySet()) {
            if (entry.getValue().compareTo(minValue.getValue()) < 0) {
                // new min
                minValue = new Pair<>(entry.getKey(), entry.getValue());
            }
        }
        return minValue;
    }

    private Pair<User, BigDecimal> getMax(HashMap<User, BigDecimal> userCreditDebt) {
        Pair<User, BigDecimal> maxValue = new Pair<>(new User(), BigDecimal.ZERO);

        for (Map.Entry<User, BigDecimal> entry : userCreditDebt.entrySet()) {
            if (entry.getValue().compareTo(maxValue.getValue()) > 0) {
                // new max
                maxValue = new Pair<>(entry.getKey(), entry.getValue());
            }
        }
        return maxValue;
    }

    public HashMap<User, BigDecimal> calcCreditDebt(List<Expense> aptExpenses, List<User> users) {
        BigDecimal aptSum = BigDecimal.ZERO;
        HashMap<User, BigDecimal> sumPerUser = getUsersSumMap(users);
        for (Expense expense : aptExpenses) {
            aptSum = aptSum.add(expense.getAmount());

            User temp = expense.getUser();
            sumPerUser.put(temp, sumPerUser.get(temp).add(expense.getAmount()));
        }
        BigDecimal userShare = aptSum.divide(BigDecimal.valueOf(users.size()), 2, BigDecimal.ROUND_DOWN);
        sumPerUser.replaceAll((k, v) -> v.subtract(userShare));
        return sumPerUser;
    }


    private HashMap<User, BigDecimal> getUsersSumMap(List<User> users) {
        HashMap<User, BigDecimal> sumPerUser = new HashMap<User, BigDecimal>();
        for (User user : users) {
            sumPerUser.put(user, BigDecimal.ZERO);
        }
        return sumPerUser;
    }

    // List<List<debt>>  computeAllBalance(aptId)     --- for each user in apt, computeBalance
}
