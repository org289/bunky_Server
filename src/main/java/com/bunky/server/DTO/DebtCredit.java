package com.bunky.server.DTO;

import com.bunky.server.Entity.User;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class DebtCredit {
    HashMap<User, BigDecimal> UserDebt;  // User owes someone
    HashMap<User, BigDecimal> UserCredit;  // someone owes User

    public DebtCredit(List<Debt> debts, User user) {
        this.UserCredit = createUserCreditMap(debts, user);
        this.UserDebt = createUserDebtMap(debts, user);
    }

    private HashMap<User, BigDecimal> createUserDebtMap(List<Debt> debts, User user) {
        HashMap<User, BigDecimal> newMap = new HashMap<>();
        // go over the debts. each debts that is FROM user, enter to map
        for (Debt debt : debts) {
            if (debt.isDebtFromMe(user)) {
                newMap.put(debt.getDebtTo(), debt.getAmount());
            }
        }
        return newMap;
    }

    private HashMap<User, BigDecimal> createUserCreditMap(List<Debt> debts, User user) {
        HashMap<User, BigDecimal> newMap = new HashMap<>();
        // go over the debts. each debts that is TO user, enter to map
        for (Debt debt : debts) {
            if (debt.isDebtToMe(user)) {
                newMap.put(debt.getDebtFrom(), debt.getAmount());
            }
        }
        return newMap;
    }

    @Override
    public String toString() {
        return "I owe:\n" + UserDebt + "\nowes me:\n" + UserCredit;
    }

    public HashMap<User, BigDecimal> getUserDebt() {
        return UserDebt;
    }

    public HashMap<User, BigDecimal> getUserCredit() {
        return UserCredit;
    }
}
