package com.example.balcamgym.MODELS;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Subscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToOne
    private Account account;

    @OneToMany(mappedBy = "subscriptions" , fetch = FetchType.EAGER)
    Set<Workout> workouts = new HashSet<>();

    private SubscriptionsTypes subscriptionsTypes;

    private double amount;

    private LocalDate fromDate, toDate;



    public Subscriptions(){}

    public Subscriptions(SubscriptionsTypes subscriptionsTypes, double amount, LocalDate fromDate, LocalDate toDate, Account account) {
        this.subscriptionsTypes = subscriptionsTypes;
        this.amount = amount;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.account = account;
    }



    public SubscriptionsTypes getSubscriptionsTypes() {
        return subscriptionsTypes;
    }
    public void setSubscriptionsTypes(SubscriptionsTypes subscriptionsTypes) {
        this.subscriptionsTypes = subscriptionsTypes;
    }



    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }



    public LocalDate getFromDate() {
        return fromDate;
    }
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }



    public LocalDate getToDate() {
        return toDate;
    }
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }



    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public Set<Workout> getWorkouts() {
        return workouts;
    }
}