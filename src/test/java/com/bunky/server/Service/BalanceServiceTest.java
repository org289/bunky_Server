package com.bunky.server.Service;

import com.bunky.server.DTO.Debt;
import com.bunky.server.Entity.Expense;
import com.bunky.server.Entity.ExpenseCategory;
import com.bunky.server.Entity.Refund;
import com.bunky.server.Entity.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BalanceServiceTest {

    private static final ArrayList<User> users;
    private static final ArrayList<Expense> aptExpenses;
    private static final ArrayList<Expense> aptExpenses2;
    private static final ArrayList<Refund> aptRefunds;
    private static final ArrayList<Refund> aptRefunds2;

    static {
        users = new ArrayList<User>() {
            {
                add(new User("Amy Parizada", "amy@gmail.com"));
                add(new User("Or Gut", "or@gmail.com"));
                add(new User("Yuval Cohen", "yuval@gmail.com"));
                add(new User("Miriel Jerbi", "miriel@gmail.com"));
            }
        };
        aptExpenses = new ArrayList<Expense>() {
            {
                add(new Expense(users.get(0), new ExpenseCategory(),"test", LocalDate.now(), BigDecimal.valueOf(500)));
//                add(new Expense(users.get(1), new ExpenseCategory(), LocalDate.now(), BigDecimal.valueOf(500)));
                add(new Expense(users.get(1), new ExpenseCategory(),"test", LocalDate.now(), BigDecimal.valueOf(250)));
                add(new Expense(users.get(3), new ExpenseCategory(),"test", LocalDate.now(), BigDecimal.valueOf(250)));
            }
        };

        aptExpenses2 = new ArrayList<Expense>() {
            {
                add(new Expense(users.get(0), new ExpenseCategory(),"test", LocalDate.now(), BigDecimal.valueOf(250)));
                add(new Expense(users.get(0), new ExpenseCategory(),"test", LocalDate.now(), BigDecimal.valueOf(250)));
                add(new Expense(users.get(1), new ExpenseCategory(),"test", LocalDate.now(), BigDecimal.valueOf(250)));
                add(new Expense(users.get(3), new ExpenseCategory(),"test", LocalDate.now(), BigDecimal.valueOf(25)));
                add(new Expense(users.get(2), new ExpenseCategory(),"test", LocalDate.now(), BigDecimal.valueOf(30)));
            }
        };

        aptRefunds = new ArrayList<Refund>() {
            {
                add(new Refund(users.get(2), users.get(0), LocalDate.now(), BigDecimal.valueOf(50)));
            }
        };

        aptRefunds2 = new ArrayList<Refund>() {

            };


    } // end static

    @Test
    void splitEqually() {
        BalanceService service = new BalanceService();
        List<Debt> result = service.splitEqually(aptExpenses, aptRefunds, users );
        // debt --> yuval to amy -200
        // test1:
        assertEquals(new BigDecimal("200.00"), result.get(0).getAmount());
        assertEquals(users.get(2), result.get(0).getDebtFrom());
        assertEquals(users.get(0), result.get(0).getDebtTo());
        assertEquals(1, result.size());

        // test2:
        List<Debt> result3 = service.splitEqually(aptExpenses2, aptRefunds2, users);
        assertEquals(3, result3.size());
        assertEquals(new BigDecimal("176.25"), result3.get(0).getAmount());
        assertEquals(new BigDecimal("122.50"), result3.get(1).getAmount());
        assertEquals(new BigDecimal("48.75"), result3.get(2).getAmount());
    }


    @Test
    void calcCreditDebt() {
        BalanceService service = new BalanceService();
        // test 1
        HashMap<User, BigDecimal> result = service.calcCreditDebt(aptExpenses, users);
        assertEquals(new BigDecimal("250.00"), result.get(users.get(0)));
        assertEquals(new BigDecimal("0.00"), result.get(users.get(1)));
        assertEquals(new BigDecimal("-250.00"), result.get(users.get(2)));
        assertEquals(new BigDecimal("0.00"), result.get(users.get(3)));

        //test 2
        HashMap<User, BigDecimal> result2 = service.calcCreditDebt(aptExpenses2, users);
        assertEquals(new BigDecimal("298.75"), result2.get(users.get(0)));
        assertEquals(new BigDecimal("48.75"), result2.get(users.get(1)));
    }

}