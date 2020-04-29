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
        User user = loginDao.getUserById(newExpense.getUserId());
        ExpenseCategory category = expenseCategoryRepo.findById(newExpense.getCategoryId()).orElse(null);
        LocalDate date = LocalDate.parse(newExpense.getDate()); // TODO if format is "yyyy-MM-dd", fine. else need to add: DateTimeFormatter.ofPattern("dd/MM/yyyy");
        BigDecimal amount = new BigDecimal(newExpense.getAmount());
        balanceService.createExpense(user, category, newExpense.getName(), date, amount);
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
    @RequestMapping(value = "/getAptExpenses/{aptId}", method = RequestMethod.GET)
    public List<Expense> aptExpenses(@PathVariable UUID aptId) {
        return balanceService.allAptExpenses(aptId);
    }

    //TODO: only for test
    @RequestMapping(value = "/getAptExpenses/{aptId}/{fromDate}", method = RequestMethod.GET)
    public List<Expense> aptExpensesFromDate(@PathVariable UUID aptId, @PathVariable String fromDate) {
        LocalDate date = LocalDate.parse(fromDate);
        return expenseRepo.getAllByApartmentFromDate(aptId, date);
    }

}
