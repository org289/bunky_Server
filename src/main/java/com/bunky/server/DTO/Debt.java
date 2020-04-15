package com.bunky.server.DTO;

import com.bunky.server.Entity.User;

import java.math.BigDecimal;

public class Debt {
    private User debtFrom;
    private User debtTo;
    private BigDecimal amount;

    public Debt(User debtFrom, User debtTo, BigDecimal amount) {
        this.debtFrom = debtFrom;
        this.debtTo = debtTo;
        this.amount = amount;
    }

    public User getDebtTo() {
        return debtTo;
    }

    public void setDebtTo(User debtTo) {
        this.debtTo = debtTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getDebtFrom() {
        return debtFrom;
    }

    public void setDebtFrom(User debtFrom) {
        this.debtFrom = debtFrom;
    }
}
