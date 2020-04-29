package com.bunky.server.DTO;

import com.bunky.server.Entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DebtTest {
    private static ArrayList<User> users;
    private static ArrayList<Debt> debts;

    @BeforeAll
    static void SetUp() {
        users = new ArrayList<User>() {
            {
                add(new User("Amy Parizada", "amy@gmail.com"));
                add(new User("Or Gur", "or@gmail.com"));
                add(new User("Yuval Cohen", "yuval@gmail.com"));
                add(new User("Miriel Jerbi", "miriel@gmail.com"));
            }
        };
        debts = new ArrayList<Debt>(){
            {
                add(new Debt(users.get(0), users.get(1), BigDecimal.valueOf(250)));
                add(new Debt(users.get(2), users.get(3), BigDecimal.valueOf(250)));


                add(new Debt(users.get(1), users.get(2), BigDecimal.valueOf(320)));
                add(new Debt(users.get(1), users.get(3), BigDecimal.valueOf(20)));
                add(new Debt(users.get(2), users.get(1), BigDecimal.valueOf(50)));
            }
        };
    }

    @Test
    void containUser() {
        Debt firstDebt = debts.get(0);
        assertTrue(firstDebt.containUser(users.get(0)));
        assertTrue(firstDebt.containUser(users.get(1)));
        assertFalse(firstDebt.containUser(users.get(2)));

        Debt secondDebt = debts.get(1);
        assertTrue(secondDebt.containUser(users.get(3)));
        assertTrue(secondDebt.containUser(users.get(2)));
        assertFalse(secondDebt.containUser(users.get(0)));
    }

    @Test
    void isDebtToMe() {
        assertTrue(debts.get(0).isDebtToMe(users.get(1)));
        assertFalse(debts.get(0).isDebtToMe(users.get(0)));
        assertFalse(debts.get(0).isDebtToMe(users.get(2)));
    }

    @Test
    void isDebtFromMe() {
        assertFalse(debts.get(0).isDebtFromMe(users.get(1)));
        assertTrue(debts.get(0).isDebtFromMe(users.get(0)));
        assertFalse(debts.get(0).isDebtFromMe(users.get(2)));
    }

    @Test
    void DebtCredit(){
        DebtCredit debtCredit = new DebtCredit(debts, users.get(1));
        System.out.println(debtCredit);
    }
}