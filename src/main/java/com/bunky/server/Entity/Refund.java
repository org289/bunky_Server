package com.bunky.server.Entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "refunds")
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer refundId;

    @ManyToOne
    @JoinColumn(name = "giver_id")
    private User giver;

    @ManyToOne
    @JoinColumn(name = "reciever_id")
    private User receiver;

    private LocalDate date;

    private BigDecimal amount;

    private Boolean confirmed;

    public Refund() {
    }

    public Refund(User giver, User receiver, LocalDate date, BigDecimal amount) {
        this.giver = giver;
        this.receiver = receiver;
        this.date = date;
        this.amount = amount;
    }

    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public User getGiver() {
        return giver;
    }

    public void setGiver(User giver) {
        this.giver = giver;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
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

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }
}
