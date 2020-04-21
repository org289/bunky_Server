package com.bunky.server.DTO;

public class NewExpense {
    // TODO - check if can get the objects
    private Integer userId;
    private Integer categoryId;
    private String name;
    private String date;
    private String amount;

    public NewExpense(Integer userId, Integer categoryId, String name, String date, String amount) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    //    private User user;
//    private ExpenseCategory expenseCategory;
//    private String name;
//    private LocalDate date;
//    private BigDecimal amount;
//
//    public NewExpense(User user, ExpenseCategory expenseCategory, String name, LocalDate date, BigDecimal amount) {
//        this.user = user;
//        this.expenseCategory = expenseCategory;
//        this.name = name;
//        this.date = date;
//        this.amount = amount;
//    }
//
//    public NewExpense(User user, ExpenseCategory expenseCategory, LocalDate date, BigDecimal amount) {
//        this.user = user;
//        this.expenseCategory = expenseCategory;
//        this.date = date;
//        this.amount = amount;
//        // no special name entered. the name is the category name
//        this.name = expenseCategory.getName();
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public ExpenseCategory getExpenseCategory() {
//        return expenseCategory;
//    }
//
//    public void setExpenseCategory(ExpenseCategory expenseCategory) {
//        this.expenseCategory = expenseCategory;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public BigDecimal getAmount() {
//        return amount;
//    }
//
//    public void setAmount(BigDecimal amount) {
//        this.amount = amount;
//    }
}
