package com.bunky.server.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer expanseId;

    // TODO: connect to the users table
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // TODO: add this table...
//    private ExpanseCategory expanseCategory;

    // TODO: check if this class is suitable
    private LocalDate date;

    private BigDecimal amount;

    private boolean balanced;

    //TODO: add a picture? (of billing)


}
