package com.bunky.server.Controller;

import com.bunky.server.DTO.Debt;
import com.bunky.server.DTO.NewExpense;
import com.bunky.server.DTO.SumExpensesFromDate;
import com.bunky.server.Dao.LoginDao;
import com.bunky.server.Entity.Expense;
import com.bunky.server.Entity.ExpenseCategory;
import com.bunky.server.Entity.User;
import com.bunky.server.Service.BalanceService;
import com.bunky.server.repository.ExpenseCategoryRepo;
import com.bunky.server.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class BalanceController {

    private final BalanceService balanceService;
    private final LoginDao loginDao;
    private final ExpenseCategoryRepo expenseCategoryRepo;

    //TODO - delete (for tests)
    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    public BalanceController(BalanceService balanceService, LoginDao loginDao,ExpenseCategoryRepo expenseCategoryRepo) {
        this.balanceService = balanceService;
        this.expenseCategoryRepo = expenseCategoryRepo;
        this.loginDao = loginDao;
    }

    @RequestMapping(value = "/addExpense", method = RequestMethod.POST)
    public void addExpense(@RequestBody NewExpense newExpense) {
        ExpenseCategory category = expenseCategoryRepo.findById(newExpense.getCategoryId()).orElse(null);
        balanceService.createExpense(newExpense.getUser(), category, newExpense.getName(), newExpense.getDate(), newExpense.getAmount());
    }

    // TODO: change the returning object
    @RequestMapping(value = "/computeUserBalance", method = RequestMethod.POST)
    public List<Debt> computeUserBalance(@RequestBody User user) {
        return balanceService.computeBalance(user);
    }

    // TODO: change the returning object
    @RequestMapping(value = "/computeSumExpenses", method = RequestMethod.POST)
    public HashMap<User, BigDecimal> computeSumExpensesPerUser(@RequestBody SumExpensesFromDate data) {
       return balanceService.computeSumExpensesPerUser(data.getUser(), data.getDate());
    }


    //TODO: only for test
    @RequestMapping(value = "/getAllAptExpenses/{user}", method = RequestMethod.GET)
    public List<Expense> aptExpenses(@PathVariable User user) {
        UUID aptId = loginDao.aptByUser(user).getId();
        return balanceService.allAptExpenses(aptId);
    }

    //TODO: only for test
    @RequestMapping(value = "/getAptExpenses", method = RequestMethod.GET)
    public List<Expense> aptExpensesFromDate(User user, String fromDate) {
        LocalDate date = LocalDate.parse(fromDate);
        UUID aptId = loginDao.aptByUser(user).getId();
        return expenseRepo.getAllByApartmentFromDate(aptId, date);
    }

}
