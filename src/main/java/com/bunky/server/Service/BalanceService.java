package com.bunky.server.Service;

import com.bunky.server.DTO.Debt;
import com.bunky.server.Dao.BalanceDao;
import com.bunky.server.Dao.LoginDao;
import com.bunky.server.Entity.Apartment;
import com.bunky.server.Entity.Expense;
import com.bunky.server.Entity.ExpenseCategory;
import com.bunky.server.Entity.Refund;
import com.bunky.server.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BalanceService {

    private BalanceDao balanceDao;
    private LoginDao loginDao;

    @Autowired
    public BalanceService(BalanceDao balanceDao, LoginDao loginDao) {
        this.balanceDao = balanceDao;
        this.loginDao = loginDao;
    }

    public BalanceService() {
    }

    public void createExpense(User user, ExpenseCategory expenseCategory, String name, LocalDate date, BigDecimal amount) {
        if (name == null) {
            name = expenseCategory.getName();
        }
        balanceDao.createExpense(new Expense(user, expenseCategory, name, date, amount));
    }

    // * createRefund(...)

    public List<Expense> allAptExpenses(UUID aptId) {
        return balanceDao.getAllAptExpenses(aptId);
    }

    public List<Expense> getOpenExpenses(UUID aptId) {
        List<Expense> allExpenses = allAptExpenses(aptId);
        // for each expense, if not balanced, add.
        allExpenses.removeIf(Expense::isBalanced);
        return allExpenses;
    }

    private List<Refund> getConfirmedRefunds(UUID id) {
        return null;
    }

    //    computes balance for this user
    public List<Debt> computeBalance(User user) {
        // needs to get this user apt
        Apartment userApt = loginDao.aptByUser(user.getUserId());
        // get from the apt the expenses not balanced, the refunds confirmed
        List<Expense> aptExpenses = getOpenExpenses(userApt.getId());
        List<Refund> aptRefunds = getConfirmedRefunds(userApt.getId());
        // gets List of debts for ALL users
        List<Debt> debts = splitEqually(aptExpenses, aptRefunds, userApt.getUsers());
        // create new list only with this User in from/to fields
        debts.removeIf(debt -> !debt.containUser(user));
        return debts;
    }

    public List<Debt> splitEqually(List<Expense> aptExpenses, List<Refund> aptRefunds, List<User> users) {
        List<Debt> debts = new ArrayList<>();
        // first, we calculate for each user it's credit / debt
        HashMap<User, BigDecimal> userCreditDebt = calcCreditDebt(aptExpenses, users);
        subtractRefunds(userCreditDebt, aptRefunds);
        debts = getDebts(debts, userCreditDebt);
        return debts;
    }

    // List<debt>  computeAllBalance(aptId)     --- return all users debit/credit (sum)


    private void subtractRefunds(HashMap<User, BigDecimal> userCreditDebt, List<Refund> aptRefunds) {
        if (aptRefunds == null){
            // if there are no refunds to subtract...
            return;
        }
        for (Refund refund : aptRefunds) {
            BigDecimal amount = refund.getAmount();
            BigDecimal currentGiverVal = userCreditDebt.get(refund.getGiver());
            userCreditDebt.replace(refund.getGiver(), currentGiverVal.add(amount));
            BigDecimal currentReceiverVal = userCreditDebt.get(refund.getReceiver());
            userCreditDebt.replace(refund.getReceiver(), currentReceiverVal.subtract(amount));
        }
    }

    private List<Debt> getDebts(List<Debt> debts, HashMap<User, BigDecimal> userCreditDebt) {
        Map.Entry<User, BigDecimal> maxCredit = userCreditDebt.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        Map.Entry<User, BigDecimal> maxDebt = userCreditDebt.entrySet().stream().min(Map.Entry.comparingByValue()).get();
        if (maxCredit.getValue().compareTo(BigDecimal.ZERO) == 0 && maxDebt.getValue().compareTo(BigDecimal.ZERO) == 0) {
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

    public HashMap<User, BigDecimal> calcCreditDebt(List<Expense> aptExpenses, List<User> users) {
        BigDecimal aptSum = BigDecimal.ZERO;
        HashMap<User, BigDecimal> sumPerUser = getUsersSumMap(users);
        if (aptExpenses != null) {
            // if there are no apt expenses the map should stay "0"
            for (Expense expense : aptExpenses) {
                aptSum = aptSum.add(expense.getAmount());

                User temp = expense.getUser();
                sumPerUser.put(temp, sumPerUser.get(temp).add(expense.getAmount()));
            }
            BigDecimal userShare = aptSum.divide(BigDecimal.valueOf(users.size()), 2, BigDecimal.ROUND_DOWN);
            sumPerUser.replaceAll((k, v) -> v.subtract(userShare));
        }
        return sumPerUser;
    }

    private HashMap<User, BigDecimal> getUsersSumMap(List<User> users) {
        HashMap<User, BigDecimal> sumPerUser = new HashMap<User, BigDecimal>();
        for (User user : users) {
            sumPerUser.put(user, BigDecimal.ZERO);
        }
        return sumPerUser;
    }

}

