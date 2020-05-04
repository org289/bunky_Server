package com.bunky.server.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "expense_category")
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "category_generator")
    @SequenceGenerator(name = "category_generator", sequenceName = "category_seq" )
    private Integer categoryId;

    private String name;

    public ExpenseCategory() {
    }

    public ExpenseCategory(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
