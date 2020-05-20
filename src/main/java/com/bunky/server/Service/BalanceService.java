package com.bunky.server.Service;

import com.bunky.server.DTO.Debt;
import com.bunky.server.Dao.BalanceDao;
import com.bunky.server.Dao.UserAptDao;
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

@Service
public class BalanceService {

    private BalanceDao balanceDao;
    private UserAptDao userAptDao;

    @Autowired
    public BalanceService(BalanceDao balanceDao, UserAptDao userAptDao) {
        this.balanceDao = balanceDao;
        this.userAptDao = userAptDao;
    }

    public BalanceService() {
    }

    public Expense createExpense(User user, ExpenseCategory expenseCategory, String name, LocalDate date, BigDecimal amount) {
        if (name == null) {
            name = expenseCategory.getName();
        }
        return balanceDao.createExpense(new Expense(user, expenseCategory, name, date, amount));
    }

    // * createRefund(...)
    public Refund createRefund(User giver, User receiver, LocalDate date, BigDecimal amount) {
        return balanceDao.createRefund(new Refund(giver, receiver, date, amount));
    }

    public List<Expense> allAptExpenses(Integer aptId) {
        return balanceDao.getAllAptExpenses(aptId);
    }


    //    computes balance for this user
    public List<Debt> computeBalance(User user) {
        // needs to get this user apt
        Apartment userApt = userAptDao.aptByUser(user);
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

    public HashMap<User, BigDecimal> computeSumExpensesPerUser(User user, LocalDate fromDate) {
        //--- return all users debit/credit (sum)
        Apartment apt = userAptDao.aptByUser(user);
        HashMap<User, BigDecimal> sumExpenses = getUsersSumMap(apt.getUsers());
        List<Expense> expensesFromDate = balanceDao.getAllAptExpensesFromDate(apt.getId(), fromDate);
        calcSumUsersExpenses(expensesFromDate, sumExpenses);
        return sumExpenses;
    }

    // between dates
    public HashMap<User, BigDecimal> computeSumExpensesPerUser(User user, LocalDate fromDate, LocalDate toDate) {
        Apartment apt = userAptDao.aptByUser(user);
        HashMap<User, BigDecimal> sumExpenses = getUsersSumMap(apt.getUsers());
        List<Expense> expensesBetweenDates = balanceDao.getAllByApartmentBetweenDates(apt.getId(), fromDate, toDate);
        calcSumUsersExpenses(expensesBetweenDates, sumExpenses);
        return sumExpenses;
    }

    public HashMap<ExpenseCategory, BigDecimal> computeSumExpensesPerCategory(User user, LocalDate fromDate) {
        //--- return sum of expenses by category
        Apartment apt = userAptDao.aptByUser(user);
        HashMap<ExpenseCategory, BigDecimal> sumExpenses = getCategorySumMap();
        List<Expense> expensesFromDate = balanceDao.getAllAptExpensesFromDate(apt.getId(), fromDate);
        calcSumCatExpenses(expensesFromDate, sumExpenses);
        return sumExpenses;
    }

    // between dates
    public HashMap<ExpenseCategory, BigDecimal> computeSumExpensesPerCategory(User user, LocalDate fromDate, LocalDate toDate) {
        //--- return sum of expenses by category
        Apartment apt = userAptDao.aptByUser(user);
        HashMap<ExpenseCategory, BigDecimal> sumExpenses = getCategorySumMap();
        List<Expense> expensesFromDate = balanceDao.getAllByApartmentBetweenDates(apt.getId(), fromDate, toDate);
        calcSumCatExpenses(expensesFromDate, sumExpenses);
        return sumExpenses;
    }


    private void subtractRefunds(HashMap<User, BigDecimal> userCreditDebt, List<Refund> aptRefunds) {
        if (aptRefunds == null) {
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
        if (maxCredit.getValue().compareTo(BigDecimal.ZERO.setScale(2)) == 0 && maxDebt.getValue().compareTo(BigDecimal.ZERO.setScale(2)) == 0) {
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
        BigDecimal aptSum = BigDecimal.ZERO.setScale(2);
        HashMap<User, BigDecimal> sumPerUser = getUsersSumMap(users);
        calcSumUsersExpenses(aptExpenses, sumPerUser);
        BigDecimal checkSumAfter = BigDecimal.ZERO.setScale(2);
        for (BigDecimal val : sumPerUser.values()) {
            aptSum = aptSum.add(val);
        }
        BigDecimal userShare = aptSum.divide(BigDecimal.valueOf(users.size()), 2, BigDecimal.ROUND_DOWN);
        sumPerUser.replaceAll((k, v) -> v.subtract(userShare));
        for (BigDecimal value : sumPerUser.values()) {
            checkSumAfter = checkSumAfter.add(value);
        }
        if (checkSumAfter.compareTo(BigDecimal.ZERO.setScale(2)) != 0) {
            // Rounding total sum is incorrect. remove this incorrectness from the biggest credit
            Map.Entry<User, BigDecimal> maxCredit = sumPerUser.entrySet().stream().max(Map.Entry.comparingByValue()).get();
            sumPerUser.replace(maxCredit.getKey(), maxCredit.getValue().subtract(checkSumAfter));
        }
        return sumPerUser;
    }

    private void calcSumUsersExpenses(List<Expense> aptExpenses, HashMap<User, BigDecimal> sumPerUser) {
        if (aptExpenses != null) {
            // if there are no apt expenses the map should stay "0"
            for (Expense expense : aptExpenses) {
                User temp = expense.getUser();
                sumPerUser.put(temp, sumPerUser.get(temp).add(expense.getAmount()));
            }
        }
    }

    private void calcSumCatExpenses(List<Expense> aptExpenses, HashMap<ExpenseCategory, BigDecimal> sumPerCat) {
        if (aptExpenses != null) {
            // if there are no apt expenses the map should stay "0"
            for (Expense expense : aptExpenses) {
                ExpenseCategory category = expense.getExpenseCategory();
                sumPerCat.put(category, sumPerCat.get(category).add(expense.getAmount()));
            }
        }
    }

    private HashMap<User, BigDecimal> getUsersSumMap(List<User> users) {
        HashMap<User, BigDecimal> sumPerUser = new HashMap<User, BigDecimal>();
        for (User user : users) {
            sumPerUser.put(user, BigDecimal.ZERO.setScale(2));
        }
        return sumPerUser;
    }

    private HashMap<ExpenseCategory, BigDecimal> getCategorySumMap() {
        HashMap<ExpenseCategory, BigDecimal> sumPerCat = new HashMap<>();
        List<ExpenseCategory> catNames = balanceDao.getListOfExpenseCategory();
        for (ExpenseCategory category : catNames) {
            sumPerCat.put(category, BigDecimal.ZERO.setScale(2));
        }
        return sumPerCat;
    }

    private List<Expense> getOpenExpenses(Integer aptId) {
        List<Expense> allExpenses = allAptExpenses(aptId);
        // for each expense, if not balanced, add.
        if (allExpenses != null) {
            allExpenses.removeIf(Expense::isBalanced);
        }
        return allExpenses;
    }


    private List<Refund> getConfirmedRefunds(Integer id) {
        List<Refund> allRefunds = allAptRefunds(id);
        if (allRefunds != null) {
            allRefunds.removeIf(refund -> !refund.isConfirmed());
        }
        return allRefunds;
    }

    private List<Refund> allAptRefunds(Integer id) {
        return balanceDao.getAllAptRefunds(id);
    }

    public Integer deleteExpenseById(Integer expenseId) {
        Expense expense = balanceDao.getExpenseById(expenseId);
        if (expense != null) {
            balanceDao.deleteExpense(expense);
            return expense.getExpenseId();
        }
        return null;
    }

    public List<Expense> getExpensesWithLimit(Integer aptID, int limit) {
        return balanceDao.getExpensesWithLimit(aptID, limit);
    }

    public List<Expense> getAllByApartmentBetweenDates(Integer aptId, LocalDate from, LocalDate to) {
        List<Expense> expenses =  balanceDao.getAllByApartmentBetweenDates(aptId, from, to);
        expenses.sort((e1, e2) -> e2.getExpenseId().compareTo(e1.getExpenseId()));
        return expenses;
    }
}
