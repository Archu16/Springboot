package com.archana.social_bank.demo.account;

public class AccountView {
    private String balance;
    private String userId;

    private String creditCard;

    private String exp;

    private String name;

    private String cvv;
    private String limit;


    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public AccountView(String balance, String userId, String creditCard, String exp, String name, String cvv, String limit) {
        this.balance = balance;
        this.userId = userId;
        this.creditCard = creditCard;
        this.exp = exp;
        this.name = name;
        this.cvv = cvv;
        this.limit = limit;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public AccountView() {
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
