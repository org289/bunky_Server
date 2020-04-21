package com.bunky.server.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer expanseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExpenseCategory expenseCategory;

    private String name;

    private LocalDate date;

    private BigDecimal amount;

    private boolean balanced;

    //TODO: add a picture? (of billing)

    public Expense() {
    }

//    public Expense(NewExpense newExpense){
//        this.user = newExpense.getUser();
//        this.expenseCategory = newExpense.getExpenseCategory();
//        this.name = newExpense.getName();
//        this.date = newExpense.getDate();
//        this.amount = newExpense.getAmount();
//        this.balanced = false;
//    }

    public Expense(User user, ExpenseCategory expenseCategory, String name, LocalDate date, BigDecimal amount) {
        this.user = user;
        this.expenseCategory = expenseCategory;
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.balanced = false;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isBalanced() {
        return balanced;
    }

    public void setBalanced(boolean balanced) {
        this.balanced = balanced;
    }

    public Integer getExpanseId() {
        return expanseId;
    }

    public void setExpanseId(Integer expanseId) {
        this.expanseId = expanseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
