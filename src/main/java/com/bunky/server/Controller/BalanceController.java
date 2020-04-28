package com.bunky.server.Controller;

import com.bunky.server.DTO.Debt;
import com.bunky.server.DTO.NewExpense;
import com.bunky.server.Dao.LoginDao;
import com.bunky.server.Entity.Expense;
import com.bunky.server.Entity.ExpenseCategory;
import com.bunky.server.Entity.User;
import com.bunky.server.Service.BalanceService;
import com.bunky.server.repository.ExpenseCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class BalanceController {

    private final BalanceService balanceService;
    private final LoginDao loginDao;
    private final ExpenseCategoryRepo expenseCategoryRepo;

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

    @RequestMapping(value = "/computeUserBalance", method = RequestMethod.POST)
    public List<Debt> computeUserBalance(@RequestBody User user) {
        return balanceService.computeBalance(user);
    }


    //TODO: only for test
    @RequestMapping(value = "/getAptExpenses/{aptId}", method = RequestMethod.GET)
    public List<Expense> aptExpenses(@PathVariable UUID aptId) {
        return balanceService.allAptExpenses(aptId);
    }

}
