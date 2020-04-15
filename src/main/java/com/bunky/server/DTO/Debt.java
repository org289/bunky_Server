package com.bunky.server.DTO;

import com.bunky.server.Entity.User;

import java.math.BigDecimal;

public class Debt {
    private User debtTo;
    private BigDecimal amount;

    public Debt(User debtTo, BigDecimal amount) {
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
}
